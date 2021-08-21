<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
    
<%@ include file="../include/header.jsp" %>
    <!-- main -->
     <!--main content start-->
      <section>
          <section class="wrapper">
          	<h3><i class="fa fa-angle-right"></i> 게시글 조회 페이지</h3>
          	<!-- role 속성: 
          		.HTML5에서 새롭게 추가된 속성 
          		.ARIA(Accessible Rich Internet Application)라는 HTML5의 상세 규격 중 하나
          		.시각장애인을 위해 만들어진 기능(시각장애인이 컴퓨터의 리더기를 사용해서 웹 페이지를 읽을 때 "해당 부분이 form이다"라고 정의해주는 것
          		.role은 필수적인 요소는 아니지만 화면용 리더기를 이용해야하는 사람들에게도 불편함이 없는 사이트를 제공하고자 이 속성을 이용함
          		-->
          	<form role="form" method="post">
          		<input type="hidden" name="bid" value="${bbsVO.bid}" />
          		<input type="hidden" name="page" value="${fCri.page}" />
          		<input type="hidden" name="numPerPage" value="${fCri.numPerPage }" />
          		<input type="hidden" name="findType" value="${fCri.findType }" />
          		<input type="hidden" name="keyword" value="${fCri.keyword}" />
          	</form>
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  <form class="form-horizontal style-form" method="post">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i>조회 내용</h4>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">제목</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="subject" value="${bbsVO.subject }" readonly>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">내용</label>
                              <div class="col-sm-10">
                                      <textarea class="form-control" name="content" rows="4" readonly>${bbsVO.content }</textarea>                              
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">작성자</label>
                              <div class="col-sm-10">
                                  <input type="text" name="writer" class="form-control" value="${bbsVO.writer }" readonly>
                              </div>
                          </div>
                               <div class="form-group">
		           			<div class="col-sm-12" align="center"> 
		           			<!-- button 타입을 type="submit"으로 하면 ajax와 연동에러가 발생
		           					 Request method 'POST' not supported 예외가 발생한다.  -->
				          		<button type="button" id="btn_modify" class="btn btn-primary">수정하기</button>&nbsp;&nbsp;
				          		<button type="button" class="btn btn-danger">삭제하기</button>&nbsp;&nbsp;
				          		<button type="button" class="btn btn-info" id="pglist">목록으로</button>
			          		</div>
                          </div><!-- form - pannel --> 
		          	 	</form>
		          	  </div>  
       	  	<!-- 댓글 -->
         	  	<div class="row mt">
         	  		<div class="col-lg-12">
         	  			<div class="form-panel">
         	  				<h3>>댓글</h3>
         	  				<div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">작성자</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="replyer" id="writer">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">댓글</label>
                              <div class="col-sm-10">
                            	  <input type="text" name="replyContent" id="addReContent" class="form-control" />
                              </div>
                          </div>
                          <button id="submitBtn" type="submit" class="btn btn-primary">댓글 작성</button>
                          
                          <!-- 댓글 수정 시 화면 -->
                          <div id="modifyDiv" style="display:none">
							<span class="title-dialog"></span>번 댓글
							<div>
								수정할 내용 : <br/>
								<input type="text" id="reContent" size="50" />
							</div>
							<div>
								<button type="button" id="reModifyBtn">수정</button>
								<button type="button" id="reDelBtn">삭제</button>
								<button type="button" id="closeBtn">닫기</button>
							</div>
						</div>
                          
                      	<!-- 댓글 목록/ 페이지 번호 목록 -->
                          	<div class="form-group">
	                          	<ul id="reply">                    
								</ul>
								<ul class="pgeNumList">
								</ul>
							</div>
		         		</div>
         	  		</div>
         	  	</div>
	          	  <script type="text/javascript">
	          	  	//$는 jQuery의 의미
	          	  	//현재 이 문서가 준비 되면 다음 function을 실행한다.라는 뜻
	          	  	$(document).ready(function(){
	          	  		var frmObj = $("form[role='form']"); //form를 선택하는데 role이 form인 폼만 선택하겠다
	          	  		console.log("폼태그입니다.");
	          	  		
	          	  		/*$("#btn_modify").on("click", function(){ //id가 btn_modify인것을 on(이벤트처리) 하겠다.
	          	  			frmObj.attr("action", "/start/bbs/modify"); //frmObj.attr에 action을 /bbs/modify로 하겠다.
	          	  			frmObj.attr("method", "get");
	          	  			frmObj.submit();
	          	  		});*/
	          	  		
	          	  		
	          	 	 	$("#btn_modify").on("click", function(){ //id가 btn_modify인것을 on(이벤트처리) 하겠다.
	         	  			frmObj.attr("action", "/fbbs/modifyPage"); //frmObj.attr에 action을 /bbs/modify로 하겠다.
	         	  			frmObj.attr("method", "get");
	         	  			frmObj.submit();
	         	  		});
	          	  		
	          	  		/*$(".btn-danger").on("click", function(){
	          	  			frmObj.attr("action", "/start/bbs/delete"); 
	          	  		//method선언을 안하면 post방식이 됨 왜냐면 컨트롤러에서 post를 지정하였고 form에서 method방식이 post이기때문
	          	  			frmObj.submit();
	          	  		});*/
	         		 
	          	  		$(".btn-danger").on("click", function(){
	          	  		//위에서 폼태그에서 hidden으로 page,numPerPage, bid를 넘겨 줘야하므로 method방식을 get으로 바꿔야함
	        	  		//	frmObj.attr("method","get");
	          	  			frmObj.attr("action", "/fbbs/delPage"); 
	        	  			frmObj.submit();
	        	  		});
	          	  		
	          	  		$("#pglist").on("click", function(){
	          	  			//self.location="/start/bbs/list";
	          	  			frmObj.attr("method","get");
	          	  			frmObj.attr("action", "/fbbs/list");
	          	  			frmObj.submit();
	          	  		});
	          	  	});
	          	  </script>
	   	<script type="text/javascript">
		   	function refreshMemList(){
				location.reload();
			}
	   		var bid = 303;
			//댓글 페이징
			getPageNum(1);
			function getPageNum(page){
				$.getJSON("/replies/"+bid+"/"+page, function(data){
					console.log(data.reList.length);
					
					//화면에 뿌려줄 댓글 리스트
					var str = "";
					$(data.reList).each(function(){
						str += "<li data-rebid='"+this.rebid+"'class='reList'>"
							+ this.rebid + " | " + this.replyer + " | "+ this.replyContent
							+ "<button>수정</button>"
							+ "</li>";
					});
					$("#reply").html(str);
					showPageNum(data.pagingMaker);
				});
			}//getPageNum()
			
			function showPageNum(pagingMaker){
				var str = "";
				if(pagingMaker.prev){
					str += "<li><a href='"+(pagingMaker.startPage-1)+"'>◀</a></li>";
				}
				
				for(var i=pagingMaker.startPage, end=pagingMaker.endPage; i<=end; i++){
					str += "<a href='"+i+"'><button type='button'>"+i+"</button></a>";
				}
				
				if(pagingMaker.next){
					str += "<li><a href='"+(pagingMaker.endPage+1)+"'>▶</a></li>";
				}
				
				$(".pgeNumList").html(str);
			}//showPageNum()
			
			//페이징 하단 번호 페이지를 얻어오기 위한 함수
			var rePage = 1;
			$(".pgeNumList").on("click", "a button", function(e){
				//댓글리스트 하단에서 페이지번호 클릭시 이동할때 <a>태그의 화면전환이 되지 않은 메서드
				e.preventDefault();
				rePage = $(this).parent().attr("href");
				getPageNum(rePage);
			});
			//getJSON함수의 ""은 경로가 들어간다. function()은 콜백함수이다. 선택자가 없으면 .이 들어간다.
			//bid에 303이 2개가 있으면 2가 나옴
			//console.log(data.length);
			//each는 for문
			//ajax를 이용해서 JSON형태로 전송
			//X-HTTP-Method-Override는 보통의 웹브라우저는 get과 post만 지원하는 브라우저가 있기 때문에 이것을 사용하면 지원가능하다.
			//web.xml에서 HiddenHttpMethodFilter를 등록한다.
			//댓글 리스트를 가져오기
			function reListAll(){
				$.getJSON("/replies/selectAll/"+bid, function(data){
					var str = "";
					$(data).each(function(){
						str += "<li data-rebid='"+this.rebid+"'class='reList'>"
							+ 
							+ this.rebid + " | " + this.replyContent
							+ "<button>수정</button>"
							+ "</li>";
					});
					$("#reply").html(str);
				});
				refreshMemList();
			} //reListAll()
			
			//댓글 작성
			$("#submitBtn").on("click", function(){
				var reWriter = $("#writer").val();
				var reContent = $("#addReContent").val();
				
				$.ajax({
					type : 'post',
					url : '/replies',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : 'text',
					data : JSON.stringify({
						bid : bid,
						replyer : reWriter,
						replyContent : reContent
					}),
					success : function(result){
						if(result == 'success'){
							//alert("댓글 등록 성공");
							reListAll();
						}
					}
				});
				
			});
			//id가 reply인 클릭시 이벤트를 주는데 클래스가 reList이면서 button인것
			//댓글 수정 버튼 클릭 시 이벤트
			$("#reply").on("click", ".reList button", function(){
				var li = $(this).parent();
				var rebid = li.attr("data-rebid");
				var reContent = li.text();
				//alert("댓글번호 :"+rebid+" 수정할 댓글 내용:"+reContent);
				$(".title-dialog").html(rebid);
				$("#reContent").val(reContent);
				$("#modifyDiv").show("slow");
			});
			
			//댓글 수정처리
			$("#reModifyBtn").on("click", function(){
				var rebid = $(".title-dialog").html();
				var reContent = $("#reContent").val();
				
				$.ajax({
					type : 'put',
					url : '/replies/'+rebid,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "put"
					},
					data:JSON.stringify({replyContent:reContent}),
					dataType : 'text',
					success : function(result){
						console.log("result:"+result);
						if(result=='success'){
							alert("댓글 수정 성공!!!");
							$("#modifyDiv").hide("slow");
							reListAll();
						}
					}
				});//ajax
			});
			
			//댓글 삭제
			$("#reDelBtn").on("click", function(){
				var rebid = $(".title-dialog").html();
				var reContent = $("#reContent").val();
				
				$.ajax({
					type : 'delete',
					url : '/replies/'+rebid,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					dataType : 'text',
					success : function(result){
						console.log("result:"+result);
						if(result=='success'){
							alert("댓글 삭제 성공!!!");
							$("#modify-dialog").hide("slow");
							reListAll();
						}
					}
				});//ajax
			});
				
			//댓글창 닫기
			$("#closeBtn").on("click", function(){
				$("#modifyDiv").hide("slow");
			});
		
		</script>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->          	
		</section><!--/wrapper -->
      </section><!-- /MAIN CONTENT -->
    
<%@include file="../include/footer.jsp" %>