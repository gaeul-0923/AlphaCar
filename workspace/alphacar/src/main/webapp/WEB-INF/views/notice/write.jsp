<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <div class="sub_image">
        <img src="img/main_image.jpg" alt="">
    </div>
	<div class="page_header_text">
		<p>공지사항</p>
		<p>새로운 공지사항을 작성하는 페이지 입니다.</p>
	</div>
  </nav>

  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <h1>신규글 작성</h1>
		<form action="insert.noa" class="page_write" method="post">
			<div class="page_write_index">
				<div class="page_search_index">
					<select name="notice_attribute" id="notice_search_index">
						<option value="N" selected="selected">공지</option>
						<option value="A">알파카</option>
						<option value="M">점검</option>
					</select>
				</div>
				<div class="page_write_title">
					<input type="text" placeholder="제목" name="notice_title" id="notice_title">
				</div>
			</div>
			<div class="page_write_space">
				<textarea id="summernote" name="notice_content"></textarea> 
			</div>
			<div class="page_write_button">
				<button type="button" onclick="check()">작성완료</button>
				<button type="button" onclick="location.href='list.no'" >취소</button>
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
		  
			if ($("#notice_title").val() == "") {
				alert("제목을 입력하세요.");
				$("#notice_title").focus();
				return false;
			}else if ($('#summernote').summernote('isEmpty')) {
				  alert('editor content is empty');
				  return false;
			}else {
				$('form').submit();
			}
			
			
		}
  </script>

