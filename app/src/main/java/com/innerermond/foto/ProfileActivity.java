package com.innerermond.foto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.innerermond.foto.base.BaseActivity;
import com.squareup.picasso.Picasso;

public class    ProfileActivity extends BaseActivity {
    TextView PName;
    TextView PName1;
    TextView PEmail;
    ImageView PPR;
    Button LOGOUT;
    GoogleApiClient mGoogleApiClient;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        setContentView(R.layout.activity_profile);
        PName = (TextView)findViewById(R.id.Ptext);
        PName1 = (TextView)findViewById(R.id.Ptext1);

        PEmail = (TextView)findViewById(R.id.PEmail);
        PPR = (ImageView) findViewById(R.id.ppr);
        LOGOUT  = (Button)findViewById(R.id.LOUT);


     user = FirebaseAuth.getInstance().getCurrentUser();

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount == null && user == null) {
            finish();
            SharedPref.write(SharedPref.SKIP, 0);
            SharedPref.write(SharedPref.SKIP2, 1);

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);

        }
        if (signInAccount != null){
            PName.setText(signInAccount.getDisplayName());
            PName1.setText(signInAccount.getDisplayName());
            PEmail.setText(signInAccount.getEmail());

            Picasso.get().load(signInAccount.getPhotoUrl()).into(PPR);


        }

        if(user != null  ) {
            String UN = user.getEmail();


            if(user.getDisplayName() != null){
                PName.setText(user.getDisplayName());
                PName1.setText(user.getDisplayName());

            }
            else {
                PName.setText(UN.replace("@gmail.com",""));
                PName1.setText(UN.replace("@gmail.com",""));
            }

            PEmail.setText(user.getEmail());

            if(user.getPhotoUrl() != null){
                Picasso.get().load(user.getPhotoUrl()).into(PPR);

            }
            else {
                PPR.setBackground(getDrawable(R.drawable.ic_baseline_person));
             }


        }



        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                                Toast.makeText(getApplicationContext(),"Logged Out", Toast.LENGTH_SHORT).show();
                                FirebaseAuth.getInstance().signOut();

                                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                                finish();
                                startActivity(i);
                            }
                        });

            }
        });



    }
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
         mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}