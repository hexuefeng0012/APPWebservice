package com.raincent.web.model;

public class Banner {

	private int bid;
	private String pic_name;
	private String pic_url;
	private int uid;
	private String news_fav;
	private String newstitle;
	private String author;
	private String summary;
	private String content;
	private String news_url;
	private String newstime;
	public Banner() {
		super();
	}
	public Banner(int bid, String pic_name, String pic_url, int uid,
			String news_fav, String newstitle, String author, String summary,
			String content, String news_url, String newstime) {
		super();
		this.bid = bid;
		this.pic_name = pic_name;
		this.pic_url = pic_url;
		this.newstitle = newstitle;
		this.author = author;
		this.summary = summary;
		this.content = content;
		this.news_url = news_url;
		this.newstime = newstime;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getPic_name() {
		return pic_name;
	}
	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNews_fav() {
		return news_fav;
	}
	public void setNews_fav(String news_fav) {
		this.news_fav = news_fav;
	}
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNews_url() {
		return news_url;
	}
	public void setNews_url(String news_url) {
		this.news_url = news_url;
	}
	public String getNewstime() {
		return newstime;
	}
	public void setNewstime(String newstime) {
		this.newstime = newstime;
	}

}
