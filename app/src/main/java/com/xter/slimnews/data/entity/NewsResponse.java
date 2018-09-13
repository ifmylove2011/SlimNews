package com.xter.slimnews.data.entity;

import com.google.gson.annotations.SerializedName;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.Relation;
import com.xter.slimnews.data.constant.LC;
import com.xter.support.entity.BaseModelAuto;

import java.util.ArrayList;

/**
 * Created by XTER on 2018/9/11.
 * 资讯
 */
@Table(LC.TABLE_NEWS_RES)
public class NewsResponse extends BaseModelAuto {
	public String channel;
	public int num;
	@SerializedName("list")
	@Mapping(Relation.OneToMany)
	public ArrayList<News> news;

	public NewsResponse(String channel, int num, ArrayList<News> news) {
		this.channel = channel;
		this.num = num;
		this.news = news;
	}
}
