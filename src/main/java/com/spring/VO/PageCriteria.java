package com.spring.VO;

public class PageCriteria {
	private int page;
	private int numPerPage;
	
	
	public PageCriteria() {
		this.page = 1;
		this.numPerPage = 10;
	}
	
	public void setPage(int Page) {
		if(page<=0) {
			this.page=1;
			return;
		}
		this.page = Page;
	}
	
	public void setNumPerPage(int numPerPage) {
		if(numPerPage <= 0 || numPerPage > 100) {
			this.numPerPage = 10;
			return;
		}
		this.numPerPage = numPerPage;
	}
	
	public int getPage() {
		return page;
	}

	public int getStartPage() {
		return (this.page -1 )*numPerPage;
	}
	
	public int getNumPerPage() {
		return numPerPage;
	}
	@Override
	public String toString() {
		return "PageCriteria[page="+page+","+"numPerPage"+numPerPage+"]";
	}
	
}
