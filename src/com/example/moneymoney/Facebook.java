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

public class Facebook extends Activity implements
OnItemLongClickListener,OnItemClickListener {
	
	ImageButton rent,friend,profile,facebook,setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_facebook);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		rent = (ImageButton) findViewById(R.id.imageButtonFacebookRent);
		rent.setOnClickListener(openRent);
		friend = (ImageButton) findViewById(R.id.imageButtonFacebookFriend);
		friend.setOnClickListener(openFriend);
		setting = (ImageButton) findViewById(R.id.imageButtonFacebookSetting);
		setting.setOnClickListener(openSetting);
		profile = (ImageButton) findViewById(R.id.imageButtonFacebookProfile);
		profile.setOnClickListener(openProfile);
	}
	
	public Button.OnClickListener openRent = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenRent = new Intent();
			intentOpenRent.setClass(Facebook.this, RentPage.class);
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
			intentOpenSetting.setClass(Facebook.this, Setting.class);
			intentOpenSetting.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenSetting);

			finish_page();
		}
	};
	
	private Button.OnClickListener openFriend = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenFriend = new Intent();
			intentOpenFriend.setClass(Facebook.this, Friend.class);
			intentOpenFriend.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenFriend);

			finish_page();
		}
	};
	
	private Button.OnClickListener openProfile = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenProfile = new Intent();
			intentOpenProfile.setClass(Facebook.this, MainActivity.class);
			intentOpenProfile.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenProfile);

			finish_page();
		}
	};
    
	public void finish_page(){
		Facebook.this.finish();
		Facebook.this.overridePendingTransition(0,0);
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
