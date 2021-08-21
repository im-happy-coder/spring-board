package com.spring.start;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.DAO.ReplyDAO;
import com.spring.VO.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyDAOTest {
	
	@Inject
	private ReplyDAO rdao;
	
	private static Logger logger= LoggerFactory.getLogger(ReplyDAOTest.class);
	
	@Test
	public void writeTest() throws Exception {
		ReplyVO rvo = new ReplyVO();
		rvo.setBid(301);
		rvo.setReplyContent("replyÅ×½ºÆ®1");
		rvo.setReplyer("replyerTest1");
		
		rdao.write(rvo);
	}
	
}
