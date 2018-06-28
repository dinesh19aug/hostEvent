package com.event.hostevent.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.event.hostevent.LandingPageActivity;
import com.event.hostevent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class LoginPageUtil {
    private static final String EMPTY_FIELDS = "EMPTY_FIELDS";
    private static final String INVALID_EMAIL_PATTERN = "INVALID_EMAIL_PATTERN";
    private static final String INVALID_CREDENTIALS =  "INVALID_CREDENTIALS"   ;
    private static String TAG = "LoginPageUtil";

    public static void login(String emailId, String password, FirebaseAuth mAuth, Context context){
        List <String> faultCode =doFieldValiadation(emailId, password,mAuth,context);
        if(faultCode.size()==0) {
            firebaseSign(emailId, password, mAuth, context);
        }else{
            createAndShowToast(context, faultCode);
        }

    }

    private static List<String>  doFieldValiadation(String emailId, String password, FirebaseAuth mAuth, Context context){
        List <String> faultCode = new ArrayList<>();
        if(isFieldEmpty(emailId, password)){
            faultCode.add(EMPTY_FIELDS);
        }else if(!isAnEmailAddress(emailId)){
            faultCode.add(INVALID_EMAIL_PATTERN);
        }
        return faultCode;
    }


    private static void toggleProgressSpinner(Context context) {
        ProgressBar loadSignSpinner = ((Activity)context).findViewById(R.id.loadLoginSpinner);
        if(loadSignSpinner.getVisibility()== View.VISIBLE) {
            loadSignSpinner.setVisibility(View.GONE);
        }
        else{
            loadSignSpinner.setVisibility(View.VISIBLE);
        }
    }

    private static boolean firebaseSign(String emailId, String password, final FirebaseAuth mAuth, final Context context) {
        toggleProgressSpinner(context);
        final boolean[] signInSuccess = {false};
        mAuth.signInWithEmailAndPassword(emailId, password).addOnCompleteListener((Activity) context,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            signInSuccess[0] = true;

                            toggleProgressSpinner(context);
                            startLoginAvtivity(context);

                        } else {
                             Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, EnumErrorCode.INVALID_CREDENTIALS.getErrorDesc(),
                                    Toast.LENGTH_SHORT).show();
                            signInSuccess[0] = false;
                            toggleProgressSpinner(context);

                        }


                        }
                    });
                return signInSuccess[0];

    }

    private static void startLoginAvtivity(Context context) {
        Intent landingPageIntent;
        landingPageIntent = new Intent(context.getApplicationContext(), LandingPageActivity.class);
        context.startActivity(landingPageIntent);
    }

    private static boolean isAnEmailAddress(String emailId) {
        return Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
    }

    private static boolean isFieldEmpty(String emailId, String password) {
        if(TextUtils.isEmpty(emailId) || TextUtils.isEmpty(password)){
            return true;
        }
        return false;
    }



    public static void createAndShowToast(Context context, List<String> faultCodes) {
        String errorText=null;
        if(faultCodes.get(0).equalsIgnoreCase(EMPTY_FIELDS)){
            errorText = EnumErrorCode.EMPTY_FIELDS.getErrorDesc();
        }else if(faultCodes.get(0).equalsIgnoreCase(INVALID_EMAIL_PATTERN)){
            errorText = EnumErrorCode.INVALID_EMAIL_PATTERN.getErrorDesc();
        }

        Toast.makeText(context,errorText, Toast.LENGTH_LONG).show();

    }
}
