<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mypage_image">

    </div>
    <div class="mypage_select">
      <div class="mypage_userinfo">
        <a href=""><p class="mypage_userinfo_select">세차장 정보 수정</p></a>

      </div>
    </div>
  </nav>
  <!-- 메인 시작 -->
  <main class="mypage">
    <div id="page">
      <form class="form company_update_form" method="post" name="dataForm" id="dataForm" enctype="multipart/form-data"
      			action="update_work.mps">
	      <input type="hidden" name='store_number' value="${vo.store_number}"/>
				<input type="hidden" name='attach' />
        <h1>세차장 정보 수정</h1>
	        <div class="company_update">
	          <div>
	            <h3>세차장 이름</h3>
	            <input type="text" name="store_name" id="new_store" value="${vo.store_name }">
	          </div>
	          <div>
	            <h3>우편번호</h3>
	            <button type="button" onclick='daum_post()'>우편번호 찾기</button>
	            <input type="text" name="store_post" id="new_post" value="${vo.store_post }"><br/>		
	          </div>
	          <div>
	            <h3>주소</h3>
	            <input type="text" name="store_addr" id="new_addr" value="${vo.store_addr }">	
	          </div>
	          <div>
	            <h3>상세주소</h3>
	            <input type="text" name="store_detail_addr" id="new_detail_addr"value="${vo.store_detail_addr }">	
	          </div>
	          <div>
	            <h3>전화번호</h3>
	            <input type="text" name="store_tel" id="new_tel" value="${vo.store_tel }"  onkeyup="checkTel()">
							<div id="telError"></div>
	          </div>
	          <div>
	            <h3>영업시간</h3>
	            <input type="text" name="store_time" id="new_time" value="${vo.store_time }">
	          </div>
	          <div>
	            <h3>휴무일</h3>
	            <input type="text" name="store_dayoff" id="new_dayoff" value="${vo.store_dayoff }">
	          </div>
	          <div class="company_introduce">
	            <h3>세차장 소개</h3>
	            <input type="text" name="introduce" id="new_introduce" value="${vo.introduce }">
	            <!-- <textarea name="" id=""></textarea> -->
	          </div>
	          <div>
	            <h3>베이수</h3>
	            <input type="text" name="inventory" id="new_inventory" value="${vo.inventory }" onkeyup="checkInventory()">
							<div id="invenError"></div>
	          </div>
	          <div>
	            <h3>가격</h3>
	            <input type="text" name="store_price" id="new_price" value="${vo.store_price }" >
	          </div>
	          <div>
	            <h3>사업주 이름</h3>
	            <input type="text" name="store_master_name" id="new_master" value="${vo.store_master_name }" onkeyup="checkMaster()">
							<div id="masterError"></div>
	          </div>
	          <div class="last_store_insert">
	            <h3>사업자 번호</h3>
	            <input type="text" name="store_registration_number" id="new_regi" value="${vo.store_registration_number }" readonly="readonly">
	          </div>
	          <div class="join_profile_images"> 
	            <h3>사진</h3>
	            <div>
	            	<c:forEach items="${img }" var="img">
								<div class="join_profile_image" >
									<img src="${img.imgpath }" onclick="choose_image()" class="join_profile_image${img.rank }">
								</div>
		            </c:forEach>
	            </div>
	          </div>
	        </div>
	        <!-- style="display: none" -->
	        <div style="display: none" id="articlefileChange"></div>
	    		<input style="display: none" multiple="multiple" type="file" class='input_file' id='input_file' name="input_file" accept="image/*" >
	        <button type="button" onclick="check()">수정하기</button>
      </form>
    </div>
  </main>
  
  <!-- 다음 주소 검색 API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script type="text/javascript">

	const regName =  /^[가-힣]{2,4}$/
	const regDigit = /[0-9]/g;
	const regRegi = /[0-9]{10}/g;
	const regTel = /^010([0-9]{3,4})([0-9]{4})$/;
	const regInven = /^[1-9]{1}$/
	
	let name = document.getElementById("new_store");
	let post = document.getElementById("new_post");
	let addr = document.getElementById("new_addr");
	let addr2 = document.getElementById("new_detail_addr");
	let tel = document.getElementById("new_tel");
	let time = document.getElementById("new_time");
	let dayoff = document.getElementById("new_dayoff");
	let introduce = document.getElementById("new_introduce");
	let inventory = document.getElementById("new_inventory");
	let price = document.getElementById("new_price");
	let master = document.getElementById("new_master");
	let regi = document.getElementById("new_regi");
	
	let teToken = true;
	let inToken = true;
	let prToken = true;
	let maToken = true;
	let reToken = true;
	let r2Token = true;
	
	function regiDupl() {
		$.ajax({
			url : 'regiDupl.mps'
			, data : {id:regi.value}
			, type : 'post'
			, async : false
			, success : function (res) {
				if (res == true) {
					alert("사용 가능한 사업자등록번호입니다.");
					r2Token = true;
				} else {
					alert("중복되는 번호가 있습니다.");
				}
			}, error : function ( req, text ) {
				alert(text + ':' + req.status);
			}
		});
	}
	
	function checkTel() {
		if(!regTel.test(tel.value)) {
			document.getElementById("telError").innerText = "-를 제외한 휴대폰번호를 입력해주세요.";
			document.getElementById("telError").style.color = "red";
			teToken = false;
		}else {
			document.getElementById("telError").innerText = "전화번호가 입력되었습니다.";
			document.getElementById("telError").style.color = "green";
			teToken = true;
		}
	}

	function checkInventory() {
		if(!regInven.test(inventory.value)) {
			document.getElementById("invenError").innerText = "숫자만 입력해주세요.";
			document.getElementById("invenError").style.color = "red";
			inToken = false;
		}else {
			document.getElementById("invenError").innerText = "베이수가 입력되었습니다.";
			document.getElementById("invenError").style.color = "green";
			inToken = true;
		}
	}

	function checkPrice() {
		if(!regDigit.test(price.value)) {
			document.getElementById("priceError").innerText = "숫자만 입력해주세요.";
			document.getElementById("priceError").style.color = "red";
			prToken = false;
		}else {
			document.getElementById("priceError").innerText = "가격이 입력되었습니다.";
			document.getElementById("priceError").style.color = "green";
			prToken = true;
		}
	}

	function checkMaster() {
		if(!regName.test(master.value)) {
			document.getElementById("masterError").innerText = "2~4자의 한글만 입력해주세요.";
			document.getElementById("masterError").style.color = "red";
			maToken = false;
		}else {
			document.getElementById("masterError").innerText = "이름이 입력되었습니다.";
			document.getElementById("masterError").style.color = "green";
			maToken = true;
		}
	}

	
	function check() {
		if(name.value == "") {
			alert("세차장 이름을 입력하세요.");
			name.focus();
			return false;
		}
		else if(post.value == "") {
			alert("우편번호를 입력하세요.");
			post.focus();
			return false;
		}
		else if(addr.value == "") {
			alert("주소를 입력하세요.");
			addr.focus();
			return false;
		}
		else if(addr2.value == "") {
			alert("상세주소를 입력하세요.");
			addr2.focus();
			return false;
		}
		else if(!teToken) {
			alert("전화번호를 입력하세요.");
			tel.focus();
			return false;
		}
		else if(time.value == "") {
			alert("영업시간을 입력하세요.");
			time.focus();
			return false;
		}
		else if(dayoff.value == "") {
			alert("휴무일을 입력하세요.");
			dayoff.focus();
			return false;
		}
		else if(introduce.value == "") {
			alert("세차장 소개를 입력하세요.");
			introduce.focus();
			return false;
		}
		else if(!inToken) {
			alert("베이수를 입력하세요.");
			inventory.focus();
			return false;
		}
		else if(price.value == "") {
			alert("가격을 입력하세요.");
			price.focus();
			return false;
		}
		else if(!maToken) {
			alert("사업주 이름을 입력하세요.");
			master.focus();
			return false;
		}else {
			$('form').submit();
		}
	}
	</script>
	<script type="text/javascript">
	function daum_post() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 조회된 우편번호를 입력하기 위한 선언
	            // name 이 post 인 태그의 val(값)을 받아온 변수 (data) 내 zonecode 값을 담음
	            $('[name=store_post]').val( data.zonecode);
	            
	            // 지번 주소 : J  도로명 주소 : R
	            var addr =data.userSelectedType == 'J' ? data.jibunAddress : data.roadAddress;
	            
	            // 건물명이 있을 경우 추가
	            if ( data.buildingName != '') addr += ' ('+ data.buildingName + ')'; 
	            	$('[name=store_addr]').eq(0).val( addr );
	        }
	    }).open();
	}
	$(document).ready(function()
			// input file 파일 첨부시 fileCheck 함수 실행
			{
				$("#input_file").on("change", fileCheck);
			});
	
	
	
	const image = document.querySelector('.join_profile_image');
	const input_file = document.querySelector('#input_file');
	//이미지 파일 누를 때 input_file도 같이 클릭
	function choose_image() {
		input_file.click();
	}
	//첨부파일 선택시 처리
	  $(document).on('change', '#input_file', function() {
	  	var attached1 = this.files[0];
	  	if (attached1) { // 첨부된 파일이 있을 경우
	  		$('.join_profile_image1').html('<img src="" />');
	  		var reader = new FileReader();
	  		reader.onload = function(e) {
	  			$('.join_profile_image1').attr('src', e.target.result);
	  		}
	  		reader.readAsDataURL(attached1);
	  	}
	  })
	   $(document).on('change', '#input_file', function() {
	  	var attached2 = this.files[1];
	  	if (attached2) { // 첨부된 파일이 있을 경우
	  		$('.join_profile_image2').html('<img src="" />');
	  		var reader = new FileReader();
	  		reader.onload = function(e) {
	  			$('.join_profile_image2').attr('src', e.target.result);
	  		}
	  		reader.readAsDataURL(attached2);
	  	}
	  })
	  $(document).on('change', '#input_file', function() {
	  	var attached3 = this.files[2];
	  	if (attached3) { // 첨부된 파일이 있을 경우
	  		$('.join_profile_image3').html('<img src="" />');
	  		var reader = new FileReader();
	  		reader.onload = function(e) {
	  			$('.join_profile_image3').attr('src', e.target.result);
	  		}
	  		reader.readAsDataURL(attached3);
	  	}
	  })
	  /**
	 * 첨부파일로직
	 */
	$(function () {
	    $('#btn-upload').click(function (e) {
	        e.preventDefault();
	        $('#input_file').click();
	    });
	});
	
	// 파일 현재 필드 숫자 totalCount랑 비교값
	var fileCount = 0;
	// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
	var totalCount = 3;
	// 파일 고유넘버
	var fileNum = 0;
	// 첨부파일 배열
	var content_files = new Array();
	
	function fileCheck(e) {
	    var files = e.target.files;
	    
	    // 파일 배열 담기
	    var filesArr = Array.prototype.slice.call(files);
	    
	    // 파일 개수 확인 및 제한
	    if (fileCount + filesArr.length != totalCount) {
	      alert('파일은 '+totalCount+'개만 업로드 할 수 있습니다.');
				$("#input_file").val('');
	      return;
	    } else {
	    	 fileCount = fileCount + filesArr.length;
	    }
	    
	    // 각각의 파일 배열담기 및 기타
	    filesArr.forEach(function (f) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        content_files.push(f);
	        $('#articlefileChange').append(
	       		'<div id="file' + fileNum + '" onclick="fileDelete(\'file' + fileNum + '\')">'
	       		+ '<font style="font-size:12px">' + f.name + '</font>'  
	       		+ '<img src="/img/icon_minus.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;"/>' 
	       		+ '<div/>'
			);
	        fileNum ++;
	      };
	      reader.readAsDataURL(f);
	    });
	    console.log(content_files);
	    //초기화 한다.
	    //$("#input_file").val("");
	  }
	
	// 파일 부분 삭제 함수
	function fileDelete(fileNum){
	    var no = fileNum.replace(/[^0-9]/g, "");
	    content_files[no].is_delete = true;
		$('#' + fileNum).remove();
		fileCount --;
	    console.log(content_files);
	}
	  
	  /*
	 * 폼 submit 로직
	 */
		function registerAction(){
			$('form').submit();

		}
</script>
	