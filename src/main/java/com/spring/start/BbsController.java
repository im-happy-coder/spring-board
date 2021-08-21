package com.spring.start;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.VO.BbsVO;
import com.spring.VO.PageCriteria;
import com.spring.VO.PagingMaker;
import com.spring.service.BbsService;

@Controller
@RequestMapping("/bbs/*") 
//클래스 앞에 RequestMapping은 공통 경로
//@Model는 키와 값으로 데이터를 저장한다. 즉, 데이터를 전달하고자 할때 사용한다. 
//GET방식은 url를 통해서 데이터를 전송하니까 외부에 노출이 되어 되는 경우에 사용한다 입력페이지, 조회 페이지 
//많은 정보를 입력하는 경우에 사용하고 url상에 정보를 보여주지 않도록하기 위해 사용한다. 외부에 노출되면 안되는 경우는 POST방식을 이용한다.
//String은 직접 뷰를 보여주겠다
//public String writePost(BbsVO bvo, Model model) throws Exception { 
//RedirectAttributes객체는 리다이렉트 시점에 한번만 사용되는 데이터를 전송할 수 있는 addFlashAttribute()를 지원한다.(url데이터를숨김)
public class BbsController {
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Inject
	private BbsService bsvc;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(Model model) throws Exception {
		logger.info("글목록 가져오기............");
		model.addAttribute("list", bsvc.list());
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void writeGET(BbsVO bvo, Model model) throws Exception {
		logger.info("write GET....");
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePost(BbsVO bvo, RedirectAttributes reAttr) throws Exception { 
		logger.info("write POST....");
		logger.info(bvo.toString());
		bsvc.write(bvo);
		//model.addAttribute("result", "success");
		//1번만 사용하기 때문에 url에 데이터가 노출되지 않음
		reAttr.addFlashAttribute("result", "success");
		
		return "redirect:/bbs/list";
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void read(@RequestParam("bid") int bid, @ModelAttribute("pCri") PageCriteria pCri, Model model) throws Exception {
		
		
		model.addAttribute(bsvc.read(bid));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyGET(@RequestParam("bid") int bid, @ModelAttribute("pCri") PageCriteria pCri, Model model) throws Exception {
		model.addAttribute(bsvc.read(bid));
		
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPOST(BbsVO bvo, PageCriteria pCri , RedirectAttributes reAttr) throws Exception {
		logger.info("modifyPOST().........");
		bsvc.modfiy(bvo);
		
		reAttr.addAttribute("page", pCri.getPage());
		reAttr.addAttribute("numPerPage", pCri.getNumPerPage());
		
		reAttr.addFlashAttribute("result", "success");
		return "redirect:/bbs/pageList";
	}
		

	@RequestMapping(value="/delPage", method=RequestMethod.GET)
	public String delete(@RequestParam("bid") int bid, PageCriteria pCri, RedirectAttributes reAttr) throws Exception {
		
		bsvc.remove(bid);
		reAttr.addAttribute("page",pCri.getPage());
		reAttr.addAttribute("numPerPage", pCri.getNumPerPage());
		
		reAttr.addFlashAttribute("result", "success");
		return "redirect:/bbs/pageList";
	}
	
	//addAttribute는 키값을 사용하지 않았을 경우 key는 클래스의 이름을 자동으로 소문자로 시작해서 사용한다.
	//read메서드의 리턴값 BbsVO의 클래스가 bbsVO로 key가 된다.
	
	//@RequestParam : Servlet에서 request.getParameter()와 유사한 기능이다.
	//Servlet의 request는 HttpServletRequest
	//@RequestParam과 HttpServletRequest의 차이점 : Servlet의 request는 HttpServletRequest는 문자열, 숫자, 날짜 등의 형변환 여부
	//@RequestMapping(value="/delete", method=RequestMethod.POST)
	//public String delete(@RequestParam("bid") int bid, RedirectAttributes reAttr) throws Exception {
	//	
	//	bsvc.remove(bid);
	//	reAttr.addFlashAttribute("result", "success");
	//	return "redirect:/bbs/list";
	//}
	
	
	//수정조회
//	@RequestMapping(value="/modify", method=RequestMethod.GET)
//	public void modifyGET(int bid, Model model) throws Exception {
//		model.addAttribute(bsvc.read(bid));
//	}
	
	//수정처리
//	@RequestMapping(value="/modify", method=RequestMethod.POST)
//	public String modifyPOST(BbsVO bvo, RedirectAttributes reAttr) throws Exception {
//		logger.info("modifyPOST().........");
//		bsvc.modfiy(bvo);
//		
//		reAttr.addFlashAttribute("result", "success");
//		return "redirect:/bbs/list";
//	}
	
	
	//페이징처리는 GET방식
	//스프링에서 자동으로 메서드의 파라미터의 클래스 생성자를 자동으로 생성하여 실행한다. 
	//즉 PageCriteria클래스의 생성자를 보면 page=1, numPerPage=10으로 생성자에 값을 저장해서 스프링으로 인해 자동으로 실행되는것
	@RequestMapping(value="/PageListTest", method=RequestMethod.GET)
	public void pageListTest(PageCriteria pCria, Model model) throws Exception {
		logger.info("pageListTest()...............");
		model.addAttribute("list", bsvc.listCriteria(pCria));
	}
	
	@RequestMapping(value="/pageList", method=RequestMethod.GET)
	public void pageList(PageCriteria pCria, Model model) throws Exception {
		logger.info(pCria.toString());
		model.addAttribute("list", bsvc.listCriteria(pCria));
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCria);
//		pagingMaker.setTotalData(155);
		pagingMaker.setTotalData(bsvc.listCountData(pCria));
		model.addAttribute("pagingMaker", pagingMaker);

		model.addAttribute("finalEndPage", pagingMaker.getFinalEndPage());
	}
	
}
