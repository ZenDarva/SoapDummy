package com.suprtek.SoapMock;
import java.io.IOException;

import fi.iki.elonen.*;

public class dummyMain {

	public static void main(String[] args) {

		MockListener Mock = new MockListener(8080);
		try {
			Mock.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
