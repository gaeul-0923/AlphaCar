<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <div class="sub_image">
        <img src="img/main_image.jpg" alt="">
    </div>
	<div class="page_detail_text"> 
		<p>
			<c:if test="${vo.notice_attribute eq 'N'}">[공지]</c:if>
	        <c:if test="${vo.notice_attribute eq 'A'}">[알파카]</c:if>
	        <c:if test="${vo.notice_attribute eq 'M'}">[점검]</c:if>
		${vo.notice_title }</p>
		<p>${vo.customer_name }</p>
		<p>${vo.notice_writedate }</p>
	</div>
  </nav>
  <!-- 메인 시작 -->
  <main>
    <div id="page">
      <div class="detail">
		<p>
			${vo.notice_content }
		</p>
	  </div>
	  
	   <!-- 댓글 입력 -->
	  <div class='comment'>
	  	<div id='comment_list'>
	  	<!-- 입력된 댓글이 나오는 곳 -->
	  	</div>
		<div id='comment_regist'> 
			<textarea id="summernote" name="coment_content" class="comment_write"></textarea>	
			<button onclick="comment_regist()" class="comment_insert">댓글작성</button>
		</div>
	  </div>
	  <div class="page_write_button">
		<button type="button" onclick="location.href='list.no?curPage=${page.curPage }&serch=${page.search }&keyword=${page.keyword}'">목록으로</button>
		<c:if test="${loginInfo.customer_email eq vo.customer_email || loginInfo.admin eq 'A'}">
			<button type="button" onclick="location.href='update.noa?id=${vo.notice_id }'">수정</button>
			<button type="button" onclick="
			if(confirm('정말 삭제 하시겠습니까?')) { 
				location.href='delete.noa?id=${vo.notice_id }'} 
			">삭제</button>
			
	  	</c:if> 
	  </div>
    </div>
  </main>
 <!-- 서머노트를 위해 추가해야할 부분 -->
 <script src="resources/js/summernote-lite.js"></script>
 <script src="resources/js/lang/summernote-ko-KR.js"></script>
 <link rel="stylesheet" href="resources/css/summernote-lite.css">
 <script>
 	//서머노트
	$('#summernote').summernote({
	      height: 150,
	      lang: "ko-KR",
	      toolbar: [
	           // [groupName, [list of button]]
	           ['fontname', ['fontname']],
	           ['fontsize', ['fontsize']],
	           ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
	           ['color', ['forecolor','color']],
	           ['para', ['ul', 'ol', 'paragraph']],
	           ['height', ['height']],
		       // 그림첨부, 링크만들기, 동영상첨부
	           ['view', ['help']]
	         ],
	       fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
	       fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
	    });
	
	comment_list();
	
	//코맨트 버튼 눌렀을 때 처리
	function comment_regist() {
		if ( ${ empty loginInfo}) { 	
			//로그인 안했을 경우
			alert ('댓글을 등록하려면 로그인하세요!');
			return;
		} else if ( $.trim( $('.comment_write').val() ) == ''  ) { 
			//내용 없이 등록하기를 눌렀을 경우
			alert ('댓글을 입력하세요!');
			$('.comment_write').val('');
			$('.comment_write').focus();
			return;
		}
		$.ajax ({
			//경로 형태로 url 지정
			url : "board/comment/regist"
			, data : { notice_id:${vo.notice_id} , coment_content:$('.comment_write').val()  }
			, success : function ( response ) {
				if ( response ) {	// true == true T, false == true F
					alert ('댓글이 등록되었습니다.');
					$('.comment_write').val('');
					comment_list();		// 댓글 목록 조회 요청 함수
				} else 
					alert('댓글 등록이 실패하였습니다.');
			}, error : function (req, text) {
				alert(text + ":" + req.status);
			}	
		});	
	}
	
	function comment_list() {
		$.ajax({
			url: 'board/comment/list/${vo.notice_id}'
			, success: function ( response ) {
				$('#comment_list').html( response );
				$('.comment_write').summernote('code', '');
			}, error: function (req, text) {
				alert (text + ":" + req.status);
			}	
		})
	}
	
  </script>