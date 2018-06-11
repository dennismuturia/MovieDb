package com.dennismoviedb.moviedb.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dennismoviedb.moviedb.R;
import com.dennismoviedb.moviedb.model.Movie;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;


public class Login_Activity extends Activity {
    private Movie movie;
    public static int APP_REQUEST_CODE = 99;
    Button btnPhone, btnEmail;
    AccessToken accessToken = AccountKit.getCurrentAccessToken();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        //Declaring the buttons that I am using.
        btnEmail = (Button)findViewById(R.id.emailButton);
        btnPhone = (Button)findViewById(R.id.phoneButton);
        if (accessToken != null){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }else{
            //Action when the email button is clicked.
            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startLoginPage(LoginType.EMAIL);//Create a new method for this
                }
            });

            btnPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLoginPage(LoginType.PHONE);//We will need to create a new method for this
                }
            });
        }



    }
    private void startLoginPage(LoginType loginType) {
        if (loginType == loginType.EMAIL){
            Intent intent = new Intent(this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                    new AccountKitConfiguration.AccountKitConfigurationBuilder
                            (LoginType.EMAIL, AccountKitActivity.ResponseType.TOKEN);//Done when 'Enable client token is set to Yes'

            intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
            startActivityForResult(intent, APP_REQUEST_CODE);
        }else if(loginType == loginType.PHONE){
            Intent intent = new Intent(this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                    new AccountKitConfiguration.AccountKitConfigurationBuilder
                            (LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN);//Done when 'Enable client token is set to Yes'

            intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
            startActivityForResult(intent, APP_REQUEST_CODE);
        }
    }
    //Press Ctr+o

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APP_REQUEST_CODE){
            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (result.getError() != null){
                Toast.makeText(this, ""+result.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            else if (result.wasCancelled()){
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            }
        }
    }
}
