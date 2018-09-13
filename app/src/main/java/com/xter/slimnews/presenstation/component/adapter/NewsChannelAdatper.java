package com.xter.slimnews.presenstation.component.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.xter.slimnews.R;
import com.xter.support.adapter.QuickRecycleAdapter;
import com.xter.support.adapter.ViewHolder;

import java.util.List;

/**
 * Created by XTER on 2018/9/10.
 * 新闻频道
 */

public class NewsChannelAdatper extends QuickRecycleAdapter<String> {

	public int selectPos;

	public NewsChannelAdatper(Context context, int res, List<String> data) {
		super(context, res, data);
	}

	@Override
	public void bindView(ViewHolder holder, int position) {

		TextView tv = holder.getView(R.id.tv_channel_name);

		tv.setText(data.get(position));
		if(position==selectPos){
			tv.setTextColor(Color.RED);
		}else{
			tv.setTextColor(Color.BLACK);
		}
	}

	public void setFocus(int selectPos){
		this.selectPos = selectPos;
		notifyDataSetChanged();
	}
}
