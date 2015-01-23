package com.suprtek.SoapMock;

import java.io.File;

public class ResponseDirectoryGetter {
	public String getResponsesDirectoryPath(){
		return System.getProperty("user.dir") + File.separator + "responses";
	}
}
