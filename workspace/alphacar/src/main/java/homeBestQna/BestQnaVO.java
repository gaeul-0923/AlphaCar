package homeBestQna;

import java.util.Date;

public class BestQnaVO {
	private int best_qna_id           ;
	private String customer_email        ;
	private String customer_name        ;
	private String best_qna_title        ;
	private String best_qna_content      ;
	private Date best_qna_writedate    ;
	private int best_qna_readcnt      ;
	private String best_qna_filename     ;
	private String best_qna_filepath     ;
	private String best_qna_attribute    ; 
	private String best_qna_time; 
	
	public String getBest_qna_time() {
		best_qna_time = common.Time.txtDate(getBest_qna_writedate());
		return best_qna_time;
	}
	public void setBest_qna_time(String best_qna_time) {
		this.best_qna_time = best_qna_time;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getBest_qna_id() {
		return best_qna_id;
	}
	public void setBest_qna_id(int best_qna_id) {
		this.best_qna_id = best_qna_id;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getBest_qna_title() {
		return best_qna_title;
	}
	public void setBest_qna_title(String best_qna_title) {
		this.best_qna_title = best_qna_title;
	}
	public String getBest_qna_content() {
		return best_qna_content;
	}
	public void setBest_qna_content(String best_qna_content) {
		this.best_qna_content = best_qna_content;
	}
	public Date getBest_qna_writedate() {
		return best_qna_writedate;
	}
	public void setBest_qna_writedate(Date best_qna_writedate) {
		this.best_qna_writedate = best_qna_writedate;
	}
	public int getBest_qna_readcnt() {
		return best_qna_readcnt;
	}
	public void setBest_qna_readcnt(int best_qna_readcnt) {
		this.best_qna_readcnt = best_qna_readcnt;
	}
	public String getBest_qna_filename() {
		return best_qna_filename;
	}
	public void setBest_qna_filename(String best_qna_filename) {
		this.best_qna_filename = best_qna_filename;
	}
	public String getBest_qna_filepath() {
		return best_qna_filepath;
	}
	public void setBest_qna_filepath(String best_qna_filepath) {
		this.best_qna_filepath = best_qna_filepath;
	}
	public String getBest_qna_attribute() {
		return best_qna_attribute;
	}
	public void setBest_qna_attribute(String best_qna_attribute) {
		this.best_qna_attribute = best_qna_attribute;
	}
	
	
}
