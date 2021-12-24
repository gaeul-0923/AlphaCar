package member;

import java.sql.Date;

public class WebMemberVO {

	String customer_email, customer_pw, customer_name, 
		customer_picture, admin, city, addr, customer_filename, social, kakao, naver, authority_name;;
	private Date joindate;
	
	String ip_addr, com_name,auto_login;
	Date login_time;
	
	
	
	public String getAuthority_name() {
		return authority_name;
	}

	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getAuto_login() {
		return auto_login;
	}

	public void setAuto_login(String auto_login) {
		this.auto_login = auto_login;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public String getKakao() {
		return kakao;
	}

	public void setKakao(String kakao) {
		this.kakao = kakao;
	}

	public String getNaver() {
		return naver;
	}

	public void setNaver(String naver) {
		this.naver = naver;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public String getCustomer_filename() {
		return customer_filename;
	}

	public void setCustomer_filename(String customer_filename) {
		this.customer_filename = customer_filename;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_pw() {
		return customer_pw;
	}

	public void setCustomer_pw(String customer_pw) {
		this.customer_pw = customer_pw;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_picture() {
		return customer_picture;
	}

	public void setCustomer_picture(String customer_picture) {
		this.customer_picture = customer_picture;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	
}
