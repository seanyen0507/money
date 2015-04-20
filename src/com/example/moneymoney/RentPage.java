package com.example.moneymoney;

import com.facebook.UiLifecycleHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
<<<<<<< HEAD
=======
import android.widget.PopupWindow;
>>>>>>> master
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
<<<<<<< HEAD
=======
import android.graphics.drawable.BitmapDrawable;
>>>>>>> master
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

=======

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

>>>>>>> master
public class RentPage extends FragmentActivity  implements
OnItemLongClickListener,OnItemClickListener {
	
	private static final List<String> PERMISSIONS = new ArrayList<String>() {
        {
            add("user_friends");
            add("public_profile");
        }
    };
	ImageButton rent,friend,profile,facebook,setting,electric,water,gas,total,pickFriendsButton,AddFriendManual;
	private static final int PICK_FRIENDS_ACTIVITY = 1;
	String userId;
    private TextView resultsText;
    private UiLifecycleHelper lifecycleHelper;
    boolean pickFriendsWhenSessionOpened;
	
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
		AddFriendManual = (ImageButton) findViewById(R.id.imageButtonAddFriendManual);
		resultsText = (TextView) findViewById(R.id.resultsTextView);
        pickFriendsButton = (ImageButton) findViewById(R.id.imageButtonAddFriend);
        pickFriendsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickPickFriends();
            }
        });
        
        lifecycleHelper = new UiLifecycleHelper(this, new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                
            	onSessionStateChanged(session, state, exception);
            }
        });
        lifecycleHelper.onCreate(savedInstanceState);

        ensureOpenSession();
        Session.openActiveSession(this, true, new Session.StatusCallback() {

  	      // callback when session changes state
  	      @Override
  	      public void call(Session session, SessionState state, Exception exception) {
  	        if (session.isOpened()) {

  	          // make request to the /me API
  	          Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

  	            // callback after Graph API response with user object
  	            @Override
  	            public void onCompleted(GraphUser user, Response response) {
  	              if (user != null) {
  	                
  	                userId = user.getId();
  	                Toast toast = Toast.makeText(RentPage.this, userId, Toast.LENGTH_SHORT);
  	                toast.show();
  	              }
  	            }
  	          });
  	        }
  	      }
  	    });
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_FRIENDS_ACTIVITY:
                displaySelectedFriends(resultCode);
                break;
            default:
                Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
                break;
        }
    }

    private boolean ensureOpenSession() {
        if (Session.getActiveSession() == null ||
                !Session.getActiveSession().isOpened()) {
            Session.openActiveSession(
                    this, 
                    true, 
                    PERMISSIONS,
                    new Session.StatusCallback() {
                        @Override
                        public void call(Session session, SessionState state, Exception exception) {
                            onSessionStateChanged(session, state, exception);
                        }
                    });
            return false;
        }
        return true;
    }
    
    private boolean sessionHasNecessaryPerms(Session session) {
        if (session != null && session.getPermissions() != null) {
            for (String requestedPerm : PERMISSIONS) {
                if (!session.getPermissions().contains(requestedPerm)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private List<String> getMissingPermissions(Session session) {
        List<String> missingPerms = new ArrayList<String>(PERMISSIONS);
        if (session != null && session.getPermissions() != null) {
            for (String requestedPerm : PERMISSIONS) {
                if (session.getPermissions().contains(requestedPerm)) {
                    missingPerms.remove(requestedPerm);
                }
            }
        }
        return missingPerms;
    }

    private void onSessionStateChanged(final Session session, SessionState state, Exception exception) {
        if (state.isOpened() && !sessionHasNecessaryPerms(session)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.need_perms_alert_text);
            builder.setPositiveButton(
                    R.string.need_perms_alert_button_ok, 
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            session.requestNewReadPermissions(
                                    new NewPermissionsRequest(
                                            RentPage.this, 
                                            getMissingPermissions(session)));
                        }
                    });
            builder.setNegativeButton(
                    R.string.need_perms_alert_button_quit,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            builder.show();
        } else if (pickFriendsWhenSessionOpened && state.isOpened()) {
            pickFriendsWhenSessionOpened = false;

            startPickFriendsActivity();
        }
    }

    private void displaySelectedFriends(int resultCode) {
        String results = "";
        FriendPickerApplication application = (FriendPickerApplication) getApplication();

        Collection<GraphUser> selection = application.getSelectedUsers();
        if (selection != null && selection.size() > 0) {
            ArrayList<String> names = new ArrayList<String>();
            for (GraphUser user : selection) {
                names.add(user.getName());
            }
            results = TextUtils.join(", ", names);
        } else {
            results = "<No friends selected>";
        }

        resultsText.setText(results);
    }

    private void onClickPickFriends() {
        startPickFriendsActivity();
    }

    private void startPickFriendsActivity() {
        if (ensureOpenSession()) {
            Intent intent = new Intent(this, PickerActivity.class);
            // Note: The following line is optional, as multi-select behavior is the default for
            // FriendPickerFragment. It is here to demonstrate how parameters could be passed to the
            // friend picker if single-select functionality was desired, or if a different user ID was
            // desired (for instance, to see friends of a friend).
            PickerActivity.populateParameters(intent, userId, true, true);
            startActivityForResult(intent, PICK_FRIENDS_ACTIVITY);
        } else {
            pickFriendsWhenSessionOpened = true;
        }
    }
	
	private Button.OnClickListener openElectric = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			
			vibrate();
			electric.setImageResource(R.drawable.rent_electric_click);
			water.setImageResource(R.drawable.rent_water);
			gas.setImageResource(R.drawable.rent_gas);
			total.setImageResource(R.drawable.rent_total);
			openPopupWindow(view,"Electric",electric);
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
			openPopupWindow(view,"Water",water);
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
			openPopupWindow(view,"Gas",gas);
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
	
	@SuppressWarnings("deprecation")
	private void openPopupWindow(View v, final String s, ImageButton b){
		LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		final View popupView = layoutInflater.inflate(R.layout.popup, null);
		final PopupWindow popupWindow = new PopupWindow(popupView, 600 , LayoutParams.WRAP_CONTENT);
		TextView k = (TextView) popupView.findViewById(R.id.kind);
		k.setText(s);
		Button buttonClose = (Button)popupView.findViewById(R.id.closepopupwindow);
		buttonClose.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText value = (EditText) popupView.findViewById(R.id.value);
				String t = value.getText()+" has be input as "+s;
				Toast toast = Toast.makeText(RentPage.this, t, Toast.LENGTH_SHORT);
	            toast.show();
				popupWindow.dismiss();
		}});
		
		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
//		popupWindow.showAsDropDown(b);
		popupWindow.setFocusable(true);
		popupWindow.update();
	}
    
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
