package com.xter.slimnews.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.SQLiteHelper;
import com.xter.slimnews.data.constant.LC;
import com.xter.slimnews.presenstation.component.app.SlimApp;
import com.xter.support.util.L;

/**
 * Created by XTER on 2018/4/21.
 * 数据库管理 --DataBaseManager
 */
public class DBM {
	private static LiteOrm liteOrm;

	/**
	 * 获取默认的orm，使用级联
	 * context建议使用Application级别
	 *
	 * @return DefaultOrm
	 */
	public static LiteOrm getDefaultOrm(Context context) {
		if (liteOrm == null) {
			DataBaseConfig dataBaseConfig = new DataBaseConfig(context, LC.DB_DEFAULT_NAME, false, LC.DB_VERSION, updateListener);
			liteOrm = LiteOrm.newCascadeInstance(dataBaseConfig);
		}
		return liteOrm;
	}

	public static LiteOrm getDefaultOrm() {
		return getDefaultOrm(SlimApp.getContext());
	}

	private static SQLiteHelper.OnUpdateListener updateListener = new SQLiteHelper.OnUpdateListener() {
		@Override
		public void onUpdate(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
			L.i("数据库有升级操作");
			for (int i = oldVersion; i < newVersion; i++) {
				switch (i) {
					case 1:
						upgradeToVersion2(sqLiteDatabase);
						break;
					default:
						break;
				}
			}
		}
	};

	private static void upgradeToVersion2(SQLiteDatabase db){

	}
}
