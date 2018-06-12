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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.event.hostevent.R;
import com.event.hostevent.SignUpPageActivity;
import com.event.hostevent.dao.UserDao;
import com.event.hostevent.vo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private Button signUpButton;
    private FirebaseFirestore db;
    private static String TAG = "SignUp";
    EditText emailIdField, phoneNumberText, firstNameText, lastNameText, passwordText;

    AwesomeValidation mAwesomeValidation;
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
         mAwesomeValidation = new AwesomeValidation(BASIC);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        setUpSignUp();

    }

    private void setUpSignUp() {
        signUpButton = getView().findViewById(R.id.b_signUp);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_signUp){
            Log.d(TAG,"Sign Up Button Clicked");
            initFields();
            mAwesomeValidation.clear();
           if( mAwesomeValidation.validate()) {
               String password = passwordText.getText().toString();
               User user = createUserObject();
               boolean isAccountCreated = createAccount(user, password);
           }


        }
    }

    private void initFields() {
         emailIdField = getView().findViewById(R.id.t_emailId);
         phoneNumberText = getView().findViewById(R.id.t_phone_number);
         firstNameText = getView().findViewById(R.id.t_first_name);
         lastNameText = getView().findViewById(R.id.t_last_name);
         passwordText = getView().findViewById(R.id.t_passowrd);
        validateFields();
    }

    private void validateFields() {
        mAwesomeValidation.addValidation(getActivity(),R.id.t_emailId,android.util.Patterns.EMAIL_ADDRESS, R.string.signup_err_email);
        mAwesomeValidation.addValidation(getActivity(), R.id.t_first_name, RegexTemplate.NOT_EMPTY, R.string.signup_invalid_fname);
        mAwesomeValidation.addValidation(getActivity(), R.id.t_last_name, RegexTemplate.NOT_EMPTY, R.string.signup_invalid_lname);
        mAwesomeValidation.addValidation(getActivity(), R.id.t_phone_number, RegexTemplate.TELEPHONE, R.string.signup_invalid_phone);
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{4,}$";
        mAwesomeValidation.addValidation(getActivity(), R.id.t_passowrd, passwordRegex, R.string.signup_invalid_lname);
    }

    private User createUserObject(){
        String emailId = emailIdField.getText().toString();
        String phoneNumber = phoneNumberText.getText().toString();
        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        User user = new User(firstName,lastName,phoneNumber,emailId);
        return user;
    }

    private boolean createAccount(User user, String password) {
        UserDao userDao = new UserDao();
        return userDao.createAccount((SignUpPageActivity) getContext(),user, password, mAuth,db);
    }

}
