package com.example.muhammadimran.minimarathon.AdimPanelViews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.example.muhammadimran.minimarathon.Models.AddStoreModel;
import com.example.muhammadimran.minimarathon.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class AddStore extends AppCompatActivity {

    private ImageButton imageLogo;
    private EditText resturant_name, adress, deliveryfee, OrderAmount;
    private RatingBar ratingBar;
    public static int REQUEST = 1;
    private Uri mImageUri = null;
    private DatabaseReference mdDatabase;
    private StorageReference mStorage;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);
        imageLogo = (ImageButton) findViewById(R.id.logoimage);
        resturant_name = (EditText) findViewById(R.id.RestName);
        adress = (EditText) findViewById(R.id.Adress);
        deliveryfee = (EditText) findViewById(R.id.DeliveryFee);
        OrderAmount = (EditText) findViewById(R.id.minimummOrderAmount);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        getSupportActionBar().hide();

        // -- Firebase
        mdDatabase = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        // --ProgressDialog
        progressDialog = new ProgressDialog(AddStore.this);

    }

    public void SelectLogo(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), REQUEST);
    }

    public void AddStore(View view) {
        progressDialog.setTitle("Adding Store");
        progressDialog.setMessage("Plz Wait...");
        progressDialog.show();
        final String name = resturant_name.getText().toString();
        final String addres = adress.getText().toString();
        final String deliveryFee = deliveryfee.getText().toString();
        final String Amount = OrderAmount.getText().toString();
        final String ratingbar = String.valueOf(ratingBar.getRating());
        StorageReference storage = mStorage.child("Images").child(mImageUri.getLastPathSegment().toString());
        storage.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String imageUrl = taskSnapshot.getDownloadUrl().toString();
                AddStoreModel addStoreModel = new AddStoreModel(imageUrl, name, addres, ratingbar, deliveryFee, Amount);
                mdDatabase.child("Store-info").push().setValue(addStoreModel);
                mdDatabase.child("Stores").push().child("HotelName").setValue(name);
                finish();
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULT_OK) {
            Uri ImageUri = data.getData();
            CropImage.activity(ImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mImageUri = result.getUri();

                Log.d("TAG", "val : " + mImageUri);

                Glide.with(AddStore.this).load(mImageUri).into(imageLogo);
                // ImageButton.setImageURI(mImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }
    }
}
