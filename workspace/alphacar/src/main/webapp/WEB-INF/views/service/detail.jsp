<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    <meta charset="utf-8">

   <div class="sub_image">
      <img src="img/main_image.jpg">
   </div>
  
	<div class="page_detail_text"> 
		<p>
			<c:if test="${vo.best_qna_attribute eq 'C'}">[고객]</c:if>
      <c:if test="${vo.best_qna_attribute eq 'S'}">[가게]</c:if>
      <c:if test="${vo.best_qna_attribute eq 'M'}">[모바일/홈페이지]</c:if>
      <c:if test="${vo.best_qna_attribute eq 'A'}">[알파카]</c:if>
			 ${vo.best_qna_title }</p>
		<p>${vo.customer_name }</p>
		<p>${vo.best_qna_writedate }</p>
	</div>
  </nav>
  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <div class="detail">
	    	<p>
					${vo.best_qna_content }
				</p>
		  </div>
		  <div class="page_write_button">
			<button type="button" onclick="location.href='list.se'">목록으로</button>
			<!-- 관리자로 로그인된 경우만 수정 가능 -->
			<c:if test="${loginInfo.admin eq 'A'}">
	    	<button type="button" onclick="location.href='update.sea?best_qna_id=${vo.best_qna_id}'">수정</button>
				<button type="button" onclick="if(confirm('정말 삭제 하시겠습니까?')) { 
				location.href='delete.sea?best_qna_id=${vo.best_qna_id }'} ">삭제</button>
			</c:if>
		  </div>
    </div>
    
    
  </main>
  <!-- 서머노트를 위해 추가해야할 부분 -->
 <script src="resources/js/summernote-lite.js"></script>
 <script src="resources/js/lang/summernote-ko-KR.js"></script>
 <link rel="stylesheet" href="resources/css/summernote-lite.css">
 
	