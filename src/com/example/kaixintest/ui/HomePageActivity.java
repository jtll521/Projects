package com.example.kaixintest.ui;

import com.example.kaixintest.R;
import com.example.kaixintest.arcmenu.ArcMenu;
import com.example.kaixintest.arcmenu.RayMenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomePageActivity extends FragmentActivity {

	private static final int[] ITEM_DRAWABLES = { R.drawable.composer_camera,
			R.drawable.composer_music, R.drawable.composer_place,
			R.drawable.composer_sleep, R.drawable.composer_thought,
			R.drawable.composer_with };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homeactivity_view);

		ArcMenu arcMenu = (ArcMenu) findViewById(R.id.arc_menu);
		ArcMenu arcMenu2 = (ArcMenu) findViewById(R.id.arc_menu_2);

		initArcMenu(arcMenu, ITEM_DRAWABLES);
		initArcMenu(arcMenu2, ITEM_DRAWABLES);

		RayMenu rayMenu = (RayMenu) findViewById(R.id.ray_menu);
		final int itemCount = ITEM_DRAWABLES.length;
		for (int i = 0; i < itemCount; i++) {
			ImageView item = new ImageView(this);
			item.setImageResource(ITEM_DRAWABLES[i]);

			final int position = i;
			rayMenu.addItem(item, new View.OnClickListener() {

				@Override
				public void onClick(View v) {


				}
			});// Add a menu item
		}
	}

	private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
		final int itemCount = itemDrawables.length;
		for (int i = 0; i < itemCount; i++) {
			ImageView item = new ImageView(this);
			item.setImageResource(itemDrawables[i]);

			final int position = i;
			menu.addItem(item, new View.OnClickListener() {

				@Override
				public void onClick(View v) {
			
				}
			});
		}
	}



}
