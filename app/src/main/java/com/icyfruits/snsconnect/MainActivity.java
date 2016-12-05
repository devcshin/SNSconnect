package com.icyfruits.snsconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {

    //keytool -exportcert -alias androiddebugkey -keystore C:\Users\m09-5\.android\debug.keystore  | openssl sha1 -binary | openssl base64
    //Qce+5cPoBrUhZu5LF5UFADzGUno=

    //https://developers.facebook.com/docs/facebook-login/android/#addbutton
    //https://developers.facebook.com/quickstarts/377866572558467/?platform=android

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void login(){

    }
}
