package com.xter.slimnews.data.source;

import com.xter.slimnews.data.entity.JiSuResponse;
import com.xter.slimnews.data.entity.News;
import com.xter.slimnews.data.entity.NewsResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by XTER on 2018/9/10.
 * 新闻
 */

public interface INewsAPI {

	@Streaming
	@GET()
	Observable<ResponseBody> download(@Url String url);

	@GET("news/channel")
	Observable<JiSuResponse<List<String>>> getNewsChannel(@Query("appkey") String appKey);

	@GET("news/get")
	Observable<JiSuResponse<NewsResponse>> getNewsList(@QueryMap Map<String, Object> params);
}
