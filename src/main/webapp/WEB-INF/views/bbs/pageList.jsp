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
                              <c:forEach items="${list}" var="bvo">
	                              <tr>
	                                  <td>${bvo.bid}</td>
	                                  <td><a href="/start/bbs/readPage${pagingMaker.makeURI(pagingMaker.cri.page)}&bid=${bvo.bid}">${bvo.subject}</a></td>
	                                  <td>${bvo.writer}</td>
	                                  <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${bvo.regdate}"/></td>
	                                  <td class="numeric">${bvo.hit}</td>
	                              </tr>
	                           </c:forEach>   
                              </tbody>
                          </table>
                          </section>
                  </div><!-- /content-panel -->
                  <! -- BUTTONS GROUP -->
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
						  <a href="pageList${pagingMaker.makeURI(pagingMaker.startPage - 1)}"> 
							  <button type="button" class="btn btn-theme03">◀</button>
						  </a>
						</c:if>
						
						<c:forEach begin="${pagingMaker.startPage}" end="${pagingMaker.endPage}" var="pNum">
						<%--   <a href="pageList?page=${pNum}&numPerPage=${pagingMaker.numPerPage}"> 
								이 방식대신 스프링에서 제공하는 UriComponents 기능을 이용--%>
						<a href="pageList${pagingMaker.makeURI(pNum)}">
						  	<button type="button" 
						  		class="<c:out value="${pagingMaker.cri.page == pNum ? 'btn btn-theme':'btn btn-default'}"/>">${pNum}</button>
					      </a>
						</c:forEach>
						
						  <c:if test="${pagingMaker.next && pagingMaker.endPage > 0}">
							 <a href="pageList${pagingMaker.makeURI(pagingMaker.endPage + 1)}"> 
							 	<button type="button" class="btn btn-theme03">▶</button>
						  	</a>
						  </c:if>
						  
						  
					  	  <c:if test="${pagingMaker.next && pagingMaker.endPage > 0}">
							 <a href="pageList${pagingMaker.makeURI(finalEndPage)}"> 
							 	<button type="button" class="btn btn-theme03">▶▶</button>
						  	</a>
						  </c:if>
						</div>      				
      				</div><!-- /showback -->
               </div><!-- /col-lg-4 -->		
		  	</div><!-- /row -->

		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <script>
      	var result = '${result}';
      	
      	if(result == 'success'){
      		alert("정상 처리 되었습니다!!!");
      	}
      	
      	//first페이지로 이동
      	function goPage() { location.href="pageList?page=${pagingMaker.cri.page = 1}&numPerPage=${pagingMaker.cri.numPerPage}"; }

      </script>
      
<%@include file="../include/footer.jsp" %>