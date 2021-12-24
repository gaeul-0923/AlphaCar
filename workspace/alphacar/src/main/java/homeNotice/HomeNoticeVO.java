package homeNotice;

import java.sql.Date;

public class HomeNoticeVO {
	
	private int notice_id, notice_readcnt, no, root, step, indent;
	private String notice_title, notice_content, customer_email, customer_name, notice_attribute, notice_time;
	private Date notice_writedate;
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public String getNotice_time() {
		notice_time = common.Time.txtDate(getNotice_writedate());
		return notice_time;
	}
	public void setNotice_time(String notice_time) {
		this.notice_time = notice_time;
	}
	public String getNotice_attribute() {
		return notice_attribute;
	}
	public void setNotice_attribute(String notice_attribute) {
		this.notice_attribute = notice_attribute;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public int getNotice_readcnt() {
		return notice_readcnt;
	}
	public void setNotice_readcnt(int notice_readcnt) {
		this.notice_readcnt = notice_readcnt;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public Date getNotice_writedate() {	
		return notice_writedate;
	}
	public void setNotice_writedate(Date notice_writedate) {
		this.notice_writedate = notice_writedate;	
	}

	
}
