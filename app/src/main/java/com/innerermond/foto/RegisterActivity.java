package com.innerermond.foto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.CustomLoading;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.innerermond.foto.base.BaseActivity;
import com.innerermond.foto.topsnackbar.TSnackbar;

import java.util.List;

import static bolts.Task.delay;

public class RegisterActivity extends BaseActivity {
    private FirebaseAuth firebaseAuthRegister;
     EditText RegisterEmail;
    EditText RegisterPass;
    EditText RegisterRePass;
    Button Btn_Register;
    FirebaseUser user1;
    RelativeLayout RRLAYOUT;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
          makeFullScreen();
        setContentView(R.layout.activity_register);
        //  changeStatusBarColor();

        RegisterEmail = (EditText)findViewById(R.id.registeremail);
        RegisterPass = (EditText)findViewById(R.id.registerpass);
        RegisterRePass = (EditText)findViewById(R.id.registerconpass);
        Btn_Register = (Button)findViewById(R.id.registerbutton);
        RRLAYOUT = (RelativeLayout) findViewById(R.id.rrl);

        //RegisterEmail = (EditText)findViewById(R.id.registeremail);


        firebaseAuthRegister = FirebaseAuth.getInstance();
        Btn_Register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                String Email = RegisterEmail.getText().toString().trim();
                String Password = RegisterPass.getText().toString().trim();
                String ConfirPassword = RegisterRePass.getText().toString().trim();


                 if(TextUtils.isEmpty(Email) && TextUtils.isEmpty(Password)){
                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Please enter a email id and a password", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;

                }
                if(TextUtils.isEmpty(Email)){
                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Please enter a email id", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;

                }
                if(TextUtils.isEmpty(Password)){
                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Please enter a password", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;
                }

                if(TextUtils.isEmpty(ConfirPassword)){
                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "please enter confirm password", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;
                }

                if(Password.length() <6){
                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Password must be 6 or more", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }
                 if(ConfirPassword.length() <6){
                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Password must be 6 or more", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }
                  if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Please enter a valid email address", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }

                if(!Password.equals(ConfirPassword)){

                    TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Password doesn't match", TSnackbar.LENGTH_LONG);
                      View snackbarView = snackbar.getView();
                      snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                      snackbar.setDuration(5000);
                      snackbar.setActionTextColor(Color.WHITE);
                      snackbar.show();
                  }


                if(Password.equals(ConfirPassword)){

                    firebaseAuthRegister.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                    //    FirebaseUser user = mAuth.getCurrentUser();
                                        user1 = FirebaseAuth.getInstance().getCurrentUser();
                                        user1.sendEmailVerification();
                                        FirebaseAuth.getInstance().signOut();
                                        TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "Register is successful we have sent a verification link to your email.Please verify and login", TSnackbar.LENGTH_LONG);
                                        View snackbarView = snackbar.getView();
                                        snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorSB));
                                        snackbar.setDuration(5000);
                                        snackbar.setActionTextColor(Color.WHITE);
                                        snackbar.show();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        TSnackbar snackbar = TSnackbar.make(RRLAYOUT, "This email id is already registered", TSnackbar.LENGTH_LONG);
                                        View snackbarView = snackbar.getView();
                                        snackbarView.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorLG));
                                        snackbar.setDuration(5000);
                                        snackbar.setActionTextColor(Color.WHITE);
                                        snackbar.show();

                                    }

                                    // ...
                                }



                            });

                }


            /*    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    //Log.w("TAG", "signInWithEmail:failed", task.getException());

                                } else {
                                    checkIfEmailVerified();
                                }
                                // ...
                            }
                        });*/

            }
        });



    }



    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

//    public void onLoginClick(View view){
//        startActivity(new Intent(this,LoginActivity.class));
//        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
//
//    }

//    public void Loginlay(View view){
//        signIn();
//
//    }
//
//    public void Loginfb(View view){
//        LoadFB();
//
//    }
//


    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}