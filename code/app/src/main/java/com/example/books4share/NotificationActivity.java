// Zexin Cai is responsible for this part
// This is to implement notification activities

package com.example.books4share;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    ListView incomingList;
    ListView outgoingList;
    ArrayAdapter<IncomingRequest> inAdapter;
    ArrayList<IncomingRequest> inDataList;
    ArrayAdapter<OutgoingRequest> outAdapter;
    ArrayList<OutgoingRequest> outDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_request);

        incomingList = findViewById(R.id.incoming_list);
        outgoingList = findViewById(R.id.outgoing_list);

        String []bookName = {"book1", "book2", "book3"};
        String []borrowerName = {"borrower1", "borrower2", "borrower3"};
        String []bookStatus = {"status1", "status2", "status3"};
        String []ownerName = {"owner1", "owner2", "owner3"};

        inDataList = new ArrayList<>();
        outDataList = new ArrayList<>();

        for (int i = 0; i < bookName.length; i++) {
            inDataList.add((new IncomingRequest(bookName[i], borrowerName[i])));
            outDataList.add((new OutgoingRequest(bookName[i], bookStatus[i], ownerName[i])));
        }

        inAdapter = new ArrayAdapter<>(this, R.layout.incoming_content, inDataList);
        outAdapter = new ArrayAdapter<>(this, R.layout.outgoing_content, outDataList);

        incomingList.setAdapter(inAdapter);
        outgoingList.setAdapter(outAdapter);
    }
}
