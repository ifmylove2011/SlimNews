package com.xter.slimnews.domain;

import com.xter.slimnews.data.entity.JiSuResponse;
import com.xter.slimnews.data.source.NewsAPI;
import com.xter.support.interactor.UseCase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by XTER on 2018/9/10.
 * 获取新闻频道
 */

public class NewsChannelUseCase extends UseCase<JiSuResponse<List<String>>,String> {

	private final NewsAPI newsAPI;

	public NewsChannelUseCase(Scheduler observerThread, Scheduler subcriberThread, NewsAPI newsAPI) {
		super(observerThread, subcriberThread);
		this.newsAPI = newsAPI;
	}

	@Override
	protected Observable<JiSuResponse<List<String>>> buildUseCaseObservable(String request) {
		return newsAPI.getNewsChannel(request);
	}
}
