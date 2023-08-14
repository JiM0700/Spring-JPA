package com.jpacrud.demo.model;

public class Response {
	
	private int ResponseCode;
	
	private String ResponseMessage;
	
	private String data;
	
	private Object jData;

	public int getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(int responseCode) {
		ResponseCode = responseCode;
	}

	public String getResponseMessage() {
		return ResponseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		ResponseMessage = responseMessage;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Object getjData() {
		return jData;
	}

	public void setjData(Object jData) {
		this.jData = jData;
	}
	
	
	
	
}
