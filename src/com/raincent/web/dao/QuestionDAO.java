package com.raincent.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import com.raincent.web.model.Questions;
import com.raincent.web.util.TextUtility;

/**
 * 对r_question数据表进行访问
 * 
 * @author DELL
 */
public class QuestionDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public QuestionDAO() throws IOException, ClassNotFoundException {
		manager = SqlManager.createInstance();
	}

	/**
	 * 获得问题的相关信息
	 * 
	 * @param qid
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> getQuestion(int qid) throws SQLException {
		sql = "SELECT qid,qtitle,subject_img,createdat,reply_count,nick,avator FROM r_user,r_question WHERE r_user.uid=r_question.uid&&qid=?";
		Object[] params = new Object[] { qid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		HashMap<String, String> qrs = new HashMap<String, String>();

		if (rs.next()) {
			qrs.put("qid", rs.getInt("qid") + "");
			qrs.put("qtitle", rs.getString("qtitle"));
			qrs.put("subject_img", rs.getString("subject_img"));
			try {
				qrs.put("createdat",
						TextUtility.formatDate(rs.getString("createdat")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			qrs.put("reply_count", rs.getInt("reply_count") + "");
			qrs.put("nick", rs.getString("nick"));
			qrs.put("avator", rs.getString("avator"));
		}
		manager.closeDB();
		return qrs;
	}

	/**
	 * 获取跟作者相关的所有问题
	 * 
	 * @param qid
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String, String>> getQuestionList(int uid,
			int start, int count) throws SQLException {
		sql = "SELECT qid,qtitle,subject_img,createdat,reply_count,nick,avator FROM r_user,r_question WHERE r_user.uid=r_question.uid&&r_user.uid=? order by createdat DESC limit ?,?";
		Object[] params = new Object[] { uid, start, count };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		while (rs.next()) {
			HashMap<String, String> q = new HashMap<String, String>();
			q.put("qid", rs.getInt("qid") + "");
			q.put("qtitle", rs.getString("qtitle"));
			try {
				q.put("createdat",
						TextUtility.formatDate(rs.getString("createdat")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			q.put("subject_img", rs.getString("subject_img"));
			q.put("reply_count", rs.getInt("reply_count") + "");
			q.put("nick", rs.getString("nick"));
			q.put("avator", rs.getString("avator"));
			list.add(q);
		}
		manager.closeDB();
		return list;
	}

	/**
	 * 获取所有的问题列表
	 * 
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String, String>> getAllQuestions(int type,
			int start, int count) throws SQLException {
		sql = "SELECT qid,qtitle,subject_img,createdat,reply_count,nick,avator FROM r_user,r_question WHERE r_user.uid=r_question.uid&&type=? order by createdat DESC limit ?,?";
		// sql =
		// "SELECT qid,qtitle,subject_img,createdat,reply_count,nick,avator FROM r_user,r_question WHERE type=? order by createdat DESC limit 0,?";
		Object[] params = new Object[] { type, start, count };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		while (rs.next()) {
			HashMap<String, String> q = new HashMap<String, String>();
			q.put("qid", rs.getInt("qid") + "");
			q.put("qtitle", rs.getString("qtitle"));
			try {
				q.put("createdat",
						TextUtility.formatDate(rs.getString("createdat")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			q.put("subject_img", rs.getString("subject_img"));
			q.put("reply_count", rs.getInt("reply_count") + "");
			q.put("nick", rs.getString("nick"));
			q.put("avator", rs.getString("avator"));
			list.add(q);
		}
		manager.closeDB();
		return list;
	}

	/**
	 * 插入问题条目
	 * 
	 * @param uid
	 * @param title
	 * @param img
	 * @return
	 * @throws SQLException
	 */
	public int addQuestion(int uid, String title, String img)
			throws SQLException {
		sql = "INSERT INTO r_question (uid,qtitle,subject_img) VALUES (?,?,?)";
		Object[] params = new Object[] { uid, title, img };
		manager.connectDB();
		int status = manager.executeUpdate(sql, params);
		manager.closeDB();
		return status;
	}

	public static void main(String args[]) {
		// try{
		// QuestionDAO qd = new QuestionDAO();
		// System.out.println(qd.getQuestionList(1,2));
		// System.out.println(qd.getQuestion(2));
		// System.out.println(qd.getAllQuestions(1,2));
		// System.out.println(qd.addQuestion(1,"We are here!","http://image.baidu.com/channel?c=%E5%8A%A8%E6%BC%AB&t=%E5%85%A8%E9%83%A8&s=1"));
		// }catch(Exception e){
		// e.printStackTrace();
		// }
	}
}
