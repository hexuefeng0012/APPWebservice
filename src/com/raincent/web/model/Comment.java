package com.raincent.web.model;

/**
 * Comment Model
 * @author tongy
 */
public class Comment
{
	private int cmid;
	private int qid;
    private int uid;
	private String createdat;
	private String content;
	private String subject_img;

    //public constructors
	public Comment()
	{
		super();
	}

	public Comment(int cmid, int qid, int uid,
			String createdat, String content, 
            String subject_img) {
		super();
		this.cmid = cmid;
		this.qid = qid;
		this.uid = uid;
		this.createdat = createdat;
		this.content = content;
		this.subject_img = subject_img;
	}

    //public getters and setters
	public int getCmid() {
		return cmid;
	}
	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject_img() {
		return subject_img;
	}
	public void setSubject_img(String subject_img) {
		this.subject_img = subject_img;
	}

}
