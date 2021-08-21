package com.spring.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.VO.PageCriteria;
import com.spring.VO.ReplyVO;

//저장소 역할
@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> relist(Integer bid) throws Exception {
		return sqlSession.selectList("relist", bid);
	}
	
	@Override
	public void write(ReplyVO rvo) throws Exception {
		sqlSession.insert("write", rvo);
	}
	
	@Override
	public void modify(ReplyVO rvo) throws Exception {
		sqlSession.update("modify", rvo);
	}
	
	@Override
	public void redelete(Integer rebid) throws Exception {
		sqlSession.update("redelete", rebid);
	}
	
	@Override
	public List<ReplyVO> reListPage(Integer bid, PageCriteria pCri) throws Exception {
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("bid", bid);
		reMap.put("pCri", pCri);
		return sqlSession.selectList("reListPage", reMap);
	}
	
	@Override
	public int reCount(Integer bid) throws Exception {
		return sqlSession.selectOne("reCount", bid);
	}
}
