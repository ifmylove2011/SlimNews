package com.xter.support.interactor;

import com.xter.support.util.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * 修改自android10 / Android-CleanArchitecture
 * 业务基类，依赖于RxJava2
 */
public abstract class UseCase<P, Q> {
	private final Scheduler observerThread;
	private final Scheduler subcriberThread;
	private final CompositeDisposable disposables;

	public UseCase(Scheduler observerThread, Scheduler subcriberThread) {
		this.observerThread = observerThread;
		this.subcriberThread = subcriberThread;
		this.disposables = new CompositeDisposable();
	}

	protected abstract Observable<P> buildUseCaseObservable(Q request);

	public void execute(DisposableObserver<P> observer, Q request) {
		Preconditions.checkNotNull(observer);
		final Observable<P> observable = this.buildUseCaseObservable(request)
				.subscribeOn(observerThread)
				.observeOn(subcriberThread);
		addDisposable(observable.subscribeWith(observer));
	}

	/**
	 * 取消事务订阅
	 */
	public void dispose() {
		if (!disposables.isDisposed()) {
			disposables.dispose();
		}
	}

	/**
	 * 添加事务
	 *
	 * @param disposable 事务
	 */
	public void addDisposable(Disposable disposable) {
		Preconditions.checkNotNull(disposable);
		Preconditions.checkNotNull(disposables);
		disposables.add(disposable);
	}
}
