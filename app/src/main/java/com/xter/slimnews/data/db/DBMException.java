package com.xter.slimnews.data.db;

import java.util.Locale;

/**
 * Created by XTER on 2017/5/11.
 * 数据库异常
 */
public class DBMException extends Exception {
	public DBMException() {
	}

	public DBMException(String detailMessage) {
		super(detailMessage);
	}

	public DBMException(String tableName, String whereClause, Object whereArgs) {
		this(String.format(Locale.CHINA, "cannot find %s where %s is %s", tableName, whereClause, whereArgs));
	}
}
