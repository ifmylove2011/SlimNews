package com.xter.support.component;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xter.support.gen.BasePresenter;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * DialogFragment基类，仅用于以onCreateView创建的情况，onCreateDialog需自行继承DialogFragment
 */
public abstract class BaseDialogFragment extends DialogFragment {

	private Unbinder unbinder;

	protected BasePresenter presenter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflate(inflater, container);
		unbinder = ButterKnife.bind(this, rootView);
		presenter = genPresenter();
		if (presenter != null)
			//noinspection unchecked
			presenter.attachView(this);
		initData(savedInstanceState);
		return rootView;
	}

	protected abstract BasePresenter genPresenter();

	/**
	 * 构建视图
	 *
	 * @param inflater  视图渲染器
	 * @param container 视图容器
	 * @return 视图
	 */
	public abstract View inflate(LayoutInflater inflater, ViewGroup container);

	/**
	 * 初始化数据
	 */
	public abstract void initData(Bundle savedInstanceState);

	/**
	 * handler消息接收
	 *
	 * @param msg msg
	 */
	@Deprecated
	protected void uiHandleMessage(Message msg) {
	}

	@Override
	public void onDestroyView() {
		if (presenter != null) {
			presenter.detach();
			presenter.detachView();
			presenter = null;
		}
		unbinder.unbind();
		super.onDestroyView();
	}

	@Deprecated
	protected static class BaseDialogFragmentUIHandler extends Handler {
		WeakReference<BaseDialogFragment> baseFragmentWeakReference;

		public BaseDialogFragmentUIHandler(BaseDialogFragment baseFragment) {
			baseFragmentWeakReference = new WeakReference<BaseDialogFragment>(baseFragment);
		}

		@Override
		public void handleMessage(Message msg) {
			BaseDialogFragment baseFragment = baseFragmentWeakReference.get();
			baseFragment.uiHandleMessage(msg);
		}
	}
}
