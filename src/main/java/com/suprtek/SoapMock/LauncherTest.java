
package com.suprtek.SoapMock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Test;

public class LauncherTest {

	@Test
	public void testLaunch() {
		MockListener mockListener = mock(MockListener.class);
		Launcher testObject = new Launcher(8080);
				
		try {
			verify(mockListener).start();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

}
