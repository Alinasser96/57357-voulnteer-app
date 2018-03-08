package com.example.aly.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {
    private Button signout;
    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref;
    private static final String TAG = "SigninActivity";
    final ArrayList<Feeds> feedsList= new ArrayList<>();
    final ArrayList<Feeds> feedsList2= new ArrayList<>();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signout=(Button)findViewById(R.id.button4);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();
        Feeds feed2 = new Feeds("a","b","c","d");
        feedsList2.add(feed2);

        //listview to display data
        listView = (ListView)findViewById(R.id.listview21);

        //database
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref= mfirebasedatabase.getReference();





        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();
            signout.setText("good by "+ name);
        }

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("feed");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList.clear();
                for (DataSnapshot ds :dataSnapshot.getChildren()) {
                    Feeds feed = ds.getValue(Feeds.class );

                    feedsList.add(feed);
                    Log.d("TAG",feed.getFeed());
                    Myadapter myAdapter=new Myadapter(SigninActivity.this,R.layout.feed,feedsList);
                    listView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
       Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void newf(View view) {
        Intent intent = new Intent(this,Write.class);
        startActivity(intent);


    }













}
