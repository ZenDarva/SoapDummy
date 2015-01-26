package com.suprtek.SoapMock;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FileHandlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFileHandlerConstructor_SetsTopLevelPath() {
		String testPath = "testPathValue";
		FileHandler testFileHandler = new FileHandler(testPath);
		assert(testPath.equals(testFileHandler.topLevelPath));
	}

	@Test
	public void testGetWSDL_delegatesToFileContentGetter() {
		String testTopLevelPath = "test" + File.separator + "Top"+ File.separator + "Level" + File.separator +"Path" + File.separator + "Value" + File.separator;
		String relativePath = "test" + File.separator + "Relative" + File.separator + "Path" + File.separator + "Value";
		FileHandler testFileHandler = new FileHandler(testTopLevelPath);
		FileContentGetter mockFileContentGetter = mock(FileContentGetter.class);
		testFileHandler.getWSDL(relativePath, mockFileContentGetter);
		
		verify(mockFileContentGetter).getFileContents(testTopLevelPath+relativePath + File.separator + "Value.wsdl");
		
	}

//	@Test
//	public void testCreateResponse() {
//		fail("Not yet implemented");
//	}

}
