package com.tool.util.frequent;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Guid;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.io.XmlReader;

public class UtilForRSS {

	/***
	 * ����RSS����XML
	 * @param url	RSSԴ
	 */
	public static void parseXml(URL url) {
		try {
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
			for (int i = 0; i < entries.size(); i++) {
				SyndEntry entry = (SyndEntry) entries.get(i);
				System.out.println((i + 1) + "." + entry.getTitle() + "\t" + entry.getUri());
				System.out.println(entry.getDescription().getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/***
	 * ����RSSԴ��XML��
	 * @return
	 * @throws Exception
	 */
	public static String createXml() throws Exception {
		Channel channel = new Channel("rss_2.0");
		channel.setTitle("channel����");
		channel.setDescription("channel������");
		channel.setLink("www.shlll.net");
		channel.setEncoding("utf-8");
		channel.setLanguage("zh-cn");
		channel.setTtl(5);
		channel.setCopyright("��Ȩ����");
		channel.setPubDate(new Date());
		List<Item> items = new ArrayList<Item>();
		Item item = new Item();
		item.setAuthor("hxliu");
		item.setTitle("���ű���");
		item.setGuid(new Guid());
		item.setPubDate(new Date());
		item.setComments("ע��");
		Description description = new Description();
		description.setValue("��������");
		item.setDescription(description);
		items.add(item);
		channel.setItems(items);
		WireFeedOutput out = new WireFeedOutput();
		try {
			return out.outputString(channel);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}