package com.example.aly.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends Activity {
    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref;
    private TextView pnamee,roome,feede;
    private String pname,room,feeds,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pnamee= (TextView)findViewById(R.id.pnamee);
        roome= (TextView)findViewById(R.id.roome);
        feede= (TextView)findViewById(R.id.feede);
    }
}
