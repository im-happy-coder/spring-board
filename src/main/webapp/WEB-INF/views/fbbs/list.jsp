<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>



<%@include file="../include/header.jsp" %>
<!--main content start-->
      <section>
          <section class="wrapper">
          	<h3><i class="fa fa-angle-right"></i> 리스트 페이지</h3>
		  		<div class="row mt">
			  		<div class="col-lg-12">
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i> 게시판 목록</h4>
                          <section id="unseen">
	                          <div align="right">
	                          <span class="col-md-12">
	                          <select name="findType">
								  <option value="N"
								  	<c:out value="${ fCri.findType == null ? 'selected' : ''}"/>>---------</option>
								  <option value="S"
								  	<c:out value="${fCri.findType == 'S' ? 'selected' : '' }"/>>제목</option>
								  <option value="C"
								  	<c:out value="${fCri.findType == 'C' ? 'selected' : '' }"/>>내용</option>
								  <option value="W"
								  	<c:out value="${fCri.findType == 'W' ? 'selected' : '' }"/>>작성자</option>
								  <option value="SC"
								  	<c:out value="${fCri.findType == 'SC' ? 'selected' : '' }"/>>제목+내용</option>
								  <option value="CW"
								  	<c:out value="${fCri.findType == 'CW' ? 'selected' : '' }"/>>내용+작성자</option>
								  <option value="SCW"
								  	<c:out value="${fCri.findType == 'SCW' ? 'selected' : '' }"/>>제목+내용+작성자</option>
								</select>
								<input type="text" name="keyword" id="findword" value="${fCri.keyword }"/>
								<button id="findBtn">검색</button>
								</span>
							</div>
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>
	                              <tr>
	                                  <th>글번호</th>
	                                  <th>제목</th>
	                                  <th>작성자</th>
	                                  <th>작성일</th>
	                                  <th class="numeric">조회수</th>
	                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${list}" var="bvo" varStatus="i">
	                              <tr>
	                                  <td>${(pagingMaker.totalData - ((pagingMaker.cri.page-1)*pagingMaker.cri.numPerPage))-i.index}</td>
	                                  <td><a href="/fbbs/readPage${pagingMaker.makeFind(pagingMaker.cri.page)}&bid=${bvo.bid}">${bvo.subject}</a></td>
	                                  <td>${bvo.writer}</td>
	                                  <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${bvo.regdate}"/></td>
	                                  <td class="numeric">${bvo.hit}</td>
	                              </tr>
	                           </c:forEach>   
                              </tbody>
                          </table>
                          	<div align="left">
      							<button id="writeBtn" class="btn btn-primary">글쓰기</button>
      						</div>  
                          </section>
                  </div><!-- /content-panel -->
                  <!-- BUTTONS GROUP -->
      				<div class="showback" align="center">
						<div class="btn-group">
					
					<%-- 	
						처음 페이지로 이동하기가 어려움 
						해결 : 자바스크립트를 이용해서 사용하면 되지만 makeURI(UriComponentsBuilder)를 사용할수 없음
						
						<a href="pageList${pagingMaker.makeURI(pagingMaker.startPage = 1)}"> 
						 <button type="button" class="btn btn-theme03" onclick="goPage()" >◀◀</button>
						</a>
						--%>
					<c:if test="${pagingMaker.prev}">
						   <button type="button" class="btn btn-theme03" onclick="goPage()" >◀◀</button>
						</c:if>
						
						<c:if test="${pagingMaker.prev}">
						  <a href="list${pagingMaker.makeFind(pagingMaker.startPage - 1)}"> 
							  <button type="button" class="btn btn-theme03">◀</button>
						  </a>
						</c:if>
						
						<c:forEach begin="${pagingMaker.startPage}" end="${pagingMaker.endPage}" var="pNum">
						<%--   <a href="pageList?page=${pNum}&numPerPage=${pagingMaker.numPerPage}"> 
								이 방식대신 스프링에서 제공하는 UriComponents 기능을 이용--%>
						<a href="list${pagingMaker.makeFind(pNum)}">
						  	<button type="button" 
						  		class="<c:out value="${pagingMaker.cri.page == pNum ? 'btn btn-theme':'btn btn-default'}"/>">${pNum}</button>
					      </a>
						</c:forEach>
						
						  <c:if test="${pagingMaker.next && pagingMaker.endPage > 0}">
							 <a href="list${pagingMaker.makeFind(pagingMaker.endPage + 1)}"> 
							 	<button type="button" class="btn btn-theme03">▶</button>
						  	</a>
						  </c:if>
						  
						  
					  	  <c:if test="${pagingMaker.next && pagingMaker.endPage > 0}">
							 <a href="list${pagingMaker.makeFind(finalEndPage)}"> 
							 	<button type="button" class="btn btn-theme03">▶▶</button>
						  	</a>
						  </c:if>
						</div>    
      				</div><!-- /showback -->
               </div><!-- /col-lg-4 -->		
		  	</div><!-- /row -->

		</section><!--/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <script>
      	var result = '${result}';
      	
      	if(result == 'success'){
      		alert("정상 처리 되었습니다!!!");
      	}
      	
      	//first페이지로 이동
      	function goPage() { location.href="list?page=${pagingMaker.cri.page = 1}&numPerPage=${pagingMaker.cri.numPerPage}"; }
      </script>
    	 <script type="text/javascript">
    	 	$(document).ready(function(){
    	 		$('#findBtn').on("click", function(e){
					self.location = "list"+"${pagingMaker.makeURI(1)}"
						+"&findType="+$("select option:selected").val()
						+"&keyword="+$("#findword").val();
    	 		});
    	 		
    	 		$('#writeBtn').on("click", function(e){
    	 			self.location = "write";
    	 		});
    	 	});
    	 	//검색버튼을 눌르면 1페이지의 데이터를 검색함
 			//select밑에 option에서 selected인 항목을 검색한다.
 			// #findword는 id가 findword로 설정된 검색버튼 위에 input태그
    	 </script>
   
      
<%@include file="../include/footer.jsp" %>