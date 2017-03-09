package com.example.muhammadimran.minimarathon.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.muhammadimran.minimarathon.Models.getItemModel;
import com.example.muhammadimran.minimarathon.R;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class OredrCartAdapter extends BaseAdapter {
    ArrayList<getItemModel> modelArrayList;
    Context context;

    public OredrCartAdapter(ArrayList<getItemModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.order_cart_view, null);
        TextView hotelname = (TextView) v.findViewById(R.id.RestaurantNameInCartView);
        TextView totalnumber = (TextView) v.findViewById(R.id.totalnumber);
        TextView itemname = (TextView) v.findViewById(R.id.ItemNameinCart);
        TextView itemprise = (TextView) v.findViewById(R.id.ItemPriseInCart);

        getItemModel model = modelArrayList.get(i);
        itemname.setText("Name: " + model.getItemName());
        itemprise.setText("Prise: " + model.getItemPrise());

        return v;
    }
}
