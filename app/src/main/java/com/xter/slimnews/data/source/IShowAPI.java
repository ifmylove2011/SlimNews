package com.xter.slimnews.data.source;

import com.xter.slimnews.data.entity.NewsChannelBean;
import com.xter.slimnews.data.entity.NewsPageBean;
import com.xter.slimnews.data.entity.ShowReponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by XTER on 2018/4/9.
 * 易源专用
 */
public interface IShowAPI {

	@FormUrlEncoded
	@POST("109-34")
	Observable<ShowReponse<NewsChannelBean>> getNewsChannelBean(@FieldMap Map<String,Object> params);

	@FormUrlEncoded
	@POST("109-35")
	Observable<ShowReponse<NewsPageBean>> getNewsBean(@FieldMap Map<String,Object> params);
}
