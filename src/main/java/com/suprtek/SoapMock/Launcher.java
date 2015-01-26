package com.suprtek.SoapMock;

import java.io.IOException;

public class Launcher {

	public Launcher(int port) {
		launch(new MockListener(port), true);
	}

	Launcher(int port, MockListener mockListener) {
		launch(mockListener, false);
	}

	void launch(MockListener mockListener, boolean productionRun) {
		try {
			mockListener.start();
			keepAlive(productionRun);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			mockListener.stop();
		}

	}

	private void keepAlive(boolean productionRun) {
		while (productionRun)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
