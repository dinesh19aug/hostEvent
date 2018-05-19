package com.event.hostevent.dao;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.event.hostevent.SignUpPageActivity;
import com.event.hostevent.vo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;



public class UserDao {
    private static String TAG = "UserDao";

    public boolean createAccount(final SignUpPageActivity signUpPageActivity, final User user, final String password, final FirebaseAuth mAuth, final FirebaseFirestore db) {
        Log.d(TAG, "createAccount:" + user.getEmail_address());
        final boolean[] isSuccessful = new boolean[1];
        mAuth.createUserWithEmailAndPassword(user.getEmail_address(), password)
                .addOnCompleteListener(signUpPageActivity, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            saveRecord(user,db);
                            showToast(signUpPageActivity, "Account Created.");
                            isSuccessful[0] = true;
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            showToast(signUpPageActivity, "Authentication failed.");
                            isSuccessful[0] = false;
                        }
                    }
                });

    return isSuccessful[0];
    }

    private void showToast(SignUpPageActivity signUpPageActivity, String message) {
        Toast.makeText(signUpPageActivity.getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    private void saveRecord(User user, FirebaseFirestore db) {
        db.collection("users").document().set(user);
    }
}
