package com.example.kaixintest.view;

import java.io.IOException;
import java.io.InputStream;

import com.example.kaixintest.R;
import com.example.kaixintest.anim.UgcAnimations;
import com.example.kaixintest.application.MyApplication;
import com.example.kaixintest.ui.HomePageActivity;
import com.example.kaixintest.util.ViewUtils;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SlideView {

	private Context mContext;
	private MyApplication myApplication;
	private static final String WALLPAPER = "wallpaper/wallpaper0.png";
	// 整个Slideview的根视图
	private View mRootView;
	// listview
	private ListView mListView;
	private SlideMenuAdapter mSlideMenuAdapter;
	// 各个view
	private LinearLayout mSlideWallpaper;

	// UGCview的各个部分显示
	private View ugcView;
	private RelativeLayout ugcLayout;
	private ImageView ugcVoice;
	private ImageView ugcPhoto;
	private ImageView ugcMessage;
	private ImageView ugcLocation;
	private ImageView ugcIcon;
	private ImageView ugcBg;
	/**
	 * 判断当前的path菜单是否已经显示
	 */
	private boolean mUgcIsShowing = false;
	
	// 接口类，用来回调各个view
	private CloseViewInClientActivity mCloseViewInClientActivity;
	private ChangeViewListener mChangeViewListener;

	public SlideView(Context context, Activity activity) {
		// mCloseViewInClientActivity = (CloseViewInClientActivity)activity;
		mContext = context;
		myApplication = (MyApplication) activity.getApplication();

		findView();
		initView();
		setOnListener();
	}

	// 查找view
	public void findView() {
		mRootView = LayoutInflater.from(mContext).inflate(R.layout.slideview,
				null);
		mSlideWallpaper = (LinearLayout) mRootView
				.findViewById(R.id.slideview_backgroud);
		mListView = (ListView) mRootView.findViewById(R.id.slide_listview);
		mSlideMenuAdapter = new SlideMenuAdapter(mContext);
		mListView.setAdapter(mSlideMenuAdapter);

		// ugcview
		ugcView = mRootView.findViewById(R.id.slideview_ugc_menu);
		ugcLayout = (RelativeLayout) ugcView.findViewById(R.id.ugc_layout);
		ugcVoice = (ImageView) ugcView.findViewById(R.id.ugc_voice);
		ugcPhoto = (ImageView) ugcView.findViewById(R.id.ugc_photo);
		ugcMessage = (ImageView) ugcView.findViewById(R.id.ugc_record);
		ugcLocation = (ImageView) ugcView.findViewById(R.id.ugc_lbs);
		ugcIcon = (ImageView) ugcView.findViewById(R.id.ugc);
		ugcBg = (ImageView)ugcView.findViewById(R.id.ugc_bg);
	}

	// 设置监听
	public void setOnListener() {

		ugcIcon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 判断是否显示,已经显示则隐藏,否则则显示
				mUgcIsShowing = !mUgcIsShowing;
				if (mUgcIsShowing) {
					UgcAnimations.startOpenAnimation(ugcLayout, null, ugcIcon,
							500);
				} else {
					UgcAnimations.startCloseAnimation(ugcLayout, null, ugcIcon,
							500);
				}
			}
		});

		ugcLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		ugcPhoto.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		ugcVoice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		ugcMessage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 返回当前的视图
	 */
	public View getView() {
		return mRootView;
	}

	/**
	 * 初始化各个视图
	 */
	@SuppressLint("NewApi")
	public void initView() {

		mSlideWallpaper.setBackground(myApplication
				.getSlidWallPaper(myApplication.WALLPAPERPOSITION));

	}

	// 滑动菜单各项
	private static final String[] slideMenuName = { "首页", "消息", "好友", "照片",
			"转帖", "礼物", "游戏", "附近" };
	// 滑动菜单图像
	private static final int[] slideMenuIcon = {
			R.drawable.sidebar_icon_dynamic, R.drawable.sidebar_icon_news,
			R.drawable.sidebar_icon_friends, R.drawable.sidebar_icon_photo,
			R.drawable.sidebar_icon_viewed, R.drawable.sidebar_icon_gifts,
			R.drawable.sidebar_icon_recommend, R.drawable.sidebar_icon_lbs };
	private static final int[] slideMenuIconPressed = {
			R.drawable.sidebar_icon_dynamic_pressed,
			R.drawable.sidebar_icon_news_pressed,
			R.drawable.sidebar_icon_friends_pressed,
			R.drawable.sidebar_icon_photo_pressed,
			R.drawable.sidebar_icon_viewed_pressed,
			R.drawable.sidebar_icon_gifts_pressed,
			R.drawable.sidebar_icon_recommend_pressed,
			R.drawable.sidebar_icon_lbs_pressed };

	class SlideMenuAdapter extends BaseAdapter {

		private Context mContext;
		private int mChoose = 0;

		public SlideMenuAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return slideMenuName.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * 设置每个item的选择状态
		 */
		public void setChoose(int choose) {
			this.mChoose = choose;
		}

		@Override
		public View getView(final int position, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = LayoutInflater.from(mContext);
			ViewHolder viewHolder;
			if (view == null) {
				view = inflater.inflate(R.layout.slideview_item, null);
				viewHolder = new ViewHolder();
				viewHolder.layout = (LinearLayout) view
						.findViewById(R.id.slideview_item_layout);
				viewHolder.icon = (ImageView) view
						.findViewById(R.id.slideview_item_icon);
				viewHolder.name = (TextView) view
						.findViewById(R.id.slideview_item_name);

				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}

			viewHolder.name.setText(slideMenuName[position]);
			if (mChoose == position) {
				viewHolder.name.setTextColor(Color.parseColor("#ffffffff"));
				viewHolder.layout.setBackgroundColor(Color
						.parseColor("#20000000"));
				viewHolder.icon.setImageDrawable(mContext.getResources()
						.getDrawable(slideMenuIconPressed[position]));
			} else {
				viewHolder.name.setTextColor(Color.parseColor("#7fffffff"));
				viewHolder.layout.setBackgroundColor(Color
						.parseColor("#00000000"));
				viewHolder.icon.setImageDrawable(mContext.getResources()
						.getDrawable(slideMenuIcon[position]));
			}
			view.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					switch (position) {
					case ViewUtils.SLIDE_VIEW_HOME:
						// mCloseViewInClientActivity.setCurrentView(ViewUtils.SLIDE_VIEW_HOME);
						mChangeViewListener
								.changeView(ViewUtils.SLIDE_VIEW_HOME);
						Intent intent = new Intent();
						intent.setClass(mContext, HomePageActivity.class);
						mContext.startActivity(intent);
						break;
					case ViewUtils.SLIDE_VIEW_MSG:
						Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT)
								.show();
						break;
					case ViewUtils.SLIDE_VIEW_FRIEND:
						break;
					case ViewUtils.SLIDE_VIEW_PHOTOS:
						break;
					case ViewUtils.SLIDE_VIEW_VIEWED:
						break;
					case ViewUtils.SLIDE_VIEW_GIFT:
						break;
					case ViewUtils.SLIDE_VIEW_GAMES:
						break;
					case ViewUtils.SLIDE_VIEW_NERABY:
						break;
					}

					mChoose = position;
					notifyDataSetChanged();
				}
			});

			return view;
		}

		class ViewHolder {
			LinearLayout layout;
			ImageView icon;
			TextView name;
		}
	}

	/**
	 * 接口，用来在clientmainactivity回调各个view界面 这是一种实现方式，主要通过acivity实现接口，然后赋值
	 * 另外一种参考changeviewlistener
	 */
	public interface CloseViewInClientActivity {
		public void setCurrentView(int index);
	}

	public interface ChangeViewListener {
		public void changeView(int index);
	}

	public void setChangeViewListener(ChangeViewListener chView) {
		mChangeViewListener = chView;
	}
}
