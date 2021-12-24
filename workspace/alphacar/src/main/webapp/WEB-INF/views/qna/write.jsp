<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sub_image">
    <img src="img/main_image.jpg" alt="">
</div>
<div class="page_header_text">
<p>고객센터</p>
<p>1:1 문의를 작성하는 페이지 입니다.</p>
</div>
</nav>
<!-- 메인 시작 -->
<main>
<div id="page">
  <h1>1:1 문의 작성</h1>
  <form action="insert.qn" class="page_write" method="post">
    <div class="page_write_index">
      <div class="page_search_index">
        <select name="qna_search_index" id="qna_search_index">
          <option value="user-info" selected="selected">고객</option>
           <option value="store">가게</option>
           <option value="app_web" >모바일/홈페이지</option>
           <option value="alphacar">알파카</option>
        </select>
      </div>
      <div class="page_write_title">
				<input type="text" placeholder="제목" name="qna_title" id="qna_title" />
      </div>
    </div>
    <div class="page_write_space">
      <textarea id="summernote" name="qna_content"></textarea> 
    </div>
    <div class="page_write_button">
      <button>작성완료</button>
      <button type="button" onclick="if(confirm('정말 취소 하시겠습니까?')) { 
					location.href='masterContact.mp'}">취소</button>
    </div>
  </form>
</div>
</main>

<!-- 제목, 내용 필수입력 -->
<script type="text/javascript">
	let title   = document.getElementById("qna_title");
	let content = document.getElementById("qna_content");
	
	function check() {
		if(title.value == "") {
		alert("제목을 입력하세요.");
		title.focus();
		}else if(content.value == "") {
		alert("내용을 입력하세요.");
		content.focus();
		}else {
			$('form').submit();
		}
	}
</script>
<!-- 서머노트를 위해 추가해야할 부분 -->
<script src="resources/js/summernote-lite.js"></script>
<script src="resources/js/summernote.js"></script>
<script src="resources/js/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
