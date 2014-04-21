package com.example.kaixintest.application;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListView;

public class MyApplication extends Application {

	// slideview 的背景图
	public String[] mWallPaperList;
	public int WALLPAPERPOSITION = 0;
	// 壁纸存放路径为assets/wallpaper
	private static final String WALLPAPER = "wallpaper";

	// 将图像保存到内存中，减少内存消耗，提高壁纸读取效率
	private HashMap<String, SoftReference<Bitmap>> wallpaperCache = new HashMap<String, SoftReference<Bitmap>>();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		// 数据初始化
		try {

			mWallPaperList = getAssets().list(WALLPAPER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WALLPAPERPOSITION = (int) (Math.random() * 12);
	}

	public Drawable getSlidWallPaper(int position) {
		Drawable drawable = null;
		if (mWallPaperList.length > 0) {
			InputStream inputStream = null;
			Bitmap bitmap = null;

			String wallpaperName = mWallPaperList[position];
			// cache中有数据，直接在cache中取
			if (wallpaperCache.containsKey(wallpaperName)) {
				SoftReference<Bitmap> softReference = wallpaperCache
						.get(wallpaperName);
				bitmap = softReference.get();
				//取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
				if (bitmap != null) {
					drawable = new BitmapDrawable(getResources(), bitmap);
					return drawable;
				}
			}
			try {
				inputStream = getAssets().open(WALLPAPER + "/" + wallpaperName);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bitmap = BitmapFactory.decodeStream(inputStream);
			// 如果缓存中不存在，则将图片保存
			wallpaperCache
					.put(wallpaperName, new SoftReference<Bitmap>(bitmap));

			// Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

			drawable = new BitmapDrawable(getResources(), bitmap);
		}
		return drawable;
	}
}
