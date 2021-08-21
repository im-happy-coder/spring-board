<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- JQuery 1.8 -->
<script src="<%=request.getContextPath()%>/resources/assets/js/jquery-1.8.3.min.js"></script>
<style type="text/css">
	#modifyDiv {
		width : 500px;
		height : 100px;
		background-color : gray;
		position: absolute;
		top: 20%;
		left: 30%;
		z-index: 100;
		padding: 20px;
	}
</style>
</head>
<body>
	
	<h3>Reply REST + Ajax Test</h3>
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
	
	<div align="center">
		<div>
			작성자 : <input type="text" name="replyer" id="writer" />
		</div>
		
		<div>
			댓글 : <input type="text" name="replyContent" id="addReContent" maxlength="50" size="50" />
		</div>
		<div>
			<button id="submitBtn">댓글 작성</button>
		</div>
	</div>
	
	<h4>댓글 리스트</h4>
	<ul id="reply">
	</ul>
	
	<ul class="pgeNumList">
	</ul>

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
			
			//화면에 뿌려줄 댓글 리시트
			var str = "";
			$(data.reList).each(function(){
				str += "<li data-rebid='"+this.rebid+"'class='reList'>"
					+ this.rebid + " | " + this.replyContent
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
	
	//댓글 리스트를 가져오기
	function reListAll(){
		//getJSON함수의 ""은 경로가 들어간다. function()은 콜백함수이다. 선택자가 없으면 .이 들어간다.
		$.getJSON("/replies/selectAll/"+bid, function(data){
			//bid에 303이 2개가 있으면 2가 나옴
			//console.log(data.length);
			//each는 for문
			var str = "";
			$(data).each(function(){
				str += "<li data-rebid='"+this.rebid+"'class='reList'>"
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
		
		//ajax를 이용해서 JSON형태로 전송
		//X-HTTP-Method-Override는 보통의 웹브라우저는 get과 post만 지원하는 브라우저가 있기 때문에 이것을 사용하면 지원가능하다.
		//web.xml에서 HiddenHttpMethodFilter를 등록한다.
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
	

	//댓글 수정 버튼 클릭 시 이벤트
	//id가 reply인 클릭시 이벤트를 주는데 클래스가 reList이면서 button인것
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
</script>
</body>
</html>