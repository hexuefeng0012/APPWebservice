package com.raincent.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import com.raincent.web.util.TextUtility;


/**
 * 对r_comment数据表进行访问
 * @author DELL
 */
public class CommentDAO
{
	SqlManager manager;
	String sql = "";
	ResultSet rs;
	
	public CommentDAO() throws IOException, ClassNotFoundException
	{
		manager = SqlManager.createInstance();
	}
	
	/**
	 * 获取回复信息
	 * @param qid 问题编号
	 * @param startcmid 评论的起始ID
	 * @param count 返回数量
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String,String>> getComments(int qid,int startcmid,int count) throws SQLException
	{
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		sql = "SELECT cmid,qid,createdat,content,subject_img,nick,avator from r_comment,r_user where r_comment.uid=r_user.uid&&qid=? order by createdat DESC limit ?,?";
		Object[] params = new Object[]{ qid, startcmid, count };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);

		while (rs.next())
		{
			HashMap<String,String> comment = new HashMap<String,String>();
			
            comment.put("cmid",rs.getInt("cmid")+"");
			comment.put("qid",rs.getInt("qid")+"");
			comment.put("avator",rs.getString("avator"));
			comment.put("nick",rs.getString("nick"));
			try {
				comment.put("createdat",TextUtility.formatDate(rs.getString("createdat")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			comment.put("content",rs.getString("content"));
			comment.put("subject_img",rs.getString("subject_img"));
			list.add(comment);
		}
		manager.closeDB();
		return list;
	}
	
	/**
	 * 保存新评论
	 * @param uid 
	 * @param nid 新闻编号
	 * @param ptime 发表时间
	 * @param content 内容
	 * @throws SQLException
	 */
	public int addComment(int qid,int uid, String img,String content) throws SQLException
	{
		sql = "INSERT INTO r_comment (qid,uid,subject_img,content) VALUES (?,?,?,?)";
		Object[] params = new Object[] { qid,uid,img,content};
		manager.connectDB();
        int status=0;
		if (1==manager.executeUpdate(sql, params)){
		    sql = "UPDATE r_question SET reply_count=reply_count+1 where qid=?";
		    params = new Object[] { qid };
            status = manager.executeUpdate(sql, params);
        }
		manager.closeDB();
        return status;
	}
	
	/**
	 * 获取指定问题评论数量
	 * @param qid
	 * @return
	 * @throws SQLException
	 */
	public long getSpecifyNewsCommentsCount(int qid) throws SQLException
	{
		long count = 0;
		sql = "select count(cmid) as count from r_comment where qid=?";
		Object[] params = new Object[]{ qid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next())
		{
			count = rs.getLong("count");
		}
		return count;
	}
    
	/**
	 * 获取用户的所有回复
	 * @param uid 
	 * @param count 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String,String>> getAllComment(int uid,int start,int count) throws SQLException
	{
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		sql = "SELECT cmid,qid,createdat,content,subject_img,nick,avator from r_comment,r_user where r_comment.uid=r_user.uid&&r_comment.uid=? order by createdat DESC limit ?,?";
		Object[] params = new Object[]{ uid, start,count };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);

		while (rs.next())
		{
			HashMap<String,String> comment = new HashMap<String,String>();
			
            comment.put("cmid",rs.getInt("cmid")+"");
			comment.put("qid",rs.getInt("qid")+"");
			comment.put("avator",rs.getString("avator"));
			comment.put("nick",rs.getString("nick"));
			try {
				comment.put("createdat",TextUtility.formatDate(rs.getString("createdat")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			comment.put("content",rs.getString("content"));
			comment.put("subject_img",rs.getString("subject_img"));
			list.add(comment);
		}
		manager.closeDB();
		return list;
    }

    public static void main(String args[]){
        try{
            CommentDAO cd = new CommentDAO();
            //System.out.println(cd.getComments(1,0,2));
            System.out.println(cd.addComment(1,1,"xxxx","sssssssssssssss"));
            //System.out.println(cd.getSpecifyNewsCommentsCount(2));
            //System.out.println(cd.getAllComment(1,5));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
