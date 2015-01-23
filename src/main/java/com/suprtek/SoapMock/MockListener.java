package com.suprtek.SoapMock;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class MockListener extends NanoHTTPD {

	public MockListener(int port) {
		super(port);
	}

	@Override
	public Response serve(IHTTPSession session) {

		boolean requestIsForIcon = session.getUri().endsWith(".ico");
		if (requestIsForIcon)
			return new NanoHTTPD.Response(":(");
		
		String query = getQueryFromSession(session);

		if (query.equals("wsdl"))
		{
			String bodyText = getBodyText(session);
			boolean requestIsForWsdl = bodyText == null;
			if (requestIsForWsdl)
				return getWsdl(session);
			else
			{
				return getResponse();
			}
		}

		return new NanoHTTPD.Response("Something went wrong.");
	}

	private String getBodyText(IHTTPSession session) {
		String returnMe = null;
		try {
			if (session.getInputStream().available() > 0)
			{
				byte[] bodyBytes = new byte[session.getInputStream().available()];
				session.getInputStream().read(bodyBytes, 0, session.getInputStream().available()-1);
				returnMe = new String(bodyBytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnMe;
	}

	private String getQueryFromSession(IHTTPSession session) {
		String returnMe = null;
		if (session.getQueryParameterString() != null){			
			returnMe = session.getQueryParameterString().toLowerCase();
		}
		return returnMe;
	}

	private Response getWsdl(IHTTPSession session) {
		FileHandler files = new FileHandler("C:\\temp");
		return new NanoHTTPD.Response(Status.OK, "text/xml", files.getWSDL(session.getUri()));
	}
	private Response getResponse() {
		// TODO Implement
		return null;
	}
	
	
}
