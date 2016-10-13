package bjtu.pyrmont;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {

	public static String[] servletName = new String[2];
	public static String[] url_pattern = new String[2];
	public static String[] servlet_class = new String[2];
	
	public void xmlreader() {
		// TODO Auto-generated method stub
		int n=0;int m=0;
		Element element = null;
		  // 可以使用绝对路劲
		  File f = new File("web.xml");

		  // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		  DocumentBuilder db = null;
		  DocumentBuilderFactory dbf = null;
		  try {
		   // 返回documentBuilderFactory对象
		   dbf = DocumentBuilderFactory.newInstance();
		   // 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
		   db = dbf.newDocumentBuilder();

		   // 得到一个DOM并返回给document对象
		   Document dt = db.parse(f);
		   // 得到一个elment根元素
		   element = dt.getDocumentElement();
		
		   NodeList childNodes = element.getChildNodes();

		   // 遍历这些子节点
		   for (int i = 0; i < childNodes.getLength(); i++) {
		    // 获得每个对应位置i的结点
		    Node node1 = childNodes.item(i);
		    if ("servlet".equals(node1.getNodeName())) {
		     NodeList nodeDetail = node1.getChildNodes();

		     for (int j = 0; j < nodeDetail.getLength(); j++) {
		      // 获得<Accounts>元素每一个节点
		      Node detail = nodeDetail.item(j);
		      if ("servlet-name".equals(detail.getNodeName())) // 输出code
		    	  servletName[n] = detail.getTextContent();
		      else if ("servlet-class".equals(detail.getNodeName())) {// 输出pass
		    	  servlet_class[n] = detail.getTextContent();
		      }
		     }
		     n++;
		    }
		    
		    if ("servlet-mapping".equals(node1.getNodeName())) {
		    	 NodeList nodeDetail = node1.getChildNodes();
		    	 
		    	 for (int j = 0; j < nodeDetail.getLength(); j++) {
				      // 获得<Accounts>元素每一个节点
				      Node detail = nodeDetail.item(j);
				      if ("servlet-name".equals(detail.getNodeName())) { // 输出code
				    	  servletName[m] =  detail.getTextContent();

				      }
				      else if ("url-pattern".equals(detail.getNodeName())) {// 输出pass
				    	  url_pattern[m] = detail.getTextContent();

				      }
		    	 }
		    	 
		    	 m++;
		    }

		   }
		  }

		  catch (Exception e) {
		   e.printStackTrace();
		  }

	}

}
