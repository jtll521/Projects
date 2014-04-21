package com.example.kaixintest.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.kaixintest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("NewApi")
public class GauideActivity extends Activity {

	//使用viewpapger作为功能导航
	private ViewPager mViewPager;
	//viewpager 的适配器
	private GuideViewAdapter guideViewAdapter;
	//每一页的导航视图
	private View pageView1,pageView2,pageView3;
	
	private Button btnStart;
	
	//viewpaper包含内容
	private List<View> mViews = new ArrayList<View>();
	
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		//hide actionbar
		getActionBar().hide();
		setContentView(R.layout.guide_activity);
		
		mViewPager = (ViewPager)findViewById(R.id.guide_activity_viewpager);
		/**
		 * 初始化guideview
		 */
		pageView1 = LayoutInflater.from(this).inflate(R.layout.guide_view_pager1, null);
		pageView2 = LayoutInflater.from(this).inflate(R.layout.guide_view_pager2, null);
		pageView3 = LayoutInflater.from(this).inflate(R.layout.guide_view_pager3, null);
		btnStart = (Button)pageView3.findViewById(R.id.guide_activity_start);
		btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(mContext, ClientMainActivity.class);
				startActivity(intent);
			}
		});
		
		//添加视图
		mViews.add(pageView1);
		mViews.add(pageView2);
		mViews.add(pageView3);
		
		//设置viewpapger的adapter
		guideViewAdapter = new GuideViewAdapter();
		mViewPager.setAdapter(guideViewAdapter);
	}
    
	public class GuideViewAdapter extends PagerAdapter{

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(mViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(mViews.get(position));
			
			return mViews.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
	}
}
