package com.xter.support.exception;

/**
 * Created by XTER on 2017/10/25.
 * 数据库操作异常
 */
public class DataOperationException extends Exception {

	public DataOperationException() {
	}

	public DataOperationException(String message) {
		super(message);
	}
}
