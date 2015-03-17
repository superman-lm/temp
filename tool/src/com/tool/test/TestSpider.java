package com.tool.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;

public class TestSpider {

	private static String ENCODE = "GBK";

	public static void message(String msg) {
		try {
			System.out.println(new String(msg.getBytes(ENCODE), System.getProperty("file.encoding")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String openFile(String file) {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), ENCODE));
			String szContent = "";
			String szTemp;
			while ((szTemp = bis.readLine()) != null) {
				szContent += szTemp + "\n";
			}
			bis.close();
			return szContent;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		long start = new Date().getTime();
		try {
			Parser parser = new Parser(
					(HttpURLConnection) (new URL("http://tech.163.com/14/1215/02/ADFKMTCM000915B2.html")).openConnection());
//			PrototypicalNodeFactory p = new PrototypicalNodeFactory(); 
//	        p.registerTag(new ScriptTag()); 
//	        parser.setNodeFactory(p); 
//			NodeFilter filterJS = new NodeClassFilter(ScriptTag.class); 
//	        NodeList nodelistJS=parser.extractAllNodesThatMatch(filterJS);
//	        System.out.println(nodelistJS);
			
			for (NodeIterator i = parser.elements(); i.hasMoreNodes();) {
				Node node = i.nextNode();
//				message("getText:" + node.getText());
//				message("getPlainText:" + node.toPlainTextString());
//				message("toHtml:" + node.toHtml());
				message("toHtml(true):" + node.toHtml(true));
//				message("toHtml(false):" + node.toHtml(false));
//				message("toString:" + node.toString());
				
			}
			long end = new Date().getTime();
			System.out
			.println("=========================================================================================ºÄÊ±£º"
					+ (end - start));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
