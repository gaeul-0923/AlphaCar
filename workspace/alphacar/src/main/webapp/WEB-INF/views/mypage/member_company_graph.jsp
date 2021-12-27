<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
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
      <h3>${vo.store_name } </h3>
      <jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
			<p>업데이트날짜 : <c:out value="${today}"/></p>


      <div>
      	<p>월별 이용자수</p>
      	<canvas id="myChart"></canvas>
      </div>
      <div>
      	<p>이번주 요일 별 이용자수</p>
      	<canvas id="myChart2"></canvas>
      </div><br />
      <div>
      	<p>이번달 인기있는 시간대</p>
      	<canvas id="myChart3"></canvas>
      </div>
    </div>
  </main>
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
	
	<script type="text/javascript"  charset="UTF-8">         
	const number = '${store_number}';
	$.ajax({
	   type: "POST",
	    dataType: "json",
	    data: {store_number:number},
	    url: "month_list.cha",
	   	success : function( datas ) 
	      {             
	      tt = JSON.parse(datas);
	      var context = document
	       .getElementById('myChart')
	       .getContext('2d');
	      var arrLabel ;
		    arrLabel = new Array(12);
		    arrData = new Array(12);
		    
		    for (var i=0; i<tt.length; i++) {
				var data = ""
				var cnt = 0;
				arrLabel[i] = new Array(tt.length);
				arrData[i] = new Array(tt.length);
				for (key in tt[i]) {
					if(key == 'tt'){
						arrData[i] = tt[i][key];
						//arrtitle[i]= datas[i][key];
					}else if(key == 'color'){
					//	arrcolor[i]= datas[i][key];
					}else{
	
						arrLabel[i] = (i+1)+"월"
						cnt ++ ;
					}
					
			  }
			
			}
		    
	   		var myChart = new Chart(context, {
	       type: 'line', // 차트의 형태
	       data: { // 차트에 들어갈 데이터
	           labels: arrLabel,
	           datasets: [
	               { //데이터
	                   label: '이용자수', //차트 제목
	                   fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
	                   data: arrData,
	                   backgroundColor: [
	                       //색상
	                       
	                       'rgba(255, 159, 64, 0.2)'
	                   ],
	                   borderColor: [
	                       //경계선 색상
	                       
	                       'rgba(255, 159, 64, 1)'
	                   ],
	                   borderWidth: 1 //경계선 굵기
	               }/* ,
	               {
	                   label: 'test2',
	                   fill: false,
	                   data: [
	                       8, 34, 12, 24
	                   ],
	                   backgroundColor: 'rgb(157, 109, 12)',
	                   borderColor: 'rgb(157, 109, 12)'
	               } */
	           ]
	       },
	       options: {
	           scales: {
	               yAxes: [
	                   {
	                       ticks: {
	                           beginAtZero: true
	                       }
	                   }
	               ]
	           }
	       }
	   });
	   }, error : function (req, text) {
	      alert(text + ':' + req.status);
	   }
	});            
	</script>
	
 	<script type="text/javascript"  charset="UTF-8">         
	$.ajax({
	   type: "POST",
	    dataType: "json",
	    data: {store_number:number},
	    url: "week_cnt.cha",
	   	success : function( datas ) 
	      {             
		   customer = JSON.parse(datas);
	      
	      
	      var context = document
	       .getElementById('myChart2')
	       .getContext('2d');
	      
	      var arrLabel ;
		    arrLabel = new Array(7);
		    arrLabel[0] = '월'
		    arrLabel[1] = '화'
		    arrLabel[2] = '수'
		    arrLabel[3] = '목'
		    arrLabel[4] = '금'
		    arrLabel[5] = '토'
		    arrLabel[6] = '일'
		     
		    arrData = new Array();  
		    for (var i=0; i<customer.length; i++) {
				var cnt = 0;
				arrData[i] = new Array(customer.length);
				for (key in customer[i]) {
					if(key == 'customer'){
						arrData[i] = customer[i][key];
						//arrtitle[i]= datas[i][key];
					}else if(key == 'color'){
					//	arrcolor[i]= datas[i][key];
					}else{
	
						cnt ++ ;
					}
			  }
			
			}
	    var myChart = new Chart(context, {
		       type: 'bar', // 차트의 형태
		       data: { // 차트에 들어갈 데이터
		           labels: arrLabel,
		           datasets: [
		               { //데이터
		                   label: '이용자수', //차트 제목
		                   fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
		                   data: arrData,
		                   backgroundColor: [
		                       //색상
		                       

		                	   	 'rgba(255, 99, 132, 0.2)',
		                       'rgba(54, 162, 235, 0.2)',
		                       'rgba(255, 206, 86, 0.2)',
		                       'rgba(75, 192, 192, 0.2)',
		                       'rgba(153, 102, 255, 0.2)',
		                       'rgba(255, 159, 64, 0.2)',
		                       
		                       'rgba(236, 75, 102, 0.3)',
		                   ],
		                   borderColor: [
		                       //경계선 색상
		                       

		                	   	 'rgba(255, 99, 132, 1)',
		                       'rgba(54, 162, 235, 1)',
		                       'rgba(255, 206, 86, 1)',
		                       'rgba(75, 192, 192, 1)',
		                       'rgba(153, 102, 255, 1)',
		                       'rgba(255, 159, 64, 1)',
		                       
		                       'rgba(236, 75, 102, 1)',
		                   ],
		                   borderWidth: 1 //경계선 굵기
		               }/* ,
		               {
		                   label: 'test2',
		                   fill: false,
		                   data: [
		                       8, 34, 12, 24
		                   ],
		                   backgroundColor: 'rgb(157, 109, 12)',
		                   borderColor: 'rgb(157, 109, 12)'
		               } */
		           ]
		       },
		       options: {
		           scales: {
		               yAxes: [
		                   {
		                       ticks: {
		                           beginAtZero: true
		                       }
		                   }
		               ]
		           }
		       }
		   });
		   }, error : function (req, text) {
		      alert(text + ':' + req.status);
		   }
		});            
	</script>

	<script type="text/javascript"  charset="UTF-8">         
	
	$.ajax({
	   type: "POST",
	    dataType: "json",
	    data: {store_number:number},
	    url: "time_rank.cha",
	   	success : function( datas ) 
	      {             
	      time = JSON.parse(datas);
	      
	      var context = document
	       .getElementById('myChart3')
	       .getContext('2d');
	      var arrLabel ;
	      arrLabel = new Array(time.length);
		    arrData = new Array(time.length);
		    
		    for (var i=0; i<time.length; i++) {
					var data = ""
					var count = 0;
					arrLabel[i] = new Array(time.length);
					arrData[i] = new Array(time.length);
					for (key in time[i]) {
						if(key == 'time'){
							arrLabel[i] = time[i][key]+"시";
							//arrtitle[i]= datas[i][key];
						}else if(key == 'cnt'){
						//	arrcolor[i]= datas[i][key];
							arrData[i] = time[i][key];
						}else{
							count ++ ;
						}
				
				
					}
					
			  }
		   
		    
	   		var myChart = new Chart(context, {
	       type: 'pie', // 차트의 형태
	       data: { // 차트에 들어갈 데이터
	           labels: arrLabel,
	           datasets: [
	               { //데이터
	                   label: '시간대', //차트 제목
	                   fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
	                   data: arrData,
	                   backgroundColor: [
	                       //색상

	                	   	 'rgba(255, 99, 132, 0.2)',
	                       'rgba(54, 162, 235, 0.2)',
	                       'rgba(255, 206, 86, 0.2)',
	                       'rgba(75, 192, 192, 0.2)',
	                       'rgba(153, 102, 255, 0.2)',
	                       'rgba(255, 159, 64, 0.2)',
	                       
	                       'rgba(236, 75, 102, 0.3)',
	                       'rgba(75, 107, 236, 0.3)',
	                       'rgba(255, 43, 0, 0.3)',
	                       'rgba(212, 0, 255, 0.3)',
	                       'rgba(0, 168, 95, 0.3)',
	                       'rgba(255, 250, 97, 0.3)'
	                   ],
	                   borderColor: [
	                       //경계선 색상
	                       
	                	   	 'rgba(255, 99, 132, 1)',
	                       'rgba(54, 162, 235, 1)',
	                       'rgba(255, 206, 86, 1)',
	                       'rgba(75, 192, 192, 1)',
	                       'rgba(153, 102, 255, 1)',
	                       'rgba(255, 159, 64, 1)',
	                       
	                       'rgba(236, 75, 102, 1)',
	                       'rgba(75, 107, 236, 1)',
	                       'rgba(255, 43, 0, 1)',
	                       'rgba(212, 0, 255, 1)',
	                       'rgba(0, 168, 95, 1)',
	                       'rgba(255, 250, 97, 1)'
	                   ],
	                   borderWidth: 1 //경계선 굵기
	               }/* ,
	               {
	                   label: 'test2',
	                   fill: false,
	                   data: [
	                       8, 34, 12, 24
	                   ],
	                   backgroundColor: 'rgb(157, 109, 12)',
	                   borderColor: 'rgb(157, 109, 12)'
	               } */
	           ]
	       },
	       options: {
		    	   title: {
		       			diplay: true,
		       			text: "이번 달 인기있는 시간대"
		       		}
	       		
	       }
	   });
	   }, error : function (req, text) {
	      alert(text + ':' + req.status);
	   }
	});            
	</script>
	
	
	