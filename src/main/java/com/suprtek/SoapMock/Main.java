package com.suprtek.SoapMock;

public class Main {

	public static void main(String[] args) {
		String argument0 = getArg0(args);
		new Launcher(getPortArgumnet(argument0));
	}

	private static int getPortArgumnet(String portString) {
		int defaultPortValue = 8080;
		int portInt;
		try {
			portInt = Integer.parseInt(portString);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			portInt = defaultPortValue;
		}
		return portInt;
	}

	private static String getArg0(String[] args) {
		String argument0;
		try {
			argument0 = args[0];
		} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			argument0 = null;
		}
		return argument0;
	}

}
