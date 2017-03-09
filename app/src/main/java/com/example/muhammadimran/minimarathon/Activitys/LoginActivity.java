package com.example.muhammadimran.minimarathon.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.muhammadimran.minimarathon.NavigationActivity.Home;
import com.example.muhammadimran.minimarathon.R;
import com.example.muhammadimran.minimarathon.UserPanel.ResturantView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private TextView noAccount;
    private FirebaseAuth mAuth;
    private EditText UserEmail, UserPassword;
    private ProgressDialog progressDialog;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Profile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        noAccount = (TextView) findViewById(R.id.noAccount);
        UserEmail = (EditText) findViewById(R.id.Email);
        UserPassword = (EditText) findViewById(R.id.Password);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();


        // --progressDialog
        progressDialog = new ProgressDialog(LoginActivity.this);


        //
        fbLogin();
        noaccount();
    }

    public void noaccount() {
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void Login(View view) {
        progressDialog.setTitle("LogingIn..");
        progressDialog.setMessage("Plz Wait...");
        progressDialog.show();
        final String uName = UserEmail.getText().toString();
        final String uPassword = UserPassword.getText().toString();
        if (uName.equals("Admin@gmail.com") && uPassword.equals("admin123")) {
            Intent intent = new Intent(LoginActivity.this, Home.class);
            startActivity(intent);
            finish();
            progressDialog.dismiss();
        } else {
            mAuth.signInWithEmailAndPassword(uName, uPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Intent intent = new Intent(LoginActivity.this, ResturantView.class);
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void fbLogin() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_birthday", "email"));
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                // App code

                                Intent i = new Intent(LoginActivity.this, ResturantView.class);
                                startActivity(i);
                                AccessToken accessToken = loginResult.getAccessToken();
                                profile = Profile.getCurrentProfile();
                                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        try {
                                            Log.e("Graph Resp Json:", "" + object.toString());
                                            Log.e("Graph Resp Raw:", "" + response.getRawResponse());
                                            String token = AccessToken.getCurrentAccessToken().getToken();
                                            Log.e("Graph Token:", "" + token);
                                            Log.e("respJSONObj", "" + response.getJSONObject());

//                                            String first_namefire = object.getString("first_name");
//                                            Log.e("FirstName", "" + first_namefire);
//                                            user.setFname(first_namefire);
//                                            String last_namefire = object.getString("last_name");
//                                            Log.e("LastName", "" + last_namefire);
//                                            user.setLname(last_namefire);
//                                            String emailfire = object.getString("email");
//                                            Log.e("Email", "" + emailfire);
//                                            user.setEmail(emailfire);
//                                            String birthdayfire = object.getString("birthday");
//                                            Log.e("birthday", "" + birthdayfire);
//                                            user.setDob(birthdayfire);
//                                            String genderfire = object.getString("gender");
//                                            Log.e("gender :", "" + genderfire);
//                                            user.setGender(genderfire);
//                                            String dpfire = Profile.getCurrentProfile().getProfilePictureUri(400, 400).toString();
//                                            Log.e("Graph Dp:", "" + dpfire);
//                                            user.setImgUrl(dpfire);


                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }

                                });
                                Bundle parameters = new Bundle();
                                String request_params = "id,name,gender,email,birthday";
                                String old_req_params = "id, first_name, last_name, email,gender, birthday, location";
//                        parameters.putString("Name", "first_name");
//                        parameters.putString("last Name", "last_name");
//                        parameters.putString("email", "email");
//                        parameters.putString("Gender", "gender");
                                request.setParameters(parameters);
                                request.executeAsync();

                                /////////////////FB user data save and Sign in END/////////////////////


//                        handleFacebookAccessToken(loginResult.getAccessToken());

                            }

                            @Override
                            public void onCancel() {
                                // App code
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                // App code
                            }
                        });
                try {
                    PackageInfo info = null;
                    try {
                        info = getPackageManager().getPackageInfo(
                                "com.example.muhammadimran.minimarathon", PackageManager.GET_SIGNATURES);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Signature signature : info.signatures) {
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        md.update(signature.toByteArray());
                        Log.i("KeyHash:",
                                Base64.encodeToString(md.digest(), Base64.DEFAULT));
                    }
                } catch (NoSuchAlgorithmException e) {

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
