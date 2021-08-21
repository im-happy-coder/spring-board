package com.spring.start;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.VO.PageCriteria;
import com.spring.VO.PagingMaker;
import com.spring.VO.ReplyVO;
import com.spring.service.ReplySvc;


//SqlSession에 사용될 메소드명이 같으면 예외 발생
//댓글 등록 POST
//댓글 처리는 REST방식(view, JSP없이 오직 데이터만 가지고 사용함),
//REST Client에서 URL방식을 지정할 때 REST방식에서 댓글 등록은 = POST, 댓글 수정 PUT 또는 PATCH, 댓글 리스트 가져오기 GET
//ResponseEntity는 HttpStatus 즉 400번대 에러코드 300번대 에러코드 등
//@RequestBody는 전송된 데이터를 객체로 변환시켜준다. @ModelAttributes와 비슷함
@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Inject
	private ReplySvc rsvc;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> input(@RequestBody ReplyVO rvo) {
		
		ResponseEntity<String> resEntity = null;
		
		try {
			rsvc.inputReply(rvo);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			//ResponseEntity가 String으로 제네릭타입을 선언해서 String으로 반환해야함. e.getMessage()가 String타입을 반환하기 때문에 사용가능. 
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	//댓글 리스트 가져오기 GET
	@RequestMapping(value="/selectAll/{bid}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bid") Integer bid) {
		
		ResponseEntity<List<ReplyVO>> resEntity = null;
		
		try {
			resEntity = new ResponseEntity<List<ReplyVO>>(rsvc.replyList(bid), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	
	//댓글 수정
	//method가 배열타입이여서 JSON을 변환할려면 @RequestBody를 사용
	@RequestMapping(value="/{rebid}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> modify(@PathVariable("rebid") Integer rebid, 
				@RequestBody ReplyVO rvo) {
		ResponseEntity<String> resEntity = null;
		
		try {
			rvo.setRebid(rebid);
			rsvc.modifyReply(rvo);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	//댓글 삭제 DELETE
	@RequestMapping(value="/{rebid}", method= RequestMethod.DELETE)
	public ResponseEntity<String> reDel(@PathVariable("rebid") Integer rebid) {
		ResponseEntity<String> resEntity = null;
		
		try {
			rsvc.delReply(rebid);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		//예외 발생시 ReplyDAOImpl 클래스에서 sqlSession.delete를 update로 바꿔주자
		return resEntity;
	}
	
	//댓글 페이징 처리
	@RequestMapping(value="/{bid}/{page}", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reListPage(@PathVariable("bid") Integer bid,
															@PathVariable("page") Integer page) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		try {
		PageCriteria pCri = new PageCriteria();
		pCri.setPage(page);
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);
		
		Map<String, Object> reMap = new HashMap<>();
		List<ReplyVO> reList = rsvc.replyListPage(bid, pCri);
		
		reMap.put("reList", reList);
		int reCount = rsvc.reCount(bid);
		
		pagingMaker.setTotalData(reCount);
		
		reMap.put("pagingMaker", pagingMaker);
		
		resEntity = new ResponseEntity<Map<String,Object>>(reMap, HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
}
