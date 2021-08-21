package com.spring.start;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MybatisTest {
	
	@Inject
	private SqlSessionFactory sqlfactory;
	
	@Test
	public void testSqlFactory() {
		System.out.println(sqlfactory);
	}
	
	@Test
	public void sessionTest() throws Exception {
		try(SqlSession sqlSessionTemplate = sqlfactory.openSession()){
			System.out.println(sqlSessionTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
