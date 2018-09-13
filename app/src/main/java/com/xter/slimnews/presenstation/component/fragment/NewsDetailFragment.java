package com.xter.slimnews.presenstation.component.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.xter.slimnews.R;
import com.xter.slimnews.presenstation.component.app.SlimApp;
import com.xter.support.component.BaseFragment;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.L;
import com.xter.support.util.Preconditions;

import butterknife.BindView;

/**
 * Created by XTER on 2018/9/11.
 * 新闻详情
 */

public class NewsDetailFragment extends BaseFragment {

	private static final String URL = "url";

	public static NewsDetailFragment newInstance(String url) {
		Bundle args = new Bundle();
		args.putString(URL, url);
		NewsDetailFragment fragment = new NewsDetailFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@BindView(R.id.ll_detail)
	LinearLayout llLayout;

	WebView wvNews;

	@Override
	public View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_news_detail, container, false);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		wvNews = new WebView(SlimApp.getContext());
		wvNews.setLayoutParams(params);
		llLayout.addView(wvNews);

		WebSettings webSettings = wvNews.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setDefaultFontSize(72);
		webSettings.setJavaScriptEnabled(true);

		String url = Preconditions.checkNotNull(getArguments()).getString(URL);
		wvNews.loadUrl(url);
//		wvNews.loadDataWithBaseURL("about:blank",url,"text/htmll","utf-8",null);
		if (!TextUtils.isEmpty(url)) {
			wvNews.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					L.i(url);
					wvNews.loadUrl(url);
					return true;
				}
			});
		}
	}

	@Override
	protected BasePresenter genPresenter() {
		return null;
	}


	@Override
	public void onDestroyView() {
		if (wvNews != null) {
			wvNews.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
			wvNews.clearHistory();

			((ViewGroup) wvNews.getParent()).removeView(wvNews);
			wvNews.destroy();
			wvNews = null;
		}
		super.onDestroyView();
	}

}
