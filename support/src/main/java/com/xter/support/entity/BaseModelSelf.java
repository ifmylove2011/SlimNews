package com.xter.support.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by XTER on 2017/10/28.
 * model基类，id自命，依赖LiteOrm库
 */
public abstract class BaseModelSelf implements Serializable {
	@PrimaryKey(AssignType.BY_MYSELF)
	@Column("_id")
	public long id;
}
