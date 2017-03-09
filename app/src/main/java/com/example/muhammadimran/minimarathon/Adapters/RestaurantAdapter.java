package com.example.muhammadimran.minimarathon.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muhammadimran.minimarathon.Activitys.ViewMenue;
import com.example.muhammadimran.minimarathon.AdimPanelViews.AddStore;
import com.example.muhammadimran.minimarathon.Models.AddStoreModel;
import com.example.muhammadimran.minimarathon.Models.getItemModel;
import com.example.muhammadimran.minimarathon.R;
import com.example.muhammadimran.minimarathon.UserPanel.ResturantView;

import java.net.URI;
import java.util.List;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.AdapterViewHolder> {

    private List<AddStoreModel> addStoreModels;
    private Context context;

    public RestaurantAdapter(List<AddStoreModel> addStoreModels, Context context) {
        this.addStoreModels = addStoreModels;
        this.context = context;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_view, parent, false);
        return new AdapterViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, final int position) {

        final AddStoreModel model = addStoreModels.get(position);
//        Uri myUri = Uri.parse(model.getLogo());
//        holder.imageView.setImageURI(myUri);
        Glide.with(context).load(model.getLogo()).into(holder.imageView);
        holder.restaurant_name.setText("Name: " + model.getName());
        holder.delivery_fee.setText("Delivery Fee: " + model.getDeliveryfee());
        holder.M_Amount_Order.setText("Minimum Amount Order: " + model.getOrderamount());
        holder.ratingBars.setRating(Float.parseFloat(model.getRatings()));

        // -- menue button
        holder.Menue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewMenue.class);
                i.putExtra("name", addStoreModels.get(position).getName());
                i.putExtra("DeliveryFee", addStoreModels.get(position).getDeliveryfee());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addStoreModels.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView restaurant_name;
        private TextView delivery_fee;
        private TextView M_Amount_Order;
        private RatingBar ratingBars;
        private Button Menue;

        public AdapterViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.resturantimage);
            restaurant_name = (TextView) itemView.findViewById(R.id.name_Resturant);
            delivery_fee = (TextView) itemView.findViewById(R.id.Deliver_fee);
            M_Amount_Order = (TextView) itemView.findViewById(R.id.MinimumAmount);
            ratingBars = (RatingBar) itemView.findViewById(R.id.ratingBars);
            Menue = (Button) itemView.findViewById(R.id.ViewMenue);
        }
    }
}
