package com.suprtek.SoapMock;
import java.io.IOException;

import fi.iki.elonen.*;

public class dummyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MockListener Mock = new MockListener();
		try {
			Mock.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
