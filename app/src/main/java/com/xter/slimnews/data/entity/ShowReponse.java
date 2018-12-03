package com.xter.slimnews.data.entity;

import com.google.gson.annotations.SerializedName;
import com.xter.support.entity.BaseModel;

/**
 * Created by XTER on 2018/4/9.
 * ShowAPI专用头
 */
public class ShowReponse<T> extends BaseModel {

	@SerializedName("showapi_res_error")
	public String resError;

	@SerializedName("showapi_res_code")
	public int resCode;

	@SerializedName("showapi_res_id")
	public String resId;

	@SerializedName("showapi_res_body")
	public T resBody;

	@Override
	public String toString() {
		return "ShowReponse{" +
				"resError='" + resError + '\'' +
				", resCode=" + resCode +
				", resId='" + resId + '\'' +
				", resBody=" + resBody +
				'}';
	}
}
