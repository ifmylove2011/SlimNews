package com.xter.slimnews.data.db;

import com.xter.slimnews.data.entity.NewsChannel;

import java.util.List;

/**
 * Created by XTER on 2018/9/18.
 * 常用数据快捷查询
 */

public class NewsDao {

	public static List<NewsChannel> getNewsChannel() {
		return DBM.getDefaultOrm().query(NewsChannel.class);
	}
}
