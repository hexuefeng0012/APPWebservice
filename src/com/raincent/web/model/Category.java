package com.raincent.web.model;

/**
 * 新闻分类模型
 * @author DELL
 */
public class Category
{
	private int cid;
	private String newstype;

	public Category()
	{
		super();
	}

	public Category(int cid, String newstype) {
		super();
		this.cid = cid;
		this.newstype = newstype;
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

}
