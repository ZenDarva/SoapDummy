package com.suprtek.SoapMock;

import java.io.File;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class WSDLGetter {
	public Response getWsdl(IHTTPSession session, String topLevelPath) {
		return new NanoHTTPD.Response(Status.OK, "text/xml", getWSDLString(session, topLevelPath));
	}
	
	private String getWSDLString(IHTTPSession session, String topLevelPath) {
		return getWSDLString(session, topLevelPath);
	}

	private String getWSDLString(String path, FileContentGetter fileContentGetter, String topLevelPath) {
		String fullPath = (topLevelPath + path + path.substring(path.lastIndexOf(File.separatorChar)) + ".wsdl").replace('/', '\\');
		return fileContentGetter.getFileContents(fullPath);
	}
}
