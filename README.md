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
![20211229_122634](https://user-images.githubusercontent.com/90816804/147624694-a67608ce-2936-4361-9eef-b2d4d97ae755.jpg)
-페이징처리 된 게시판(계층형 구조)</br></br>
![20211229_122634](https://user-images.githubusercontent.com/90816804/147624632-e0b047f3-16ae-4ebe-bc5b-b76a8952282d.jpg)
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
   1-3. 자동로그인 라디오버튼을 클릭하고 로그인 할 때 remember-me 쿠키를 물려주고 로그아웃 시 쿠키 삭제</br>
      <beans 
        xmlns="http://www.springframework.org/schema/beans" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xmlns:security="http://www.springframework.org/schema/security" 
        xsi:schemaLocation="http://www.springframework.org/schema/security 
        http://www.springframework.org/schema/security/spring-security.xsd 
        http://www.springframework.org/schema/beans 
        http://www.springframework.org/schema/beans/spring-beans.xsd"> 

        <security:http pattern="/resources/**" security="none"/>
        <security:http pattern="/resources/pictures/" security="none"/>
        <security:http pattern="/**/*.js" security="none"/>
        <security:http pattern="/**/*.css" security="none"/>
        <security:http pattern="/img/**" security="none"/>		
        <security:http pattern="/ioTCarWash" security="none"/>		
        <security:http pattern="/**/*.an" security="none"/>		
        <security:http pattern="/homeStoreRegister.mps" security="none"/>		
        <security:http pattern="board/comment/update" security="none"/>	
        <security:http pattern="board/comment/list/*" security="none"/>	
        <security:http pattern="board/comment/list/" security="none"/>	




        <security:http auto-config="true" use-expressions="true"> 
          <security:csrf disabled="true"/>
          <security:intercept-url pattern="/" access="permitAll"/>
          <security:intercept-url pattern="/homeLogin" access="permitAll"/>
          <security:intercept-url pattern="/**/*.ho" access="permitAll"/>
          <security:intercept-url pattern="/**/*.no" access="permitAll"/>
          <security:intercept-url pattern="/**/*.wa" access="permitAll"/>
          <security:intercept-url pattern="/**/*.se" access="permitAll"/>
          <security:intercept-url pattern="/**/*.mp" access="permitAll"/>

      <!-- 		<security:intercept-url pattern="/**/*.mp" access="hasAnyRole('ROLE_ALPHACHR, ROLE_ADMIN, ROLE_CUSTOMER')"/> -->
          <security:intercept-url pattern="/**/*.qn" access="isAuthenticated()" />
          <security:intercept-url pattern="/**/*.mps" access="isAuthenticated()" />
          <security:intercept-url pattern="/**/*.chat" access="isAuthenticated()" />
          <security:intercept-url pattern="/**/*.mpa" access="hasRole('ROLE_ALPHACHR')" />
          <security:intercept-url pattern="/**/*.noa" access="hasRole('ROLE_ALPHACHR')" />
          <security:intercept-url pattern="/**/*.qna" access="hasRole('ROLE_ALPHACHR')" />
          <security:intercept-url pattern="/**/*.sea" access="hasRole('ROLE_ALPHACHR')" />
          <security:intercept-url pattern="/**/*.cha" access="hasRole('ROLE_ADMIN')" />


          <!-- 로그인 설정 -->
          <security:form-login
                username-parameter="customer_email"
                password-parameter="customer_pw"
                login-processing-url="/webLogin.ho"
                login-page="/homeLogin"
                authentication-failure-handler-ref="loginFailureHandler"
                authentication-success-handler-ref="loginSuccessHandler"
              />	

          <security:remember-me 
                key="uniqueAndSecret" 
                token-validity-seconds="60000"
                authentication-success-handler-ref="loginSuccessHandler"/>

          <!-- 로그아웃 설정 -->
          <security:logout 
            logout-url="/homelogout.ho"
            invalidate-session="true"
            logout-success-url="/"
            delete-cookies="JSESSIONID, remember-me"
            />

          <security:session-management>
                <security:concurrency-control
                  max-sessions="5"
                  error-if-maximum-exceeded="true"
                />
          </security:session-management>

          <!-- 권한이 없어서 금지된 URI 접속할 때 보여줄 페이지(403 에러 페이지 대체) -->
          <security:access-denied-handler error-page="/no_permission" />

        </security:http> 


        <security:authentication-manager>
            <security:authentication-provider ref="userAuthProvider">
            </security:authentication-provider>
            <security:authentication-provider user-service-ref="userService">
            <security:password-encoder ref="bcryptPasswordEncoder"/>

          </security:authentication-provider>
        </security:authentication-manager>

        <bean id="bcryptPasswordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"></bean>
        <bean id="userAuthProvider" class="security.CustomAuthenticationProvider"/>
        <bean id="userService" class="security.CustomUserDetailsService"/>

        <bean id="loginFailureHandler" class="security.LoginFailureHandler">
          <property name="loginidname" value="customer_email"/>
          <property name="loginpwdname" value="customer_pw"/>
          <property name="errormsgname" value="ERRORMSG"/>
          <property name="defaultFailureUrl" value="/homeLogin"/>

        </bean>
        <bean id="loginSuccessHandler" class="security.LoginSuccessHandler">
          <property name="loginidname" value="customer_email"/>
          <property name="defaultUrl" value="/"/>
      </bean>
     </beans>
</br>
2. summernote API를 이용한 게시판 구현(1:1문의, 자주묻는 ) </br>
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
4. 신규 가게 등록/수정</br>
  controller : 
  jsp : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/webapp/WEB-INF/views/mypage/member_company_insert.jsp)</br>
  mapper : [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/resources/sqlmap/homeMyPage-mapper.xml)</br>
  -controller</br>
      ```
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
      ```
   </br>  
-mapper(pl/sql 문 이용)</br>
   
          ```
          <!-- 신규 가게 등록 -->
          <insert id="company_register">
            DECLARE
              V_STORE_NUM number ;
              V_SENSOR number;
              V_MAX_ID number;
            BEGIN

            SELECT NVL(MAX(FILE_ID),0)  INTO  V_MAX_ID from store_file;
            SELECT SEQ_STORE.nextval  INTO  V_STORE_NUM from DUAL;
            SELECT NVL(MAX(SENSOR_ID),0)  INTO  V_SENSOR from SENSOR;
            INSERT ALL
              into store (store_number, customer_email, store_name, store_post, store_addr, store_detail_addr, store_tel, 
                store_time, store_dayoff, introduce, inventory, store_price, store_master_name, store_registration_number)
              values (V_STORE_NUM, #{customer_email, jdbcType=VARCHAR}, #{store_name, jdbcType=VARCHAR}, 
                #{store_post, jdbcType=VARCHAR}, #{store_addr, jdbcType=VARCHAR}, #{store_detail_addr, jdbcType=VARCHAR}, 
                #{store_tel, jdbcType=VARCHAR}, #{store_time, jdbcType=VARCHAR}, 
                #{store_dayoff, jdbcType=VARCHAR}, #{introduce, jdbcType=VARCHAR}, 
                #{inventory, jdbcType=VARCHAR}, #{store_price, jdbcType=VARCHAR}, 
                #{store_master_name, jdbcType=VARCHAR}, #{store_registration_number, jdbcType=VARCHAR})

              SELECT 1
              FROM dual ;



              <foreach collection="now_state" item="item" index="i" >
                SELECT V_SENSOR + 1 INTO V_SENSOR FROM dual ;

                INSERT INTO SENSOR (SENSOR_ID, LATITUDE, LONGITUDE, STORE_NUMBER, sensor_number)
                VALUES (V_SENSOR  , 1 , 2 ,V_STORE_NUM, #{i, jdbcType=VARCHAR}+1 );
                INSERT INTO STATE(INVENTORY_NUMBER, STORE_NUMBER, SENSOR_ID, NOW_STATE, CREATE_DATE, CHANGE_DATE)
                VALUES (V_SENSOR  , V_STORE_NUM , V_SENSOR , #{item, jdbcType=VARCHAR}, sysdate, sysdate );


                COMMIT;
              </foreach>

            END;

          </insert>

          <!-- 가게 이미지 저장 -->
          <insert id="companyImg_insert">
            DECLARE
              V_MAX_ID number;
            BEGIN

              SELECT NVL(MAX(FILE_ID),0)  INTO  V_MAX_ID from store_file;

              insert into store_file (file_id, store_number, imgpath, imgname, rank)
              values (V_MAX_ID+1, SEQ_STORE.currval, #{imgpath, jdbcType=VARCHAR}, #{imgname, jdbcType=VARCHAR}, #{rank, jdbcType=VARCHAR});
            end;
          </insert>
          ```
5. 회원가입 [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/java/com/hanul/alphacar/HomeMemberController.java)</br>
  

## 소스 코드

  - [전체 소스코드](https://github.com/holic4570/AlphaCar/tree/main/workspace/alphacar/src/main)

# 스프링부트로 옮기기(2022.01.01 ~)

## 수정 된 DB 설계도
![db_update](https://user-images.githubusercontent.com/90816804/147851446-05d711d9-a847-4621-b00f-d9690a171741.png)

