package com.xter.slimnews.presenstation.component.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.xter.slimnews.R;
import com.xter.slimnews.data.constant.LC;
import com.xter.slimnews.data.db.SPM;
import com.xter.slimnews.data.entity.NewsChannel;
import com.xter.slimnews.presenstation.component.adapter.NewsChannelAdatper;
import com.xter.slimnews.presenstation.component.adapter.NewsFragmentAdapter;
import com.xter.slimnews.presenstation.component.fragment.NewsFragment;
import com.xter.slimnews.presenstation.gen.IMainRule;
import com.xter.slimnews.presenstation.presenter.MainPresenter;
import com.xter.slimnews.presenstation.util.InjectUtil;
import com.xter.support.adapter.QuickRecycleAdapter;
import com.xter.support.component.BaseActivity;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.ActivityUtil;
import com.xter.support.util.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

public class MainActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener, IMainRule.V {

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.drawer_layout)
	DrawerLayout drawer;

	@BindView(R.id.rv_news_channels)
	RecyclerView rvNewsChannel;

	@BindView(R.id.nav_view)
	NavigationView navigationView;

	@BindView(R.id.vp_news)
	ViewPager vpNews;

	NewsChannelAdatper newsChannelAdatper;

	NewsFragmentAdapter newsFragmentAdapter;

	IMainRule.P mainPresenter;

	private static final String NEWS = "news";


	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initView() {
		mainPresenter.requestPermission(this);

		setSupportActionBar(toolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		navigationView.setNavigationItemSelectedListener(this);

	}

	@Override
	protected void initData() {
		newsChannelAdatper = new NewsChannelAdatper(this, R.layout.item_news_channel, null);
		rvNewsChannel.setLayoutManager(new LinearLayoutManager(this, HORIZONTAL, false));
		rvNewsChannel.setAdapter(newsChannelAdatper);
		newsChannelAdatper.setOnItemClickLitener(new QuickRecycleAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
				newsChannelAdatper.setFocus(position);
				vpNews.setCurrentItem(position);
			}

			@Override
			public void onItemLongClick(View view, int position) {

			}
		});

		mainPresenter.loadNewsChannel();

		vpNews.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				newsChannelAdatper.setFocus(position);
				rvNewsChannel.smoothScrollToPosition(position);
			}
		});
	}

	@Override
	protected BasePresenter genPresenter() {
		mainPresenter = new MainPresenter(InjectUtil.getNewsChannelPageUseCase());
		return (BasePresenter) mainPresenter;
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			// Handle the camera action
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void loadNewsChannel(List<NewsChannel> channels) {
		if (newsFragmentAdapter == null) {
			newsFragmentAdapter = new NewsFragmentAdapter(getSupportFragmentManager(), channels);
			vpNews.setAdapter(newsFragmentAdapter);
		}

		newsChannelAdatper.replaceAll(channels);
	}


}
