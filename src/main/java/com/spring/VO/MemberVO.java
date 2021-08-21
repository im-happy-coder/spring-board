package com.spring.VO;

import java.sql.Timestamp;

public class MemberVO {

	private String uid;
	private String pwd;
	private String username;
	private String email;
	private Timestamp regdate;
	private Timestamp updateDate;
	
	@Override
	public String toString() {
		return "MemberVO[uid="+uid+", pwd="+pwd+", username="+username+", regdate="+regdate+", updateDate="+updateDate
				+", getUid()="+getUid()+", getPwd()="+getPwd()+", getEmail()="+getEmail()+", getUpdate()="+getUpdateDate()
				+", getClass()="+getClass()+", hashCode()="+hashCode()+", toString()="+super.toString()+"]";
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
