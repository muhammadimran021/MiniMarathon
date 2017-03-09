package com.example.muhammadimran.minimarathon.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.muhammadimran.minimarathon.Models.SignUpModel;
import com.example.muhammadimran.minimarathon.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText firstname, lastname, email, password;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstname = (EditText) findViewById(R.id.Fname);
        lastname = (EditText) findViewById(R.id.Lname);
        email = (EditText) findViewById(R.id.UserEmail);
        password = (EditText) findViewById(R.id.UserPassword);

        getSupportActionBar().hide();

        // --Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

    }

    public void SignUp(View view) {
        final String FirstName = firstname.getText().toString();
        final String LastName = lastname.getText().toString();
        final String Email = email.getText().toString();
        final String Password = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(Email, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                SignUpModel model = new SignUpModel(FirstName, LastName, Email, Password);
                mDatabase.child("User-Data").child(authResult.getUser().getUid().toString()).setValue(model);
                finish();
            }
        });

    }
}
