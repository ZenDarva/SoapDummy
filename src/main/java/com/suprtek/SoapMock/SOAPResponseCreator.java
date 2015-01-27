package com.suprtek.SoapMock;

import java.io.File;

public class SOAPResponseCreator {
	
	// BUG:  this response lookup requires the request xml to contain a non standard '<wsa:Action>' tag to function
	//    need to determine request type another way, probably via WSDL inspection.
	public String createResponse(String request, String path, String topLevelPath) {
		SOAPActionGetter soapRequestNameGetter = new SOAPActionGetter(); 
		String contentsOfWsaActionTag = soapRequestNameGetter.getRequestName(request);

		FileContentGetter fileContentGetter = new FileContentGetter();
		String result = fileContentGetter.getFileContents(topLevelPath + path + File.separator + contentsOfWsaActionTag + ".soap");

		if (result == null) {
			result = fileContentGetter.getFileContents(topLevelPath + File.separator + "Fault.soap");
			System.out.println("Fault: " + result);
		}
		return result;
	}
}
