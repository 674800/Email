package org.lxz.utils.myjava.xml;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;

public class ElementUtils {

	private ElementUtils(){};
	
	public interface ElementExcute{
		void nextElement(Element e);
		void elementFinsh();
	}
	
	public interface AttributeExcute{
		void nextAttribute(Element e);
		void AttributeFinsh();
	}
	/** 枚举所有子节点
	 * @param root 根节点
	 * @param excute 回调接口
	 */
	public static void getElement(Element root,ElementExcute excute) {
		
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			excute.nextElement(element);
		}

		excute.elementFinsh();
	}

	/**
	 * 枚举名称为foo的节点
	 * @param root 根节点
	 * @param excute 回调接口
	 * */
	public static void getElement(Element root, String foo,ElementExcute excute) {

		// 枚举名称为foo的节点
		for (Iterator i = root.elementIterator(foo); i.hasNext();) {
			Element child = (Element) i.next();
			excute.nextElement(child);
		}
		excute.elementFinsh();
	}
     /**
	 * 枚举Element的节点的所有属性
	 * @param root 根节点
	 * @param excute 回调接口
	 * */
	public static void getAttribute(Element root,AttributeExcute attributeExcute) {

		// 枚举属性
		for (Iterator i = root.attributeIterator(); i.hasNext();) {
			Attribute attribute = (Attribute) i.next();
			attributeExcute.AttributeFinsh();
		}
	}
	
	
	 /**
	 * 获得Element的节点的xPath的路径值
	 * @param root 根节点
	 * @param strpath xPath路径
	 * */
	public static String getPathText(Element root, String strpath) {
		String result = null;
		try {
			Node xpath = (Node) root.selectObject(strpath);
			result = xpath.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 打印所有节点的值和属性
	 * */
	public static void println(Element root) {
		if (root == null)
			return;
		// 获取属性
		@SuppressWarnings("unchecked")
		List<Attribute> attrs = root.attributes();
		if (attrs != null && attrs.size() > 0) {
			for (Attribute attr : attrs) {
				System.err.print(attr.getValue() + " ");
			}
			System.err.println();
		}
		// 获取他的子节点
		List<Element> childNodes = root.elements();
		for (Element e : childNodes) {
			println(e);
		}
	}

}
