package com.xter.slimnews.data.source;

import com.xter.slimnews.data.entity.JiSuResponse;
import com.xter.slimnews.data.entity.News;
import com.xter.slimnews.data.entity.NewsResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by XTER on 2018/9/10.
 * 网络请求
 */

public class NewsAPI {

	private static class Holder{
		private static NewsAPI INSTANCE = new NewsAPI();
	}

	public static NewsAPI get(){
		return Holder.INSTANCE;
	}

	public Observable<JiSuResponse<List<String>>> getNewsChannel(String appKey){
		return NewsHttp.build().getNewsAPI().getNewsChannel(appKey);
	}

	public Observable<JiSuResponse<NewsResponse>> getNewsChannel(Map<String,Object> params){
		return NewsHttp.build().getNewsAPI().getNewsList(params);
	}
}
