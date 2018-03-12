package com.volunteer.aly.volunteerAPP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SigninActivity extends AppCompatActivity {

    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref;
    private String named,currentuser;
    private static final String TAG = "SigninActivity";
    final ArrayList<Feeds> feedsList= new ArrayList<>();
    final ArrayList<Feeds> feedsList2= new ArrayList<>();

    private ListView listView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        currentuser = FirebaseAuth.getInstance().getUid();
        if(currentuser.equals("b3C28u0hX3WAIEPvtLnCMcUoUMn1")){
            getMenuInflater().inflate(R.menu.menu2, menu);
        }
        else {
            getMenuInflater().inflate(R.menu.menu1, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.newfeed:
                //your action
                Intent intent = new Intent(this,Write.class);
                startActivity(intent);
                break;
            case R.id.newvisit:
                //your action
                Intent intent3 = new Intent(this,NewVisit.class);
                startActivity(intent3);
                break;
            case R.id.visits:
                //your action
                Intent intent4 = new Intent(this,visits.class);
                startActivity(intent4);
                break;
            case R.id.signouti:
                //your action
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(this,MainActivity.class);
                startActivity(intent2);
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        named = user.getDisplayName();






        //listview to display data
        listView = (ListView)findViewById(R.id.listview21);

        //database
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref= mfirebasedatabase.getReference();
        currentuser = FirebaseAuth.getInstance().getUid();
        if(currentuser.equals("b3C28u0hX3WAIEPvtLnCMcUoUMn1")){

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
















}
