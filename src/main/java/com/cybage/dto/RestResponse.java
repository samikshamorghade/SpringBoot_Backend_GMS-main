package com.cybage.dto;

public class RestResponse {
	private String message;
	private Object object1;
	private Object object2;

	public RestResponse() {

	}

	public RestResponse(Object object1, String message) {
		super();
		this.message = message;
		this.object1 = object1;

	}

	@Override
	public String toString() {
		return "RestResponse [message=" + message + ", object1=" + object1 + ", object2=" + object2 + "]";
	}

	public Object getObject2() {
		return object2;
	}

	public void setObject2(Object object2) {
		this.object2 = object2;
	}

	public RestResponse(Object object1, Object object2) {
		super();
		this.object1 = object1;
		this.object2 = object2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject1() {
		return object1;
	}

	public void setObject1(Object object1) {
		this.object1 = object1;
	}
}
