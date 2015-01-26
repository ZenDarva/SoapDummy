package com.suprtek.SoapMock;

import java.io.IOException;

public class Launcher {

	public Launcher(int port) {
		launch(new MockListener(port));
	}

	void launch(MockListener mock) {
		try {
			mock.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 keepAlive();
	}

	private void keepAlive() {
		while (true)
		 try {
		 Thread.sleep(100);
		 } catch (InterruptedException e) {
		 e.printStackTrace();
		 }
	}

}
