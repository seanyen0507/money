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

public class RentPage extends Activity  implements
OnItemLongClickListener,OnItemClickListener {
	

	ImageButton rent,friend,profile,facebook,setting,electric,water,gas,total;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rent_page);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		friend = (ImageButton) findViewById(R.id.imageButtonRentFriend);
		friend.setOnClickListener(openFriend);
		facebook = (ImageButton) findViewById(R.id.imageButtonRentFacebook);
		facebook.setOnClickListener(openFacebook);
		setting = (ImageButton) findViewById(R.id.imageButtonRentSetting);
		setting.setOnClickListener(openSetting);
		profile = (ImageButton) findViewById(R.id.imageButtonRentProfile);
		profile.setOnClickListener(openProfile);
		electric = (ImageButton) findViewById(R.id.imageButtonElectric);
		electric.setOnClickListener(openElectric);
		water = (ImageButton) findViewById(R.id.imageButtonWater);
		water.setOnClickListener(openWater);
		gas = (ImageButton) findViewById(R.id.imageButtonGas);
		gas.setOnClickListener(openGas);
		total = (ImageButton) findViewById(R.id.imageButtonTotal);
		total.setOnClickListener(openTotal);
	}
	
	private Button.OnClickListener openElectric = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			electric.setImageResource(R.drawable.rent_electric_click);
			water.setImageResource(R.drawable.rent_water);
			gas.setImageResource(R.drawable.rent_gas);
			total.setImageResource(R.drawable.rent_total);
		}
	};
	
	private Button.OnClickListener openWater = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			electric.setImageResource(R.drawable.rent_electric);
			water.setImageResource(R.drawable.rent_water_click);
			gas.setImageResource(R.drawable.rent_gas);
			total.setImageResource(R.drawable.rent_total);
		}
	};
	
	private Button.OnClickListener openGas = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			electric.setImageResource(R.drawable.rent_electric);
			water.setImageResource(R.drawable.rent_water);
			gas.setImageResource(R.drawable.rent_gas_click);
			total.setImageResource(R.drawable.rent_total);
		}
	};
	
	private Button.OnClickListener openTotal = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			electric.setImageResource(R.drawable.rent_electric);
			water.setImageResource(R.drawable.rent_water);
			gas.setImageResource(R.drawable.rent_gas);
			total.setImageResource(R.drawable.rent_total_click);
		}
	};
	
	private Button.OnClickListener openFriend = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenFriend = new Intent();
			intentOpenFriend.setClass(RentPage.this, Friend.class);
			intentOpenFriend.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenFriend);

			finish_page();
		}
	};
   
	private Button.OnClickListener openSetting = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenSetting = new Intent();
			intentOpenSetting.setClass(RentPage.this, Setting.class);
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
			intentOpenFace.setClass(RentPage.this, Facebook.class);
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
			intentOpenProfile.setClass(RentPage.this, MainActivity.class);
			intentOpenProfile.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenProfile);

			finish_page();
		}
	};
    
	public void finish_page(){
		RentPage.this.finish();
		RentPage.this.overridePendingTransition(0,0);
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
