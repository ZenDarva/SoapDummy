
package com.suprtek.SoapMock;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import com.suprtek.SoapMock.WSDLGetter;
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
			boolean requestIsForWsdl = (bodyText == null);
			if (requestIsForWsdl){
				WSDLGetter wsdlGetter = new WSDLGetter();
				Response returnMe = wsdlGetter.getWsdl(session, System.getProperty("user.dir"));
				return returnMe;
			}
			else{
				SOAPResponseGetter soapResponseGetter = new SOAPResponseGetter();
				return soapResponseGetter.getResponse(session,bodyText, System.getProperty("user.dir"));
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
		if (session.getQueryParameterString() != null){			
			returnMe = session.getQueryParameterString().toLowerCase();
		}
		return returnMe;
	}


}
