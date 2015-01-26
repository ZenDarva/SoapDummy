package com.suprtek.SoapMock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class MockListener extends NanoHTTPD {

	public MockListener(int port) {
		super(port);
	}

	@Override
	public Response serve(IHTTPSession session) {

		System.out.println("serve start");

		boolean requestIsForIcon = session.getUri().endsWith(".ico");
		if (requestIsForIcon) {
			System.out.println("requestForIcon");
			return new NanoHTTPD.Response(":(");
		}

		String query = getQueryFromSession(session);

		if (query.equals("wsdl"))
		{
			String bodyText = getBodyText(session);
			boolean requestIsForWsdl = (bodyText == null);
			if (requestIsForWsdl)
				return getWsdl(session);
			else
				return getResponse(session,bodyText);
		}

		System.out.println("requestIsForWsdl");
		return getResponse(session);

	}

	private String getBodyText(IHTTPSession session) {
		String returnMe = null;
		try {
			if (session.getInputStream().available() > 0)
			{
				byte[] bodyBytes = new byte[session.getInputStream().available()];
				session.getInputStream().read(bodyBytes, 0, session.getInputStream().available());
				returnMe = new String(bodyBytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnMe;
	}

	private String getQueryFromSession(IHTTPSession session) {
		String returnMe = null;
		if (session.getQueryParameterString() != null) {
			returnMe = session.getQueryParameterString().toLowerCase();
		}
		return returnMe;
	}

	private Response getWsdl(IHTTPSession session) {
		ResponseDirectoryGetter responseDirectoryGetter = new ResponseDirectoryGetter();
		FileHandler files = new FileHandler();
		return new NanoHTTPD.Response(Status.OK, "text/xml",
				files.getWSDL(session.getUri()));
	}

	private Response getResponse(IHTTPSession session, String request) {
		FileHandler files = new FileHandler();
		files.serveResponse(request, session.getUri());
		return null;
	}
	
	private Response getResponse(IHTTPSession session) {
		// hash the request message
		Map<String, String> parsedBody = new HashMap<String, String>();
		try {
			session.parseBody(parsedBody);
		} catch (IOException e) {
			e.printStackTrace();
			return new Response(Response.Status.INTERNAL_ERROR, MIME_PLAINTEXT,
					"SERVER INTERNAL ERROR: IOException: " + e.getMessage());
		} catch (ResponseException e) {
			e.printStackTrace();
			return new Response(e.getStatus(), MIME_PLAINTEXT, e.getMessage());

		}
		String requestedAction = parsedBody.get("Action");
		int hashedRequestBody = 0;

		if(requestedAction != null){
			hashedRequestBody = parsedBody.get("Action").hashCode();
		}
		FileHandler fileHandler = getFileHandler();
		return new NanoHTTPD.Response(Status.OK, "text/xml",
				fileHandler.getResponse(session.getUri(), hashedRequestBody));
	}

	private FileHandler getFileHandler() {
		ResponseDirectoryGetter responseDirectoryGetter = new ResponseDirectoryGetter();
		FileHandler returnMe = new FileHandler();
		return returnMe;
	}

}
