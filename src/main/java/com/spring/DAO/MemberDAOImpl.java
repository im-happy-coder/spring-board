package com.spring.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.VO.MemberVO;

//DAO의 구현 객체
//@Service밑에 있는 놈이 Repository는 DAO를 스프링에 인식시키기 위한 어노테이션이다.
@Repository
public class MemberDAOImpl implements MemberDAO{
	
	//memberMapper.xml을 namespace를 상수로 이용한 방법
	private static final String namespace = "com.spring.MemberMapper";
	
	//빈을 주입-- Autowired대신 Inject를 사용
	@Inject
	private SqlSession sqlSession;
	
	
	@Override
	public String getTime() {
		//selectOne(Mapper.xml의 id값)
		return sqlSession.selectOne("getTime");
		
		//memberMapper.xml을 namespace를 이용한 방법
//		return sqlSession.selectOne(namespace+".getTime");
	}
	
	
	@Override
	public void inserMember(MemberVO mvo) {
		sqlSession.insert("insertMember", mvo);
		
		//memberMapper.xml을 namespace를 상수로 이용한 방법
//		sqlSession.insert(namespace+".insertMember",mvo);
	}
}
