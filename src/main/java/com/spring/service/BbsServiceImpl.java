package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.DAO.BbsDAO;
import com.spring.VO.BbsVO;
import com.spring.VO.FindCriteria;
import com.spring.VO.PageCriteria;

//DAO와 Controller의 중간역할(연결고리)
@Service //스프링에서 bean으로 인식하기 위해서 사용
public class BbsServiceImpl implements BbsService {
	
	@Inject
	private BbsDAO bdao;
	
	@Override
	public void write(BbsVO bvo) throws Exception {
		bdao.insert(bvo);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BbsVO read(Integer bid) throws Exception {
		bdao.boardHit(bid);
		return bdao.read(bid);
	}
	
	@Override
	public void modfiy(BbsVO bvo) throws Exception {
		bdao.update(bvo);
	}
	
	@Override
	public void remove(Integer bid) throws Exception {
		bdao.delete(bid);
	}
	
	@Override
	public List<BbsVO> list() throws Exception {
		return bdao.list();
	}
	
	@Override
	public List<BbsVO> listCriteria(PageCriteria pCria) throws Exception {
		return bdao.listCriteria(pCria);
	}
	
	@Override
	public int listCountData(PageCriteria pCria) throws Exception {
		return bdao.countData(pCria);
	}
	
	@Override
	public List<BbsVO> listFind(FindCriteria findCri) throws Exception {
		return bdao.listFind(findCri);
	}
	
	@Override
	public int findCountData(FindCriteria findCri) throws Exception {
		return bdao.findCountData(findCri);
	}
	
	
	
}
