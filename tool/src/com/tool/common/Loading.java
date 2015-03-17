package com.tool.common;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.tool.entity.rss.News;
import com.tool.entity.rss.Rss;
import com.tool.util.frequent.UtilForHttpUnit;
import com.tool.util.frequent.UtilForJson;
import com.tool.util.io.Files;

public class Loading {
	
	
	private static List<URL> urlList;
	List<Rss> rssList;
	String date = SimpleDateFormat.getDateInstance(2).format(new Date());
	static {
		try {
			urlList = new ArrayList<URL>();
			URL url1 = new URL("http://tech.163.com/special/000944OI/headlines.xml");
			urlList.add(url1);
			URL url2 = new
			URL("http://news.163.com/special/00011K6L/rss_gn.xml");
			urlList.add(url2);
		} catch (Exception e) {
			System.out.println("init url error...");
		}
	}
	private boolean rss() {
		try {
			Files files = new Files();
			rssList = new ArrayList<Rss>();
			UtilForJson json = new UtilForJson();
			File file = null;
			for (URL url : urlList) {
				Rss rss = getRss(url);
				rssList.add(rss);
			}
			file = new File("D:\\RSS\\" + date);
			if (files.exist(file)) {
				return true;
			}
			files.mkdir(file);
			for (Rss rss : rssList) {
				file = new File("D:\\RSS\\" + date + "\\" + rss.getId() + ".txt");
				files.create(file);
				files.write(file, json.writeValueAsString(rss));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean load() {
		
		try {
			long start = new Date().getTime();
			Files files = new Files();
			WebClient webClient = UtilForHttpUnit.createWebClient();
			HtmlPage page = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
			File file = null;
			
			file = new File("D:\\RSS\\" + date + "\\content");
			if (!files.exist(file)) {
				files.mkdir(file);
			}else {
				return true;
			}
			int count = 1;
			for (Rss rss : rssList) {
				file = new File("D:\\RSS\\" + date + "\\content\\"+rss.getId());
				files.mkdir(file);
				for (News news : rss.getNews()) {
					page = webClient.getPage(news.getGuid());
					HtmlElement contElement = (HtmlElement) page.getElementById("endText");
					if (contElement!=null) {
						String cont = contElement.asXml();
						file = new File("D:\\RSS\\" + date + "\\content\\" +rss.getId()+"\\"+ rss.getId()+"-"+news.getId()+".txt");
						files.write(file, cont);
						System.out.println("===========================================加载第："+count+"篇文章类容");
					}else {
						//TODO rss target is true
					}
					count++;
				}
			}
			long end = new Date().getTime();
			System.out
					.println("==========================================================================================耗时："
							+ (end - start)+"毫秒！");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Rss getRss(URL url) {
		try {
			LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog"); 
			Rss rss = new Rss();
			List<News> newsList = new ArrayList<News>();
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = null;
			URLConnection conn;
			conn = url.openConnection();
			String content_encoding = conn.getHeaderField("Content-Encoding");
			if (content_encoding != null && content_encoding.contains("gzip")) {
				GZIPInputStream gzin = new GZIPInputStream(conn.getInputStream());
				feed = input.build(new XmlReader(gzin));
			} else {
				feed = input.build(new XmlReader(conn.getInputStream()));
			}
			List<?> entries = feed.getEntries();
			for (Object obj : entries) {
				News news = new News();
				SyndEntry entry = (SyndEntry)obj;
				news.setId(UUID.randomUUID().toString().replace("-", ""));
				news.setTitle(entry.getTitle());
				news.setLink(entry.getLink());
				news.setPubDate(entry.getPublishedDate().toString());
//				news.setDescription(entry.getDescription().getValue());
				news.setGuid(entry.getUri());
				newsList.add(news);
			}
			rss.setId(UUID.randomUUID().toString().replace("-", ""));
			rss.setTitle(feed.getTitle());
			rss.setLink(feed.getLink());
			rss.setPubDate(feed.getPublishedDate().toString());
//			rss.setDescription(feed.getDescription());
			rss.setNews(newsList);
			return rss;
		} catch (Exception e) {
			return null;
		}
	}

	
	public void init() {
		boolean flag = Boolean.FALSE;
		flag = rss();
		flag = load();
		if (flag) {
			System.out.println("已加载完毕...");
		}
	}
}
