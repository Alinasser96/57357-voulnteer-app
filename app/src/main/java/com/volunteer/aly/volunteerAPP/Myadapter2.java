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

public class Myadapter2 extends ArrayAdapter<Visit> {
    private  Context mcontext;
    private int resource;
    private ArrayList<Visit> arrayList;
    public Myadapter2(@NonNull Context context, int resource, @NonNull ArrayList<Visit> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.resource= resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String date = getItem(position).getDate();
        String time = getItem(position).getTime();
        String count = getItem(position).getCount();
        String relationship = getItem(position).getRelashionship();
        String volunteer= getItem(position).getVolunteer();
        Visit visit = new Visit(date,time,count ,relationship,volunteer);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView = inflater.inflate(resource,parent,false);
        TextView namet = (TextView)convertView.findViewById(R.id.namet);
        TextView pnamet = (TextView)convertView.findViewById(R.id.pnamet);
        TextView roomt = (TextView)convertView.findViewById(R.id.roomt);
        TextView feedt = (TextView)convertView.findViewById(R.id.feedt);
        namet.setText(date+"\n"+time);
        pnamet.setText(count);
        roomt.setText(relationship);
        feedt.setText(volunteer);
        return  convertView;
    }
    @Override
    public int getCount(){
        return arrayList.size();

    }
    @Override
    public  Visit getItem(int position){
        return arrayList.get(getCount()-position-1);

    }
}
