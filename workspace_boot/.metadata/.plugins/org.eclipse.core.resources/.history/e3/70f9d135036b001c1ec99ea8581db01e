<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="sub_image">
        <img src="img/main_image.jpg" alt="">
    </div>
	<div class="page_header_text">
		<p>고객센터</p>
		<p>Q&A 답글을 수정하는 페이지 입니다.</p>
	</div>
  </nav>

  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <h1>Q&A 답글 수정</h1>
		<form action="reply_update.qna" class="page_write" method="post">
			<input type="hidden" name='qna_id' value="${vo.qna_id}"/>
			<input type="hidden" name='attach' />
			<div class="page_write_index">
				<div class="page_write_title">
					<input type="text" name="qna_title" id="qna_title" value="${vo.qna_title }">
				</div>
			</div>
			<div class="page_write_space">
				<textarea id="summernote" name="qna_content" cols="100" >${vo.qna_content }</textarea>
				
			</div>
			
			<div class="page_write_button">
				<button type="button" onclick='check()'>수정완료</button>
				<button type="button" onclick="if(confirm('정말 취소 하시겠습니까?')) { 
					location.href='masterContact.mpa'}">취소</button>
			</div>
		</form>
    </div>
  </main>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="resources/js/summernote-lite.js"></script>
  <script src="resources/js/summernote.js"></script>
  <script src="resources/js/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="resources/css/summernote-lite.css">
  
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
