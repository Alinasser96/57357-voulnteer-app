package com.volunteer.aly.volunteerAPP;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Write extends AppCompatActivity {
    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref;
    private TextView pnamee,roome,feede;
    private String pname,room,feeds,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pnamee= (TextView)findViewById(R.id.pnamee);
        roome= (TextView)findViewById(R.id.roome);
        feede= (TextView)findViewById(R.id.feede);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
         name = user.getDisplayName();


    }

    public void post(View view) {
        pname= pnamee.getText().toString();
        room= roome.getText().toString();
        feeds= feede.getText().toString();
        mfirebasedatabase= FirebaseDatabase.getInstance();
        Feeds feed = new Feeds(name,pname,room,feeds);
        myref= mfirebasedatabase.getReference();
        DatabaseReference newref =myref.child("feed").push();
        newref.setValue(feed);
        Toast.makeText(Write.this, "Done",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SigninActivity.class);
        startActivity(intent);
    }
}
