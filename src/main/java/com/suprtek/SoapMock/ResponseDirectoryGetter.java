package com.suprtek.SoapMock;

import java.io.File;

public class ResponseDirectoryGetter {
	public String getResponsesDirectoryPath(){
		String returnMe =  System.getProperty("user.dir") + File.separator + "responses";
		return returnMe;
	}
}
