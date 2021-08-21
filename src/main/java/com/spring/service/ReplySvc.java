package com.spring.service;

import java.util.List;

import com.spring.VO.PageCriteria;
import com.spring.VO.ReplyVO;

public interface ReplySvc {
	public List<ReplyVO> replyList(Integer bid) throws Exception;
	
	public void inputReply(ReplyVO rvo) throws Exception;
	
	public void modifyReply(ReplyVO rvo) throws Exception;
	
	public void delReply(Integer rebid) throws Exception;
	
	public List<ReplyVO> replyListPage(Integer bid, PageCriteria pCri) throws Exception;
	
	public int reCount(Integer bid) throws Exception;
}
