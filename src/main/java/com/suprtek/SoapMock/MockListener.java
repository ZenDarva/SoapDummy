package com.suprtek.SoapMock;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class MockListener extends NanoHTTPD {
	private FileHandler files;

	public MockListener(int port) {
		super(port);
		files = new FileHandler("C:\\temp");
	}

	@Override
	public Response serve(IHTTPSession session) {

		boolean requestIsForIcon = session.getUri().endsWith(".ico");
		if (requestIsForIcon)
		{
			System.out.println("ico");
			return new NanoHTTPD.Response(":(");
		}	
		
		String query = null;
		if (session.getQueryParameterString() != null){			
			query = session.getQueryParameterString().toLowerCase();
		}
		
		String bodyText = null;
		try {
			if (session.getInputStream().available() > 0)
			{
				byte[] bodyBytes = new byte[session.getInputStream().available()];
				session.getInputStream().read(bodyBytes, 0, session.getInputStream().available()-1);
				bodyText = new String(bodyBytes);
				System.out.println(bodyText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if (query.equals("wsdl"))
		{
			boolean respondWithWSDL = bodyText == null;
			if (respondWithWSDL)
				return getWsdl(session);
			else
			{
				return getResponse();
			}
		}

		return new NanoHTTPD.Response("Something went wrong.");
	}

	private Response getWsdl(IHTTPSession session) {
		return new NanoHTTPD.Response(Status.OK, "text/xml", files.getWSDL(session.getUri()));
	}
	private Response getResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
