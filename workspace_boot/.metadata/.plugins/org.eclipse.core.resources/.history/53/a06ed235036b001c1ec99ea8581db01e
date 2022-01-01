<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mypage_image">
    </div>
    <div class="mypage_select">
      <div class="mypage_userinfo">
        <a href="list.wa"><p class="mypage_userinfo_select">전체 세차장 지도</p></a>
        <!-- <a href=""><p>보안설정</p></a> -->
      </div>
    </div>
  </nav>
  <!-- 메인 시작 -->
  <main class="mypage">
    <div id="page">
      <div class="wash_zone_boxs">
      	<input type="hidden" value="${loginInfo.city} " id="wash_zone_city">
      	<input type="hidden" value="${loginInfo.addr} " id="wash_zone_addr">
        <div id="map" style="width:1200px;height:500px;"></div>
        <div>
        	
        </div>
      </div>  
    </div>
  </main>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e147583a7db59a9ae45a17222b6e7203&libraries=services"></script>
<script>
	const positions = JSON.parse('${wash_zone}'); //model로 보낸 데이터를 json형태로 변환
	// 주소-좌표 변환 객체를 생성합니다
	const geocoder = new kakao.maps.services.Geocoder();
	const city = document.getElementById('wash_zone_city').value;
	const addr = document.getElementById('wash_zone_addr').value;
	if(city==' '||city=='시/도 선택 '){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new kakao.maps.LatLng(35.153411178408, 126.88799074307386), // 지도의 중심좌표
		    level: 6 // 지도의 확대 레벨
		};
		const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성
		
		// 주소로 좌표를 검색합니다
		for (let i = 0; i < positions.length; i++) {
			geocoder.addressSearch(positions[i].store_addr, function(result, status) {
			    // 정상적으로 검색이 완료됐으면 
			    if (status === kakao.maps.services.Status.OK) {
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords,
			            clickable: true,
			        });      
			     	// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성
				    var iwRemoveable = true;
			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			        	content: '<div style="width:150px;text-align:center;padding:6px 0;"><a href="detail.wa?store_number='+positions[i].store_number+'">'+positions[i].store_name+'</a></div>',
			            removable : iwRemoveable
			        });
			        kakao.maps.event.addListener(marker, 'click', markerClick(map, marker,infowindow));
			        
			    }
			});
		};
		// 마커에 클릭이벤트를 등록
		function markerClick(map, marker, infowindow) {
			return function () {
				infowindow.open(map, marker);
			};
		}
	}else{	
		geocoder.addressSearch(city+addr, function(result, status){
			if (status === kakao.maps.services.Status.OK) {
				let x = result[0].x;
				let y = result[0].y;
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				mapOption = { 	
				    center: new kakao.maps.LatLng(y, x), // 지도의 중심좌표
				    level: 6 // 지도의 확대 레벨
				};
				const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성
				
				// 주소로 좌표를 검색합니다
				for (let i = 0; i < positions.length; i++) {
					geocoder.addressSearch(positions[i].store_addr, function(result, status) {
					    // 정상적으로 검색이 완료됐으면 
					    if (status === kakao.maps.services.Status.OK) {
					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					        // 결과값으로 받은 위치를 마커로 표시합니다
					        var marker = new kakao.maps.Marker({
					            map: map,
					            position: coords,
					            clickable: true,
					        });      
					     	// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성
						    var iwRemoveable = true;
					        // 인포윈도우로 장소에 대한 설명을 표시합니다
					        var infowindow = new kakao.maps.InfoWindow({
					        	content: '<div style="width:150px;text-align:center;padding:6px 0;"><a href="detail.wa?store_number='+positions[i].store_number+'">'+positions[i].store_name+'</a></div>',
					            removable : iwRemoveable
					        });
					        kakao.maps.event.addListener(marker, 'click', markerClick(map, marker,infowindow));
					    }
					});
				};
				// 마커에 클릭이벤트를 등록
				function markerClick(map, marker, infowindow) {
					return function () {
						infowindow.open(map, marker);
					};
				}
		    }
		});

	}
   
</script>