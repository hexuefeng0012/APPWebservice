package com.raincent.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.raincent.web.model.Category;

/**
 * 对r_category进行数据访问
 * @author DELL
 */
public class CategoryDAO
{
	SqlManager manager;
	String sql = "";
	ResultSet rs;
	
	public CategoryDAO() throws IOException, ClassNotFoundException
	{
		manager = SqlManager.createInstance();
	}
	
	/**
	 * 获取新闻类型
	 * @param startTid 起始类型编号
	 * @param count 数量
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Category> getTypes(int startcid,int count) throws SQLException
	{
		ArrayList<Category> list = new ArrayList<Category>();
		sql = "select * from r_category order by cid LIMIT ?,?";
		Object[] params = new Object[]{startcid,count};
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		while(rs.next())
		{
			Category category = new Category(rs.getInt("cid"), rs.getString("newstype"));
			list.add(category);
		}
		manager.closeDB();
		return list;
	}
	
	public void add(Category category)
	{
	}
	
	public void update(Category category)
	{
	}
	
	public void delete(int tid)
	{
	}
	
	public ArrayList<Category> getAllTypes()
	{
        return new ArrayList<Category>();
	}
}
