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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class FileHandler {

	private String topLevelPath;
	
	public FileHandler(String TopLevelPath)
	{
		topLevelPath = TopLevelPath;
		
	}
	
	public String getWSDL(String path)
	{		
		
		return readFile(topLevelPath + path+path +".wsdl");		
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
	
	private String readFile(String path)
	{
		File file = new File(path);		
		boolean isFileInvalid = file == null || file.exists() == false;
		if (isFileInvalid)
			return null;
		
		byte[] returnMe = null;
		try {
			returnMe = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(returnMe);		

	}
	
	public String createResponse(String request, String path)
	{
		String requestName = getRequestName(request);
		
		String result = readFile(topLevelPath + path +"/" + requestName + ".soap" );
		
		if (result == null)
		{
			result = readFile(topLevelPath + "/Fault.soap");
			System.out.println("Fault: " + result);
		}
		return result;
		
	}
	
	private String getRequestName(String request)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		System.out.println(request);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(new ByteArrayInputStream(request.getBytes("UTF-8")));
			
			NodeList nodes = dom.getElementsByTagName("wsa:Action");
			if (nodes == null)
				return null;
					
			
			nodes = nodes.item(0).getChildNodes();
			if (nodes == null)
				return null;
			Node node = nodes.item(0);
			
			if (node == null)
				return null;
			
			return node.getTextContent();
			
			
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
