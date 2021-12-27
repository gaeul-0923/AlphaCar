<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="mypage_image">
    </div>
    <div class="mypage_select">
      <div class="mypage_userinfo">
        <a href=""><p class="mypage_userinfo_select">회원정보</p></a>
     
      </div>
    </div>
  </nav>
  <!-- 메인 시작 -->
  <main class="mypage">
    <div id="page">
      <div class="mypage_boxs">
        <div class="mypage_box mypage_info_update">
          <div>
            <h1>회원정보수정</h1>
            <div class="mypage_userinfo_view">
              <div class="mypage_user_image">
                <c:if test="${loginInfo.customer_picture == null}">
              	  <img src="resources/pictures/profiles/noImage.png" alt="noProfileImage">
              	</c:if>
              	<c:if test="${loginInfo.customer_picture != null}">
              	  <img src="${loginInfo.customer_picture}" alt="profileImage">
              	</c:if>
              </div>
              <div class="mypage_user_name">
                <h3>이름</h3>
                <h3>${loginInfo.customer_name}</h3>
              </div>
            </div>
          </div>
          <c:if test="${empty loginInfo.social}">
          	<button onclick="location.href='memberUpdate.mp'">수정하기</button>
          </c:if>
          <c:if test="${!empty loginInfo.social}">
          	<button onclick="location.href='memberSocialUpdate.mp'">수정하기</button>
          </c:if>
        </div>
        <c:if test="${loginInfo.admin != 'A'}">
        <div class="mypage_box mypage_contact">
          <h1>1:1 문의 내역</h1>
          <div class="mypage_content">
            <p>알파카 이용시 궁금하셨던 내용,</p>
            <p>1:1 문의 내역을 편하게 이용해 보세요.</p>
          </div>
          <button onclick="location.href='memberContact.mp'">보러가기</button>
        </div>
        </c:if>
        <c:if test="${loginInfo.admin == 'M'}">
        <div class="mypage_box mypage_contact">
          <h1>내 가게 정보</h1>
          <div class="mypage_content">
            <p>내가 저장한 가게의 정보를 확인하거나</p>
            <p>관리할 수 있습니다.</p>
          </div>
          <button onclick="location.href='memberCompany.mps'">보러가기</button>
        </div>
        </c:if>
        <c:if test="${loginInfo.admin == 'A'}">
	        <div class="mypage_box mypage_contact">
	          <h1>전체 회원 관리</h1>
	          <div class="mypage_content">
	            <p>알파카에 가입한 모든 회원의 정보를</p>
	            <p>검색하고 수정할 수 있습니다.</p>
	          </div>
	          <button onclick="location.href='masterMemberList.mpa'">수정하기</button>
	        </div>
	        <div class="mypage_box mypage_contact">
	          <h1>1:1 문의 처리</h1>
	          <div class="mypage_content">
	            <p>회원이 작성한 문의내용을</p>
	            <p>답변할 수 있습니다.</p>
	          </div>
	          <button onclick="location.href='masterContact.mpa'">처리하기</button>
	        </div>
	      </div>
      </c:if>
      <div class="user_delete">
        <p>더 이상 알파카를 이용하지 않는다면 </p>
        <%-- <button onclick="user_delete('${loginInfo.customer_pw}', '${loginInfo.customer_email}')">회원탈퇴 바로가기</button> --%>
        <button onclick="user_delete('${loginInfo.customer_email}')">회원탈퇴 바로가기</button>
      </div>
    </div>
  </main>
<script type="text/javascript">

function user_delete(customer_emails) {
	if(confirm('정말 탈퇴 하시겠습니까?')){
		$.ajax({
			url : "memberDelete.mp"
			, data : {customer_email: customer_emails}
			, type : 'post'
			, async : false
			, success : function (res) {
				window.location.replace("homeLogin");
				alert("탈퇴되었습니다.");				
			}, error : function ( req, text ) {
				alert("탈퇴실패.");
			}
		});
		//location.href="memberDelete.mp?customer_email="+customer_email;
	}
}

/* function user_delete(customer_pw, customer_email) {
	while(true){
		//변수 ID 선언
		var id = prompt("삭제하시려면 비밀번호를 입력하세요.","");
		//id 변수 안의 데이터가 "abc"면 반복문 중단
		if(id=="${loginInfo.customer_pw}"){ 
			alert("탈퇴되었습니다."); 
			location.href="memberDelete.mp?customer_email="+customer_email;
			break;
		}
		//id 변수 안의 데이터가 "abc"가 아니면 alert 상자 띄우기
		else{ 
			alert("비밀번호가 틀립니다."); 
			break;
		}
	}
} */
</script>