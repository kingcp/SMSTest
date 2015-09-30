package com.example.smstest;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends ActionBarActivity {
	
	private String APPKEY = "abe95755db3e";
	private String APPSECRET = "8a9f019900e33dba11ab3bf3bba9ff7e";
	private Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
         
		SMSSDK.initSDK(getApplicationContext(), APPKEY, APPSECRET);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				RegisterPage register = new RegisterPage();
				
				register.setRegisterCallback(new EventHandler(){
					@Override
					public void afterEvent(int event, int result, Object data) {
						if(result == SMSSDK.RESULT_COMPLETE){
							HashMap<String, Object> map = (HashMap<String, Object>) data;
							String country = (String) map.get("country");
							String phone = (String) map.get("phone");
							SMSSDK.submitUserInfo("UserId", "NikeName", "avator",country,phone);
						}
					}
				});
				
				register.show(MainActivity.this);
				
			}
		});
	
	}

	
}
