package com.example.kaixintest.ui;

import com.example.kaixintest.util.ViewUtils;
import com.example.kaixintest.view.FlipperLayout;
import com.example.kaixintest.view.FlipperLayout.OnOpenListener;
import com.example.kaixintest.view.HomeView;
import com.example.kaixintest.view.SlideView;
import com.example.kaixintest.view.SlideView.ChangeViewListener;
import com.example.kaixintest.view.SlideView.CloseViewInClientActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ClientMainActivity extends Activity implements
		CloseViewInClientActivity {

	// FlipperLayout继承自viewgroup
	private FlipperLayout mRootContainer;
	// 滑出菜单试图
	private SlideView mSlideView;
	// 主页菜单
	private HomeView mHomeView;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// hide actionbar
		getActionBar().hide();
		// 初始化FlipperLayout，填充整个activity
		mRootContainer = new FlipperLayout(this);
		LayoutParams lParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mRootContainer.setLayoutParams(lParams);

		// 添加视图
		mSlideView = new SlideView(this, this);
		mHomeView = new HomeView(this, this);

		mRootContainer.addView(mSlideView.getView());
		mRootContainer.addView(mHomeView.getView());

		setContentView(mRootContainer);
		//设置监听
		setListener();
		Log.i("jiangtao4", "oncreate");
	}
	
	public void setListener(){
		mSlideView.setChangeViewListener(new ChangeViewListener() {
			
			@Override
			public void changeView(int index) {
				// TODO Auto-generated method stub
				switch (index) {
				case ViewUtils.SLIDE_VIEW_HOME:
					mRootContainer.close(mHomeView.getView());
					break;
				case ViewUtils.SLIDE_VIEW_MSG:
					break;
				case ViewUtils.SLIDE_VIEW_FRIEND:
					break;
				case ViewUtils.SLIDE_VIEW_PHOTOS:
					break;
				default:
					break;
				}
			}
		});
	}
	@Override
	public void setCurrentView(int index) {
		// TODO Auto-generated method stub
		switch (index) {
		case ViewUtils.SLIDE_VIEW_HOME:
			Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
			mRootContainer.close(mHomeView.getView());
			break;
		case ViewUtils.SLIDE_VIEW_MSG:
			break;
		case ViewUtils.SLIDE_VIEW_FRIEND:
			break;
		case ViewUtils.SLIDE_VIEW_PHOTOS:
			break;
		default:
			break;
		}
	}

}
