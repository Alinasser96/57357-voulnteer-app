package com.volunteer.aly.volunteerAPP;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Leaders extends AppCompatActivity {
    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref ,usersRef;
    final ArrayList<Emails> feedsList2= new ArrayList<>();
    final ArrayList<String> KeyList= new ArrayList<>();
    private ListView listView;
    Myadapter3 myAdapter;
    private Button add;
    private AutoCompleteTextView actv ;
    private ArrayList<Emails> feedsList4=new ArrayList<>();
    private ArrayList<String> feedsList5=new ArrayList<>();
    private ArrayList<String> feedsList6=new ArrayList<>();
    private ArrayAdapter<String> feedsList3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaders);
        listView = (ListView)findViewById(R.id.listviewiv);
        add =(Button)findViewById(R.id.addLeader);
        feedsList3= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);


        //database
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref= mfirebasedatabase.getReference();
        actv = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("leaders_emails");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedsList2.clear();
                KeyList.add(dataSnapshot.getKey());
                for (DataSnapshot ds :dataSnapshot.getChildren()) {
                    Emails visit = ds.getValue(Emails.class );

                    feedsList2.add(visit);
                    feedsList6.add(visit.getEmail_name());

                    myAdapter=new Myadapter3(Leaders.this,R.layout.leaders,feedsList2);
                    listView.setAdapter(myAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final DatabaseReference usersRef2 = rootRef.child("all_emails");
        usersRef2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                feedsList3.clear();
                feedsList4.clear();
                feedsList5.clear();


                for (DataSnapshot ds :dataSnapshot.getChildren()) {
                    Emails emails = ds.getValue(Emails.class);
                    String suggestion = emails.getEmail_name();
                    feedsList3.add(suggestion);
                    feedsList5.add(suggestion);
                    feedsList4.add(emails);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        actv.setAdapter(feedsList3);






        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Leaders.this);
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

    public void addleader(View view) {
         if (feedsList5.contains(actv.getText().toString())){

        int i = feedsList5.indexOf(actv.getText().toString());
        if(feedsList6.contains(feedsList4.get(i).getEmail_name())){
            Toast.makeText(Leaders.this, "this account is already added",
                    Toast.LENGTH_LONG).show();
        }

        else{
        DatabaseReference newref =myref.child("leaders_emails").push();
        newref.setValue(feedsList4.get(i));}
    }
    else{
        Toast.makeText(Leaders.this, "this is not valid email",
                Toast.LENGTH_LONG).show();
    }}
}
