package com.suprtek.SoapMock;

import java.io.BufferedInputStream;
import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class MockListener extends NanoHTTPD {
	private FileHandler files;

	public MockListener() {
		super(8080);
		files = new FileHandler("C:\\temp");
	}
	
	

	@Override
	public Response serve(IHTTPSession session) {
		String query = null;
		String bodyText = null;
		byte[] bodyBytes;

		
		
		if (session.getUri().endsWith(".ico"))
		{
			//go away you horrible browser you.
			System.out.println("ico");
			return new NanoHTTPD.Response(":(");
		}
		
		if (session.getQueryParameterString() != null){			
			query = session.getQueryParameterString().toLowerCase();
		}
		
		try {
			if (session.getInputStream().available() > 0)
			{
				bodyBytes = new byte[session.getInputStream().available()];
				session.getInputStream().read(bodyBytes, 0, session.getInputStream().available()-1);
				bodyText = new String(bodyBytes);
				System.out.println(bodyText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if (query.equals("wsdl"))
		{
			if (bodyText == null)
				return new NanoHTTPD.Response(Status.OK, "text/xml", files.getWSDL(session.getUri()));
			else
			{
				//Write code here.
			}
		}

		return new NanoHTTPD.Response("This is a tribute");
	}
	
	
}
