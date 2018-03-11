package com.example.aly.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class visits extends AppCompatActivity {
    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref;
    final ArrayList<Visit> feedsList2= new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits);

        listView = (ListView)findViewById(R.id.listviewv);

        //database
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref= mfirebasedatabase.getReference();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("visits");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList2.clear();
                for (DataSnapshot ds :dataSnapshot.getChildren()) {
                    Visit visit = ds.getValue(Visit.class );

                    feedsList2.add(visit);
                    Log.d("TAG",visit.getCount());
                    Myadapter2 myAdapter=new Myadapter2(visits.this,R.layout.feed,feedsList2);
                    listView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
