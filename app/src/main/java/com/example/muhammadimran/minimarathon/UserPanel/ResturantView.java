package com.example.muhammadimran.minimarathon.UserPanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.muhammadimran.minimarathon.Activitys.LoginActivity;
import com.example.muhammadimran.minimarathon.Adapters.RestaurantAdapter;
import com.example.muhammadimran.minimarathon.Models.AddStoreModel;
import com.example.muhammadimran.minimarathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ResturantView extends AppCompatActivity {

    DatabaseReference mDatabase;
    RestaurantAdapter adapter;
    List<AddStoreModel> storeList;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_view);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        storeList = new ArrayList<>();
        adapter = new RestaurantAdapter(storeList, this);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        RetriveRestaurant();
    }

    public void RetriveRestaurant() {
        mDatabase.child("Store-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("TAG", dataSnapshot.getValue().toString());
                // for (DataSnapshot data : dataSnapshot.getChildren()) {
                AddStoreModel model = dataSnapshot.getValue(AddStoreModel.class);
                storeList.add(new AddStoreModel(model.getLogo(), model.getName(), model.getAddress(), model.getRatings(), model.getDeliveryfee(), model.getOrderamount()));
                adapter.notifyDataSetChanged();
                //}
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
