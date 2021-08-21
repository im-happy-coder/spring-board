package com.spring.start;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.VO.BbsVO;
import com.spring.VO.FindCriteria;
import com.spring.VO.PagingMaker;
import com.spring.service.BbsService;
@Controller
@RequestMapping("/fbbs/*")
public class FindController {
	private static final Logger logger = LoggerFactory.getLogger(FindController.class);
	
	@Inject
	private BbsService bsvc;
	
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public void list(@ModelAttribute("fCri") FindCriteria fCri, Model model) throws Exception {
		logger.info(fCri.toString());
		
		//FindCriteria클래스가 PageCriteria클래스를 상속받고 있으므로 fCri를 매개변수로 쓸 수 있다.
//		model.addAttribute("list", bsvc.listCriteria(fCri));
		//검색
		model.addAttribute("list", bsvc.listFind(fCri));
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(fCri);
		
		//검색
		pagingMaker.setTotalData(bsvc.findCountData(fCri));
		
		model.addAttribute("pagingMaker", pagingMaker);
		model.addAttribute("finalEndPage", pagingMaker.getFinalEndPage());
		
		//글번호 동적할당
//		int dNum = (pagingMaker.getTotalData() - (pagingMaker.getCri().getPage() - 1) * pagingMaker.getCri().getNumPerPage());
//		model.addAttribute("dNum", dNum);
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
//	@GetMapping(value="/readPage")
	public void readPage(@RequestParam("bid") int bid, @ModelAttribute("fCri") FindCriteria fCri, Model model) throws Exception {
		model.addAttribute(bsvc.read(bid));
	}
	
	@RequestMapping(value="/delPage", method=RequestMethod.POST)
	public String delPage(@RequestParam("bid") int bid, FindCriteria fCri, RedirectAttributes reAttr) throws Exception {
		bsvc.remove(bid);
	
		reAttr.addAttribute("page", fCri.getPage());
		reAttr.addAttribute("numPerPage", fCri.getNumPerPage());
		reAttr.addAttribute("findType", fCri.getFindType());
		reAttr.addAttribute("keyword", fCri.getKeyword());
		reAttr.addFlashAttribute("result", "success");
		return "redirect:/fbbs/list";
	}
	
	//수정 조회 페이지
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modify(int bid, @ModelAttribute("fCri") FindCriteria fCri, Model model) throws Exception {
		model.addAttribute(bsvc.read(bid));
	}
	
	//DB 수정 처리
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPOST(BbsVO bvo, FindCriteria fCri, RedirectAttributes reAttr) throws Exception {
		logger.info(fCri.toString());
		bsvc.modfiy(bvo);
		
		reAttr.addAttribute("page", fCri.getPage());
		reAttr.addAttribute("numPerPage", fCri.getNumPerPage());
		reAttr.addAttribute("findType", fCri.getFindType());
		reAttr.addAttribute("keyword", fCri.getKeyword());
		reAttr.addFlashAttribute("result", "success");
		
		return "redirect:/fbbs/list";
	}
	
	//글쓰기 페이지
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void writeGET() throws Exception {
		logger.info("writerGET() 호출......");
	}
	
	//DB글 입력 처리
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePOST(BbsVO bvo, RedirectAttributes reAttr) throws Exception {
		logger.info("writePOST()호출 .....");
		
		bsvc.write(bvo);
		reAttr.addFlashAttribute("result", "success");
		return "redirect:/fbbs/list";
	}
	

}