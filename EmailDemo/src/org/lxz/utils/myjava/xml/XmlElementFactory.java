package org.lxz.utils.myjava.xml;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XmlElementFactory {
	
	//防止被实例
	private XmlElementFactory(){};
	
	//读取流
	public static Element decodeRoot(InputStream in) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			return root;	
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	//读取流
	public static Element decodeRoot(File f) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			return root;	
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static  Element decodeRoot(String xmlStr)
	{

		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			Element root=doc.getRootElement();
			return root;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public static  Element decodeRootResource(String classResourcePath)
	{
		return decodeRoot(XmlElementFactory.class.getResourceAsStream(classResourcePath));
	}
	


}
