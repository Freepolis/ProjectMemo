package com.gilgamesh.aop;

import java.io.Serializable;

public class ResultBean <T> implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;
	public static final int NO_PERMISSION = 2;
	private String resultMsg = "success";
	private int resultCode = SUCCESS;
	private T data;
	
	public ResultBean() {
		super();
	}
	public ResultBean(T data) {
		super();
		this.data = data;
	}
	public ResultBean(Throwable e) {
		super();
		this.resultMsg = e.toString();
		this.resultCode = FAIL ;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String msg) {
		this.resultMsg = msg;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int code) {
		this.resultCode = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
