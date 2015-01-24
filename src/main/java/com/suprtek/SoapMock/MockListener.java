package com.suprtek.SoapMock;

import java.io.File;
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

		if (query.equals("wsdl")) {
			boolean requestIsForWsdl = (getBodyText(session) == null);
			if (requestIsForWsdl){
				System.out.println("requestIsForWsdl");
				return getWsdl(session);

			}
			else{
				System.out.println("requestIsForWsdl");
				return getResponse(session);
			}
		}

		return new NanoHTTPD.Response("Something went wrong.");
	}

	private String getBodyText(IHTTPSession session) {
		String returnMe = null;
		try {
			if (session.getInputStream().available() > 0) {
				byte[] bodyBytes = new byte[session.getInputStream()
						.available()];
				session.getInputStream().read(bodyBytes, 0,
						session.getInputStream().available() - 1);
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
		FileHandler files = new FileHandler(
				responseDirectoryGetter.getResponsesDirectoryPath());
		return new NanoHTTPD.Response(Status.OK, "text/xml",
				files.getWSDL(session.getUri()));
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
		parsedBody.get("Action");
		int hashedRequestBody = parsedBody.get("Action").hashCode();
		//
		FileHandler files = getFileHandler();
		return new NanoHTTPD.Response(Status.OK, "text/xml",
				files.getResponse(session.getUri()));
	}

	private FileHandler getFileHandler() {
		ResponseDirectoryGetter responseDirectoryGetter = new ResponseDirectoryGetter();
		FileHandler returnMe = new FileHandler(
				responseDirectoryGetter.getResponsesDirectoryPath());
		return returnMe;
	}

}
