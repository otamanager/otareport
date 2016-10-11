package com.base.model;

public class ResultData {

	public static int NO_DATA = 1000;

	public static int LOGIN_ERROR = 1001;

	public static int SOURCE_ERROR = 1002;

	public static int TOKEN_ERROR = 1005;

	private int code = 0;

	private String message;

	private String status;
	
	/**
	 * 页面请求新产生token 一般异步请求用
	 */
	private String token;

	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return 返回{@linkplain #token}
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param 设定{@linkplain #token}
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
