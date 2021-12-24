package homeChart;


public class ChartVO{
   
   private int tt;
   private int time;
   private int cnt;
   private int dcode;
   private String customer;
   
    
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getDcode() {
		return dcode;
	}
	
	public void setDcode(int dcode) {
		this.dcode = dcode;
	}
	
	
	public int getTime() {
		   return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getCnt() {
		return cnt;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public int getTt() {
	      return tt;
   }

   public void setTt(int tt) {
      this.tt = tt;
   }
   
   
}