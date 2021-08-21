package com.spring.service;

import java.util.List;

import com.spring.VO.BbsVO;
import com.spring.VO.FindCriteria;
import com.spring.VO.PageCriteria;

public interface BbsService {
	public void write(BbsVO bvo) throws Exception;
	
	public BbsVO read(Integer bid) throws Exception;
	
	public void modfiy(BbsVO bvo) throws Exception;
	
	public void remove(Integer bid) throws Exception;
	
	public List<BbsVO> list() throws Exception;
	
	public List<BbsVO> listCriteria(PageCriteria pCria) throws Exception;

	public int listCountData(PageCriteria pCria) throws Exception;
	
	public List<BbsVO> listFind(FindCriteria findCri) throws Exception;
	
	public int findCountData(FindCriteria findCri) throws Exception;
	
}
