package com.volunteer.aly.volunteerAPP;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class visits extends AppCompatActivity {
    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref ,usersRef;
    final ArrayList<Visit> feedsList2= new ArrayList<>();
    final ArrayList<String> KeyList= new ArrayList<>();
    private ListView listView;
    Myadapter2 myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits);

        listView = (ListView)findViewById(R.id.listviewv);

        //database
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref= mfirebasedatabase.getReference();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("visits");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList2.clear();
                KeyList.add(dataSnapshot.getKey());
                for (DataSnapshot ds :dataSnapshot.getChildren()) {
                    Visit visit = ds.getValue(Visit.class );

                    feedsList2.add(visit);
                    Log.d("TAG",visit.getCount());
                    myAdapter=new Myadapter2(visits.this,R.layout.feed,feedsList2);
                    listView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(visits.this);
                builder1.setMessage("Want to delete this feed ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                feedsList2.remove(i);
                                myAdapter.notifyDataSetChanged();
                                //new code below
                                usersRef.getRoot().child("visit").child(KeyList.get(i)).removeValue();
                                KeyList.remove(i);
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
    }
}
