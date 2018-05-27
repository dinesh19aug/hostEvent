package com.event.hostevent;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.event.hostevent.fragments.LoginPage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frameLayout,new LoginPage());
        ft.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onClick(View v) {
        int i = v.getId();

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
