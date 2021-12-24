<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      <h1>내 가게 정보</h1>
      <div class="mypage_companys">
      	<c:forEach items="${company}" var="vo">
      	<c:if test="${vo.rank == 1}">
	        <div class="mypage_company">
	          <img src="${vo.imgpath}" alt="회사이미지">
	          <div class="mypage_company_back"></div>
	          <button class="mypage_company_delete" type="button" onclick="if(confirm('정말 삭제 하시겠습니까?')) { 
									location.href='memberCompanyDelete.mps?store_number=${vo.store_number }'} ">X</button>
	          
	          <div class="mypage_company_select">
	            <button onclick="location.href='memberCompanyGraph.mps?store_number=${vo.store_number }'">그래프로 보기</button>
	            <button onclick="location.href='memberCompanyUpdate.mps?store_number=${vo.store_number }'">수정하기</button>
	          </div>
	        </div>
	    </c:if>    
        </c:forEach>
        <div class="mypage_company">  
          <a href="memberCompanyInsert.mps">+</a>
        </div>
      </div>
    </div>
  </main>
 <script type="text/javascript">
function company_delete(store_number) {
	while(true){
		//변수 ID 선언
		var id = prompt("삭제하시려면 비밀번호를 입력하세요.","");
				
		//id 변수 안의 데이터가 "abc"면 반복문 중단
		if(id=="${loginInfo.customer_pw}"){ 
			alert("삭제되었습니다."); 
			location.href="memberCompanyDelete.mps?store_number="+store_number;
			break;
		}
		//id 변수 안의 데이터가 "abc"가 아니면 alert 상자 띄우기
		else{ 
			alert("비밀번호가 틀립니다."); 
			break;
		}

	}
}
</script>