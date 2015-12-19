package com.raincent.web.model;

/**
 * 新闻模型
 * @author DELL
 */
public class News
{
	private int newsid;
	private int cid;
	private String newstype;
	private String newstitle;
	private String summary;
	private String content;
	private String thumbnail_url;	
	private String author;
	private String newstime;
	private String news_fav;
	private int uid;
	private String news_url;

	public News()
	{
		super();
	}

	public News(int newsid, int cid, String newstype, String newstitle,
			String summary, String thumbnail_url, String content,
			String author, String newstime, String news_fav, int uid,
			String news_url) {
		super();
		this.newsid = newsid;
		this.cid = cid;
		this.newstype = newstype;
		this.newstitle = newstitle;
		this.summary = summary;
		this.thumbnail_url = thumbnail_url;
		this.content = content;
		this.author = author;
		this.newstime = newstime;
		this.news_fav = news_fav;
		this.uid = uid;
		this.news_url = news_url;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getNewstype() {
		return newstype;
	}

	public void setNewstype(String newstype) {
		this.newstype = newstype;
	}

	public String getNewstitle() {
		return newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNewstime() {
		return newstime;
	}

	public void setNewstime(String newstime) {
		this.newstime = newstime;
	}

	public String getNews_fav() {
		return news_fav;
	}

	public void setNews_fav(String news_fav) {
		this.news_fav = news_fav;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNews_url() {
		return news_url;
	}

	public void setNews_url(String news_url) {
		this.news_url = news_url;
	}

	
}
