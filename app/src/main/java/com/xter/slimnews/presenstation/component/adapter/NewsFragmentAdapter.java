package com.xter.slimnews.presenstation.component.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xter.slimnews.data.entity.NewsChannel;
import com.xter.slimnews.presenstation.component.fragment.NewsFragment;
import com.xter.support.util.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XTER on 2018/9/12.
 * 界面管理器
 */

public class NewsFragmentAdapter extends FragmentPagerAdapter {
	private List<NewsChannel> channels;
	private Map<String, NewsFragment> fragmentMap;

	public NewsFragmentAdapter(FragmentManager fm, List<NewsChannel> channels) {
		super(fm);
		this.channels = channels;
		fragmentMap = new HashMap<>(channels.size());
	}

	@Override
	public Fragment getItem(int position) {
		NewsChannel newsChannel = channels.get(position);
		L.i(newsChannel.toString());
		NewsFragment fragment;
		if (fragmentMap.containsKey(newsChannel.channelId)) {
			fragment = fragmentMap.get(newsChannel.channelId);
		} else {
			fragment = NewsFragment.newInstance(newsChannel.channelId);
			fragmentMap.put(newsChannel.channelId, fragment);
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return channels.size();
	}
}
