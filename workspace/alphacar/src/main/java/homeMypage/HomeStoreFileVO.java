package homeMypage;

import java.util.ArrayList;
import java.util.Date;

public class HomeStoreFileVO {
	private int file_id       ;
	private int store_number  ;
	private int rank          ;
	private String imgpath       ;
	private String imgname       ;
	
	//private ArrayList<String> imgpath = new ArrayList<String>();
	//private ArrayList<String> imgname = new ArrayList<String>();
	
//	private class img{
//		private String imgpath       ;
//		private String imgname       ;
//		
//		private ArrayList<String> imgpath = new ArrayList<String>();
//		private ArrayList<String> imgname = new ArrayList<String>();
//		
//	}
	
	
	

	public int getFile_id() {
		return file_id;
	}

//	public ArrayList<String> getImgpath() {
//		return imgpath;
//	}
//
//	public void setImgpath(ArrayList<String> imgpath) {
//		this.imgpath = imgpath;
//	}
//
//	public ArrayList<String> getImgname() {
//		return imgname;
//	}
//
//	public void setImgname(ArrayList<String> imgname) {
//		this.imgname = imgname;
//	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public int getStore_number() {
		return store_number;
	}

	public void setStore_number(int store_number) {
		this.store_number = store_number;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
		
	
	
}
