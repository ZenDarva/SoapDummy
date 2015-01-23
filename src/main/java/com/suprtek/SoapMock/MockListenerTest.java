package com.suprtek.SoapMock;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import static org.mockito.Mockito.*
;public class MockListenerTest {

	@Test
	public void testMockListener() {
		int testPortValue = 8080;
		MockListener testObject = new MockListener(testPortValue);
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
		
		assertSame(testResult.toString(), expectedResult.toString());
		
	}

}
