package com.xter.slimnews.presenstation.presenter;

import com.xter.slimnews.data.constant.NC;
import com.xter.slimnews.data.db.DBM;
import com.xter.slimnews.data.entity.JiSuResponse;
import com.xter.slimnews.data.entity.NewsResponse;
import com.xter.slimnews.domain.NewsListUseCase;
import com.xter.slimnews.presenstation.gen.INewsListRule;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.L;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by XTER on 2018/9/11.
 * 资讯界面
 */

public class NewsListPresenter extends BasePresenter<INewsListRule.V> implements INewsListRule.P {

	private final NewsListUseCase newsListUseCase;
	private int length;
	private String channel;

	public NewsListPresenter(NewsListUseCase newsListUseCase) {
		this.newsListUseCase = newsListUseCase;
	}

	@Override
	public void loadNews(String channel, int start, int num) {
		this.channel = channel;
		newsListUseCase.execute(new NewsListObserver(), new NewsListUseCase.Request(channel, start, num));
	}

	@Override
	public void loadMoreNews(int num) {
		newsListUseCase.execute(new NewsListObserver(), new NewsListUseCase.Request(channel, length, num));
	}

	@Override
	public void detach() {
		newsListUseCase.dispose();
	}

	public final class NewsListObserver extends DisposableObserver<JiSuResponse<NewsResponse>> {

		@Override
		public void onNext(JiSuResponse<NewsResponse> newsResponseJiSuResponse) {
			if (NC.STATUS_OK.equals(newsResponseJiSuResponse.status)) {
				getView().loadNews(newsResponseJiSuResponse.result.news);
				length += newsResponseJiSuResponse.result.news.size();
				DBM.getDefaultOrm().save(newsResponseJiSuResponse.result);
			}
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
			getView().hideLoading();
		}

		@Override
		public void onComplete() {
			getView().hideLoading();
			L.i("加载成功");
		}
	}
}
