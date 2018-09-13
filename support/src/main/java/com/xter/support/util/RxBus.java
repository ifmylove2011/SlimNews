package com.xter.support.util;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * 利用RxJava2实现类似事件总线的工具类
 * 由于RxJava本身的一些机制影响，一旦onComplete之后，可能会出现无法再次订阅的情况
 */
public class RxBus {

	private final FlowableProcessor<Object> mBus;

	private RxBus() {
		mBus = PublishProcessor.create().toSerialized();
	}

	private static class Holder {
		private static RxBus instance = new RxBus();
	}

	public static RxBus getDefault() {
		return Holder.instance;
	}

	public void post(@NonNull Object obj) {
		mBus.onNext(obj);
	}

	public <T> Flowable<T> register(Class<T> clz) {
		return mBus.ofType(clz);
	}

	public Flowable<Object> register() {
		return mBus;
	}

	public void unregisterAll() {
		//会将所有由mBus生成的Flowable都置completed状态后续的所有消息都收不到了
		mBus.onComplete();
	}

	public boolean hasSubscribers() {
		return mBus.hasSubscribers();
	}
}
