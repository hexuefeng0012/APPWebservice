package com.raincent.web.model;

/**
 * User Model
 * @author tong
 */
public class User {
	private int    uid;
	private String usename;
	private String pwd;
	private String avator;
	private String nick;
	private int    coin;
	private String email;
	private String login_date;

    //public constructors
    public User() {
		super();
	}

    public User(int uid, String usename, String pwd,int coin, String avator, String nick,String email,String login_date) {
		super();
		this.uid = uid;
		this.usename = usename;
		this.pwd = pwd;
		this.email = email;
		this.login_date = login_date;
		this.coin = coin;
		this.avator = avator;
		this.nick = nick;
	}

    //getters and setters
    public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

    public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}

    public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    public String getLogin_date() {
		return login_date;
	}
	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}

    public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
	}

    public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

    public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	
}
