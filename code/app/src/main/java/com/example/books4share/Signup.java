package com.example.books4share;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.HashMap;

public class Signup extends AppCompatActivity {

    public static final String SignUpUser = "SignUp";
    public String TAG = "SignUp";

    private TextView SignTiltle;
    private TextView SignEmailText;
    private TextView SignPasswordText;
    private EditText SignupEmail;
    private EditText SignupPassword;
    private EditText SignupConfirm;
    FloatingActionButton SignupExit;
    Button LoginButton;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Intent intent = getIntent();
        final String TAG = "SignUp";

        initView();

        TestValidEmail(collectionReference);

        SignupExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    public void initView() {
        SignTiltle = findViewById(R.id.signup_text);
        SignEmailText = findViewById(R.id.text_email2);
        SignPasswordText = findViewById((R.id.text_password2));

        SignupEmail = findViewById(R.id.editTextTextEmailAddress2);
        SignupPassword = findViewById(R.id.editTextTextPassword2);
        SignupConfirm = findViewById(R.id.editTextTextPassword3);
        SignupExit = findViewById(R.id.signupback);
        LoginButton = findViewById(R.id.signupButton);
    }

    public static boolean isEmail(String strEmail) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if (TextUtils.isEmpty(emailRegex)) {
            return false;
        } else {
            return strEmail.matches(emailRegex);
        }
    }

    public static int isExist(String strEmail, CollectionReference collection) {
        if(collection.whereEqualTo("Email", strEmail).get() == null){
            return 0;
        }else{
            return 1;
        }
    }

    public void TestValidEmail(final CollectionReference collection){
        LoginButton.setOnClickListener(new View.OnClickListener() {
            int flag = 0;

            @Override
            public void onClick(View v) {
                String EnterEmail = SignupEmail.getText().toString();
                String EnterPassword = SignupPassword.getText().toString();
                String EnterConfirm = SignupConfirm.getText().toString();

                if (EnterEmail.length() == 0 || EnterPassword.length() == 0 || EnterConfirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Information Missing", Toast.LENGTH_SHORT).show();
                } else if (!(EnterPassword).equals(EnterConfirm)) {
                    Toast.makeText(getApplicationContext(), "Password is not same", Toast.LENGTH_SHORT).show();
                    SignupConfirm.setText("");
                    SignupPassword.setText("");
                } else if (isEmail(EnterEmail) == false) {
                    Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                    SignupEmail.setText("");
                }else if(isExist(EnterEmail, collection) == 1 ){
                    Toast.makeText(getApplicationContext(), "Email existed", Toast.LENGTH_SHORT);

                } else {
                    HashMap<String, String> Data = new HashMap<>();
                    if (EnterEmail.length() > 0 && EnterPassword.length() > 0 && EnterConfirm.length() > 0) {
                        Data.put("Email", EnterEmail);
                        Data.put("Password", EnterPassword);
                        collectionReference
                                .document(EnterEmail)
                                .set(Data)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                        flag = 1;
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });
                    }
                    if (flag == 1) {
                        Intent SignInfo = new Intent(Signup.this, SignInfo.class);
                        SignInfo.putExtra(SignUpUser, EnterEmail);
                        startActivity(SignInfo);
                    }
                }
            }
        });
    }
}
