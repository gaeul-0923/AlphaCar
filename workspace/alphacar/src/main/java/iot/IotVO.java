package iot;

public class IotVO {
	private int store_number     ;
	private String now_state        ;
	private int sensor_number        ;
	
	public String getNow_state() {
		return now_state;
	}
	public void setNow_state(String now_state) {
		this.now_state = now_state;
	}
	public int getSensor_number() {
		return sensor_number;
	}
	public void setSensor_number(int sensor_number) {
		this.sensor_number = sensor_number;
	}

	public int getStore_number() {
		return store_number;
	}
	public void setStore_number(int store_number) {
		this.store_number = store_number;
	}
	
}
