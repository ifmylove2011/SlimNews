package com.xter.slimnews.presenstation.component.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xter.slimnews.R;
import com.xter.slimnews.data.entity.News;
import com.xter.slimnews.presenstation.component.adapter.NewsListAdapter;
import com.xter.slimnews.presenstation.gen.INewsListRule;
import com.xter.slimnews.presenstation.presenter.NewsListPresenter;
import com.xter.slimnews.presenstation.util.InjectUtil;
import com.xter.support.adapter.QuickItemDecoration;
import com.xter.support.adapter.QuickRecycleAdapter;
import com.xter.support.component.BaseFragment;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.ActivityUtil;
import com.xter.support.util.Preconditions;

import java.util.List;

import butterknife.BindView;

/**
 * Created by XTER on 2018/9/10.
 * 资讯界面
 */

public class NewsFragment extends BaseFragment implements INewsListRule.V {

	private static final String CHANNEL = "channel";

	public static NewsFragment newInstance(String channel) {
		Bundle args = new Bundle();
		args.putString(CHANNEL, channel);
		NewsFragment fragment = new NewsFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@BindView(R.id.srl_news)
	SwipeRefreshLayout srlNews;

	@BindView(R.id.rv_news_abstract)
	RecyclerView rvNewsList;

	NewsListAdapter newsListAdapter;

	private INewsListRule.P newsPresenter;


	@Override
	public View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_news, container, false);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		String channel = Preconditions.checkNotNull(getArguments()).getString(CHANNEL);
		if (!TextUtils.isEmpty(channel)) {
			newsPresenter.loadNews(channel, 0, 10);
		}

		newsListAdapter = new NewsListAdapter(getActivity(), R.layout.item_news_abstract, null);
		rvNewsList.setLayoutManager(new LinearLayoutManager(getActivity()));
		rvNewsList.addItemDecoration(new QuickItemDecoration(getActivity(), QuickItemDecoration.VERTICAL_LIST));
		rvNewsList.setAdapter(newsListAdapter);

		srlNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				newsPresenter.loadMoreNews(10);
			}
		});

		newsListAdapter.setOnItemClickLitener(new QuickRecycleAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
				showDetail(newsListAdapter.getItem(position));
				newsListAdapter.getItem(position).isRead = true;
				newsListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onItemLongClick(View view, int position) {

			}
		});
	}

	private void showDetail(News news) {
		NewsDetailFragment newsDetailFragment = NewsDetailFragment.newInstance(news.url);
		ActivityUtil.showFragment(getFragmentManager(), R.id.fl_content, newsDetailFragment, news.title);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		newsPresenter = new NewsListPresenter(InjectUtil.getNewsListUseCase());
	}

	@Override
	protected BasePresenter genPresenter() {
		return (BasePresenter) newsPresenter;
	}

	@Override
	public void loadNews(List<News> newsList) {
		newsListAdapter.addAllFirst(newsList);
	}

	@Override
	public void loadSuccess(String message) {

	}

	@Override
	public void loadFailed(String exception) {

	}

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {
		srlNews.setRefreshing(false);
	}

}
