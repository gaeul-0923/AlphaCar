# AlphaCar
전국의 셀프세차장 소개

## 기간

2021.10.25 ~ 2021.12.29

## 역할

DB 설계, 웹 summernote API를 이용해 1:1 문의, 자주묻는 질문 게시판 구현, spring security를 이용한 로그인, 회원가입 구현, chart.js를 이용한 데이터 시각화, 회사 등록, 수정 구현

## 소개

‘AlphaCar’는 전국의 셀프 세차장 정보를 제공하는 소프트웨어 시스템입니다.</br></br>
‘AlphaCar’ 어플을 통해 전국의 세차장을 검색하여 지도로 확인할 수 있고 점포의 정보, 방문자의 리뷰 등 다양한 컨텐츠를 제공합니다. 또한 사용자는 시간 낭비없이 실시간으로 비어있는 세차자리를 확인 할 수 있습니다. </br></br>
웹 페이지에서는 사업주는 월별 이용자 수, 이번주 이용자 수, 인기있는 시간대 등 가게를 운영하는데 유용한 데이터를 볼 수 있습니다. 사용자는 어플이나 웹 페이지를 이용할 때 문제가 생긴다면 게시판으로 파이어베이스를 이용한 1:1 채팅으로도 AlphaCar사에 문의 할 수 있습니다.

## 개발 환경

- 개발 언어 : Java, HTML, CSS, JavaScript, jQuery
- 개발 환경 : Spring Framework
- DBMS : Oracle
- 서버 : Apache Tomcat, MyBatis
- etc : Notion, GitHub

## 화면

