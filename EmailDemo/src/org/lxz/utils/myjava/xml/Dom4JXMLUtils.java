package org.lxz.utils.myjava.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;


public class Dom4JXMLUtils {
	
	public static String getPathText(String xmlStr,String strpath)
	{
		Document doc = null;
		String result = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element root=doc.getRootElement();
			Node xpath=(Node) root.selectObject(strpath);
			result=xpath.getText();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static HashMap<String,String> getPathText(String xmlStr,String[] strpath)
	{
		Document doc = null;
		HashMap<String,String> result = null;
		System.out.println("AA="+xmlStr);
		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element root=doc.getRootElement();
			int length=strpath.length;
			result=new HashMap<String,String>();
			for(int i=0;i<length;i++)
			{
				Node xpath=(Node) root.selectObject(strpath[i]);			
				result.put(strpath[i],xpath.getText());
			}
			System.out.println("AA="+xmlStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
   
	public static List<Node> getPathNodeList(String xmlStr,String strpath)
	{
		Document doc = null;

		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element root=doc.getRootElement();

	
				return (List<Node>) root.selectObject(strpath);			


		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//	public String getPath(String xmlStr,String path)
//	{
//		Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(xmlStr.replace("\\?", ""));
//			Element rootElt = doc.getRootElement();
//			readNode(rootElt, null);
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return path;
//	}
	
//	static String xPathNode(Document doc,XPathFactory factory,String strpath) {
//		XPath xpath = factory.newXPath();
//	
//		try {
//			XPathExpression expr = xpath.compile(strpath);
//			NodeList nodes = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);
//			return nodes.item(0).getNodeValue();
//		} catch (XPathExpressionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return strpath;
//	}

	
	//遍历所有的节点
	public static void readNode(Element root, String prefix) {
		if (root == null)
			return;
		// 获取属性
		@SuppressWarnings("unchecked")
		List<Attribute> attrs = root.attributes();
		if (attrs != null && attrs.size() > 0) {
			System.err.print(prefix);
			for (Attribute attr : attrs) {
				System.err.print(attr.getValue() + " ");
			}
			System.err.println();
		}
		// 获取他的子节点
		List<Element> childNodes = root.elements();
		prefix += "\t";
		for (Element e : childNodes) {
			readNode(e, prefix);
		}
	}
	//读取流
	public static Element loadData(InputStream in) {
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

	
}
