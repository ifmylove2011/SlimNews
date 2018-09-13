package com.xter.slimnews.presenstation.util;

import com.xter.slimnews.data.source.NewsAPI;
import com.xter.slimnews.domain.NewsChannelUseCase;
import com.xter.slimnews.domain.NewsListUseCase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by XTER on 2018/9/10.
 * 生成实例
 */

public class InjectUtil {

	public static NewsChannelUseCase getNewsChannelUseCase(){
		return new NewsChannelUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), NewsAPI.get());
	}

	public static NewsListUseCase getNewsListUseCase(){
		return new NewsListUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), NewsAPI.get());
	}
}
