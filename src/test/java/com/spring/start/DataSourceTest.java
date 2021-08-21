package com.spring.start;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class DataSourceTest {
	
	//@Inject는 스프링에서 자동으로 인스턴스변수를 스프링에서 자동으로 주입해준다.
	@Inject
	private DataSource dataSource;
	
	@Test
	public void testConnection() throws Exception {
		try(Connection conn = dataSource.getConnection()){
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
