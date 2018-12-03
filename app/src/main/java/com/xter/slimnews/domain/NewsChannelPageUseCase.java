package com.xter.slimnews.domain;

import com.xter.slimnews.data.constant.NC;
import com.xter.slimnews.data.entity.NewsChannelBean;
import com.xter.slimnews.data.entity.ShowReponse;
import com.xter.slimnews.data.source.NewsAPI;
import com.xter.support.interactor.UseCase;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by XTER on 2018/9/18.
 * 请求频道
 */

public class NewsChannelPageUseCase extends UseCase<ShowReponse<NewsChannelBean>,Void> {

	private final NewsAPI newsAPI;
	private Map<String,Object> params;


	public NewsChannelPageUseCase(Scheduler observerThread, Scheduler subcriberThread, NewsAPI newsAPI) {
		super(observerThread, subcriberThread);
		this.newsAPI = newsAPI;
		params = new HashMap<>();
	}

	@Override
	protected Observable<ShowReponse<NewsChannelBean>> buildUseCaseObservable(Void request) {
		params.put("showapi_appid", NC.APP_ID_SHOWAPI);
		params.put("showapi_sign", NC.APP_KEY_SHOWAPI);
//		params.put("showapi_timestamp", getTime());
//		params.put("showapi_sign_method", "md5");
//		params.put("showapi_res_gzip", "1");
		return newsAPI.getNewsChannelPage(params);
	}
}
