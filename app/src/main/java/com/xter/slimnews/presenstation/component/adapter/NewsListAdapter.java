package com.xter.slimnews.presenstation.component.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xter.slimnews.R;
import com.xter.slimnews.data.entity.News;
import com.xter.slimnews.presenstation.util.TimeUtil;
import com.xter.support.adapter.QuickRecycleAdapter;
import com.xter.support.adapter.ViewHolder;

import java.util.List;

/**
 * Created by XTER on 2018/9/11.
 * 资讯列表
 */

public class NewsListAdapter extends QuickRecycleAdapter<News> {

	private RequestOptions options = new RequestOptions()
//			.placeholder(R.mipmap.ic_launcher)    //加载成功之前占位图
//			.error(R.mipmap.ic_launcher)    //加载错误之后的错误图
//			.override(40, 40)    //指定图片的尺寸
			//指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
//			.fitCenter()
			//指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
			.centerCrop()
//			.circleCrop()//指定图片的缩放类型为centerCrop （圆形）
			.skipMemoryCache(false)    //不跳过内存缓存
			.diskCacheStrategy(DiskCacheStrategy.RESOURCE)    //只缓存最终的图片
			;

	public NewsListAdapter(Context context, int res, List<News> data) {
		super(context, res, data);
	}

	@Override
	public void bindView(ViewHolder holder, int position) {
		TextView tvTitle = holder.getView(R.id.tv_title);
		TextView tvInfo = holder.getView(R.id.tv_info);
		ImageView ivAbstract = holder.getView(R.id.iv_image);

		News news = data.get(position);

		tvTitle.setText(news.title);
		tvInfo.setText(String.format("%s   %s", news.src, TimeUtil.getDelayFormat(news.time)));

		if(TextUtils.isEmpty(news.pic)){
			ivAbstract.setVisibility(View.GONE);
		}
		if(news.isRead){
			tvTitle.setTextColor(Color.GRAY);
			tvInfo.setTextColor(Color.GRAY);
		}else{
			tvTitle.setTextColor(Color.BLACK);
			tvInfo.setTextColor(Color.BLACK);
		}
		Glide.with(context).load(news.pic).apply(options).into(ivAbstract);
	}
}
