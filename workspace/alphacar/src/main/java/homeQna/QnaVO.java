package homeQna;

import java.util.Date;

public class QnaVO {
	private int    qna_id   ;
	private String customer_email   ;
	private String customer_name        ;
	private String qna_title  ;
	private String qna_content   ;
	private Date   qna_writedate   ;
	private int    qna_readcnt   ;
	private String qna_filename   ;
	private String qna_filepath   ;
	private int qna_root   ;
	private int qna_step   ; 
	private int qna_indent ;
	private String qna_attribute;
	private String qna_time;
	private String qna_pid ;
	
	
	public String getQna_pid() {
		return qna_pid;
	}
	public void setQna_pid(String qna_pid) {
		this.qna_pid = qna_pid;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Date getQna_writedate() {
		return qna_writedate;
	}
	public void setQna_writedate(Date qna_writedate) {
		this.qna_writedate = qna_writedate;
	}
	public int getQna_readcnt() {
		return qna_readcnt;
	}
	public void setQna_readcnt(int qna_readcnt) {
		this.qna_readcnt = qna_readcnt;
	}
	public String getQna_filename() {
		return qna_filename;
	}
	public void setQna_filename(String qna_filename) {
		this.qna_filename = qna_filename;
	}
	public String getQna_filepath() {
		return qna_filepath;
	}
	public void setQna_filepath(String qna_filepath) {
		this.qna_filepath = qna_filepath;
	}
	public int getQna_root() {
		return qna_root;
	}
	public void setQna_root(int qna_root) {
		this.qna_root = qna_root;
	}
	public int getQna_step() {
		return qna_step;
	}
	public void setQna_step(int qna_step) {
		this.qna_step = qna_step;
	}
	public int getQna_indent() {
		return qna_indent;
	}
	public void setQna_indent(int qna_indent) {
		this.qna_indent = qna_indent;
	}
	public String getQna_attribute() {
		return qna_attribute;
	}
	public void setQna_attribute(String qna_attribute) {
		this.qna_attribute = qna_attribute;
	}
	public String getQna_time() {
		qna_time = common.Time.txtDate(getQna_writedate());
		return qna_time;
	}
	public void setQna_time(String qna_time) {
		this.qna_time = qna_time;
	}
	
	
	
}
