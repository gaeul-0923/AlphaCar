package favorite;


public class FavoriteCustomerVO {
    private int fav_number,store_number ;
    private String customer_email;
    private String imgpath,store_name;

	/*
	 * public FavoriteVO(int fav_number, int store_number, String customer_email,
	 * String imgpath, String store_name) { this.fav_number = fav_number;
	 * this.store_number = store_number; this.customer_email = customer_email;
	 * this.imgpath = imgpath; this.store_name = store_name; }
	 */
    
    public int getFav_number() {
        return fav_number;
    }

    public void setFav_number(int fav_number) {
        this.fav_number = fav_number;
    }

    public int getStore_number() {
        return store_number;
    }

    public void setStore_number(int store_number) {
        this.store_number = store_number;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
}
