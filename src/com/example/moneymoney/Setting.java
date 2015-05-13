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

public class Setting extends Activity implements
OnItemLongClickListener,OnItemClickListener {
	
	ImageButton rent,friend,profile,facebook,setting;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		rent = (ImageButton) findViewById(R.id.imageButtonSettingRent);
		rent.setOnClickListener(openRent);
		friend = (ImageButton) findViewById(R.id.imageButtonSettingFriend);
		friend.setOnClickListener(openFriend);
		facebook = (ImageButton) findViewById(R.id.imageButtonSettingFacebook);
		facebook.setOnClickListener(openFacebook);
		profile = (ImageButton) findViewById(R.id.imageButtonSettingProfile);
		profile.setOnClickListener(openProfile);
	}
	
	public Button.OnClickListener openRent = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenRent = new Intent();
			intentOpenRent.setClass(Setting.this, RentPage.class);
			intentOpenRent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenRent);

			finish_page();
		}
	};
   
	private Button.OnClickListener openFriend = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenFriend = new Intent();
			intentOpenFriend.setClass(Setting.this, Friend.class);
			intentOpenFriend.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenFriend);

			finish_page();
		}
	};
	
	private Button.OnClickListener openFacebook = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenFace = new Intent();
			intentOpenFace.setClass(Setting.this, Facebook.class);
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
			intentOpenProfile.setClass(Setting.this, MainActivity.class);
			intentOpenProfile.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenProfile);

			finish_page();
		}
	};
    
    
	public void finish_page(){
		Setting.this.finish();
		Setting.this.overridePendingTransition(0,0);
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
