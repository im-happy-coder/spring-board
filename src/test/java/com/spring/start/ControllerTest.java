package com.spring.start;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:/src/main/webapp/WEB-INF/spring/**/*xml"})
public class ControllerTest {
	
	//톰캣없이 컨트롤러 테스트
	private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
	
	
	@Inject
	private WebApplicationContext webAppCtx;
	
	//브라우저에서 요청과 응답을 하는 객체를 의미
	private MockMvc mockMvc;
	
	//테스트하기전에 이 메소드를 먼저 실행한다는 의미
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppCtx).build();
		logger.info("setup()호출......");
	}
	
	@Test
	public void testController() throws Exception {
		//응답을 가상으로 요청
		//MockMvcRequestBuilders.get은 get방식,,, MockMvcRequestBuilders.post는 post방식
		mockMvc.perform(MockMvcRequestBuilders.get("/controller")); 
	}
}
