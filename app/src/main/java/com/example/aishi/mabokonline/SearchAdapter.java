package com.example.aishi.mabokonline;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
/**
 * Created by Aishi on 1/17/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> eventNameList;
    ArrayList<String> eventInfoList;
    ArrayList<String> eventPicList;

    class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView mainObject;
        TextView event_name, event_info;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mainObject= (ImageView) itemView.findViewById(R.id.getImages);
            event_name= (TextView) itemView.findViewById(R.id.event_name);
            event_info= (TextView) itemView.findViewById(R.id.event_info);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> eventNameList, ArrayList<String> eventInfoList, ArrayList<String> eventPicList){
        this.context= context;
        this.eventNameList= eventNameList;
        this.eventInfoList= eventInfoList;
        this.eventPicList= eventPicList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        holder.event_name.setText(eventNameList.get(position));
        holder.event_info.setText(eventInfoList.get(position));

        Glide.with(context).load(eventPicList.get(position)).asBitmap().placeholder(R.mipmap.ic_Launcher_roud).into(holder.mainObject);

    }


    @Override
    public int getItemCount() {
        return eventNameList.size();
    }

}
