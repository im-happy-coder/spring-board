package com.spring.VO;

import java.sql.Date;

public class ReplyVO {
	private Integer rebid;
	private Integer bid;
	private String replyContent;
	private String replyer;
	private Date regdate;
	private Date udatedate;
	public Integer getRebid() {
		return rebid;
	}
	public void setRebid(Integer rebid) {
		this.rebid = rebid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUdatedate() {
		return udatedate;
	}
	public void setUdatedate(Date udatedate) {
		this.udatedate = udatedate;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [rebid=" + rebid + ", bid=" + bid + ", replyContent=" + replyContent + ", replyer=" + replyer
				+ ", regdate=" + regdate + ", udatedate=" + udatedate + "]";
	}
	
	
	
}
