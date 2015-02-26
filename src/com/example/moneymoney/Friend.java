package com.example.moneymoney;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class Friend extends Activity implements
OnItemLongClickListener,OnItemClickListener {

	ImageButton rent,friend,profile,facebook,setting;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_friend);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		

		rent = (ImageButton) findViewById(R.id.imageButtonFriendRent);
		rent.setOnClickListener(openRent);
		facebook = (ImageButton) findViewById(R.id.imageButtonFriendFacebook);
		facebook.setOnClickListener(openFacebook);
		setting = (ImageButton) findViewById(R.id.imageButtonFriendSetting);
		setting.setOnClickListener(openSetting);
		profile = (ImageButton) findViewById(R.id.imageButtonFriendProfile);
		profile.setOnClickListener(openProfile);
	}
	
	public Button.OnClickListener openRent = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenRent = new Intent();
			intentOpenRent.setClass(Friend.this, RentPage.class);
			intentOpenRent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenRent);

			finish_page();
		}
	};
   
	private Button.OnClickListener openSetting = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenSetting = new Intent();
			intentOpenSetting.setClass(Friend.this, Setting.class);
			intentOpenSetting.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenSetting);

			finish_page();
		}
	};
	
	private Button.OnClickListener openFacebook = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenFace = new Intent();
			intentOpenFace.setClass(Friend.this, Facebook.class);
			intentOpenFace.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenFace);

			finish_page();
		}
	};
	
	private Button.OnClickListener openProfile = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenProfile = new Intent();
			intentOpenProfile.setClass(Friend.this, MainActivity.class);
			intentOpenProfile.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenProfile);

			finish_page();
		}
	};
    
	public void finish_page(){
		Friend.this.finish();
		Friend.this.overridePendingTransition(0,0);
	}

	

	public void vibrate(){
		
		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		vibrator.vibrate(40);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
