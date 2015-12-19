package com.raincent.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FavDAO {

	SqlManager manager;
	String sql="";
	ResultSet rs;
	public FavDAO() throws ClassNotFoundException, IOException {
		manager=SqlManager.createInstance();
	}
	public ArrayList<Integer> getFavlist(int uid)
			throws SQLException, ClassNotFoundException, IOException
	{
		
		ArrayList<Integer> fav1=new ArrayList<Integer>();
		
		sql="SELECT newsid from r_fav where uid=?";
		Object[] params = new Object[]{ uid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);	
		while (rs.next()) {
			fav1.add(Integer.valueOf(rs.getInt("newsid")));
		}
		manager.closeDB();
		return fav1;
	}
	public int addfav(int uid, int newsid) throws SQLException
	{
		sql = "INSERT INTO r_fav (uid,newsid) VALUES (?,?)";
		Object[] params = new Object[] { uid,newsid };
		manager.connectDB();
		int status = manager.executeUpdate(sql, params);
		manager.closeDB();
        return status;
	}
	public int delfav(int uid, int newsid) throws SQLException
	{
		sql = "delete from r_fav where uid=? && newsid=?";
		Object[] params = new Object[] { uid,newsid };
		manager.connectDB();
		int status = manager.executeUpdate(sql, params);
		manager.closeDB();
        return status;
	}
	public boolean wtfav(int uid, int newsid) throws SQLException
	{
		sql = "select newsid from r_fav where uid=?";
		Object[] params = new Object[] { uid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		ArrayList<Integer> fav1=new ArrayList<Integer>();
		
		while (rs.next()) {
			fav1.add(Integer.valueOf(rs.getInt("newsid")));
		}
	
		boolean status = false;
		for (int i : fav1) {
			if (i==newsid) {
				 status=true;
				 break;
			}
		}
		manager.closeDB();
        return status;
	}
}
