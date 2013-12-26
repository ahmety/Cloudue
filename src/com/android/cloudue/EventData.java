package com.android.cloudue;

public class EventData {
	String detail;
	String sharingUser;
	
	public EventData(String detail, String sharingUser) {
		this.detail = detail;
		this.sharingUser = sharingUser;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getSharingUser() {
		return sharingUser;
	}
	public void setSharingUser(String sharingUser) {
		this.sharingUser = sharingUser;
	}
	

}
