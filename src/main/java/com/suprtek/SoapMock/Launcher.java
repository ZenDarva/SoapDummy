package com.suprtek.SoapMock;

import java.io.IOException;

public class Launcher {

	public Launcher(int port) {
		launch(new MockListener(port));
	}

	void launch(MockListener mock) {
		System.out.println("launch start");
		try {
			mock.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 keepAlive();
			System.out.println("launch stop");
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
