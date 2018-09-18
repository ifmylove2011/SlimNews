package com.xter.slimnews.data.entity;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.xter.slimnews.data.constant.LC;
import com.xter.support.entity.BaseModel;

/**
 * Created by XTER on 2018/9/10.
 * 易源资讯频道
 */
@Table(LC.TABLE_NEWS_CHANNEL)
public class NewsChannel extends BaseModel{
	@PrimaryKey(AssignType.BY_MYSELF)
	public String channelId;
	public String name;

	public NewsChannel(String channelId, String name) {
		this.channelId = channelId;
		this.name = name;
	}

	@Override
	public String toString() {
		return "NewsChannel{" +
				"channelId='" + channelId + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
