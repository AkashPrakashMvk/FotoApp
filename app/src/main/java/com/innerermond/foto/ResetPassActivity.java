package com.innerermond.foto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.innerermond.foto.base.BaseActivity;
import com.innerermond.foto.topsnackbar.TSnackbar;

public class ResetPassActivity extends BaseActivity {
    RelativeLayout RPRLAYOUT;
    Button Btn_Reset;
    EditText FGPASS;
     FirebaseAuth mAuth;
    // FirebaseAuth LoginAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();

        setContentView(R.layout.activity_resetpass);

        Btn_Reset = (Button)findViewById(R.id.resetpassbut);
        FGPASS = (EditText)findViewById(R.id.fgpass);
        RPRLAYOUT = (RelativeLayout) findViewById(R.id.rprl);



        Btn_Reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                String fpasstext = FGPASS.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(fpasstext).matches()) {
                    TSnackbar snackbar = TSnackbar.make(RPRLAYOUT, "Please enter a valid email address", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(ResetPassActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;
                } else {
                    mAuth.getInstance().sendPasswordResetEmail(fpasstext).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            TSnackbar snackbar = TSnackbar.make(RPRLAYOUT, "Reset link has been successfully sent to your email address", TSnackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(ContextCompat.getColor(ResetPassActivity.this, R.color.colorSB));
                            snackbar.setDuration(5000);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            TSnackbar snackbar = TSnackbar.make(RPRLAYOUT, "Reset Link is not successful. please check your network connection", TSnackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(ContextCompat.getColor(ResetPassActivity.this, R.color.colorLG));
                            snackbar.setDuration(5000);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();

                        }
                    });
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}