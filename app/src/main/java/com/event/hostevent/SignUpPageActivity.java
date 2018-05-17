package com.event.hostevent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
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
        setUpSignUp();
    }

    private void setUpSignUp() {
        signUpButton = (Button) findViewById(R.id.b_signUp);
        signUpButton.setOnClickListener(this);
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveRecord();

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpPageActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void saveRecord() {
        Map<String, Object> user = new HashMap<>();
        user.put("First_Name", "Ada");
        user.put("Last_Name", "Lovelace");
        user.put("Phone_Number", 1815);
        user.put("Email_Id", "dinesh@gmail.com");

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_signUp){
            Log.d(TAG,"Sign Up Button Clicked");
            String emailId = ((EditText)findViewById(R.id.t_emailId)).getText().toString();
            String password =((EditText) findViewById(R.id.t_passowrd)).getText().toString();
            createAccount(emailId,password);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
