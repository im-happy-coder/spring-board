package com.spring.VO;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PagingMaker {
	private int totalData; //전체 데이터 갯수
	private int startPage; //페이지목록의 시작번호
	private int endPage;  //페이지목록의 끝번호
	private boolean prev; //이전 버튼을 나타내는 부울 값
	private boolean next; //다음 버튼
	private int finalEndPage; //마지막 페이지
	private int displayPageNum = 10; //하단에 나타내는 시작번호, 끝번호의 수
	private PageCriteria cri;
	
	public void setCri(PageCriteria cri) {
		this.cri = cri;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		getPagingData();
	}
	public int getFinalEndPage() {
		finalEndPage = (int)(Math.ceil(totalData / (double)cri.getNumPerPage()));
		return finalEndPage;
	}
	public void setFinalEndPage(int finalEndPage) {
		this.finalEndPage = finalEndPage;
	}
	//시작페이지, 끝페이지 구하기
	private void getPagingData() {
		//Math.ceil함수는 double형이므로 소수점으로 받아야한다.
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		finalEndPage = (int)(Math.ceil(totalData / (double)cri.getNumPerPage()));
		if(endPage > finalEndPage) {
			endPage = finalEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage*cri.getNumPerPage() >= totalData ? false : true;
	}//getPageingData()
	
	/*
	 * double형변환 하는 이유 : 소수점이하 계산을 위함
	 * i = 3/100
	 * system.out.println(i); ====> 0
	 * i = (int)3.0 / 100
	 * system.out.println(i); ====> 33
	 * 
	 * double i = 3.0 / 100.0
	 * system.out.println(i); ====> 0.03
	 * 
	 * double i = 3 / 100.0
	 * system.out.println(i); ====> 0.03
	 * 
	 * double i = 100/(double)3;
	 * system.out.println(i); ====> 33.33333333
	 * */
	
	
	//uri
	public String makeURI(int page) {
		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.queryParam("page", page)
				.queryParam("numPerPage", cri.getNumPerPage())
				.build();
		return uriComponents.toUriString();
	}
	
	
	public String makeFind(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("numPerPage", cri.getNumPerPage())
				.queryParam("findType", ((FindCriteria)cri).getFindType())
				.queryParam("keyword", ((FindCriteria)cri).getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalData() {
		return totalData;
	}

	public PageCriteria getCri() {
		return cri;
	}
	

	
}
