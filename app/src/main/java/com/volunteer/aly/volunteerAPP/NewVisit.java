package com.volunteer.aly.volunteerAPP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewVisit extends AppCompatActivity {
    private String date,time,count,relationship,volunteer;
    private EditText datet,timet,volunteert;
    private FirebaseDatabase mfirebasedatabase ;
    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visit);
        datet = (EditText)findViewById(R.id.etdate);
        timet = (EditText)findViewById(R.id.ettime);
        volunteert = (EditText)findViewById(R.id.etvolunteer);


        init();
    }
    public void init(){

        final List<String> list = new ArrayList<String>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        list.add("Item 5");

        final String[] str = {"أصدقاء", "أقرباء", "أجانب"};
        final String[] str2 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};

        final Spinner sp1 = (Spinner) findViewById(R.id.spinner);
        final Spinner sp2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, str2);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp1);

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, str);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adp2);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                count = str2[position];
                Toast.makeText(getBaseContext(), str2[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                relationship=str[position];
                Toast.makeText(getBaseContext(), str[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void postVisit(View view) {
        date= datet.getText().toString();
        time= timet.getText().toString();
        volunteer= volunteert.getText().toString();
        mfirebasedatabase= FirebaseDatabase.getInstance();
        Visit visit = new Visit(date,time,count,relationship,volunteer);
        myref= mfirebasedatabase.getReference();
        DatabaseReference newref =myref.child("visits").push();
        newref.setValue(visit);
        Toast.makeText(NewVisit.this, "Done",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SigninActivity.class);
        startActivity(intent);
    }
}
