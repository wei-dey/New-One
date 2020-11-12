package com.example.books4share;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.books4share.Signup.SignUpUser;


public class Profile extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener{

    private TextView fullName;
    private TextView Phone;
    private TextView Address;
    private ImageView image;
    private ProfileUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        Intent intent = getIntent();

        TextView ProfileText = findViewById(R.id.MyProfileText);

        fullName = findViewById(R.id.ShowName);
        Phone = findViewById(R.id.ShowPhone);
        Address = findViewById(R.id.ShowAddress);
        image = findViewById(R.id.HeadPhoto);


        Button Edit = findViewById(R.id.EditProfile);
        String UserEmail = intent.getStringExtra(SignUpUser);
        ProfileText.setText(UserEmail);



        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProfileFragment().newInstance(user).show(getSupportFragmentManager(), "Product_Information");// pops up teh fragment
            }
        });

        Button Home = findViewById(R.id.Home);
        Button Notif = findViewById(R.id.Notification);
        Button Explore = findViewById(R.id.Explore);

    }

    /**
     *
     * @param Puser if the user click ok on fragment
     */
    @Override
    public void onOkPressed(ProfileUser Puser) {


    }






}