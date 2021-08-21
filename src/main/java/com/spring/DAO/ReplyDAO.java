package com.spring.DAO;

import java.util.List;

import com.spring.VO.PageCriteria;
import com.spring.VO.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> relist(Integer bid) throws Exception;
	
	public void write(ReplyVO rvo) throws Exception;
	
	public void modify(ReplyVO rvo) throws Exception;
	
	public void redelete(Integer rebid) throws Exception;
	
	public List<ReplyVO> reListPage(Integer bid, PageCriteria pCri) throws Exception;
	
	public int reCount(Integer bid) throws Exception;
}