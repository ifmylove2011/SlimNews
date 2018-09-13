package com.xter.slimnews.data.entity;

import com.litesuits.orm.db.annotation.Table;
import com.xter.slimnews.data.constant.LC;
import com.xter.support.entity.BaseModel;

/**
 * Created by XTER on 2018/9/10.
 * 资讯频道
 */
public class NewsChannel extends BaseModel{
	public String channelName;
	public int updateNewsNum;

	public NewsChannel(String channelName) {
		this.channelName = channelName;
	}

	public NewsChannel(String channelName, int updateNewsNum) {
		this.channelName = channelName;
		this.updateNewsNum = updateNewsNum;
	}
}
