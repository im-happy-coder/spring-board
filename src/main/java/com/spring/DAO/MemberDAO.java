package com.spring.DAO;

import com.spring.VO.MemberVO;

public interface MemberDAO {
	
	public String getTime(); //시간을 가져올 메서드
	public void inserMember(MemberVO mvo);
	
}
