package com.volunteer.aly.volunteerAPP;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aly on 3/7/18.
 */

public class Myadapter3 extends ArrayAdapter<Emails> {
    private  Context mcontext;
    private int resource;
    private ArrayList<Emails> arrayList;
    public Myadapter3(@NonNull Context context, int resource, @NonNull ArrayList<Emails> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.resource= resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String email_name = getItem(position).getEmail_name();
        String name = getItem(position).getName();



        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView = inflater.inflate(resource,parent,false);
        TextView namet = (TextView)convertView.findViewById(R.id.textView4);
        TextView emailt = (TextView)convertView.findViewById(R.id.textView5);
        namet.setText(name);
        emailt.setText(email_name);

        return  convertView;
    }
    @Override
    public int getCount(){
        return arrayList.size();

    }
    @Override
    public  Emails getItem(int position){
        return arrayList.get(getCount()-position-1);

    }
}
