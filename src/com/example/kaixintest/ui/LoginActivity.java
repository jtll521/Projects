package com.example.kaixintest.ui;

import com.example.kaixintest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@SuppressLint("NewApi")
public class LoginActivity extends Activity {
	//登陆按钮
	private Button btnSure;
	//
	private Context mContext; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		//hide actionbar
		getActionBar().hide();
		
		setContentView(R.layout.login_activity);
		
		btnSure = (Button)findViewById(R.id.login_sure);
		btnSure.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(mContext, GauideActivity.class);
				
				startActivity(intent);
				
				finish();
			}
		});
	}

}
