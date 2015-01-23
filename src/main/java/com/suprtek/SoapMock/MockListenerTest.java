package com.suprtek.SoapMock;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;
import static org.mockito.Mockito.*;

public class MockListenerTest {

	@Test
	public void testMockListener_hasPortNegativeOneBeforeStarted() {
		int testPortValue = 8080;
		MockListener testObject = new MockListener(testPortValue);
		int expectedValue = -1;
		assertEquals(expectedValue, testObject.getListeningPort());
	}

	@Test
	public void testMockListener_hasPortSetAfterStarted() {
		int testPortValue = 8080;
		MockListener testObject = new MockListener(testPortValue);
		try {
			testObject.start();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(testPortValue, testObject.getListeningPort());
	}

	@Test
	public void testServeIHTTPSession_IconRequests() {
		int testPortValue = 8080;
		MockListener testObject = new MockListener(testPortValue);
		IHTTPSession mockihttpSession = mock(IHTTPSession.class);
		when(mockihttpSession.getUri()).thenReturn(".ico");
		Response testResult = testObject.serve(mockihttpSession);
		Response expectedResult = new NanoHTTPD.Response(":(");

		assertEquals(testResult.getClass(), expectedResult.getClass());
		assertEquals(testResult.getMimeType(), expectedResult.getMimeType());
		assertEquals(testResult.getRequestMethod(),
				expectedResult.getRequestMethod());
		assertEquals(testResult.getStatus(), expectedResult.getStatus());

	}

	@Test
	public void testServeIHTTPSession_WsdlRequests() {
		int testPortValue = 8080;
		MockListener testObject = new MockListener(testPortValue);
		IHTTPSession mockihttpSession = mock(IHTTPSession.class);
		when(mockihttpSession.getUri()).thenReturn("wsdl");
		when(mockihttpSession.getQueryParameterString()).thenReturn("wsdl");
		InputStream mockInputStream = mock(InputStream.class);
		when(mockihttpSession.getInputStream()).thenReturn(mockInputStream);
		try {
			when(mockInputStream.available()).thenReturn(0);
		} catch (IOException e) {
			fail();
		}
		try {
			when(mockInputStream.read(new byte[1000], 0, 999)).thenReturn(1234);
		} catch (IOException e) {
			fail();
		}

		Response testResult = testObject.serve(mockihttpSession);

		FileHandler files = new FileHandler("C:\\temp");
		Response expectedResult = new NanoHTTPD.Response(Status.OK, "text/xml",
				files.getWSDL(mockihttpSession.getUri()));

		assertEquals(testResult.getClass(), expectedResult.getClass());
		assertEquals(testResult.getMimeType(), expectedResult.getMimeType());
		assertEquals(testResult.getRequestMethod(),
				expectedResult.getRequestMethod());
		assertEquals(testResult.getStatus(), expectedResult.getStatus());

	}

}
