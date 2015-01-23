package com.suprtek.SoapMock;

public class Main {

	public static void main(String[] args) {
		String argument0 = getArgument0(args);
		new Launcher(getPortFromString(argument0));
	}

	private static int getPortFromString(String portString) {
		int defaultPortValue = 8080;
		int portInt;
		try {
			portInt = Integer.parseInt(portString);
		} catch (NumberFormatException numberFormatException) {
			portInt = defaultPortValue;
		}
		return portInt;
	}

	private static String getArgument0(String[] args) {
		String argument0;
		try {
			argument0 = args[0];
		} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			argument0 = null;
		}
		return argument0;
	}

}
