package com.suprtek.SoapMock;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

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
}
