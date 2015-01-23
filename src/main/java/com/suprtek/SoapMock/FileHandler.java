package com.suprtek.SoapMock;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import org.ow2.easywsdl.wsdl.WSDLFactory;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.Service;
import org.ow2.easywsdl.wsdl.api.WSDLException;
import org.ow2.easywsdl.wsdl.api.WSDLReader;

public class FileHandler {

	private String topLevelPath;
	
	public FileHandler(String TopLevelPath)
	{
		topLevelPath = TopLevelPath;
		System.out.println("topLevelPath: " + topLevelPath);
		
		scanWSDLs(TopLevelPath);
	}
	
	public String getWSDL(String path)
	{		
		File wsdlFile = new File(topLevelPath + path+path +".wsdl");		
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
		try {
			WSDLReader reader = WSDLFactory.newInstance().newWSDLReader();
			Description desc = reader.read(new URL("File://"+file.getAbsolutePath()));
			
			Service serv = desc.getServices().get(0);
			System.out.println("test");
		} catch (WSDLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
