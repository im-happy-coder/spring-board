package com.spring.start;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.DAO.BbsDAO;
import com.spring.VO.BbsVO;
import com.spring.VO.FindCriteria;
import com.spring.VO.PageCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BbsDAOTest {
	
	@Inject
	private BbsDAO bdao;
	private static Logger logger = LoggerFactory.getLogger(BbsDAOTest.class);
	
	@Test
	public void testFind() throws Exception {
		FindCriteria cri = new FindCriteria();
		cri.setPage(1);
		cri.setFindType("CW");
		cri.setKeyword("홍길동");
		
		logger.info("******************글 목록 테스트 출력***************");
		List<BbsVO> list = bdao.listFind(cri);
		
		for(BbsVO bvo : list) {
			logger.info(bvo.getBid() + ":" + bvo.getSubject());
		}
		logger.info("****************테스트 Data갯수 출력*****************");
		logger.info("CountData : " + bdao.findCountData(cri));
	}
//	@Test
//	public void insertTest() throws Exception {
//		BbsVO bvo = new BbsVO();
//		bvo.setSubject("테스트 제목입니다.");
//		bvo.setContent("테스트 내용입니다.");
//		bvo.setWriter("홍길동");
//		bdao.insert(bvo);
//	}

//	@Test
//	public void readTest() throws Exception {
//		logger.info(bdao.read(1).toString());
//	}
	
//	@Test
//	public void updateTest() throws Exception {
//		BbsVO bvo = new BbsVO();
//		bvo.setBid(1);
//		bvo.setSubject("subject변경테스트");
//		bvo.setContent("content변경테스트");
//		bdao.update(bvo);
//	}
	
//	@Test
//	public void deleteTest() throws Exception {
//		bdao.delete(3);
//	}
	
	
//	@Test
//	public void listTest() throws Exception {
//		logger.info(bdao.list().toString());
//	}

//	@Test
//	public void listPageTest() throws Exception {
//		//5페이지의 내용을 페이징처리 테스트
//		int page = 5;
//		List<BbsVO> list = bdao.listPage(page);
//		for(BbsVO bbsVO : list) {
//			logger.info(bbsVO.getBid() + ":" + bbsVO.getSubject());
//		}
//	}
	

//	@Test
//	public void listCriteriaTest() throws Exception {
//		PageCriteria pcri = new PageCriteria();
//		pcri.setPage(3);
//		pcri.setNumPerPage(15);
//		
//		List<BbsVO> list = bdao.listCriteria(pcri);
//		
//		for(BbsVO bbsVO : list) {
//			logger.info(bbsVO.getBid()+ ":" + bbsVO.getSubject());
//		}
//	}

	
	//UriComponentsBuilder를 이용하는 방법 : org.springframework.web.utill 패키지에 있음.
//	@Test
//	public void uriTest() throws Exception {
//		UriComponents uriComponents = UriComponentsBuilder
//				.newInstance()
//				.path("/start/bbs/read")
//				.queryParam("bid",	100)
//				.queryParam("numPerPage", 20)
//				.build();
//		logger.info("/start/bbs/read?bid=100&numPerPage=20");
//		logger.info(uriComponents.toString());
//		
//		
//	}
	

//	@Test
//	public void uriTest2() throws Exception {
//		UriComponents uriComponents = UriComponentsBuilder
//				.newInstance()
//				.path("/{moduel}/{page}")
//				.queryParam("bid", 100)
//				.queryParam("numPerPage", 20)
//				.build()
//				.expand("start/bbs", "read"); //path의 {boduel}과 {page}에 들어갈내용
//		logger.info("/start/bbs/read?bid=100&numPerPage=20");
//		logger.info(uriComponents.toString());
//	}
}
