package com.samapps.aajabhai.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference mdbReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setUpDbConnection();
        recyclerView=(RecyclerView) findViewById(R.id.list);

        setUpFirebaseAdapter();
    }

    private void setUpDbConnection(){
        FirebaseApp.initializeApp(Main2Activity.this);
        mdbReference=FirebaseDatabase.getInstance()
                .getReference("database");
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<UserId, FirebaseHomeViewHolder>
                (UserId.class, R.layout.image_list_item, FirebaseHomeViewHolder.class,
                        mdbReference) {

            @Override
            protected void populateViewHolder(FirebaseHomeViewHolder viewHolder,
                                              UserId model, int position) {
                viewHolder.bindImages(model);
            }
        };
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
