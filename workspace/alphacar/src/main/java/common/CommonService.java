package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class CommonService {
	
	// 파일 업로드 처리
	public String fileUpload(String category, MultipartFile file, HttpSession session) {
		// 업로드 할 위치
		String resources = session.getServletContext().getRealPath("/resources");
		// D:\TeamProject\AteamAlphaCar\AlphaCar_Spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0
		// \wtpwebapps\alphacar\resources\pictures\profiles\00.jpg
		
		String folder = resources + "/pictures/" + category;
//		String save = "http://192.168.25.60:8989/alphacar/resources/pictures/"+category;
//		String folder = resources + "/upload/" + category + "/"
//				+ new SimpleDateFormat("yyyy/MM/dd").format(new Date() );
		
//		String save = "http://192.168.0.22:8989/alphacar/resources/pictures/"+category;
//		String save = "http://192.168.0.30:8989/alphacar/resources/pictures/"+category;
		String uuid =  UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		File dir = new File ( folder );
		
		if (! dir.exists() ) dir.mkdirs();
			try {
				file.transferTo(new File( folder, uuid ));
			} catch (Exception e) {
				e.printStackTrace();
			}
		// pictures\profiles\00.jpg
		return folder.substring(resources.length() + 1) + "/" + uuid;
//		return save + "/" + uuid;	
	}
	
	
	
	// 이메일 전송 메소드
	public void sendEmail(String name, String email, HttpSession session) {
		// 기본 형태 이메일 전송
		// simpleSend(name, email);
		
		// 파일 첨부하는 이메일 전송
		// attachSend(name, email, session);
		
		// html 태그 형태 이메일 전송
		htmlSend(name, email, session);
	}
	
	// html 태그 형태(파일)의 이메일을 보내는 메소드
	private void htmlSend (String name, String email, HttpSession session) {
		HtmlEmail mail = new HtmlEmail();
		
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		// 로그인하기 위한 아이디 /비번 지정
		mail.setAuthentication("dnstjszznag", "3KGE14YH1VRE");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("dnstjszznag@naver.com", "한울 IoT 관리자");
			mail.addTo(email, name);
			mail.setSubject("한울 IoT 과정 : HTML 형태");
			
			StringBuffer msg = new StringBuffer("<hmtl>");
			msg.append("<body>");
			msg.append("<a href ='https://google.com'>"
					+ "구글<img src='https://url.kr/8th3sa' /></a>");
			msg.append("<hr>");
			msg.append("<h3>한울 IoT 과정 가입 축하</h3>");
			msg.append("<p>가입을 축하합니다.</p>");
			msg.append("<p>프로젝트까지 마무리하고 모두 취업하세요.</p>");
			msg.append("</body></html>");
			
			mail.setHtmlMsg(msg.toString());
			
			EmailAttachment file = new EmailAttachment(); 
			file.setPath(session.getServletContext().getRealPath("resources/css/common.css"));
			mail.attach(file);
			
			file = new EmailAttachment();
			file.setURL(new URL("https://url.kr/8th3sa"));
			mail.attach(file);
			
			mail.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	// 파일 첨부하여 이메일 보내는 메소드
	private void attachSend(String name, String email, HttpSession session) {
		MultiPartEmail mail = new MultiPartEmail();
		
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		// 로그인하기 위한 아이디 /비번 지정
		mail.setAuthentication("cla7901", "3KGE14YH1VRE");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("cla7901@naver.com", "한울 IoT 관리자");
			mail.addTo(email, name);
			mail.setSubject("한울 IoT 과정 : 파일 첨부 형태");
			mail.setMsg("과정 가입을 축하합니다.\n 첨부된 파일을 확인하세요.");
			
			// 이메일 파일 첨부 클래스 (EmailAttachment) 생성
			EmailAttachment file = new EmailAttachment();
			file.setPath("D:\\01.IntoSpring(개발환경구축).pptx");
			mail.attach(file);
			
			// 현재 작업 중인 프로젝트 내부 파일을 보낼때 ..
			file = new EmailAttachment();
			file.setPath(session.getServletContext().getRealPath("resources/images/") 
					+ "attach.png");
			mail.attach(file);
			
			// URL 로 파일 첨부
			file = new EmailAttachment();
			file.setURL(new URL("https://news.hmgjournal.com/images_n/contents/0407_Logo_07.png"));
			mail.attach(file);
			
			mail.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 기본 형태 이메일 보내는 메소드
	private void simpleSend(String name, String email) {
		
		SimpleEmail mail = new SimpleEmail();
		
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		// 로그인하기 위한 아이디 /비번 지정
		mail.setAuthentication("cla7901", "3KGE14YH1VRE");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("cla7901@naver.com", "한울 IoT 관리자");
			mail.addTo(email, name);
			
			mail.setSubject("한울 IoT 과정");
			mail.setMsg(name + " 님! IoT 과정 가입을 축하드립니다.");
			
			mail.send();
			
		} catch (EmailException e) {
			System.out.println(e.getMessage());;
		}	
	}

	// 접근 토큰을 이용하여 프로필 API 호출하기 위하여 {access_token 과 token_type 값을 파라미터 전달}
	public String requestAPI(StringBuffer url, String property) {
		String result = "";		// result 변수 초기화 선언
		try {
//		      URL url = new URL(apiURL);
			// 연결할 개체가 HTTP 통신을 할 예정이므로 HTTP 간의 연결 개체 생성
		      HttpURLConnection con = (HttpURLConnection) new URL(url.toString() ).openConnection();
		      con.setRequestMethod("GET");
		    // 요청 헤더명이 "Authorization" 이므로 property 를 바꾸고, 
  
		      con.setRequestProperty("Authorization", property);
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode); 
		      if(responseCode >= 200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); // 한글 깨짐 처리
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();		// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();				// BufferedReader 닫기
		      con.disconnect();			// HTTP 통신 연결 종료
		      result = res.toString();		 
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		return result;
	}
	
	
	
	// API 명세 > 로그인 API 명세
	
	public String requestAPI(StringBuffer url) {
		String result = "";		// result 변수 초기화 선언
		try {
//		      URL url = new URL(apiURL);
			// 연결할 개체가 HTTP 통신을 할 예정이므로 HTTP 간의 연결 개체 생성
		      HttpURLConnection con = (HttpURLConnection) new URL(url.toString() ).openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode); 
		      if(responseCode >= 200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); // 한글 깨짐 처리
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();		// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();				// BufferedReader 닫기
		      con.disconnect();			// HTTP 통신 연결 종료
		      result = res.toString();		 
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		return result;
	}

	// 파일 다운로드 처리
	public void fileDownload(String filename, String filepath,
			 HttpSession session, HttpServletResponse response) {
		
		// 실제 파일의 위치와 파일을 찾음
		File file = new File( session.getServletContext().getRealPath("resources") + "/" + filepath );
		// 파일의 형태를 확인 
		String mime = session.getServletContext().getMimeType( filename );
		
		// 클라이언트에 파일을 첨부하여 쓰기 작업을 하는데 파일을 
		// 첨부하는 건 header에 첨부 파일 정보를 넘겨줘야 함.
		try {
			filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
			
			response.setHeader("content-disposition", "attachment; filename = " + filename);
		
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
	
}
