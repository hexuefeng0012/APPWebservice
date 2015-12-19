package com.raincent.web.model;

/**
 * Question Model
 * @author tongy
 */
public class Questions {
	private int qid;
	private String qtitle;
	private int uid;
	private int type;
	private int reply_count;
	private String createdatt;
	private String subject_img;

    //public constructors
	public Questions()
	{
		super();
	}

	public Questions(int qid, String qtitle, int uid, int type,
		    int reply_count, String createdatt, String subject_img) {
		super();
		this.qid = qid;
		this.qtitle = qtitle;
		this.uid = uid;
		this.type = type;
		this.reply_count = reply_count;
		this.createdatt = createdatt;
		this.subject_img = subject_img;
	}

    //public getters and setters
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type ;
	}

	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	public String getCreatedatt() {
		return createdatt;
	}
	public void setCreatedatt(String createdatt) {
		this.createdatt = createdatt;
	}

	public String getSubject_img() {
		return subject_img;
	}
	public void setSubject_img(String subject_img) {
		this.subject_img = subject_img;
	}

}
