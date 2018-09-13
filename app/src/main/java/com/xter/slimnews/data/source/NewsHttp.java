package com.xter.slimnews.data.source;

import android.os.Environment;

import com.xter.slimnews.data.constant.NC;
import com.xter.slimnews.presenstation.component.app.SlimApp;
import com.xter.support.util.NetUtil;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XTER on 2018/9/10.
 * 网络连接器
 */

public class NewsHttp {
	private INewsAPI iNewsAPI;
	private OkHttpClient okHttpClient;

	private void createClient() {
		File sdcache = Environment.getDownloadCacheDirectory();
		int cacheSize = 10 * 1024 * 1024;
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS)
				.readTimeout(10, TimeUnit.SECONDS)
				.cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
		builder.addInterceptor(new NewsHttp.CacheInterceptor());
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		builder.cookieJar(new JavaNetCookieJar(cookieManager));
		okHttpClient = builder.build();
	}

	public static class CacheInterceptor implements Interceptor {

		@Override
		public Response intercept(Chain chain) throws IOException {
			Request request = chain.request();

			/**
			 * 未联网获取缓存数据
			 */
			if (!NetUtil.isInternetConnection(SlimApp.getContext())) {
				//在20秒缓存有效，此处测试用，实际根据需求设置具体缓存有效时间
				CacheControl cacheControl = new CacheControl.Builder()
						.maxStale(30, TimeUnit.DAYS)
						.build();
				request = request.newBuilder()
						.cacheControl(cacheControl)
						.build();
			}

			return chain.proceed(request);
		}
	}

	private NewsHttp() {
		if (okHttpClient == null)
			createClient();
		Retrofit retrofit2 = new Retrofit.Builder().baseUrl(NC.JISU_API).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
		iNewsAPI = retrofit2.create(INewsAPI.class);
	}

	private static class NewsHttpHolder {
		private static final NewsHttp INSTANCE = new NewsHttp();
	}

	public static NewsHttp build() {
		return NewsHttpHolder.INSTANCE;
	}

	public INewsAPI getNewsAPI() {
		return iNewsAPI;
	}
}
