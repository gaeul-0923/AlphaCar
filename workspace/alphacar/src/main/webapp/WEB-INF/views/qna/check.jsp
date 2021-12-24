<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="sub_image">
        <img src="img/main_image.jpg" alt="">
    </div>
	<div class="page_header_text">
		<p>고객센터</p>
		<p>비밀번호 확인</p>
	</div>
  </nav>

  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <h1>비밀번호 입력</h1>
			<form class="form" method="post" action="detail.qn?qna_id=${vo.qna_id }">
	        <input type="password" placeholder="비밀번호" class="qna_password" id="qna_password" />
	        <div class="page_write_button">
			      <button type="button" onclick="go_check()">작성완료</button>
			      <button type="button" onclick="location.href='masterContact.mp'">목록</button>
		    	</div>
      </form>
    </div>
  </main>
  
     <script type="text/javascript">
			function go_check() {
				// 아이디 입력값이 없으면
				if($('#qna_password').val() == '') {
					alert('비밀번호를 입력하세요!');
					$('#qna_password').focus();
					return;
				} else if ($('#qna_password').val() == '${vo.qna_password}'){
					location.href = "detail.qn?qna_id=${vo.qna_id }";
					
				} else if ($('#qna_password').val() != '${vo.qna_password}'){
					alert('비밀번호가 일치하지 않습니다!');
					$('#qna_password').val('');
					$('#qna_password').focus();
					return;
					
				}				
			}
			
		
		
		</script>	
  
  