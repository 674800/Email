package org.lxz.utils.myjava.xml;

import org.dom4j.Node;

public class NodeUtils {

	   /**输入路径获得节点的text值*/
	public static String pathText(Node n,String path){
			return ((Node) n.selectObject(path)).getText();
	}


	
}
