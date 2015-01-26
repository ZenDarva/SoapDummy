package com.suprtek.SoapMock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SOAPRequestNameGetter {
	public String getRequestName(String request) {
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
