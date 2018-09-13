package com.xter.support.exception;

/**
 * Created by XTER on 2017/10/24.
 * 请求错误
 */
public class RequestException extends Exception{
	public RequestException() {
	}

	public RequestException(String message) {
		super(message);
	}
}
