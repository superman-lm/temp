package com.tool.entity.rss;

import java.util.ArrayList;
import java.util.List;

public class Rss {
	
	private String id;
	private String title;
	private String link;
	private String description;
	private String pubDate;
	private boolean target = Boolean.FALSE;
	private List<News> news = new ArrayList<News>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isTarget() {
		return target;
	}
	public void setTarget(boolean target) {
		this.target = target;
	}
	

}
