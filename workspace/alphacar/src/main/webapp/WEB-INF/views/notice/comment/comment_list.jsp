<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 서머노트를 위해 추가해야할 부분 -->

<c:forEach items="${list }" var="vo">
	<div data-seq = ${vo.notice_coment_id } class="comment_detail">
		<div class="comment_name">
			<p class="comment_write_name">${vo.customer_name }</p>
			<p class="comment_date">[${vo.coment_writedate }]<p>
		</div>
		
		<c:if test="${vo.customer_email eq loginInfo.customer_email }">
			<span class="comment_setting">
				<button type="button" onclick="comment_update_button('${vo.notice_coment_id }')">수정</button>
				<button type="button" onclick="
				if(confirm('정말 삭제 하시겠습니까?')) { 
					location.href='comment_delete.no?id=${vo.notice_coment_id }&notice_id=${vo.notice_id }'} 
				">삭제</button>
			</span>
		</c:if>
		<div class='comment_content'>${vo.coment_content }</div>
	</div>

	<div class="comment_update${vo.notice_coment_id }" style="display: none;" >
		<textarea id="summernote" name="coment_content" class="summernote ${vo.notice_coment_id }">
			${vo.coment_content }
		</textarea> 
		<button type="button" onclick="comment_update(${vo.notice_coment_id })">댓글수정</button>
	</div>
</c:forEach>

<script>
//서머노트
$('.summernote').summernote({
      height: 150,
      lang: "ko-KR",
      toolbar: [
	           // [groupName, [list of button]]
	           ['fontname', ['fontname']],
	           ['fontsize', ['fontsize']],
	           ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
	           ['color', ['forecolor','color']],
	           ['para', ['ul', 'ol', 'paragraph']],
	           ['height', ['height']]
           ],
       fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
       fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
    });
</script>


<script type="text/javascript">

//코멘트 수정 버튼 누를 때 textarea 생기게 하기

function comment_update_button(no) {
	let comment = document.querySelector(".comment_update" + no);
	//var comment = document.getElementById("comment_update" + no);
	//안보이는 상태일때
	if (comment.style.display == 'none') {
		//보이는것으로 변경
		comment.style.display = '';
	} else {
		comment.style.display = 'none';
	}
}

//코멘트 수정시 처리
function comment_update(no) {
	if ($.trim($("." + no).val()) == '') {
		//내용 없이 등록하기를 눌렀을 경우
		alert('댓글을 입력하세요!');
		$("." + no).val('');
		$("." + no).focus();
		return;
	}

	$.ajax({
		url: "board/comment/update",
		data: { coment_content: $("." + no).val(), notice_coment_id: no },
		success: function(response) {
			if (response) {	// true == true T, false == true F
				alert('댓글이 수정되었습니다.');
				$("." + no).val('');
				comment_list();		// 댓글 목록 조회 요청 함수
				
			} else
				alert('댓글 수정이 실패하였습니다.');
		}, error: function(req, text) {
			alert(text + ":" + req.status);
		}
	});
}
</script>




