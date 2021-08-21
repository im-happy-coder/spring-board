package com.spring.start;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.DAO.MemberDAO;
import com.spring.VO.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {
	
	
	@Inject //스프링에서 자동으로 객체를 만듬
	private MemberDAO memberDAO;
	
	@Test
	public void testTime() throws Exception {
		System.out.println(memberDAO.getTime());
	}
	
	@Test
	public void testInserMember() throws Exception {
		MemberVO mVo = new MemberVO();
		mVo.setUid("test3");
		mVo.setPwd("test1234");
		mVo.setUsername("홍길동");
		mVo.setEmail("testemail");
		memberDAO.inserMember(mVo);
	}
}
