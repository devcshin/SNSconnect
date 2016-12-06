package com.icyfruits.snsconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class Login extends Activity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ProgressDialog progressDialog;
    User user;
    private android.widget.TextView btnLogin;
    private LoginButton loginbutton;
    private android.widget.RelativeLayout activitymain;



    //keytool -exportcert -alias androiddebugkey -keystore C:\Users\m09-5\.android\debug.keystore  | openssl sha1 -binary | openssl base64
    //Qce+5cPoBrUhZu5LF5UFADzGUno=

    //https://developers.facebook.com/docs/facebook-login/android/#addbutton
    //https://developers.facebook.com/quickstarts/377866572558467/?platform=android

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activitymain = (RelativeLayout) findViewById(R.id.activity_main);
        this.loginbutton = (LoginButton) findViewById(R.id.login_button);

//        if(PrefUtils.getCurrentUser(Login.this)!=null){
//            Intent homeIntent = new Intent(Login.this, Logout.class);
//            startActivity(homeIntent);
//            finish();
//        }
    }

//    todo
// https://github.com/niravkalola/FacebookLoginSample-master/blob/95b74d17839aeb72f5e9dc5c8be38debc8cc90c1/app/src/main/java/com/example/android/facebookloginsample/LoginActivity.java


    @Override
    protected void onResume() {
        super.onResume();
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile","email","user_friends");

        this.btnLogin = (TextView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("loading...");
                progressDialog.show();

                loginButton.performClick();
                loginButton.setPressed(true);
                loginButton.invalidate();
                loginButton.registerCallback(callbackManager, mCallBack);
                loginButton.setPressed(false);
                loginButton.invalidate();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            progressDialog.dismiss();

            //Appcode
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback(){
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.e("response",response+"");
                    try{
                        user = new User();
                        user.facebookID = object.getString("id").toString();
                        user.email = object.getString("email").toString();
                        user.name = object.getString("name").toString();
                        user.gender = object.getString("gender").toString();
                        PrefUtils.setCurrentUser(user,Login.this);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Login.this,"Welcome "+user.name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Logout.class);
                    startActivity(intent);
                    finish();
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields","id,name,email,gender,birthday");

            request.setParameters(parameters);
            request.executeAsync();



        }

        @Override
        public void onCancel() {
            progressDialog.dismiss();

        }

        @Override
        public void onError(FacebookException error) {

            progressDialog.dismiss();
        }
    };


}
