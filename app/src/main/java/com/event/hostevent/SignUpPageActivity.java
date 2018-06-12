package com.event.hostevent;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.event.hostevent.fragments.SignUp;

public class SignUpPageActivity extends AppCompatActivity implements View.OnClickListener{


    private static String TAG = "SignUpPageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frameLayout,new SignUp());
        ft.commit();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void onClick(View v) {
        int i = v.getId();

    }


}
