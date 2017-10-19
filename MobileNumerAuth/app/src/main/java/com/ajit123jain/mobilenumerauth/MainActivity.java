package com.ajit123jain.mobilenumerauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
      
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
}
