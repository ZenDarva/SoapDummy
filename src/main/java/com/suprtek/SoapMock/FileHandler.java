package com.suprtek.SoapMock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler {

	private String topLevelPath;
	
	public FileHandler(String TopLevelPath)
	{
		topLevelPath = TopLevelPath;
		System.out.println("topLevelPath: " + topLevelPath);
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
}