![ateam_db](https://user-images.githubusercontent.com/90816804/147405270-1b7452e0-86e3-434b-bd72-690e3a609543.jpg)
-데이터베이스 설계도</br></br>
![KakaoTalk_20211227_191958956_01](https://user-images.githubusercontent.com/90816804/147478849-699d525f-4c14-41ca-8ab9-1e0ff1cf3e40.jpg)
-홈페이지 메인</br></br>
![KakaoTalk_20211227_191958956](https://user-images.githubusercontent.com/90816804/147478974-65c8aaf1-0d11-4c5e-b581-4cfdf7029b54.jpg)
-마이페이지</br></br>
![20211227_222903](https://user-images.githubusercontent.com/90816804/147479046-84c5e169-1ba2-47c4-a849-40cb70498678.jpg)
-데이터시각화</br></br>
![20211227_225952](https://user-images.githubusercontent.com/90816804/147479171-aed8828b-a952-4433-8dc0-0a7027538f78.jpg)
-페이징처리 된 게시판</br></br>
![20211227_220839](https://user-images.githubusercontent.com/90816804/147479226-c2cfeb6d-0d4d-4789-918a-5c83b627d1e1.jpg)
-내 가게 정보</br></br>

## 사용 Skills
1. spring security 를 이용한 로그인 구현 [[소스코드]](https://github.com/holic4570/AlphaCar/tree/main/workspace/alphacar/src/main/java/security)</br>
  1-1. `AuthenticationProvider`인터페이스를 상속받은 CustomAuthenticationProvider 클래스에서 사용자가 입력한 정보와 DB정보가 같은지 비교해 준다.

        - 인증에 성공하면 인증된 Authentication 객체를 생성하여 리턴
        - matches 매소드를 이용하여 암호화 된 비밀번호를 비교
        
       public class CustomAuthenticationProvider implements AuthenticationProvider{
        @Autowired
          private UserDetailsService userDeSer;

        @Autowired 
        private BCryptPasswordEncoder cryptEncoder;

        @SuppressWarnings("unchecked")
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
          String cus = (String) authentication.getPrincipal();
              String password = (String) authentication.getCredentials();

              CustomUserDetails user = (CustomUserDetails) userDeSer.loadUserByUsername(cus);

              if(!user.isEnabled()) {
                throw new BadCredentialsException(cus);
              }

              Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();

          if(!cryptEncoder.matches(password, user.getPassword())) {
            //log.debug("matchPassword :::::::: false!");
            throw new BadCredentialsException(cus);
          }

              return new UsernamePasswordAuthenticationToken(cus, password, authorities);


        }

        @Override
        public boolean supports(Class<?> authentication) {
          // TODO Auto-generated method stub
          return true;
        }
      }
     </br>
   1-2. 로그인 성공 시 'LoginSuccessHandler' 클래스에서 어떤 URL로 Redirect 할 지 결정한다.
      public class LoginSuccessHandler implements AuthenticationSuccessHandler{
        @Autowired
        private UserService service;

        private RequestCache requestCache = new HttpSessionRequestCache();
        private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

        private String loginidname;
          private String defaultUrl;



        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

          HttpSession session = null;
          session = request.getSession(true);
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          String customer_email = auth.getName();
          CustomUserDetails vo = service.member_login(customer_email);

          session.setAttribute("loginInfo", vo);

          //에러 세션 지우기
          clearAuthenticationAttributes(request);

          //Redirect URL 작업
          resultRedirectStrategy(request, response, authentication);

        }
        protected void clearAuthenticationAttributes(HttpServletRequest request) {
          HttpSession session = request.getSession(false);
          if(session==null) return;
          session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
        protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

          SavedRequest savedRequest = requestCache.getRequest(request, response);

          if(savedRequest!=null) {
            //log.debug("권한이 필요한 페이지에 접근했을 경우");
            useSessionUrl(request, response);
          } else {
            //log.debug("직접 로그인 url로 이동했을 경우");
            useDefaultUrl(request, response);
          }

        }
        protected void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
          SavedRequest savedRequest = requestCache.getRequest(request, response);
          String targetUrl = savedRequest.getRedirectUrl();
          redirectStratgy.sendRedirect(request, response, targetUrl);
        }
        protected void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
          redirectStratgy.sendRedirect(request, response, defaultUrl);
        }


        public String getLoginidname() {
          return loginidname;
        }


        public void setLoginidname(String loginidname) {
          this.loginidname = loginidname;
        }


        public String getDefaultUrl() {
          return defaultUrl;
        }


        public void setDefaultUrl(String defaultUrl) {
          this.defaultUrl = defaultUrl;
        }


      }
      </br>
2. summernote API를 이용한 게시판 구현 </br>
   controller : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/java/com/hanul/alphacar/HomeMyPageController.java)</br>
   jsp : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/webapp/WEB-INF/views/mypage/member_contact.jsp)</br>
   
    -controller</br>
      ```
      @RequestMapping("/memberContact.mp")
      public String memberContact(HttpSession session, Model model, 
          @RequestParam (defaultValue = "1") int curPage,
          String search, String keyword, QnaVO vo, CustomUserDetails cus) {

        page.setCurPage(curPage);
        page.setSearch(search);
        page.setKeyword(keyword);
        //((WebMemberVO) session.getAttribute("loginInfo")).getCustomer_email() ;

        //DB에서 공지글 목록을 조회한 후 목록화면에 출력
    //		String customer_email = ((WebMemberVO) session.getAttribute("loginInfo")).getCustomer_email();
        String customer_email = ((CustomUserDetails) session.getAttribute("loginInfo")).getCustomer_email();

        List<QnaVO> qvo = service.member_qna_list(customer_email);
        if (qvo.size() == 0) {
          return "mypage/member_contact";
        } else {

          HashMap<String, Object> map = new HashMap<String, Object>();
          map.put("qna_pid", qvo.get(0).getQna_pid());
          map.put("page", page);
          page = service.member_qna_list(map);
          model.addAttribute("page", page);

          return "mypage/member_contact";
        }
      }
     ```
  </br>
  -jsp
      
      <!-- 메인 시작 -->
      <main>
        <div id="page">
          <h1>내 1:1 문의 내역</h1>
        <form action="memberContact.mp" method="post">
          <input type="hidden" name="curPage" value="1" />
        </form>

        <!-- notice 글 목록  -->
          <div class="page_list">
            <div class="page_list_name">
              <h3>글</h3>
              <h3>작성자</h3>
              <h3>조회수</h3>
              <h3>활동</h3>
            </div>
           <div class="page_list_box">
            <c:forEach items="${page.list}" var="vo">
              <div class="page_list_content">
                <div class="page_list_content_title">
                  <c:forEach begin="1" end='${vo.qna_indent }' var='i'>
                    ${i eq vo.qna_indent ? "<img src='img/re.gif' />" : "&nbsp;&nbsp;" }
                  </c:forEach>			
                  <a href='detail.qn?qna_id=${vo.qna_id }'>
                    <c:if test="${vo.qna_attribute eq 'C'}">
                      <p>[고객]</p>
                    </c:if>
                    <c:if test="${vo.qna_attribute eq 'S'}">
                      <p>[가게]</p>
                    </c:if>
                    <c:if test="${vo.qna_attribute eq 'M'}">
                      <p>[모바일/홈페이지]</p>
                    </c:if>
                    <c:if test="${vo.qna_attribute eq 'A'}">
                      <p>[알파카]</p>
                    </c:if>
                    <p>${vo.qna_title}</p>
                  </a>
                </div>
                <p>${vo.customer_name}</p>
                <p>${vo.qna_readcnt}</p>
                <p>${vo.qna_time}</p>
              </div>
            </c:forEach> 
           </div>  
          </div>
          <div class="page_content_create">
            <button type="button" onclick="location.href='write.qn'">글 작성</button>
          </div>

          <!-- 페이징 처리 -->
          <div class="notice_paging">
            <jsp:include page="/WEB-INF/views/include/page.jsp" />  
          </div>    
        </div>
      </main>
    
3. chart.js 를 이용한 데이터 시각화 
  controller : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/java/com/hanul/alphacar/ChartController.java)</br>
  jsp : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/webapp/WEB-INF/views/mypage/member_company_graph.jsp)</br>
  -controller</br>
    ```
    @Controller
    public class ChartController {

      @Autowired private HomeChartServiceImpl service;

       @ResponseBody
       @RequestMapping("/month_list.cha")
       public String month_list(Model model, int store_number)  throws Exception{
          Gson gson = new Gson();

          List<ChartVO> list = service.month_list(store_number);
          ObjectMapper mapper = new ObjectMapper();
          String datas = mapper.writeValueAsString(list);

          return gson.toJson(datas);
       }

       @ResponseBody
       @RequestMapping("/time_rank.cha")
       public String time_rank(Model model, int store_number)  throws Exception{
         Gson gson = new Gson();

         List<ChartVO> list = service.time_rank(store_number);
         ObjectMapper mapper = new ObjectMapper();
         String datas = mapper.writeValueAsString(list);

         return gson.toJson(datas);
       }

       @ResponseBody
       @RequestMapping("/week_cnt.cha")
       public String week_cnt(Model model, int store_number)  throws Exception{
         Gson gson = new Gson();
         List<ChartVO> list = new ArrayList<ChartVO>();
         list = service.week_cnt(store_number);

         ObjectMapper mapper = new ObjectMapper();
         String datas = mapper.writeValueAsString(list);

         return gson.toJson(datas);
       }

    }
    ``` 
  </br>
  -jsp
        
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
4. 신규 가게 등록
  controller : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/java/com/hanul/alphacar/ChartController.java)</br>
  jsp : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/webapp/WEB-INF/views/mypage/member_company_insert.jsp)</br>
  -controller
    //신규 가게 저장 요청
    @RequestMapping(value = "/homeStoreRegister.mps", produces = "text/html; charset=utf-8")
    public String homeStoreRegister(MultipartHttpServletRequest req, HomeStoreVO vo, int inventory,
        HttpSession session, HttpServletResponse response) throws IOException {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();

      vo.setCustomer_email( ( (CustomUserDetails) session.getAttribute("loginInfo")).getCustomer_email() );
      List<MultipartFile> fileList = req.getFiles("input_file");
      ArrayList<String> storeInventory = new ArrayList<>();
      for (int i =0; i< 9; i++){
        storeInventory.add("X");
          }

      for(int i =0; i<inventory; i++){ 
        storeInventory.set(i,"Y"); 
      }

          for (int i = 0; i < storeInventory.size(); i++) {
        vo.setNow_state(storeInventory.get(i));

      }

          homeService.company_insert(vo);

          HomeStoreFileVO fvo = new HomeStoreFileVO(); 


       if(fileList.size() > 0 && !fileList.get(0).getOriginalFilename().equals("")) { 
         int rank =0; 
         for(MultipartFile file:fileList) { 
           fvo.setImgname(file.getOriginalFilename());
           fvo.setImgpath(common.fileUpload("company", file, session));
           fvo.setRank(++rank); homeService.companyImg_insert(fvo); 
           out.println("<script>alert('저장성공!'); location='memberCompany.mps'; </script>");
           out.flush();
         }

        }


        return "memberCompany.mps";

    }
