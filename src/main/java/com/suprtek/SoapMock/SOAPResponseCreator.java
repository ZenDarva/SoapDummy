package com.suprtek.SoapMock;

import java.io.File;

public class SOAPResponseCreator {
	public String createResponse(String request, String path, String topLevelPath) {
		SOAPRequestNameGetter soapRequestNameGetter = new SOAPRequestNameGetter(); 
		String requestType = soapRequestNameGetter.getRequestName(request);

		FileContentGetter fileContentGetter = new FileContentGetter();
		String result = fileContentGetter.getFileContents(topLevelPath + path + File.separator + requestType + ".soap");

		if (result == null) {
			result = fileContentGetter.getFileContents(topLevelPath + File.separator + "Fault.soap");
			System.out.println("Fault: " + result);
		}
		return result;
	}
}
