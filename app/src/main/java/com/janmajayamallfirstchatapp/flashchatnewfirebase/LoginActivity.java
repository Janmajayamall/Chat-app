package com.janmajayamallfirstchatapp.flashchatnewfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.jar.Manifest;


public class LoginActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // TODO: Grab an instance of FirebaseAuth

        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   {
        // TODO: Call attemptLogin() here
        attemptLogin();

    }

    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.janmajayamallfirstchatapp.flashchatnewfirebase.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    // TODO: Complete the attemptLogin() method
    private void attemptLogin() {

        String emailLogin = mEmailView.getText().toString();
        String passwordLogin = mPasswordView.getText().toString();

        if (emailLogin.equals("") || passwordLogin.equals("")){
            return;
        }
        Toast.makeText(this, "Logging in the user", Toast.LENGTH_SHORT).show();



        // TODO: Use FirebaseAuth to sign in with email & password
        mAuth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("FlashChat", "signInUse onComplete(): " + task.isSuccessful());

                if (!task.isSuccessful()){
                    Log.d("FlashChat", "Problem Signing in " + task.getException());
                    showErrorDialog("Wrong Credentials!");
                    return;
                    }else{
                        Intent intent = new Intent(LoginActivity.this, MainChatActivity.class);
                        finish();
                        startActivity(intent);}
            }
        });



    }

    // TODO: Show error on screen with an alert dialog

    private void showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle("Error")
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }


}