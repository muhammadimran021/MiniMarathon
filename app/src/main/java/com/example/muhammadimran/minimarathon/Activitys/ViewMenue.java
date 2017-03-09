package com.example.muhammadimran.minimarathon.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.muhammadimran.minimarathon.Adapters.ItemListAdapter;
import com.example.muhammadimran.minimarathon.Models.AddProductModel;
import com.example.muhammadimran.minimarathon.Models.PlaceOrder;
import com.example.muhammadimran.minimarathon.Models.getItemModel;
import com.example.muhammadimran.minimarathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewMenue extends AppCompatActivity {

    ArrayList<AddProductModel> addProductModelArrayList;
    ItemListAdapter adapter;
    ListView listView;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menue);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.ListView);
        fab = (FloatingActionButton) findViewById(R.id.addCartList);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        addProductModelArrayList = new ArrayList<>();
        adapter = new ItemListAdapter(addProductModelArrayList, this);
        listView.setAdapter(adapter);


        getItemsNode();
        addMyItemsBill();
    }

    public void getItemsNode() {


        String s = getIntent().getStringExtra("name");

        mDatabase.child("Products").child(s).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AddProductModel addProductModel = dataSnapshot.getValue(AddProductModel.class);
                addProductModelArrayList.add(addProductModel);
                adapter.notifyDataSetChanged();

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

    public void addMyItemsBill() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewMenue.this, OredrCartView.class);
                startActivity(i);
            }
        });
    }

}
