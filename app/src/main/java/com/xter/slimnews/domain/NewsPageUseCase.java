package com.xter.slimnews.domain;

import android.text.TextUtils;

import com.xter.slimnews.data.constant.NC;
import com.xter.slimnews.data.entity.NewsPageBean;
import com.xter.slimnews.data.entity.ShowReponse;
import com.xter.slimnews.data.source.NewsAPI;
import com.xter.support.interactor.UseCase;
import com.xter.support.util.TextUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by XTER on 2018/9/18.
 * 易源新闻接口
 */

public class NewsPageUseCase extends UseCase<ShowReponse<NewsPageBean>,NewsPageUseCase.Request> {

	private final NewsAPI newsAPI;
	private Map<String,Object> params;

	public NewsPageUseCase(Scheduler observerThread, Scheduler subcriberThread, NewsAPI newsAPI) {
		super(observerThread, subcriberThread);
		this.newsAPI = newsAPI;
		params = new HashMap<>();
	}

	@Override
	protected Observable<ShowReponse<NewsPageBean>> buildUseCaseObservable(Request request) {
		params.put("showapi_appid", NC.APP_ID_SHOWAPI);
		params.put("showapi_sign", NC.APP_KEY_SHOWAPI);
//		params.put("showapi_timestamp", getTime());
//		params.put("showapi_sign_method", "md5");
//		params.put("showapi_res_gzip", "1");
		if (!TextUtils.isEmpty(request.channelId)) {
			params.put("channelId", request.channelId);
		}
		if (!TextUtils.isEmpty(request.channelName)) {
			params.put("channelName", request.channelName);
		}
		if (!TextUtils.isEmpty(request.title)) {
			params.put("title", request.title);
		}
		if (!TextUtils.isEmpty(request.page)) {
			params.put("page", request.page);
		}
		if (!TextUtils.isEmpty(request.needContent)) {
			params.put("needContent", request.needContent);
		}
		if (!TextUtils.isEmpty(request.needHtml)) {
			params.put("needHtml", request.needHtml);
		}
		if (!TextUtils.isEmpty(request.needAllList)) {
			params.put("needAllList", request.needAllList);
		}
		if (!TextUtils.isEmpty(request.maxResult)) {
			params.put("maxResult", request.maxResult);
		}
		if (!TextUtils.isEmpty(request.id)) {
			params.put("id", request.id);
		}
		return newsAPI.getNewsPage(params);
	}

	public static final class Request {
		public String channelId;
		public String channelName;
		public String title;
		public String page ;
		public String needContent;
		public String needHtml;
		public String needAllList;
		public String maxResult;
		public String id;

		public Request() {
		}
	}
}
