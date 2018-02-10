package com.example.aishi.mabokonline;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Aishi on 1/17/2018.
 */

public class MyAdapter extends ArrayAdapter <Person> {
    Activity activity;
    int resource;
    List<Person> list;

    public MyAdapter(Activity activity, int resource, List<Person> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(resource, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.getImages);
        TextView name = (TextView) view.findViewById(R.id.getName);
        TextView info = (TextView) view.findViewById(R.id.getInfo);

        name.setText(list.get(position).getName());
        info.setText(list.get(position).getInfo());
        Glide.with(activity).load(list.get(position).getImangeUri()).into(imageView);

        return view;
    }
}
