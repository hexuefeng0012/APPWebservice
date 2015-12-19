package com.raincent.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.raincent.web.model.News;

/**
 * 对r_news数据表进行访问
 * @author DELL
 */
public class NewsDAO
{
	SqlManager manager;
	String sql = "";
	ResultSet rs;
	ResultSet rs2;

	public NewsDAO() throws IOException, ClassNotFoundException
	{
		manager = SqlManager.createInstance();
	}

	/**
	 * 获取新闻内容
	 * @param newsid 新闻编号
	 * @return news 相信新闻结果集
	 * @throws SQLException
	 * @param rs sql查询返回的结果集
	 */
	public News getNews(int newsid) throws SQLException
	{
		sql = "SELECT r_news.*,r_category.newstype FROM r_news  INNER JOIN r_category ON r_news.cid=r_category.cid WHERE r_news.newsid=? ORDER BY newstime DESC";
		Object[] params = new Object[]{ newsid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		News news = new News();
		if (rs.next())
		{
			news.setNewsid(rs.getInt("newsid"));
			news.setCid(rs.getInt("cid"));
			news.setNewstitle(rs.getString("newstitle"));
			news.setThumbnail_url(rs.getString("thumbnail_url"));
			news.setAuthor(rs.getString("author"));
			news.setSummary(rs.getString("summary"));
			news.setContent(rs.getString("content"));
			news.setNewstime(rs.getString("newstime"));
			news.setNews_url(rs.getString("news_url"));
		}
		manager.closeDB();
		return news;
	}
	/**
	 * 获取指定类别的新闻列表
	 * @param cid 新闻类型
	 * @param startnewsid 从第几个新闻开始返回
	 * @param count 指定新闻的数量
	 * @return list 新闻列表结果集 
	 * @throws SQLException
	 */
	public ArrayList<News> getSourceList(int cid, int startnewsid, int count)
			throws SQLException
	{
		ArrayList<News> list = new ArrayList<News>();
		sql = "SELECT r_news.*,r_category.newstype FROM r_news  INNER JOIN r_category ON r_news.cid=r_category.cid WHERE r_news.cid=? ORDER BY newstime DESC LIMIT ?,?";
		Object[] params = new Object[]{ cid, startnewsid, count };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		while (rs.next())
		{
			News news = new News();
			news.setNewsid(rs.getInt("newsid"));
			news.setNewstitle(rs.getString("newstitle"));
			news.setNewstype(rs.getString("newstype"));
			news.setSummary(rs.getString("summary"));
			news.setThumbnail_url(rs.getString("thumbnail_url"));
			news.setNewstime(rs.getString("newstime"));
			list.add(news);
		}
		manager.closeDB();
		return list;
	}
	public ArrayList<News> getSearchNews(String key)
			throws SQLException
	{
		ArrayList<News> list = new ArrayList<News>();
		sql = "SELECT r_news.*,r_category.newstype FROM r_news  INNER JOIN r_category ON r_news.cid=r_category.cid WHERE r_news.newstitle like '%"+key+"%' ORDER BY newstime DESC";
		//sql = "SELECT * FROM r_news WHERE newstitle like '%中国%' ORDER BY newstime DESC";
		
		manager.connectDB();
		rs = manager.executeSearch(sql);
		while (rs.next())
		{
			News news = new News();
			news.setNewsid(rs.getInt("newsid"));
			news.setNewstitle(rs.getString("newstitle"));
			news.setNewstype(rs.getString("newstype"));
			news.setSummary(rs.getString("summary"));
			news.setThumbnail_url(rs.getString("thumbnail_url"));
			news.setNewstime(rs.getString("newstime"));
			list.add(news);
		}
		manager.closeDB();
		return list;
	}
	public ArrayList<News> getNewsList(int startnewsid, int count)
			throws SQLException
	{
		ArrayList<News> list = new ArrayList<News>();
		sql = "SELECT r_news.*,r_category.newstype FROM r_news  INNER JOIN r_category ON r_news.cid=r_category.cid WHERE r_news.cid=4 or r_news.cid=5 or r_news.cid=6 ORDER BY newstime DESC LIMIT ?,?";
		Object[] params = new Object[]{ startnewsid, count };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		while (rs.next())
		{
			News news = new News();
			news.setNewsid(rs.getInt("newsid"));
			news.setNewstitle(rs.getString("newstitle"));
			news.setNewstype(rs.getString("newstype"));
			news.setSummary(rs.getString("summary"));
			news.setThumbnail_url(rs.getString("thumbnail_url"));
			news.setNewstime(rs.getString("newstime"));
			list.add(news);
		}
		manager.closeDB();
		return list;
	}
}
