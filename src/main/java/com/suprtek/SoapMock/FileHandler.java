package com.suprtek.SoapMock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileHandler {

	String topLevelPath;

	public FileHandler(String TopLevelPath) {
		topLevelPath = TopLevelPath;
	}

	public String getWSDL(String path) {
		return getWSDL(path, new FileContentGetter());
	}

	String getWSDL(String path, FileContentGetter fileContentGetter) {
		String fullPath = (topLevelPath + path + path.substring(path.lastIndexOf(File.separatorChar)) + ".wsdl").replace('/', '\\');
		return fileContentGetter.getFileContents(fullPath);
	}

	public String createResponse(String request, String path) {
		String requestName = getRequestName(request);

		FileContentGetter fileContentGetter = new FileContentGetter();
		String result = fileContentGetter.getFileContents(topLevelPath + path + File.separator + requestName + ".soap");

		if (result == null) {
			result = fileContentGetter.getFileContents(topLevelPath + "/Fault.soap");
			System.out.println("Fault: " + result);
		}
		return result;
	}

	private String getRequestName(String request) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		System.out.println(request);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(new ByteArrayInputStream(request
					.getBytes("UTF-8")));

			NodeList nodes = dom.getElementsByTagName("wsa:Action");
			if (nodes == null)
				return null;

			nodes = nodes.item(0).getChildNodes();
			if (nodes == null)
				return null;
			Node node = nodes.item(0);

			if (node == null)
				return null;

			return node.getTextContent();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
