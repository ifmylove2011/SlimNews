package com.xter.support.entity;

import com.xter.support.util.L;

/**
 * Created by XTER on 2017/11/8.
 * 日志事件
 */
public class LogEvent extends BaseModel {
	public String time;
	public L.Level level;
	public String content;

	public LogEvent(String time, L.Level level, String content) {
		this.time = time;
		this.level = level;
		this.content = content;
	}
}
