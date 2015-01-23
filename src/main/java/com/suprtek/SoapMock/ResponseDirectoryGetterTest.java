package com.suprtek.SoapMock;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ResponseDirectoryGetterTest {

	@Test
	public void testgetResponsesDirectoryPath() {
		ResponseDirectoryGetter testObject = new ResponseDirectoryGetter();
		String expectedResult = System.getProperty("user.dir") + File.separator + "responses";
		String testResult = testObject.getResponsesDirectoryPath();
		assertTrue(testResult.equals(expectedResult));
	}

}
