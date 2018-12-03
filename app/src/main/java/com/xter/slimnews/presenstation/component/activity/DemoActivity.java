package com.xter.slimnews.presenstation.component.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;

import com.xter.slimnews.R;
import com.xter.slimnews.data.entity.NewsChannel;
import com.xter.support.util.L;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DemoActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);

//		testRx();
	}


	public void testRx() {
		Observable.interval(1, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
			@Override
			public String apply(Long aLong) throws Exception {
				L.d(Thread.currentThread().getName() + "," + aLong);
				return aLong + "--";
			}
		}).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io()).doOnNext(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				L.d(Thread.currentThread().getName() + "," + s);
			}
		}).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				L.d(Thread.currentThread().getName() + "," + s);
			}
		});
	}

}
