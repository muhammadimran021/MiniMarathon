//package com.example.muhammadimran.minimarathon.Activitys;
//
//import android.app.ProgressDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import com.example.muhammadimran.minimarathon.Models.AddProductModel;
//import com.example.muhammadimran.minimarathon.R;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AddProducts extends AppCompatActivity {
//    private EditText p_name, p_prise, p_description;
//    private Button addProduct;
//    private DatabaseReference mDatabase;
//    private ProgressDialog progressDialog;
//    private Spinner restaurantNames;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_blank);
//        getSupportActionBar().hide();
//        p_name = (EditText) findViewById(R.id.ProductName);
//        p_prise = (EditText) findViewById(R.id.ProductPrise);
//        p_description = (EditText) findViewById(R.id.Product_Description);
//        addProduct = (Button) findViewById(R.id.AddProduct);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        restaurantNames = (Spinner) findViewById(R.id.restaurantNames);
//
//        progressDialog = new ProgressDialog(this);
//
//        // -- adding values in spinner from firebase
//        mDatabase.child("Stores").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                List<String> restaurants = new ArrayList<String>();
//                for (DataSnapshot hotels : dataSnapshot.getChildren()) {
//                    Log.d("TAg", hotels.getValue().toString());
//                    String HotelsName = hotels.child("HotelName").getValue(String.class);
//                    restaurants.add(HotelsName);
//                }
//                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AddProducts.this, android.R.layout.simple_spinner_item, restaurants);
//                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                restaurantNames.setAdapter(areasAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        addProducts();
//
//    }
//
//    public void addProducts() {
//        addProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressDialog.setTitle("Adding Product");
//                progressDialog.setMessage("Plz wait...");
//                progressDialog.show();
//                String ProductName = p_name.getText().toString();
//                String ProductPrise = p_prise.getText().toString();
//                String ProductDescription = p_description.getText().toString();
//                String spinnername = restaurantNames.getSelectedItem().toString();
//                AddProductModel product = new AddProductModel(ProductName, ProductPrise, ProductDescription);
//                mDatabase.child("Products").child(spinnername).push().setValue(product);
//                p_name.setText("");
//                p_prise.setText("");
//                p_description.setText("");
//                progressDialog.dismiss();
//
//            }
//        });
//    }
//}
