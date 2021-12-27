<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="sub_image">
        <img src="img/main_image.jpg" alt="">
    </div>
	<div class="page_header_text">
		<p>고객센터</p>
		<p>FAQ 글을 수정하는 페이지 입니다.</p>
	</div>
  </nav>

  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <h1>FAQ 글 수정</h1>
		<form action="update_work.sea" class="page_write" method="post">
			<input type="hidden" name='best_qna_id' value="${vo.best_qna_id}"/>
			<input type="hidden" name='attach' />
			<div class="page_write_index">
				<div class="page_search_index">
					<select name="best_qna_search_index" id="best_qna_search_index">
						<option value="user-info" ${vo.best_qna_attribute eq 'C' ? 'selected' : '' }>고객</option>
            <option value="store" ${vo.best_qna_attribute eq 'S' ? 'selected' : '' }>가게</option>
            <option value="app_web" ${vo.best_qna_attribute eq 'M' ? 'selected' : '' }>모바일/홈페이지</option>
            <option value="alphacar" ${vo.best_qna_attribute eq 'A' ? 'selected' : '' }>알파카</option>
					</select>
				</div>
				<div class="page_write_title">
					<input type="text" name="best_qna_title" value="${vo.best_qna_title }">
				</div>
			</div>
			<div class="page_write_space">
				<textarea id="summernote" name="best_qna_content" cols="100" >${vo.best_qna_content }</textarea>
				
			</div>
			<div class="page_write_button">
				<button type="button" onclick="check()">수정완료</button>
				<button type="button" onclick="if(confirm('정말 취소 하시겠습니까?')) { 
					location.href='list.se'}">취소</button>
				
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
		if ($("#best_qna_title").val() == "") {
			alert("제목을 입력하세요.");
			$("#best_qna_title").focus();
			return false;
		}else if ($('#summernote').summernote('isEmpty')) {
			  alert('editor content is empty');
			  return false;
		}else {
			$('form').submit();
		}
		
		
	}
	</script>
