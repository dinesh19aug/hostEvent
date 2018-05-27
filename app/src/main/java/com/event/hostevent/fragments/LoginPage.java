package com.event.hostevent.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.event.hostevent.LoginPageActivity;
import com.event.hostevent.R;
import com.event.hostevent.SignUpPageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginPage extends Fragment implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button loginButton;
    private Button signUpButton;
    private static String TAG = "LoginPage";
    public LoginPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login_page, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
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

        signUpButton = getView().findViewById(R.id.email_create_account_button);
        signUpButton.setOnClickListener(this);
    }

    private void setUpLogin() {
        loginButton = getView().findViewById(R.id.email_sign_in_button);
        loginButton.setOnClickListener(this);
    }

    private void signOut() {
        mAuth.signOut();

    }


    private void sendEmailVerification() {
        // Disable button
        getView().findViewById(R.id.verify_email_button).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        getView().findViewById(R.id.verify_email_button).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(getContext(),
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_create_account_button) {
            Intent createAccountIntent;
            createAccountIntent = new Intent(getContext().getApplicationContext(), SignUpPageActivity.class);
            startActivity(createAccountIntent);


        } else if (i == R.id.email_sign_in_button) {
            Intent loginIntent;
            loginIntent = new Intent(getContext().getApplicationContext(), LoginPageActivity.class);
            startActivity(loginIntent);
            //signIn(emailId.getText().toString(), password.getText().toString());
        } else if (i == R.id.sign_out_button) {
            signOut();
        } else if (i == R.id.verify_email_button) {
            sendEmailVerification();
        }
    }
}
