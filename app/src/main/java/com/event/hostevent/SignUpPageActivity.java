package com.event.hostevent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.event.hostevent.dao.UserDao;
import com.event.hostevent.vo.Event;
import com.event.hostevent.vo.Invitee;
import com.event.hostevent.vo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpPageActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    private static String TAG = "SignUpPageActivity";
    private Button signUpButton;


    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        setUpSignUp();
    }

    private void setUpSignUp() {
        signUpButton = (Button) findViewById(R.id.b_signUp);
        signUpButton.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_signUp){
            Log.d(TAG,"Sign Up Button Clicked");
            String emailId = ((EditText)findViewById(R.id.t_emailId)).getText().toString();
            String password =((EditText) findViewById(R.id.t_passowrd)).getText().toString();
            String phoneNumber = ((EditText) findViewById(R.id.t_phone_number)).getText().toString();
            String firstName = ((EditText) findViewById(R.id.t_first_name)).getText().toString();
            String lastName = ((EditText) findViewById(R.id.t_last_name)).getText().toString();
            User user = new User(firstName,lastName,phoneNumber,emailId);
            UserDao userDao = new UserDao();
            userDao.createAccount(this,user, password, mAuth,db);


        }
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
