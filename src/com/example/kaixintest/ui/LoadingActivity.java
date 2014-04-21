package com.example.kaixintest.ui;

import com.example.kaixintest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("NewApi")
public class LoadingActivity extends Activity {

	private Context mContext; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_activity);
		mContext = this;
		//hide the actionbar
		getActionBar().hide();
		//延时启动
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(mContext, LoginActivity.class);
				startActivity(intent);
				//结束当前activity
				finish();
			}
		}, 1000);
		
	}

}