</br>
 -jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="mypage_image"></div>
    <div class="mypage_select">
      <div class="mypage_userinfo">
        <a href=""><p class="mypage_userinfo_select">세차장 정보 입력</p></a> 
      </div>
    </div>
    </nav>
    <!-- 메인 시작 -->
    <main class="mypage">
      <div id="page">
        <form class="form company_update_form" method="post" name="dataForm" id="dataForm" enctype="multipart/form-data"
          action="homeStoreRegister.mps">
          <h1>신규 세차장 등록</h1>
          <div class="company_update">
            <div>
              <h3>세차장 이름</h3>
              <input type="text" name="store_name" id="store_name">
              <div id="storeError"></div>
            </div>
            <div>
              <h3>우편번호</h3>
              <button type="button" onclick='daum_post()'>우편번호 찾기</button>
              <input type="text" name="store_post" id="store_post" value="${vo.store_post }"><br />
            </div>
            <div>
              <h3>주소</h3>
              <input type="text" name="store_addr" value="${vo.store_addr }" id="store_addr">
            </div>
            <div>
              <h3>상세주소</h3>
              <input type="text" name="store_detail_addr" id="store_detail_addr"
                value="${vo.store_detail_addr }">
            </div>
            <div>
              <h3>전화번호</h3>
              <input type="text" name="store_tel" id="store_tel" onkeyup="checkTel()">
              <div id="telError"></div>
            </div>
            <div>
              <h3>영업시간</h3>
              <input type="text" name="store_time" id="store_time">
            </div>
            <div>
              <h3>휴무일</h3>
              <input type="text" name="store_dayoff" id="store_dayoff">
            </div>
            <div class="company_introduce">
              <h3>세차장 소개</h3>
              <input type="text" name="introduce" id="introduce">
              <!-- <textarea name="" id=""></textarea> -->
            </div>
            <div>
              <h3>베이수</h3>
              <input type="number" name="inventory" id="inventory" onkeyup="checkInventory()">
              <div id="invenError"></div>
            </div>
            <div>
              <h3>가격</h3>
              <input type="text" name="store_price" id="store_price">
            </div>
            <div>
              <h3>사업주 이름</h3>
              <input type="text" name="store_master_name" id="store_master_name" onkeyup="checkMaster()">
              <div id="masterError"></div>
            </div>
            <div class="last_store_insert">
              <h3>사업자 번호</h3>
              <input type="text" name="store_registration_number" id="store_registration_number" onkeyup="checkRegi()">
              <div class="last_store_insert_click">
                <a id='btn_regi' onclick="regiDupl()">사업자 등록번호 중복검사</a>
                <div id="regiError"></div>
              </div>

            </div>
            <div class="join_profile_images">
              <h3>사진</h3>
              <div>				
                <div class="join_profile_image">
                  <img onclick="choose_image()"
                    class="join_profile_image1">
                </div>	
                <div class="join_profile_image">
                  <img onclick="choose_image()"
                    class="join_profile_image2">
                </div>	
                <div class="join_profile_image">
                  <img onclick="choose_image()"
                    class="join_profile_image3">
                </div>
              </div>				
            </div>
          </div>
          <div style="display: none" id="articlefileChange"></div>
          <input style="display: none" multiple="multiple" type="file" class='input_file' id='input_file' name="input_file" accept="image/*" >
          <button type="button" onclick="check()">등록하기</button>
        </form>
      </div>
    </main>
    <script type="text/javascript" src='js/file_check.js'></script>

    <!-- 다음 주소 검색 API -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
    const regName =  /^[가-힣]{2,4}$/
    const regDigit = /[0-9]/g;
    const regRegi = /[0-9]{10}/g;
    const regTel = /^010([0-9]{3,4})([0-9]{4})$/;
    const regInven = /^[1-9]{1}$/
    let name = document.getElementById("store_name");
    let post = document.getElementById("store_post");
    let addr = document.getElementById("store_addr");
    let addr2 = document.getElementById("store_detail_addr");
    let tel = document.getElementById("store_tel");
    let time = document.getElementById("store_time");
    let dayoff = document.getElementById("store_dayoff");
    let introduce = document.getElementById("introduce");
    let inventory = document.getElementById("inventory");
    let price = document.getElementById("store_price");
    let master = document.getElementById("store_master_name");
    let regi = document.getElementById("store_registration_number");
    let teToken = false;
    let inToken = false;
    let prToken = false;
    let maToken = false;
    let reToken = false;
    let r2Token = false;
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
    function checkRegi() {
      if(!regRegi.test(regi.value)) {
        document.getElementById("regiError").innerText = " -를 제외한 등록번호(10자리)를 입력해주세요.";
        document.getElementById("regiError").style.color = "red";
        reToken = false;
      }else {
        document.getElementById("regiError").innerText = " 사업자 등록번호가 입력되었습니다.";
        document.getElementById("regiError").style.color = "green";
        reToken = true;
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
      }
      else if(!reToken) {
        alert("등록번호를 입력하세요.");
        regi.focus();
        return false;
      }else if(!r2Token) {
        alert("등록번호 중복검사를 통과하세요.");
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
    // 		var form = $("form")[0];        
    //  	 	var formData = new FormData(form);
    // 			for (var x = 0; x < content_files.length; x++) {
    // 				// 삭제 안한것만 담아 준다. 
    // 				if(!content_files[x].is_delete){
    // 					 formData.append("article_file", content_files[x]);
    // 				}
    // 			}
         /*
         * 파일업로드 multiple ajax처리
         */
      /* 	console.log(formData);
        $.ajax({
                type: "post",
                enctype: "multipart/form-data",
                url: "homeStoreRegister.mps",
                data : formData,
                processData: false,
                contentType: false,
                success: function (data) {
                  window.location.replace("memberCompany.mps");

                  alert("가게등록 성공");
    // 						} else
    // 							alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
                },
                error: function (status, error) {
                  alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                return false;
                }
              });
              return false;  */
        }
    </script>
## 소스 코드

  - [전체 소스코드](https://github.com/holic4570/AlphaCar/tree/main/workspace/alphacar/src/main)
