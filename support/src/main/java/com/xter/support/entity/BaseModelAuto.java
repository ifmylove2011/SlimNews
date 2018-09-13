package com.xter.support.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by XTER on 2017/10/28.
 * model基类，id自增，依赖LiteOrm库
 */
public abstract class BaseModelAuto implements Serializable {
	@PrimaryKey(AssignType.AUTO_INCREMENT)
	@Column("_id")
	public long id;
}
