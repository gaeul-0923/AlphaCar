<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sub_image">
      <img src="img/main_image.jpg" alt="">
  </div>
</nav>
<!-- 메인 시작 -->
<main>
  <div id="page">
    <h1>공지사항</h1>
    <div class="page_index">
    <form action="list.no" method="get">
    <input type="hidden" name="curPage" value="1" /> 
      <div class="page_select">
        <ul>
          <li onclick="location.href='list.no'">전체</li>
          <li onclick="location.href='list.no?search=notice'">공지</li>
          <li onclick="location.href='list.no?search=alphacer'">알파카</li>
          <li onclick="location.href='list.no?search=maintainance'">점검</li>
        </ul>        
      </div>
      </form>
      <!-- notice 검색기능 -->
      <form action="list.no" method="get" class="page_search">
      <input type="hidden" name="curPage" value="1" /> 
        <div class="page_search_index">
          <select name="search" id="notice_search_index">
            <option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
            <option value="notice" ${page.search eq 'notice' ? 'selected' : '' }>공지</option>
            <option value="alphacer" ${page.search eq 'alphacer' ? 'selected' : '' }>알파카</option>
            <option value="maintainance" ${page.search eq 'maintainance' ? 'selected' : '' }>점검</option>
          </select>
        </div>
        <div class="page_search_box">
          <input type="text" placeholder="search" name="keyword" value="${page.keyword}">
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
	            <a href='detail.no?id=${vo.notice_id }'>
	              <c:if test="${vo.notice_attribute eq 'N'}">
	              	<p>[공지]</p>
	              </c:if>
	              <c:if test="${vo.notice_attribute eq 'A'}">
	              	<p>[알파카]</p>
	              </c:if>
	              <c:if test="${vo.notice_attribute eq 'M'}">
	              	<p>[점검]</p>
	              </c:if>
	              <p>${vo.notice_title}</p>
	            </a>
	          </div>
	          <p>${vo.customer_name}</p>
	          <p>${vo.notice_readcnt}</p>
	          <p>${vo.notice_time}</p>
	        </div>
		</c:forEach> 
      </div>
    </div>
    <div class="page_content_create">
      <c:if test="${loginInfo.admin eq 'A'}">
		<button type="button" onclick="location.href='write.noa'">글 작성</button>
	  </c:if>  
    </div>
    
    <!-- 페이징 처리 -->
    <jsp:include page="/WEB-INF/views/include/page.jsp" />
  </div>
</main>