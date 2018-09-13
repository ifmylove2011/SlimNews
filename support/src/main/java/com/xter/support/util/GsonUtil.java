package com.xter.support.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by XTER on 2017/9/13.
 * gson用
 */
public class GsonUtil {

	private static class GsonHolder {
		/**
		 * excludeFieldsWithoutExposeAnnotation表示如果数据无@Expose标记，则不序列化
		 */
		private static final Gson INSTANCE = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	public static Gson getGson() {
		return GsonHolder.INSTANCE;
	}
}
