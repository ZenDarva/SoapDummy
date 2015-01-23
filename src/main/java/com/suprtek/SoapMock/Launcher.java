package com.suprtek.SoapMock;

import java.io.IOException;

public class Launcher {

	public Launcher(int port) {
		
		MockListener Mock = new MockListener(port);
		try {
			Mock.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	
	
}
