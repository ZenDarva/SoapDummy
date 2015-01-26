package com.suprtek.SoapMock;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class SOAPResponseGetter {
	public Response getResponse(IHTTPSession session, String request) {
		ResponseDirectoryGetter responseDirectoryGetter = new ResponseDirectoryGetter();
		FileHandler files = new FileHandler(responseDirectoryGetter.getResponsesDirectoryPath());
		return new NanoHTTPD.Response(Status.OK, "text/xml", files.createResponse(request, session.getUri()));
	}
}
