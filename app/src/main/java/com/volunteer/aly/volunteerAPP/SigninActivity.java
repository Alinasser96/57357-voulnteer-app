package com.volunteer.aly.volunteerAPP;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    private FirebaseDatabase mfirebasedatabase;
    private DatabaseReference myref;
    private String named, currentuser;
    private static final String TAG = "SigninActivity";
    private ArrayList<Feeds> feedsList = new ArrayList<>();
    private ArrayList<String> feedsList2 = new ArrayList<>();
    private ArrayList<String> feedsList3 = new ArrayList<>();
    private int flag ;

    private ListView listView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        final Menu menus = menu;
        DatabaseReference rootRef3 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef3 = rootRef3.child("leaders_emails");
        usersRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList2.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Emails emails = ds.getValue(Emails.class);
                    feedsList2.add(emails.getEmail_id());
                   // Log.d(TAG,emails.getEmail_id());
                    flag=1;
                }
                if (feedsList2.contains(currentuser)){
                    Log.d(TAG,"true");
                    getMenuInflater().inflate(R.menu.menu3, menus);
                }
                else if (currentuser.equals("b3C28u0hX3WAIEPvtLnCMcUoUMn1")) {
                    getMenuInflater().inflate(R.menu.menu2, menus);
                }
                else{
                    Log.d(TAG,"false");
                    getMenuInflater().inflate(R.menu.menu1, menus);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


       /* currentuser = FirebaseAuth.getInstance().getUid();
        if (currentuser.equals("b3C28u0hX3WAIEPvtLnCMcUoUMn1")) {
            getMenuInflater().inflate(R.menu.menu2, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu1, menu);
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.newfeed:
                //your action
                Intent intent = new Intent(this, Write.class);
                startActivity(intent);
                break;
            case R.id.newvisit:
                //your action
                Intent intent3 = new Intent(this, NewVisit.class);
                startActivity(intent3);
                break;
            case R.id.visits:
                //your action
                Intent intent4 = new Intent(this, visits.class);
                startActivity(intent4);
                break;
            case R.id.leaderi:
                //your action
                Intent intent5 = new Intent(this,Leaders.class);
                startActivity(intent5);
                break;
            case R.id.signouti:
                //your action
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(this, MainActivity.class);
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
        flag=0;


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        named = user.getDisplayName();


        //listview to display data
        listView = (ListView) findViewById(R.id.listview21);

        //database
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref = mfirebasedatabase.getReference();
        currentuser = FirebaseAuth.getInstance().getUid();



        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("feed");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Feeds feed = ds.getValue(Feeds.class);

                    feedsList.add(feed);


                }
                Myadapter myAdapter = new Myadapter(SigninActivity.this, R.layout.feed, feedsList);
                listView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d(TAG,flag+"");
        DatabaseReference rootRef3 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef3 = rootRef3.child("leaders_emails");
        usersRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList2.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Emails emails = ds.getValue(Emails.class);
                    feedsList2.add(emails.getEmail_id());
                   // Log.d(TAG,emails.getEmail_id());
                    flag=1;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d(TAG,flag+"");

    /*    if (currentuser.equals("b3C28u0hX3WAIEPvtLnCMcUoUMn1")) {

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SigninActivity.this);
                    builder1.setMessage("Want to delete this feed ?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    feedsList.remove(i);
                                    myAdapter.notifyDataSetChanged();
                                    //new code below
                                    usersRef.getRoot().child("feed").child(keyList.get(i)).removeValue();
                                    keyList.remove(i);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                    return true;

                }
            });
        }*/

    }
}


