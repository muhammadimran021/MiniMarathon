package com.example.muhammadimran.minimarathon.fragmentss;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.muhammadimran.minimarathon.Models.AddProductModel;
import com.example.muhammadimran.minimarathon.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProduct extends Fragment {

    private EditText p_name, p_prise, p_description;
    private Button addProduct;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private Spinner restaurantNames;

    public AddProduct() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        p_name = (EditText) view.findViewById(R.id.ProductName);
        p_prise = (EditText) view.findViewById(R.id.ProductPrise);
        p_description = (EditText) view.findViewById(R.id.Product_Description);
        addProduct = (Button) view.findViewById(R.id.AddProduct);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        restaurantNames = (Spinner) view.findViewById(R.id.restaurantNames);


        // -- adding values in spinner from firebase
        mDatabase.child("Stores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> restaurants = new ArrayList<String>();
                for (DataSnapshot hotels : dataSnapshot.getChildren()) {
                    Log.d("TAg", hotels.getValue().toString());
                    String HotelsName = hotels.child("HotelName").getValue(String.class);
                    restaurants.add(HotelsName);
                }
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, restaurants);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                restaurantNames.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addProduct();
        return view;
    }

    public void addProduct() {
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Adding Product");
                progressDialog.setMessage("Plz wait...");
                progressDialog.show();
                String ProductName = p_name.getText().toString();
                String ProductPrise = p_prise.getText().toString();
                String ProductDescription = p_description.getText().toString();
                String spinnername = restaurantNames.getSelectedItem().toString();
                AddProductModel product = new AddProductModel(ProductName, ProductPrise, ProductDescription);
                mDatabase.child("Products").child(spinnername).push().setValue(product);
                p_name.setText("");
                p_prise.setText("");
                p_description.setText("");
                progressDialog.dismiss();

            }
        });
    }

}
