package com.raincent.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.raincent.web.model.User;

/**
 * 对r_user数据表进行访问
 * 
 * @author DELL
 */
public class UserDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public UserDAO() throws IOException, ClassNotFoundException, SQLException {
		manager = SqlManager.createInstance();
	}

	/**
	 * 验证用户名和密码是否正确
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录成功 返回用户详细信息,登录失败返回无效用户
	 * @throws SQLException
	 */
	public User validate(String uname, String password) throws SQLException {
		sql = "SELECT * FROM r_user  WHERE usename=? and pwd=?";
		Object[] params = new Object[] { uname, password };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		User user = new User(-1, "", "", -1, "", "", "", "");
		if (rs.next() && 1 == rs.getRow()) {
			user.setUid(rs.getInt("uid"));
			user.setUsename(rs.getString("usename"));
			user.setPwd(rs.getString("pwd"));
			user.setEmail(rs.getString("email"));
			user.setLogin_date(rs.getString("login_date"));
			user.setAvator(rs.getString("avator"));
			user.setNick(rs.getString("nick"));
			user.setCoin(rs.getInt("coinnum"));
		}
		manager.closeDB();
		return user;
	}

	/**
	 * 检查用户名是否已被注册
	 * 
	 * @param uname
	 * @return
	 * @throws SQLException
	 */
	public boolean checkExist(String uname) throws SQLException {
		sql = "SELECT * FROM r_user  WHERE usename=?";
		Object[] params = new Object[] { uname };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next() && 1 == rs.getRow()) {
			manager.closeDB();
			return true;
		}
		manager.closeDB();
		return false;
	}

	/**
	 * 获得用户信息
	 * 
	 * @param uid
	 * @return 返回用户的详细信息
	 * @throws SQLException
	 */
	public User getUser(int uid) throws SQLException {

		sql = "SELECT * FROM r_user WHERE uid=?";
		Object[] params = new Object[] { uid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		User user = new User(-1, "", "", -1, "", "", "", "");
		if (rs.next()) {
			user.setUid(rs.getInt("uid"));
			user.setUsename(rs.getString("usename"));
			user.setPwd(rs.getString("pwd"));
			user.setEmail(rs.getString("email"));
			user.setLogin_date(rs.getString("login_date"));
			user.setAvator(rs.getString("avator"));
			user.setNick(rs.getString("nick"));
			user.setCoin(rs.getInt("coinnum"));
		}
		manager.closeDB();
		return user;
	}

	/**
	 * 注册新用户
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码
	 * @return 注册成功 返回用户详细信息
	 * @throws SQLException
	 */
	public User register(String uname, String password, String email)
			throws SQLException {
		sql = "INSERT INTO r_user(usename,pwd,avator,nick,email) VALUES(?,?,?,?,?)";
		String avator = "";// http://192.168.38.16:8888/web/img/cat.png
		String nickname = uname;
		Object[] params = new Object[] { uname, password, avator, nickname,
				email };
		manager.connectDB();
		User user = new User(-1, "", "", -1, "", "", "", "");
		if (1 == manager.executeUpdate(sql, params)) {
			sql = "SELECT * FROM r_user  WHERE usename=? and pwd=?";
			rs = manager.executeQuery(sql, new Object[] { uname, password });

			if (rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setUsename(rs.getString("usename"));
				user.setPwd(rs.getString("pwd"));
				user.setEmail(rs.getString("email"));
				user.setLogin_date(rs.getString("login_date"));
				user.setAvator(rs.getString("avator"));
				user.setNick(rs.getString("nick"));
				user.setCoin(rs.getInt("coinnum"));
			}
		}
		manager.closeDB();
		return user;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码
	 * @return 更新成功 返回用户详细信息
	 * @throws SQLException
	 */
	public User updateInfo(int uid, String avator, String nick, String email)
			throws SQLException {
		sql = "UPDATE r_user SET avator=?,nick=?,email=? WHERE uid=?";
		Object[] params = new Object[] { avator, nick, email, uid };
		manager.connectDB();
		User user = new User(-1, "", "", -1, "", "", "", "");
		if (1 == manager.executeUpdate(sql, params)) {
			sql = "SELECT * FROM r_user  WHERE uid=?";
			rs = manager.executeQuery(sql, new Object[] { uid });

			if (rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setUsename(rs.getString("usename"));
				user.setPwd(rs.getString("pwd"));
				user.setEmail(rs.getString("email"));
				user.setLogin_date(rs.getString("login_date"));
				user.setAvator(rs.getString("avator"));
				user.setNick(rs.getString("nick"));
				user.setCoin(rs.getInt("coinnum"));
			}
		}
		manager.closeDB();
		return user;
	}

	/**
	 * 更新金币数量
	 * 
	 * @param uid
	 * @param coin
	 * @return 更新成功 返回用户详细信息
	 * @throws SQLException
	 */
	public User updateCoin(int uid, int coin) throws SQLException {
		sql = "UPDATE r_user SET coinnum=coinnum+? WHERE uid=?";
		Object[] params = new Object[] { coin, uid };
		manager.connectDB();
		User user = new User(-1, "", "", -1, "", "", "", "");
		if (1 == manager.executeUpdate(sql, params)) {
			sql = "SELECT * FROM r_user  WHERE uid=?";
			rs = manager.executeQuery(sql, new Object[] { uid });

			if (rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setCoin(rs.getInt("coinnum"));
			}
		}
		manager.closeDB();
		return user;
	}

	/**
	 * 签到
	 * 
	 * @param uid
	 * @param date
	 * @return 更新成功 返回用户详细信息
	 * @throws SQLException
	 */
	public User updateSignIn(int uid, String dateStr) throws SQLException {
		sql = "UPDATE r_user SET login_date=? WHERE uid=?";
		Object[] params = new Object[] { dateStr, uid };
		manager.connectDB();
		User user = new User(-1, "", "", -1, "", "", "", "");
		if (1 == manager.executeUpdate(sql, params)) {
			sql = "SELECT * FROM r_user  WHERE uid=?";
			rs = manager.executeQuery(sql, new Object[] { uid });

			if (rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setLogin_date(dateStr);
			}
		}
		manager.closeDB();
		return user;
	}

	/**
	 * 更新密码
	 * 
	 * @param uid
	 * @param old_pwd
	 *            ;
	 * @param new_pwd
	 *            ;
	 * @return 更新成功 返回用户详细信息
	 * @throws SQLException
	 */
	public User updatePwd(int uid, String old_pwd, String new_pwd)
			throws SQLException {
		sql = "UPDATE r_user SET pwd=? WHERE uid=? && pwd=?";
		Object[] params = new Object[] { new_pwd, uid, old_pwd };
		manager.connectDB();
		User user = new User(-1, "", "", -1, "", "", "", "");
		if (1 == manager.executeUpdate(sql, params)) {
			sql = "SELECT * FROM r_user  WHERE uid=?";
			rs = manager.executeQuery(sql, new Object[] { uid });

			if (rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setUsename(rs.getString("usename"));
				user.setPwd(rs.getString("pwd"));
				user.setEmail(rs.getString("email"));
				user.setLogin_date(rs.getString("login_date"));
				user.setAvator(rs.getString("avator"));
				user.setNick(rs.getString("nick"));
				user.setCoin(rs.getInt("coinnum"));
			}
		}
		manager.closeDB();
		return user;
	}

	/**
	 * 获取密码
	 * 
	 * @param uid
	 * @param uname
	 * @return true/false
	 * @throws SQLException
	 */
	public String getPwd(String uname) throws SQLException {
		sql = "SELECT * FROM r_user  WHERE usename=?";
		Object[] params = new Object[] { uname };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next() && 1 == rs.getRow()) {
			return rs.getString("pwd");
		}
		manager.closeDB();
		return "";
	}
	/**
	 * 获取绑定邮箱
	 * @param uname
	 * @return
	 * @throws SQLException
	 */
	public String validateMail(String uname) throws SQLException {
		sql = "SELECT * FROM r_user  WHERE usename=?";
		Object[] params = new Object[] { uname };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next() && 1 == rs.getRow()) {
			return rs.getString("email");
		}
		manager.closeDB();
		return "";
	}
	/**
	 * 获取账号是否存在
	 * @param uname
	 * @return
	 * @throws SQLException
	 */
	public String validateAccount(String uname) throws SQLException {
		sql = "SELECT * FROM r_user  WHERE usename=?";
		Object[] params = new Object[] { uname };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next() && 1 == rs.getRow()) {
			return rs.getString("usename");
		}
		manager.closeDB();
		return "";
	}
	public static void main(String args[]) {
		try {
			UserDAO ud = new UserDAO();
			// System.out.println(ud.updateInfo(2,"tong","1234").getUsename());
			// System.out.println(ud.updateCoin(1,4).getCoin());
			System.out.println(ud.validate("Kevin", "password").getUsename());
			// System.out.println(ud.updatePwd(1,"password","123456").getPwd());
			// System.out.println(ud.validateMail("raincent@126.com"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
