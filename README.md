# Spring  댓글 게시판 프로젝트 (개인 프로젝트)
## 목적 ##
+ 웹 개발자의 필수요소 Spring
+ Spring프레임워크 사용 경험을 극대화
+ Spring프레임워크의 기술 습득
+ 웹 개발자로써 한 단계 더 성장하기 위한 프레임워크에 대한 이해와 기술 연마
------------
# 개발 환경 및 사용한 언어
> Front-End
+ Bootstrap v3.2.0
+ jQuery v1.10.2
+ CSS 3
+ HTML 5
> Back-End
+ Spring v4.3.1.RELEASE
+ Maven v2.9
+ MyBatis v3.2.8
+ JDK v1.8
+ Tomcat v9.0
+ MySQL v8.0
------------
# 개발 기간 - 2021.07.20 ~ 2021.08.18총 22일 (주말 제외)
- 07.20~07.21 | 개발 환경 셋팅 | jUnit, MySQL, Tomcat, MyBatis, Bootstrap
- 07.21~07.23 | 동작 테스트 | jUnit Mysql 연결, Tomcat없이 Controller 테스트, DAO 동작 테스트
- 07.26~07.30 | DB 생성 | 테이블 생성 및 XML Mapper 작성, SQLSessionTemplate설정
- 08.02~08.06 | 반응형 웹페이지 뷰 구현 하기 | 게시글 리스트, 등록, 수정, 삭제 구현
- 08.09~08.11 | 페이징 처리 | 페이징 처리 계산하기, 객체 만들기, 컨트롤러 구현
- 08.12~08.13 | 검색 기능 구현 | 검색 처리 jUnit, XML Mapper, 수정, 삭제 처리
- 08.16~08.18 | REST 방식의 댓글 구현 | REST + Ajax를 이용한 댓글 수정, 삭제, 등록
------------
# 설계구조 및 구축 흐름도
![설계구조](https://user-images.githubusercontent.com/77142806/130358790-bbcfa44e-efca-48da-9409-8c82df453b0b.PNG)
------------
------------
# 작동 화면 미리 보기
> 게시글 쓰기   
![글쓰기](https://user-images.githubusercontent.com/77142806/130359474-ed4a98cf-e6dc-4d3b-abec-8f584512fdd0.gif)


> 게시글 수정   
![글수정](https://user-images.githubusercontent.com/77142806/130359464-408b4edb-ce0a-434e-9039-1d17f874f75d.gif)


> 게시글 삭제   
![글삭제](https://user-images.githubusercontent.com/77142806/130359462-b2b3102e-ec1a-49b9-a73f-fdb96c09e121.gif)


> 검색 기능   
![검색](https://user-images.githubusercontent.com/77142806/130359457-7d705851-24f9-4d46-bd80-aaf5116982ca.gif)


> 댓글 쓰기, 수정, 삭제   
![댓글수정삭제](https://user-images.githubusercontent.com/77142806/130359477-4d92e480-d721-4e80-b07b-53f92b32913a.gif)


------------
# Back-End

## 페이징 처리
![페이징1](https://user-images.githubusercontent.com/77142806/130359455-23930ae9-9c2a-4d5e-b0c8-3e5264db6b8d.PNG)
![페이징2](https://user-images.githubusercontent.com/77142806/130359449-9aef0ddc-ac26-4db5-a5dc-119e84c81d1c.PNG)
> 페이징 VO(DTO) 클래스
```
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
	
```
```
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
```
> 페이징처리 view화면(JSP) JSTL태그 라이브러리 이용   
```
<c:if test="${pagingMaker.prev}">
 <button type="button" class="btn btn-theme03" onclick="goPage()" >◀◀</button>
</c:if>
						
<c:if test="${pagingMaker.prev}">
	 <a href="list${pagingMaker.makeFind(pagingMaker.startPage - 1)}"> <button type="button" class="btn btn-theme03">◀</button> </a>
</c:if>
						
<c:forEach begin="${pagingMaker.startPage}" end="${pagingMaker.endPage}" var="pNum">
	<a href="list${pagingMaker.makeFind(pNum)}">
		<button type="button" class="<c:out value="${pagingMaker.cri.page == pNum ? 'btn btn-theme':'btn btn-default'}"/>">${pNum}</button>
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
```

> 페이징 mapper.xml

```
<!-- paging -->
<select id="listPage" resultType="BbsVO">
	<![CDATA[
		select
			bid, subject, content, writer, regdate, hit
		from
			tbl_board
		where bid > 0
			order by bid desc, regdate desc
		limit #{page}, 10
	]]>
</select>

<select id="listCriteria" resultType="BbsVO">
	<![CDATA[
		select 
			bid, subject, content, writer, regdate, hit
		from
			tbl_board
		where bid > 0
			order by bid desc, regdate desc
		limit #{startPage}, #{numPerPage}
	]]>
</select>
```


## 검색 기능
> view(JSP) select태그를 사용하여 옵션 제공


```
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
</script>
```
> 검색 시 DB처리 mapper.xml (Mybatis표현식 사용)

```
<sql id="findSql">
	<if test="findType != null">
			<if test="findType == 'S'.toString()">
				and subject like CONCAT('%',#{keyword},'%')
			</if>
			<if test="findType =='C'.toString()">
				and content like CONCAT('%', #{keyword}, '%')
			</if>
			<if test="findType =='W'.toString()">
				and writer like CONCAT('%', #{keyword}, '%')
			</if>
			<if test="findType =='SC'.toString()">
				and (subject like CONCAT('%', #{keyword}, '%') 
				or content like CONCAT('%', #{keyword}, '%'))
			</if>
			<if test="findType =='CW'.toString()">
				and (content like CONCAT('%', #{keyword}, '%') 
				or writer like CONCAT('%', #{keyword}, '%'))
			</if>
			<if test="findType =='SCW'.toString()">
				and (subject like CONCAT('%', #{keyword}, '%')
				or content like CONCAT('%', #{keyword}, '%')
				or writer like CONCAT('%', #{keyword}, '%'))
			</if>
		</if>
</sql>
<select id="listFind" resultType="BbsVO">
	<![CDATA[
		select * from tbl_board
		where bid > 0
		]]>
		<include refid="findSql"></include>
	<![CDATA[
		order by bid desc
		limit #{startPage}, #{numPerPage}			
	]]>
</select>
	
<select id="findCountData" resultType="int">
	<![CDATA[
		select count(bid) from tbl_board where bid > 0
		]]>
		<include refid="findSql"></include>
</select>
```

## 게시판 CRUD
> 게시판 CRUD DAO, DAO Implements(구현) 클래스

```
public void insert(BbsVO bvo) throws Exception;
	
	public BbsVO read(Integer bid) throws Exception;
	
	public void update(BbsVO bvo) throws Exception;
	
	public void delete(Integer bid) throws Exception;

	public void boardHit(int bno) throws Exception;
		
	public List<BbsVO> list() throws Exception;
	
	public List<BbsVO> listPage(int page) throws Exception;
}


@Repository
public class BbsDAOImpl implements BbsDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insert(BbsVO bvo) throws Exception {
		sqlSession.insert("insert", bvo);
	}
	
	@Override
	public BbsVO read(Integer bid) throws Exception {
		return sqlSession.selectOne("read", bid);
	}
	
	
	@Override
	public void update(BbsVO bvo) throws Exception {
		sqlSession.update("update", bvo);
	}
	
	@Override
	public void delete(Integer bid) throws Exception {
		sqlSession.delete("delete", bid);
	}
	
	@Override
	public List<BbsVO> list() throws Exception {
		return sqlSession.selectList("list");
	}
```

> 게시판 CRUD Service클래스와 Service Implements(구현) 클래스


```
public interface BbsService {
	public void write(BbsVO bvo) throws Exception;
	
	public BbsVO read(Integer bid) throws Exception;
	
	public void modfiy(BbsVO bvo) throws Exception;
	
	public void remove(Integer bid) throws Exception;
	
	public List<BbsVO> list() throws Exception;
}

@Service
public class BbsServiceImpl implements BbsService {
	
	@Inject
	private BbsDAO bdao;
	
	@Override
	public void write(BbsVO bvo) throws Exception {
		bdao.insert(bvo);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BbsVO read(Integer bid) throws Exception {
		bdao.boardHit(bid);
		return bdao.read(bid);
	}
	
	@Override
	public void modfiy(BbsVO bvo) throws Exception {
		bdao.update(bvo);
	}
	
	@Override
	public void remove(Integer bid) throws Exception {
		bdao.delete(bid);
	}
	
	@Override
	public List<BbsVO> list() throws Exception {
		return bdao.list();
	}
	
```

> 게시판 CRUD mapper.xml

```
<insert id="insert">
		insert into tbl_board(subject, content, writer) values(#{subject},#{content},#{writer})
</insert> 

<select id="read" resultType="BbsVO">
	select bid, subject, content, writer, regdate, hit from tbl_board where bid=#{bid}
</select>

<update id="update">
	update tbl_board set subject=#{subject}, content=#{content}, writer=#{writer}, regdate=now() where bid=#{bid}
</update>

<delete id="delete">
	delete from tbl_board where bid=#{bid}	
</delete>

<update id="boardHit" parameterType="int">
	update tbl_board set hit = hit + 1 where bid = #{bid} 
</update>

<select id="list" resultType="com.spring.VO.BbsVO">
<![CDATA[
	select bid, subject, content, writer, regdate, hit from 
		tbl_board where bid > 0 order by bid desc, regdate desc 
	]]>
</select>
```



------------
# 주요 이슈
1. 가장 자주 겪은 이슈는 버전 호환입니다. 스프링 프레임워크의 버전이나 자바 JDK의 버전이
pom.xml에 추가한 jUnit, mybatis 기능의 버전과 호환되지 못하는 경우 가 있어 
호환 가능한 버전을 찾아 버전 업을 하는 방법을 찾았습니다.

2. 그다음으로 해결하기 어려웠던 문제는 한글 인코딩 문제였습니다. 
인코딩 같은 경우 서버 내의 한글 인코딩, 클라이언트 안에서의 인코딩, 개발 툴 이클립스 안에서 
인코딩 중 원인을 찾는데 어려웠고 서버+클라이언트+개발 툴 전부 한글 인코딩을 해주어서 해결했습니다.

3. 스프링 빈 생성 에러 디버그 Error creating bean with name “” 이 문제 같은 경우 
Mybatis의 mapper.xml 파일에 오타가 있었던 것을 확인하였습니다. 
하지만 해결하는데 많은 시간이 발생한 이유는 xml의 파일 같은 경우
자동완성 기능이나 오류코드를 인식해 줄 수 없기 때문에 
많은 코드 중에서 알파벳 딱 한자 틀린 것을 찾는 데에 시간이 오래 걸렸습니다.

4. HttpRequestMethodNotSupportedException: Request method 'POST' not supported는
Jsp페이지에 <button> 태그 중에서 버튼 클릭 시 이벤트를 처리할 때 POST 방식이 아닌 
GET방식으로 지정하여 발생한 문제였습니다. 
Controller에서 @RequestMapping을 GET방식으로 바꾸어 해결 할 수 있었습니다.

------------
# 느낀점
1. 이번 스프링 프로젝트를 해보면서 가장 크게 느낀 점은 바로 편리함입니다.
이 전에 JSP로 프로젝트를 만들었을 때는 일일이 패키지와 MVC 구조를 만들어야 했습니다. 하지만 스프링 프레임워크를 사용하면 프로젝트를 바로 MVC 구조로 만들 수 있었고 jUnit를 사용하여 코드를 테스트를 먼저 할 수 있다는 것에 큰 도움을 받았습니다.
2. 가장 큰 장점은 maven을 이용해서 필요한 기능을 바로 라이브러리에 추가하여 사용할 수 있다는 점과 스프링의 다양한 장점 DI, AOP등을 통해 더욱 간결한 코드를 작성할 수 있었고, 빠르고 가독성 좋은 프로젝트를 만들 수 있었습니다. 
3. 이 프로젝트를 계기로 프레임워크를 왜 사용하고 얼마나 중요한지 알았습니다. 앞으로는 스프링을 이용해서 localhost가 아닌 aws를 이용해서 프로젝트를 배포해 보고 싶습니다.
4. 또한 프레임워크도 굉장히 많이 다양하게 존재하기 때문에 프레임워크를 경험 해 보기 위해 달려보겠습니다. 
------------

 
