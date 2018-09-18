package com.xter.slimnews.presenstation.presenter;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xter.slimnews.data.constant.NC;
import com.xter.slimnews.data.db.DBM;
import com.xter.slimnews.data.db.NewsDao;
import com.xter.slimnews.data.entity.NewsChannel;
import com.xter.slimnews.data.entity.NewsChannelBean;
import com.xter.slimnews.data.entity.ShowReponse;
import com.xter.slimnews.domain.NewsChannelPageUseCase;
import com.xter.slimnews.presenstation.gen.IMainRule;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.L;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by XTER on 2018/9/10.
 * 主界面
 */

public class MainPresenter extends BasePresenter<IMainRule.V> implements IMainRule.P {

	private final NewsChannelPageUseCase newsChannelUseCase;

	public MainPresenter(NewsChannelPageUseCase newsChannelUseCase) {
		this.newsChannelUseCase = newsChannelUseCase;
	}

	@Override
	public void requestPermission(Activity activity) {
		RxPermissions rxPermissions = new RxPermissions(activity);
		rxPermissions.requestEach(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}).subscribe(new Consumer<Permission>() {
			@Override
			public void accept(Permission permission) throws Exception {
				if (permission.granted) {
					// 用户已经同意该权限
					L.w(permission.name + " is granted.");
				} else if (permission.shouldShowRequestPermissionRationale) {
					// 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
					L.w(permission.name + " is denied. More info should be provided.");
				} else {
					// 用户拒绝了该权限，并且选中『不再询问』
					L.w(permission.name + " is denied.");
				}
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {
				throwable.printStackTrace();
			}
		});
	}

	@Override
	public void listening() {

	}

	@Override
	public void loadNewsChannel() {
		List<NewsChannel> channels = NewsDao.getNewsChannel();
		if (channels != null && channels.size() > 0) {
			getView().loadNewsChannel(channels);
		} else {
			newsChannelUseCase.execute(new NewsChannelPageObserver(), null);
		}
	}

	@Override
	public void detach() {
		newsChannelUseCase.dispose();
	}


	public final class NewsChannelPageObserver extends DisposableObserver<ShowReponse<NewsChannelBean>>{

		@Override
		public void onNext(ShowReponse<NewsChannelBean> newsChannelBeanShowReponse) {
			if(NC.RES_CODE_OK == newsChannelBeanShowReponse.resCode){
				getView().loadNewsChannel(newsChannelBeanShowReponse.resBody.channelList);
				DBM.getDefaultOrm().save(newsChannelBeanShowReponse.resBody.channelList);
			}else{
				L.w(newsChannelBeanShowReponse.resError);
			}
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
		}

		@Override
		public void onComplete() {
			L.i("加载频道完成");
		}
	}
}
