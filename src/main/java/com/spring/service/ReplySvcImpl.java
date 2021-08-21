package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.DAO.ReplyDAO;
import com.spring.VO.PageCriteria;
import com.spring.VO.ReplyVO;

@Service
public class ReplySvcImpl implements ReplySvc {
	
	@Inject
	private ReplyDAO rdao;
	
	
	@Override
	public void inputReply(ReplyVO rvo) throws Exception {
		rdao.write(rvo);
	}
	
	@Override
	public List<ReplyVO> replyList(Integer bid) throws Exception {
		return rdao.relist(bid);
	}
	
	@Override
	public void modifyReply(ReplyVO rvo) throws Exception {
		rdao.modify(rvo);
	}

	@Override
	public void delReply(Integer rebid) throws Exception {
		rdao.redelete(rebid);
	}
	
	@Override
	public List<ReplyVO> replyListPage(Integer bid, PageCriteria pCri) throws Exception {
		return rdao.reListPage(bid, pCri);
	}

	@Override
	public int reCount(Integer bid) throws Exception {
		return rdao.reCount(bid);
	}
}
