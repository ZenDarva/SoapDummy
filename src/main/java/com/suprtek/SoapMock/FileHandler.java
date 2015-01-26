package com.suprtek.SoapMock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class FileHandler {

//	private String topLevelPath;
	
	public FileHandler()
	{
		System.out.println("topLevelPath: " + getResponseDirectoryPath());
	}
	
	public String getWSDL(String path)
	{		
		File wsdlFile = new File(getResponseDirectoryPath() + path+path +".wsdl");		
		boolean wsdlFileInvalid = wsdlFile == null || wsdlFile.exists() == false;
		if (wsdlFileInvalid)
			return null;
		
		byte[] returnMe = null;
		try {
			returnMe = Files.readAllBytes(wsdlFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(returnMe);		
	}

	public String getResponse(String path, int hashKey) {
		File responseFile = new File(getResponseDirectoryPath() + path + File.separator + hashKey +".soapResponse");		
		boolean responseFileMissing = responseFile == null || responseFile.exists() == false;
		if (responseFileMissing)
			return null;
		
		byte[] returnMe = null;
		try {
			returnMe = Files.readAllBytes(responseFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(returnMe);
	}	
	
	private String getResponseDirectoryPath(){
		ResponseDirectoryGetter responseDirectoryGetter = new ResponseDirectoryGetter(); 
		return responseDirectoryGetter.getResponsesDirectoryPath();
}
	
	private void scanWSDLs(String path)
	{
		File curDir = new File(path);
		
		if (!curDir.exists())
		{
			return;
		}
		
		for ( File target : curDir.listFiles())
		{
			if (target.isDirectory())
			{
				scanWSDLs(target.getAbsolutePath());
				continue;
			}
			
			if (target.getName().endsWith(".wsdl"))
			{
				//there is a wsdl here, parse it for actions, and check to see if they exist.
			}
			continue;
		}
		
	}
	
	private void readWSDL(File file)
	{
		/* Unwritten, planned for version 2*/
		
	}
	
	public void serveResponse(String request, String path)
	{
		getRequestName(request);
	}
	
	private String getRequestName(String request)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		System.out.println(request);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(new ByteArrayInputStream(request.getBytes("UTF-8")));
			
			/*NodeList nodes = dom.getElementsByTagName("wsa:Action")*/ //This may or may not work in all situations.  Replaced by code below, if shown to work, switch back.
			
			NodeList nodes = dom.getDocumentElement().getChildNodes();
			for (int count = 0; count < nodes.getLength(); count++)
			{
				System.out.println(nodes.item(count).getNodeName());
			}
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
