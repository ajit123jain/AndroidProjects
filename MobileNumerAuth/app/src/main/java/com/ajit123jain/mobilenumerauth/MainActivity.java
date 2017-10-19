package com.ajit123jain.mobilenumerauth;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button mLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mLogout = (Button)findViewById(R.id.logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChange();
            }
        });
      
    }

    @Override
    protected void onStart() {
        super.onStart();

        //check if user signin  or not and update UI accordingly
        FirebaseUser mCurrentUser = mAuth.getCurrentUser();

        if (mCurrentUser==null){
            Intent authIntent = new Intent(MainActivity.this,AuthActivity.class);
            startActivity(authIntent);
            finish();
        }
    }
     private void activityChange(){
         mAuth.signOut();
         Intent authIntent = new Intent(MainActivity.this,AuthActivity.class);
         startActivity(authIntent);
         finish();
     }

}
