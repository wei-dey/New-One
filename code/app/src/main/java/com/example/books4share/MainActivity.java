package com.example.books4share;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView bookList;
    ArrayAdapter<Books> bookAdapter;
    ArrayList<Books> bookDataList;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = findViewById(R.id.book_list);

        bookDataList = new ArrayList<>();

        bookAdapter = new CustomList(this,bookDataList);

        bookList.setAdapter(bookAdapter);

        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Books");
    }
}