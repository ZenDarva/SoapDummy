package com.suprtek.SoapMock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileContentGetter {

	public String getFileContents(String pathToFile) {
		File file = new File(pathToFile);
		boolean isFileInvalid = file == null || file.exists() == false;
		if (isFileInvalid)
			return null;

		byte[] returnMe = null;
		try {
			returnMe = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(returnMe);

	}

}
