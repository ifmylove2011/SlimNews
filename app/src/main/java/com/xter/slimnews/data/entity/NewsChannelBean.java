package com.xter.slimnews.data.entity;

import com.google.gson.annotations.SerializedName;
import com.xter.support.entity.BaseModel;

import java.util.ArrayList;

/**
 * Created by XTER on 2018/9/18.
 * 易源接口
 */

public class NewsChannelBean extends BaseModel {

	@SerializedName("ret_code")
	public int retCode;

	public int totalNum;

	public ArrayList<NewsChannel> channelList;

	@Override
	public String toString() {
		return "NewsChannelBean{" +
				"retCode=" + retCode +
				", totalNum=" + totalNum +
				", channelList=" + channelList +
				'}';
	}
}
