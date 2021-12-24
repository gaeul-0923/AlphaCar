<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mypage_image">
    </div>
    <div class="mypage_select">
      <div class="mypage_userinfo">
        <a href=""><p class="mypage_userinfo_select">회원정보</p></a>
      </div>
    </div>
  </nav>
  <!-- 메인 시작 -->
  <main>
    <div id="page" class="master_member_list">
      <h1>회원정보 확인</h1>
      <div class="page_index">
        <div class="page_select">
          <ul>
            <li onclick="location.href='masterMemberList.mpa'">전체</li>
            <li onclick="location.href='masterMemberList.mpa?search=C'">일반</li>
            <li onclick="location.href='masterMemberList.mpa?search=M'">사업자</li>
          </ul>
        </div>
        <!-- service 검색기능 -->
        <form action="masterMemberList.mpa" class="page_search">
        <input type="hidden" name="curPage" value="1" /> 
          <div class="page_search_index">
            <select name="search" id="service_search_index">
              <option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체 검색</option>
              <option value="customer_email" ${page.search eq 'customer_email' ? 'selected' : '' }>이메일로 검색</option>
              <option value="customer_name" ${page.search eq 'customer_name' ? 'selected' : '' }>이름으로 검색</option>
            </select>
          </div>
          <div class="page_search_box">
            <input type="text" placeholder="search" name="keyword" value="${page.keyword}">
            <i class="fas fa-search" onclick="document.querySelector('.page_search').submit();"></i>
          </div>
        </form>
      </div>
      <!-- notice 글 목록  -->
      <div class="user_list">
        <div class="user_list_name">
          <h3>이메일</h3>
          <h3>이름</h3>
          <h3>사업자유무</h3>
          <h3>가입한 날짜</h3>
        </div>
        <div class="page_list_box">
          <c:forEach items="${page.list}" var="vo">
      		<div class="user_list_content">
	          <div class="user_list_content_title">
	            <a href='mastermemberUpdate.mpa?customer_email=${vo.customer_email}'>
	              <p>${vo.customer_email}</p>
	            </a>
	          </div>
	          <p>${vo.customer_name}</p>
	          <c:if test="${vo.admin eq 'M'}">
            	<p>사업자</p>
              </c:if>
              <c:if test="${vo.admin eq 'C'}">
            	<p>일반</p>
              </c:if>
              <c:if test="${vo.admin eq 'A'}">
            	<p>알파카</p>
              </c:if>
              <p>${vo.joindate }</p>
	        </div>
		  </c:forEach> 
    </div>
    <!-- 페이징 처리 -->
    <div class="page">
    	<jsp:include page="/WEB-INF/views/include/page.jsp" /> 
    </div>
    
  </main>
  