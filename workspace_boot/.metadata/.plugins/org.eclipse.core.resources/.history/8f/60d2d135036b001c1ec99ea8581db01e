<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sub_image">
    <img src="img/main_image.jpg" alt="">
</div>
<div class="page_header_text">
<p>고객센터</p>
<p>1:1 문의의 답글을 작성하는 페이지 입니다.</p>
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
  <h1>답글 작성</h1>
  <form action="reply_insert.qna" class="page_write" method="post">
	  <!-- 답글에 필요한 root, step, indent 값을 hidden 으로 전달 -->
		<input type="hidden" name="qna_root" value="${vo.qna_root }" />
		<input type="hidden" name="qna_step" value="${vo.qna_step }" />
		<input type="hidden" name="qna_indent" value="${vo.qna_indent }" />
		<input type="hidden" name="qna_id" value="${vo.qna_id }" />
    <div class="page_write_index">
      <div class="page_write_title">
				<input type="text" placeholder="제목" name="qna_title" id="qna_title">
      </div>
    </div>
    <div class="page_write_space">
      <textarea id="summernote" name="qna_content"></textarea> 
    </div>
    <div class="page_write_button">
      <button type="button" onclick="check()">작성완료</button>
<!--       <button type="button" onclick='check()'>작성완료</button> -->
      <button type="button" onclick="if(confirm('정말 취소 하시겠습니까?')) { 
					location.href='masterContact.mp'}">취소</button>
    </div>
  </form>
</div>
</main>

<!-- 서머노트를 위해 추가해야할 부분 -->
<script src="resources/js/summernote-lite.js"></script>
<script src="resources/js/summernote.js"></script>
<script src="resources/js/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="resources/css/summernote-lite.css">

<!-- 제목, 내용 필수입력 -->
<script type="text/javascript">
  function check() {
		if ($("#qna_title").val() == "") {
			alert("제목을 입력하세요.");
			$("#qna_title").focus();
			return false;
		}else if ($('#summernote').summernote('isEmpty')) {
			  alert('editor content is empty');
			  return false;
		}else {
			$('form').submit();
		}
		
		
	}
	
</script>