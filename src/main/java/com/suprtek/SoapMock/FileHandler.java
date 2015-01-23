package com.suprtek.SoapMock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler {

	private String topLevelPath;
	
	public FileHandler(String TopLevelPath)
	{
		topLevelPath = TopLevelPath;
		System.out.println("Hi, i'm here");
	}
	
	public String getWSDL(String path)
	{
		File target;
		byte[] result = null;
		
		target = new File(topLevelPath + path+path +".wsdl");
		
		
		if (target == null || target.exists() == false)
			return null;
		
		try {
			result = Files.readAllBytes(target.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(result);
	}
	
}
