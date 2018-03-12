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

public class Myadapter extends ArrayAdapter<Feeds> {
    private  Context mcontext;
    private int resource;
    private  ArrayList<Feeds> arrayList;
    public Myadapter(@NonNull Context context, int resource, @NonNull ArrayList<Feeds> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.resource= resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String pname = getItem(position).getPname();
        String room = getItem(position).getRoom();
        String feed = getItem(position).getFeed();
        Feeds feeds = new Feeds(name,pname,room ,feed);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView = inflater.inflate(resource,parent,false);
        TextView namet = (TextView)convertView.findViewById(R.id.namet);
        TextView pnamet = (TextView)convertView.findViewById(R.id.pnamet);
        TextView roomt = (TextView)convertView.findViewById(R.id.roomt);
        TextView feedt = (TextView)convertView.findViewById(R.id.feedt);
        namet.setText(name);
        pnamet.setText(pname);
        roomt.setText(room);
        feedt.setText(feed);
        return  convertView;
    }
    @Override
    public int getCount(){
        return arrayList.size();

    }
    @Override
    public  Feeds getItem(int position){
        return arrayList.get(getCount()-position-1);

    }
}
