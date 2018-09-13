package com.xter.slimnews.domain;

import com.xter.slimnews.data.constant.NC;
import com.xter.slimnews.data.entity.JiSuResponse;
import com.xter.slimnews.data.entity.News;
import com.xter.slimnews.data.entity.NewsResponse;
import com.xter.slimnews.data.source.NewsAPI;
import com.xter.support.interactor.UseCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by XTER on 2018/9/11.
 * 更新资讯
 */

public class NewsListUseCase extends UseCase<JiSuResponse<NewsResponse>, NewsListUseCase.Request> {

	private final NewsAPI newsAPI;
	private Map<String, Object> params;

	public NewsListUseCase(Scheduler observerThread, Scheduler subcriberThread, NewsAPI newsAPI) {
		super(observerThread, subcriberThread);
		this.newsAPI = newsAPI;
		params = new HashMap<>();
	}

	@Override
	protected Observable<JiSuResponse<NewsResponse>> buildUseCaseObservable(Request request) {
		params.put("channel", request.channel);
		params.put("start", request.start);
		params.put("num", request.num == 0 ? 10 : request.num);
		params.put("appkey", NC.JISU_API_APPKEY);
		return newsAPI.getNewsChannel(params);
	}


	public static final class Request {
		public final String channel;
		public final int start;
		public final int num;

		public Request(String channel, int start, int num) {
			this.channel = channel;
			this.start = start;
			this.num = num;
		}
	}
}
