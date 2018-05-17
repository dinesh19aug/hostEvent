package com.event.hostevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        setUpLogin();
        setUpSignUp();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void setUpSignUp() {
        signUpButton = (Button) findViewById(R.id.email_create_account_button);
        signUpButton.setOnClickListener(this);
    }

    private void setUpLogin() {
        loginButton = (Button) findViewById(R.id.email_sign_in_button);
        loginButton.setOnClickListener(this);
    }

    private void signOut() {
        mAuth.signOut();

    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verify_email_button).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verify_email_button).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(MainActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_create_account_button) {
            Intent createAccountIntent;
            createAccountIntent = new Intent(getApplicationContext(), SignUpPageActivity.class);
            startActivity(createAccountIntent);


        } else if (i == R.id.email_sign_in_button) {
            Intent loginIntent;
            loginIntent = new Intent(getApplicationContext(), LoginPageActivity.class);
            startActivity(loginIntent);
            //signIn(emailId.getText().toString(), password.getText().toString());
        } else if (i == R.id.sign_out_button) {
            signOut();
        } else if (i == R.id.verify_email_button) {
            sendEmailVerification();
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
