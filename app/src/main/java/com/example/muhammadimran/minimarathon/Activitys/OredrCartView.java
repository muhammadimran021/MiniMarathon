package com.example.muhammadimran.minimarathon.Activitys;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhammadimran.minimarathon.Adapters.OredrCartAdapter;
import com.example.muhammadimran.minimarathon.Models.PlaceOrder;
import com.example.muhammadimran.minimarathon.Models.getItemModel;
import com.example.muhammadimran.minimarathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OredrCartView extends AppCompatActivity {


    ArrayList<getItemModel> models;
    OredrCartAdapter adapter;
    ListView listView;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private TextView subtotals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oredr_cart_view);
        getSupportActionBar().hide();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        subtotals = (TextView) findViewById(R.id.SubTotal);


        listView = (ListView) findViewById(R.id.OrderCArtList);
        models = new ArrayList<>();
        adapter = new OredrCartAdapter(models, this);
        listView.setAdapter(adapter);
        OrderCart();

    }

    public void OrderCart() {

        mDatabase.child("User-Select-Item").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("TAGs", dataSnapshot.getValue().toString());
                if (dataSnapshot != null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {

                        Log.d("TAGG", data.child("itemPrise").toString());
                        getItemModel itemModel = data.getValue(getItemModel.class);

                        models.add(itemModel);
                        adapter.notifyDataSetChanged();

                    }

                    if (models.size() > 0) {
                        int subtotal = 0;
                        for (int i = 0; i < models.size(); i++) {
                            subtotal = subtotal + Integer.parseInt(models.get(i).getItemPrise());
                        }
                        subtotals.setText("SubTotal: " + subtotal);
                    }

                } else {
                    Toast.makeText(OredrCartView.this, "Sorry there is no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addItems(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(OredrCartView.this);
        builder.setMessage("Place Your Order...");
        final View views = LayoutInflater.from(OredrCartView.this).inflate(R.layout.order_placement_view, null);
        final EditText fname = (EditText) views.findViewById(R.id.FirstName);
        final EditText lname = (EditText) views.findViewById(R.id.LastName);
        final EditText mobile = (EditText) views.findViewById(R.id.Mobile);
        final EditText company = (EditText) views.findViewById(R.id.Company);
        final EditText street = (EditText) views.findViewById(R.id.Street);
        final EditText floor = (EditText) views.findViewById(R.id.Floor);
        final EditText area = (EditText) views.findViewById(R.id.Area);
        final EditText city = (EditText) views.findViewById(R.id.City);
        final EditText comment = (EditText) views.findViewById(R.id.Comment);
        builder.setView(views);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // Button placeOrder = (Button) views.findViewById(R.id.placeOrder);

                PlaceOrder placeOrder1 = new PlaceOrder(fname.getText().toString(), lname.getText().toString(), mobile.getText().toString(), company.getText().toString(), street.getText().toString(), floor.getText().toString(), area.getText().toString(), city.getText().toString(), comment.getText().toString());
                mDatabase.child("Place-Order").child(mAuth.getCurrentUser().getUid()).push().setValue(placeOrder1);
                mDatabase.child("User-Select-Item").child(mAuth.getCurrentUser().getUid()).removeValue();

            }
        });


        builder.create().show();

    }
}
