package com.example.kaixintest.view;

import com.example.kaixintest.R;
import com.example.kaixintest.view.FlipperLayout.OnOpenListener;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class HomeView {
	//homeview的根视图
	private View mRootView;
	private OnOpenListener mOnOpenListener;
	
	public HomeView(Context context, Activity activity){
		mRootView = LayoutInflater.from(context).inflate(R.layout.homeview, null);
	}
	
	/**
	 * 获得当前视图
	 */
	public View getView(){
		return mRootView;
	}
	
	public void setOnOpenListener(OnOpenListener onOpenListener) {
		Log.i("jiangtao4","setOnOpenListener");
		mOnOpenListener = onOpenListener;
	}
}
