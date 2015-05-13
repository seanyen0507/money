package com.example.moneymoney;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
//import android.service.textservice.SpellCheckerService.Session;
//import android.service.textservice.SpellCheckerService.Session;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.os.Build;

import com.facebook.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.facebook.model.GraphUser;

public class MainActivity extends Activity implements
OnItemLongClickListener,OnItemClickListener {
	
	ImageButton rent,friend,profile,facebook,setting;
	public String db_name = "SampleList";
	public String db_table = "Sample";
	private Database dbtest = null;
	public String user_id="";
	public String friendlist="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		dbtest = new Database(this); 
		rent = (ImageButton) findViewById(R.id.imageButtonRent);
		rent.setOnClickListener(openRent);
		friend = (ImageButton) findViewById(R.id.imageButtonFriend);
		friend.setOnClickListener(openFriend);
		facebook = (ImageButton) findViewById(R.id.imageButtonFacebook);
		facebook.setOnClickListener(openFacebook);
		setting = (ImageButton) findViewById(R.id.imageButtonSetting);
		setting.setOnClickListener(openSetting);
		Button button1;
		
		// start Facebook Login
	    Session.openActiveSession(this, true, new Session.StatusCallback() {

	      // callback when session changes state
	      @Override
	      public void call(final Session session, SessionState state, Exception exception) {
	        if (session.isOpened()) {

	          // make request to the /me API
	          Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

	            // callback after Graph API response with user object
	            @Override
	            public void onCompleted(GraphUser user, Response response) {
	            	user_id=user.getId().toString();
	            	
	              if (user != null) {
	                TextView welcome = (TextView) findViewById(R.id.textView1);
	                welcome.setText("Hello " + user.getName() + "!");

	                String imageFileURL = "https://graph.facebook.com/"+user.getId()+"/picture";
	                
	                final ProfilePictureView user_pic=(ProfilePictureView) findViewById(R.id.selection_profile_pic);
	                
	                user_pic.setProfileId(user.getId());
	                add(user.getId(), user.getName());
	              }
	              
	              
	              
	              /* make the API call */
		          new Request(
		              session,
		              "/"+user_id+"/taggable_friends",
		              null,
		              HttpMethod.GET,
		              new Request.Callback() {
		                  public void onCompleted(Response response) {

		                	  
		                	  String LOG_TAG = null;
							/* handle the result */
		                      //TEST
//		                	  Toast toast = Toast.makeText(MainActivity.this, user_id , Toast.LENGTH_SHORT);
//		      	              toast.show();
		                      
		                      
		                      friendlist=response.toString();
		                     
//		      	            Log.e(LOG_TAG,"Members: " + friendlist);
		                      
		                      //test ÁÐÓ¡È«²¿
		                      
//		                      ScrollView friendlists = (ScrollView) findViewById(R.id.scrollView1);
//		                      TextView a111 = (TextView) findViewById(R.id.textView2);
//		  	                a111.setText(friendlist);

//		                	  Log.e(LOG_TAG,"Members: " + response.toString());
		                  
		                  }
		              }
		          ).executeAsync();
	            }
	          });
	
	        		  
	          
	        		  
	        }
	      }
	    });		
		}
		
	
	
	
	
	
	private void add(String  userid, String name){
        SQLiteDatabase db = dbtest.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_USER", userid.toString());
        values.put("_NAME", name.toString());
        long check = db.insert("Sample", null, values);
        if (check == -1){
        	Toast.makeText(this,"·s¼W¥¢±Ñ¡I", Toast.LENGTH_SHORT).show();
        }
    }
	 @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	  }
		
	 public Button.OnClickListener openButton1 = new Button.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				vibrate();
//				Toast toast = Toast.makeText(MainActivity.this, user_id , Toast.LENGTH_SHORT);
//	            toast.show();
				
				
				
			}
		};	
	
	public Button.OnClickListener openRent = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenRent = new Intent();
			intentOpenRent.setClass(MainActivity.this, RentPage.class);
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
			intentOpenFriend.setClass(MainActivity.this, Friend.class);
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
			intentOpenFace.setClass(MainActivity.this, Facebook.class);
			intentOpenFace.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenFace);

			finish_page();
		}
	};
	
	private Button.OnClickListener openSetting = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			Intent intentOpenSetting = new Intent();
			intentOpenSetting.setClass(MainActivity.this, Setting.class);
			intentOpenSetting.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intentOpenSetting);

			finish_page();
		}
	};
    
	public void finish_page(){
		MainActivity.this.finish();
		MainActivity.this.overridePendingTransition(0,0);
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
