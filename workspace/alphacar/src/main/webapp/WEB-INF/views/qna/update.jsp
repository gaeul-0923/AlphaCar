<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="sub_image">
        <img src="img/main_image.jpg" alt="">
    </div>
	<div class="page_header_text">
		<p>고객센터</p>
		<p>Q&A 글을 수정하는 페이지 입니다.</p>
	</div>
  </nav>

  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <h1>Q&A 글 수정</h1>
		<form action="update_work.qn" class="page_write" method="post">
			<input type="hidden" name='qna_id' value="${vo.qna_id}"/>
			<input type="hidden" name='attach' />
			<div class="page_write_index">
				<div class="page_search_index">
					<select name="qna_search_index" id="qna_search_index">
						<option value="user-info" ${vo.qna_attribute eq 'C' ? 'selected' : '' }>고객</option>
            <option value="store" ${vo.qna_attribute eq 'S' ? 'selected' : '' }>가게</option>
            <option value="app_web" ${vo.qna_attribute eq 'M' ? 'selected' : '' }>모바일/홈페이지</option>
            <option value="alphacar" ${vo.qna_attribute eq 'A' ? 'selected' : '' }>알파카</option>
					</select>
				</div>
				<div class="page_write_title">
					<input type="text" name="qna_title" id="qna_title" value="${vo.qna_title }">
				</div>
			</div>
			<div class="page_write_space">
				<textarea id="summernote" name="qna_content" cols="100">${vo.qna_content }</textarea>
				
			</div>
			<div class="page_password_space">
				<input type="password" placeholder="비밀번호" name="qna_password" id="qna_password" />
    	</div>
			<div class="page_write_button">
				<button>수정완료</button>
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
  