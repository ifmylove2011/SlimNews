package com.xter.slimnews.presenstation.presenter;

import com.xter.slimnews.data.entity.NewsPageBean;
import com.xter.slimnews.data.entity.ShowReponse;
import com.xter.slimnews.domain.NewsPageUseCase;
import com.xter.slimnews.presenstation.gen.INewsListRule;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.L;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by XTER on 2018/9/11.
 * 资讯界面
 */

public class NewsListPresenter extends BasePresenter<INewsListRule.V> implements INewsListRule.P {

	private final NewsPageUseCase newsPageUseCase;
	private NewsPageUseCase.Request request;

	public NewsListPresenter(NewsPageUseCase newsPageUseCase) {
		this.newsPageUseCase = newsPageUseCase;
		request = new NewsPageUseCase.Request();
	}

	@Override
	public void loadNewsContent(String channelId, int page) {
		request.channelId = channelId;
		request.page = String.valueOf(page);
		newsPageUseCase.execute(new NewsPageObserver(), request);
	}

	@Override
	public void loadMoreNews() {
		request.page = String.valueOf(Integer.parseInt(request.page) + 1);
		newsPageUseCase.execute(new NewsPageObserver(), request);
	}

	@Override
	public void detach() {
		newsPageUseCase.dispose();
	}

	public final class NewsPageObserver extends DisposableObserver<ShowReponse<NewsPageBean>> {

		@Override
		public void onNext(ShowReponse<NewsPageBean> newsPageBeanShowReponse) {
			if (newsPageBeanShowReponse.resCode == 0) {
				getView().loadNewsContent(newsPageBeanShowReponse.resBody.newsPage.newsContentList);
			} else {
				L.w(newsPageBeanShowReponse.resError);
			}
		}

		@Override
		public void onError(Throwable e) {
			getView().hideLoading();
			e.printStackTrace();
		}

		@Override
		public void onComplete() {
			getView().hideLoading();
			L.i("加载资讯完成");
		}
	}

}
