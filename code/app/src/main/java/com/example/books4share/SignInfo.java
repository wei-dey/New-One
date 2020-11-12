package com.example.books4share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.books4share.Signup.SignUpUser;

public class SignInfo extends AppCompatActivity {

    public static final String SignInfo = "SignInfo";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference Users = db.collection("Users");

    private ImageView photo;
    private TextView title;
    private TextView NameText;
    private EditText inputName;
    private TextView phoneText;
    private EditText inputPhone;
    private TextView AddressText;
    private EditText inputAddress;
    Button Confirm;
    Button Clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_info);

        Intent intent = getIntent();
        String UserEmail = intent.getStringExtra(SignUpUser);

        initView();
        putDatabase(UserEmail, Users);


    }

    public void initView(){
        photo = findViewById(R.id.imageView2);
        title = findViewById(R.id.fillin);
        NameText = findViewById(R.id.text_fullname);
        inputName = findViewById(R.id.editfullname);
        phoneText = findViewById(R.id.text_phone);
        inputPhone = findViewById(R.id.editTextPhone);
        AddressText = findViewById(R.id.text_address);
        inputAddress = findViewById(R.id.editTextAddress);

    }

    public void putDatabase(final String Email, final CollectionReference collection){
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = inputName.getText().toString();
                String Phone = inputPhone.getText().toString();
                String Address = inputAddress.getText().toString();

                if (Name.length() == 0 || Phone.length() == 0 || Address.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Profile Information Missing", Toast.LENGTH_SHORT);
                }
                while (Name.length() != 0 && Phone.length() != 0 && Address.length() == 0) {
                    if (Phone.length() > 10 || Phone.length() < 10) {
                        Toast.makeText(getApplicationContext(), "Phone NUmber is invalid", Toast.LENGTH_SHORT);
                        inputPhone.setText("");
                        continue;
                    } else {
                        Map<String, String> Data = new HashMap<>();
                        Data.put("Full Name", Name);
                        Data.put("Phone Number", Phone);
                        Data.put("Address", Address);
                        collection
                                .document(Email)
                                .collection("Profile Information")
                                .document("Details")
                                .set(Data);

                        Intent intent = new Intent(SignInfo.this, Profile.class);
                        intent.putExtra(SignInfo, Email);
                        startActivity(intent);

                    }

                }

            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputPhone.setText("");
                inputAddress.setText("");
                inputAddress.setText("");
            }
        });

    }

}