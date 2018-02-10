package com.example.aishi.mabokonline;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.view.menu.CascadingMenuPopup;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.view.menu.ActionMenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.core.Tag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.util.Log.i;


public class SearchFragment extends Fragment {
    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> eventNameList;
    ArrayList<String> eventInfoList;
    ArrayList<String> eventPicList;
    SearchAdapter searchAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_search);

        search_edit_text = (EditText) getView().findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));

        eventNameList= new ArrayList<>();
        eventInfoList= new ArrayList<>();
        eventPicList=  new ArrayList<>();

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    setAdapter(s.toString());
                } else{
                    eventNameList.clear();
                    eventInfoList.clear();
                    eventPicList.clear();
                    recyclerView.removeAllViews();
                }

            }
        });
        }

        private void setAdapter(final String searchedString){

            databaseReference.child("Event Name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    eventNameList.clear();
                    eventInfoList.clear();
                    eventPicList.clear();
                    recyclerView.removeAllViews();

                    int counter=0;

                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        String uid= snapshot.getKey();
                        String event_name= snapshot.child("event_name").getValue(String.class);
                        String event_info= snapshot.child("event_info").getValue(String.class);
                        String mainObject= snapshot.child("mainObject").getValue(String.class);

                        if(event_name.toLowerCase().contains(searchedString.toLowerCase())){
                            eventNameList.add(event_name);
                            eventInfoList.add(event_info);
                            eventPicList.add(mainObject);
                            counter++;
                        } else if (event_info.toLowerCase().contains(searchedString.toLowerCase())){
                            eventNameList.add(event_name);
                            eventInfoList.add(event_info);
                            eventPicList.add(mainObject);
                            counter++;
                        }

                        if (counter ==15)
                            break;
                    }

                    searchAdapter= new SearchAdapter(SearchFragment.this, eventNameList, eventInfoList, eventPicList);
                    recyclerView.setAdapter(searchAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }