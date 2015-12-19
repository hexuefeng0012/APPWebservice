package com.raincent.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.raincent.web.model.Banner;

public class BannerDAO {
	
	SqlManager manager;
	String sql="";
	ResultSet rs;
	public BannerDAO() throws ClassNotFoundException, IOException {
		manager=SqlManager.createInstance();
	}
	
	public ArrayList<Banner> getBanner(int startbid,int count) throws SQLException{
		
		ArrayList<Banner> list=new ArrayList<Banner>();
		sql="select * from r_banner order by newstime DESC LIMIT ?,?";
		Object[] params=new Object[]{startbid,count};
		manager.connectDB();
		rs=manager.executeQuery(sql, params);
		while (rs.next()) {
			Banner banner=new Banner();
			banner.setBid(rs.getInt("bid"));
			banner.setPic_name(rs.getString("pic_name"));
			banner.setPic_url(rs.getString("pic_url"));
			list.add(banner);
		}
		manager.closeDB();
		return list;		
	}
	public Banner getBanner(int bid) throws SQLException
	{
		sql = "SELECT * FROM r_banner WHERE bid=?";
		Object[] params = new Object[]{ bid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		Banner banner=new Banner();
		if (rs.next())
		{
			banner.setBid(rs.getInt("bid"));
			banner.setAuthor(rs.getString("author"));
			banner.setContent(rs.getString("content"));
			banner.setNews_url(rs.getString("news_url"));
			banner.setNewstime(rs.getString("newstime"));
			banner.setNewstitle(rs.getString("newstitle"));
			banner.setPic_url(rs.getString("pic_url"));
			banner.setSummary(rs.getString("summary"));
		}
		manager.closeDB();
		return banner;
	}
}
