package com.xter.slimnews.data.entity;

import com.xter.support.entity.BaseModel;

/**
 * @author XTER
 * @date 2018/3/21.
 * 【极速数据】平台的响应数据
 */

public class JiSuResponse<T> extends BaseModel {
	public String status;
	public String msg;
	public T result;

	@Override
	public String toString() {
		return "JiSuResponse{" +
				"status='" + status + '\'' +
				", msg='" + msg + '\'' +
				", result=" + result +
				'}';
	}
}
