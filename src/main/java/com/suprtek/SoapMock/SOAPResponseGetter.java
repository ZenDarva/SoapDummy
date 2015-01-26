package com.suprtek.SoapMock;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class SOAPResponseGetter {
	public Response getResponse(IHTTPSession session, String request, String topLevelPath) {
		ResponseDirectoryGetter responseDirectoryGetter = new ResponseDirectoryGetter();
		SOAPResponseCreator soapResponseCreator = new SOAPResponseCreator();
		return new NanoHTTPD.Response(Status.OK, "text/xml", soapResponseCreator.createResponse(request, session.getUri(), topLevelPath));
	}
}
