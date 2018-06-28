package com.event.hostevent.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.event.hostevent.R;
import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 */
public class CreateEvent extends Fragment implements View.OnClickListener{

   FloatingActionButton addEventBtn;
    private static String TAG = "CreateEvent";
    public CreateEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        addEventBtn = getView().findViewById(R.id.createEventBtn);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.createEventBtn){
            Log.d(TAG, "Create Event Button Clicked");
            
        }
    }
}
