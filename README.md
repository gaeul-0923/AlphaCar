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
2. summernote API를 이용한 게시판 구현 [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/java/com/hanul/alphacar/HomeMyPageController.java)</br>
3. chart.js 를 이용한 데이터 시각화 [[소스코드]](https://github.com/holic4570/AlphaCar/blob/main/workspace/alphacar/src/main/java/com/hanul/alphacar/HomeMyPageController.java)</br>
