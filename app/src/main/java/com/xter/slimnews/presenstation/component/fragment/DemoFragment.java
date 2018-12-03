package com.xter.slimnews.presenstation.component.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xter.slimnews.R;
import com.xter.support.component.BaseFragment;
import com.xter.support.gen.BasePresenter;

/**
 * Created by XTER on 2018/11/20.
 * 测试
 */

public class DemoFragment extends BaseFragment {
	@Override
	public View inflate(LayoutInflater inflater, ViewGroup container) {
		return inflater.inflate(R.layout.fragment_demo, container, false);
	}

	@Override
	public void initData(Bundle savedInstanceState) {

	}

	public void set(int a){

	}

	public void set(String a){

	}

	@Override
	protected BasePresenter genPresenter() {
		return null;
	}
}
