package com.event.hostevent.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.event.hostevent.R;
import com.event.hostevent.SignUpPageActivity;
import com.event.hostevent.dao.UserDao;
import com.event.hostevent.vo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private Button signUpButton;
    private FirebaseFirestore db;
    private static String TAG = "SignUp";
    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        setUpSignUp();
    }

    private void setUpSignUp() {
        signUpButton = (Button) getView().findViewById(R.id.b_signUp);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_signUp){
            Log.d(TAG,"Sign Up Button Clicked");
            String password =((EditText) getView().findViewById(R.id.t_passowrd)).getText().toString();
            User user = createUserObject();
            boolean isAccountCreated = createAccount(user, password);

        }
    }

    private User createUserObject(){
        String emailId = ((EditText)getView().findViewById(R.id.t_emailId)).getText().toString();
        String phoneNumber = ((EditText) getView().findViewById(R.id.t_phone_number)).getText().toString();
        String firstName = ((EditText) getView().findViewById(R.id.t_first_name)).getText().toString();
        String lastName = ((EditText) getView().findViewById(R.id.t_last_name)).getText().toString();
        User user = new User(firstName,lastName,phoneNumber,emailId);
        return user;
    }

    private boolean createAccount(User user, String password) {
        UserDao userDao = new UserDao();
        return userDao.createAccount((SignUpPageActivity) getContext(),user, password, mAuth,db);
    }

}
