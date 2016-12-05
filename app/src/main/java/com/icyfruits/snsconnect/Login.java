package com.icyfruits.snsconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

public class Login extends Activity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ProgressDialog progressDialog;
    User user;



    //keytool -exportcert -alias androiddebugkey -keystore C:\Users\m09-5\.android\debug.keystore  | openssl sha1 -binary | openssl base64
    //Qce+5cPoBrUhZu5LF5UFADzGUno=

    //https://developers.facebook.com/docs/facebook-login/android/#addbutton
    //https://developers.facebook.com/quickstarts/377866572558467/?platform=android

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(PrefUtils.getCurrentUser(Login.this)!=null){
            Intent homeIntent = new Intent(Login.this, Logout.class);
            startActivity(homeIntent);
            finish();
        }

    }

//    todo



    public void login(){

    }
}
