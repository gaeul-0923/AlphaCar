<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <div class="sub_image">
    <img src="img/main_image.jpg" alt="">
  </div>
  <div class="service_buttons">
  	<button onclick="go_chat()">실시간 채팅 문의</button>
  	<%-- <c:if test="${empty loginInfo}"><button onclick="location.href='homeLogin'">실시간 채팅 문의</button></c:if>
  	<c:if test="${!empty loginInfo}"><button onclick="go_chat()">실시간 채팅 문의</button></c:if> --%>
  	<button onclick="location.href='write.qn'">1:1 문의하기</button>
  </div>
</nav>
<!-- 메인 시작 -->
<main>
  <div id="page">
    <h1>자주 묻는 질문</h1>
    <div class="page_index">
    <form action="list.se" method="get">
	    <input type="hidden" name="curPage" value="1" /> 
	      <div class="page_select">
	        <ul>
	          <li onclick="location.href='list.se'">전체</li>
	          <li onclick="location.href='list.se?search=user-info'">고객</li>
	          <li onclick="location.href='list.se?search=store'">가게</li>
	          <li onclick="location.href='list.se?search=app_web'">모바일/홈페이지</li>
	          <li onclick="location.href='list.se?search=alphacar'">알파카</li>
	        </ul>
	      </div>
      </form>
      <!-- service 검색기능 -->
      <form action="list.se" method="get" class="page_search">
      	<input type="hidden" name="curPage" value="1" /> 
        <div class="page_search_index">
          <select name="search" id="service_search_index">
            <option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
            <option value="user-info" ${page.search eq 'user-info' ? 'selected' : '' }>고객</option>
            <option value="store" ${page.search eq 'store' ? 'selected' : '' }>가게</option>
            <option value="app_web" ${page.search eq 'app_web' ? 'selected' : '' }>모바일/홈페이지</option>
            <option value="alphacar" ${page.search eq 'alphacar' ? 'selected' : '' }>알파카</option>
          </select>
        </div>
        <div class="page_search_box">
          <input type="text" placeholder="search" name="keyword" value="${best_qna_page.keyword}">
          <i class="fas fa-search" onclick='$("form").submit()'></i>
        </div>
        
      </form>
    </div>
    <!-- notice 글 목록  -->
    <div class="page_list">
      <div class="page_list_name">
        <h3>글</h3>
        <h3>작성자</h3>
        <h3>조회수</h3>
        <h3>활동</h3>
      </div>
      <div class="page_list_box">
	     	<c:forEach items="${page.list}" var="vo">
	     		<div class="page_list_content">
	          <div class="page_list_content_title">
	            <a href='detail.se?best_qna_id=${vo.best_qna_id }'>
	              <c:if test="${vo.best_qna_attribute eq 'C'}">
	              	<p>[고객]</p>
	              </c:if>
	              <c:if test="${vo.best_qna_attribute eq 'S'}">
	              	<p>[가게]</p>
	              </c:if>
	              <c:if test="${vo.best_qna_attribute eq 'M'}">
	              	<p>[모바일/홈페이지]</p>
	              </c:if>
	              <c:if test="${vo.best_qna_attribute eq 'A'}">
	              	<p>[알파카]</p>
	              </c:if>
	              <p>${vo.best_qna_title}</p>
	            </a>
	          </div>
	          <p>${vo.customer_name}</p>
	          <p>${vo.best_qna_readcnt}</p>
	          <p>${vo.best_qna_time}</p>
	        </div>
				</c:forEach> 
    	</div> 
    </div>
        
    <div class="page_content_create">
    
				<!-- 관리자로 로그인된 경우만 글쓰기 가능 -->
				<c:if test="${loginInfo.admin eq 'A'}">
      				<button type="button" onclick="location.href='write.sea'">글 작성</button>
				</c:if>
			
    </div>
    
    <!-- 페이징 처리 -->
    <jsp:include page="/WEB-INF/views/include/page.jsp" />  
  
  </div>
</main>
<script type="text/javascript">
function go_chat() {
	var width = 500; 
	var height = 420;
	var left = (window.screen.width / 2) - (width/2); 
	var top = (window.screen.height / 4);
	var windowStatus = 'width='+width+', height='+height+
		', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=yes, titlebar=yes';

	window.open("list.chat", "실시간문의", windowStatus);
}
</script>