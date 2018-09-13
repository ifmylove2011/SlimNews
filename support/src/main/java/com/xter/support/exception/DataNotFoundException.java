package com.xter.support.exception;

/**
 * Created by XTER on 2017/10/25.
 * 无此数据
 */
public class DataNotFoundException extends Exception {
	public DataNotFoundException() {
	}

	public DataNotFoundException(String message) {
		super(message);
	}
}
