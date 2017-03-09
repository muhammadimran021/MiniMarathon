package com.example.muhammadimran.minimarathon.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.muhammadimran.minimarathon.Models.AddProductModel;
import com.example.muhammadimran.minimarathon.Models.getItemModel;
import com.example.muhammadimran.minimarathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class ItemListAdapter extends BaseAdapter {

    ArrayList<AddProductModel> addProductModels;
    Context context;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    public ItemListAdapter(ArrayList<AddProductModel> addProductModels, Context context) {
        this.addProductModels = addProductModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return addProductModels.size();
    }

    @Override
    public Object getItem(int i) {
        return addProductModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.items_view, null);
        TextView name = (TextView) view1.findViewById(R.id.itemNames);
        TextView itemprise = (TextView) view1.findViewById(R.id.ItemPrise);
        TextView itemDescription = (TextView) view1.findViewById(R.id.ItemDescription);
        Button addItem = (Button) view1.findViewById(R.id.addItem);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        AddProductModel model = addProductModels.get(i);
        name.setText("Name: " + model.getProductName());
        itemprise.setText("Prise: " + model.getProductPrise());
        itemDescription.setText("Description: " + model.getProductDescription());


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Itemname = addProductModels.get(i).getProductName();
                String ItemPrise = addProductModels.get(i).getProductPrise();

                getItemModel model1 = new getItemModel(Itemname, ItemPrise);

                mDatabase.child("User-Select-Item").child(mAuth.getCurrentUser().getUid()).push().setValue(model1);


            }
        });


        return view1;
    }
}
