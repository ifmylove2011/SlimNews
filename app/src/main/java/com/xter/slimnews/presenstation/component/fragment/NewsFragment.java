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
import com.xter.slimnews.data.entity.NewsChannel;
import com.xter.slimnews.data.entity.NewsContent;
import com.xter.slimnews.presenstation.component.adapter.NewsPageListAdapter;
import com.xter.slimnews.presenstation.gen.INewsListRule;
import com.xter.slimnews.presenstation.presenter.NewsListPresenter;
import com.xter.slimnews.presenstation.util.InjectUtil;
import com.xter.support.adapter.QuickItemDecoration;
import com.xter.support.adapter.QuickRecycleAdapter;
import com.xter.support.component.BaseFragment;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.ActivityUtil;
import com.xter.support.util.Preconditions;
import com.xter.support.util.RxBus;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by XTER on 2018/9/10.
 * 资讯界面
 */

public class NewsFragment extends BaseFragment implements INewsListRule.V {

	private static final String CHANNEL = "channel";

	public static NewsFragment newInstance(NewsChannel channel) {
		Bundle args = new Bundle();
		args.putSerializable(CHANNEL, channel);
		NewsFragment fragment = new NewsFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@BindView(R.id.srl_news)
	SwipeRefreshLayout srlNews;

	@BindView(R.id.rv_news_abstract)
	RecyclerView rvNewsList;

	NewsPageListAdapter newsPageListAdapter;

	private INewsListRule.P newsPresenter;


	@Override
	public View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_news, container, false);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		NewsChannel channel = (NewsChannel) Preconditions.checkNotNull(getArguments()).getSerializable(CHANNEL);
		if (!TextUtils.isEmpty(channel.channelId)) {
			newsPresenter.loadNewsContent(channel.channelId, 1);
		}

		channel.channelId = "111111111";
		RxBus.getDefault().post(channel);

		newsPageListAdapter = new NewsPageListAdapter(getActivity(), R.layout.item_news_abstract, null);
		rvNewsList.setLayoutManager(new LinearLayoutManager(getActivity()));
		rvNewsList.addItemDecoration(new QuickItemDecoration(getActivity(), QuickItemDecoration.VERTICAL_LIST));
		rvNewsList.setAdapter(newsPageListAdapter);

		srlNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				newsPresenter.loadMoreNews();
			}
		});

		newsPageListAdapter.setOnItemClickLitener(new QuickRecycleAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
				showDetail(newsPageListAdapter.getItem(position));
				newsPageListAdapter.getItem(position).isRead = true;
				newsPageListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onItemLongClick(View view, int position) {

			}
		});
	}

	private void showDetail(NewsContent newsContent) {
		NewsDetailFragment newsDetailFragment = NewsDetailFragment.newInstance(newsContent.link);
		ActivityUtil.showFragment(getFragmentManager(), R.id.fl_content, newsDetailFragment, newsContent.title);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		newsPresenter = new NewsListPresenter(InjectUtil.getNewsPageUseCase());
	}

	@Override
	protected BasePresenter genPresenter() {
		return (BasePresenter) newsPresenter;
	}

	@Override
	public void loadNewsContent(List<NewsContent> newsContentList) {
		newsPageListAdapter.addAllFirst(newsContentList);
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
