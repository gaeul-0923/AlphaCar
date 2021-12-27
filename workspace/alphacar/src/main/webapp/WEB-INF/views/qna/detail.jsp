<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    <meta charset="utf-8">

   <div class="sub_image">
      <img src="img/main_image.jpg" alt="">
   </div>
  
	<div class="page_detail_text"> 
		<p>
			<c:if test="${vo.qna_attribute eq 'C'}">[고객]</c:if>
      <c:if test="${vo.qna_attribute eq 'S'}">[가게]</c:if>
      <c:if test="${vo.qna_attribute eq 'M'}">[모바일/홈페이지]</c:if>
      <c:if test="${vo.qna_attribute eq 'A'}">[알파카]</c:if>
			 ${vo.qna_title }</p>
		<p>${vo.customer_name }</p>
		<p>${vo.qna_writedate }</p>
	</div>
  </nav>
  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <div class="detail">
	    	<p>
					${vo.qna_content }
				</p>
		  </div>
		  <div class="page_write_button">
			<button type="button" onclick="location.href='memberContact.mp'">목록으로</button>
			<!-- 관리자로 로그인된 경우만 수정 가능 -->
			<c:if test="${loginInfo.customer_email eq vo.customer_email}">
				<c:if test="${vo.qna_indent eq 0 }">
	    		<button type="button" onclick="location.href='update.qn?qna_id=${vo.qna_id}'">수정</button>
				</c:if>
				<c:if test="${vo.qna_indent ne 0 }">
	    		<button type="button" onclick="location.href='replyUpdate.qna?qna_id=${vo.qna_id}'">수정</button>
				</c:if>
				<button type="button" onclick="if(confirm('정말 삭제 하시겠습니까?')) { 
				location.href='delete.qn?qna_id=${vo.qna_id }'} ">삭제</button>
			</c:if>
			<c:if test="${loginInfo.admin eq 'A'}">
				<button type="button" onclick="location.href='reply.qna?qna_id=${vo.qna_id}'">답글쓰기</button>
			</c:if>
		  </div>
    </div>
    
    
  </main>
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="resources/js/summernote-lite.js"></script>
  <script src="resources/js/summernote.js"></script>
  <script src="resources/js/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="resources/css/summernote-lite.css">
  
	