package homeNotice;

public class HomeNoticeCommentVO {

	private int notice_coment_id, notice_id;
	private String customer_name, customer_email, coment_content, coment_writedate;
	
	
	
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getNotice_coment_id() {
		return notice_coment_id;
	}
	public void setNotice_coment_id(int notice_coment_id) {
		this.notice_coment_id = notice_coment_id;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getComent_content() {
		return coment_content;
	}
	public void setComent_content(String coment_content) {
		this.coment_content = coment_content;
	}
	public String getComent_writedate() {
		return coment_writedate;
	}
	public void setComent_writedate(String coment_writedate) {
		this.coment_writedate = coment_writedate;
	}
}
