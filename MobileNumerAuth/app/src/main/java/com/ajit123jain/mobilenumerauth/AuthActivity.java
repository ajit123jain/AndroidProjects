package com.ajit123jain.mobilenumerauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

public class AuthActivity extends AppCompatActivity {

    private LinearLayout mPhoneLayout;
    private LinearLayout mCodeLayout;

    private EditText mPhoneText;
    private EditText mCodeText;

    private ProgressBar mPhoneProgressBar;
    protected ProgressBar mCodeProgressBar;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;

    private static final String TAG = "AuthActivity";
    private Button mSendButton;
    private TextView mErrorText;
    private String mVerificationId ;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private int btnType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth  = FirebaseAuth.getInstance();

        mPhoneLayout = (LinearLayout)findViewById(R.id.phoneLayout);
        mCodeLayout = (LinearLayout)findViewById(R.id.codeLayout);

        mPhoneText = (EditText)findViewById(R.id.phoneEditText);
        mCodeText = (EditText)findViewById(R.id.codeEditText);

        mPhoneProgressBar  = (ProgressBar)findViewById(R.id.mobileProgressBar);
        mCodeProgressBar = (ProgressBar)findViewById(R.id.codeProgressBar);

        mSendButton = (Button)findViewById(R.id.sendBtn);
        mErrorText = (TextView)findViewById(R.id.errorText);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnType==0) {
                    mPhoneProgressBar.setVisibility(View.VISIBLE);
                    mPhoneText.setEnabled(false);
                    mSendButton.setEnabled(false);

                    String phoneNumber = mPhoneText.getText().toString();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60,
                            TimeUnit.SECONDS, AuthActivity.this, mCallbacks);


                }
                else{
                   mSendButton.setEnabled(false);
                   mCodeProgressBar.setVisibility(View.VISIBLE);

                   String verificationCode =  mCodeText.getText().toString();
                    PhoneAuthCredential credential = new PhoneAuthCredential(mVerificationId,verificationCode);
                    signInWithPhoneAuthCredential(credential);
                }

             }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                 mErrorText.setText("There was some error");
                 mErrorText.setVisibility(View.VISIBLE);
            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                btnType =  1;
                mVerificationId = verificationId;
                mResendToken = token;
                mPhoneProgressBar.setVisibility(View.INVISIBLE);
                mCodeLayout.setVisibility(View.VISIBLE);
                mSendButton.setText("Verify Code");
                mSendButton.setEnabled(true);

            }
        };

    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();

                            Intent mainIntent = new Intent(AuthActivity.this,MainActivity.class);
                            startActivity(mainIntent);
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid


                            }
                        }
                    }

                });
    }
    }

