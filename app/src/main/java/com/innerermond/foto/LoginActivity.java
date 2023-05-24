  package com.innerermond.foto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.innerermond.foto.base.BaseActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.innerermond.foto.topsnackbar.TSnackbar;
import java.util.Arrays;

  public class LoginActivity extends BaseActivity {

      RelativeLayout LLRAYOUT;
    TextView LOGSKIP;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private FirebaseAuth LoginAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;

    EditText LoginEmail;
    EditText LoginPass;
  private ImageView FBLOS;

    Button Btn_Login;

    FirebaseUser user1;
    TextView ForgotPass;
    TextView TOU1;

    static int SKIP;
    static int SKIP2;


      //SHA1: FE:E1:1A:B4:A7:31:34:69:96:D9:A8:92:50:3E:C1:BE:F1:B0:19:98
//SHA-256: E0:F5:27:2A:E6:A7:26:C2:7E:69:59:DE:C8:1B:11:1C:AF:02:33:FD:7C:C6:D4:81:06:48:04:73:EB:0C:9B:F5
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null ){
            Intent intent = new Intent(getApplicationContext(),EditImageActivity.class);
            finish();
            startActivity(intent);
        }

    }

//facebook app id 431882334517554
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        makeFullScreen();
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        }
        setContentView(R.layout.activity_login);
        SharedPref.init(getApplicationContext());
        SKIP = SharedPref.read(SharedPref.SKIP, 0);
        SKIP2 = SharedPref.read(SharedPref.SKIP2, 0);






        LOGSKIP = (TextView)findViewById(R.id.logskip);
        Btn_Login = (Button)findViewById(R.id.loginbutton);

        LoginEmail = (EditText)findViewById(R.id.loginemail);
        LoginPass = (EditText)findViewById(R.id.loginpass);
        ForgotPass = (TextView)findViewById(R.id.forgotpass);
        FBLOS = (ImageView) findViewById(R.id.facebook_signIn);
        LLRAYOUT = (RelativeLayout) findViewById(R.id.loginll);


        TOU1 = (TextView)findViewById(R.id.tou1);

        if(SKIP2 == 1) {
            LOGSKIP.setVisibility(View.GONE);
            SharedPref.write(SharedPref.SKIP, 1);

        }
        if(SKIP == 1)
         {
             Intent intent = new Intent(getApplicationContext(),EditImageActivity.class);
             finish();
             startActivity(intent);
         }


        //facebook auth
        mCallbackManager = CallbackManager.Factory.create();
       FacebookSdk.sdkInitialize(getApplicationContext());

      FBLOS.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
               LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
               LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                   @Override
                   public void onSuccess(LoginResult loginResult) {
                       Log.d("djvbdfjbv", "facebook:onSuccess:" + loginResult);
                       handleFacebookAccessToken(loginResult.getAccessToken());
                   }

                   @Override
                   public void onCancel() {
                       Log.d("djvbdfjbv", "facebook:onCancel");
                       // ...
                   }

                   @Override
                   public void onError(FacebookException error) {
                       Log.d("djvbdfjbv", "facebook:onError", error);
                       // ...
                   }
               });

           }
       });


//end facebook auth


        user1 = FirebaseAuth.getInstance().getCurrentUser();




        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),ResetPassActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });


        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {

                String  Login_Email = LoginEmail.getText().toString().trim();
                String  Login_Pass = LoginPass.getText().toString().trim();

                if(TextUtils.isEmpty(Login_Email) && TextUtils.isEmpty(Login_Pass)){
                    TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Please enter a email id and a password", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    //     Snackbar.make(getWindow().getDecorView().getRootView(),  "Email is Empty", Snackbar.LENGTH_SHORT).show();

                    return;

                }

                if(TextUtils.isEmpty(Login_Email)){
                    TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Please enter a email id", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
               //     Snackbar.make(getWindow().getDecorView().getRootView(),  "Email is Empty", Snackbar.LENGTH_SHORT).show();

                    return;

                }
                if(TextUtils.isEmpty(Login_Pass)){
                    TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Please enter a password", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;
                }


                if(Login_Pass.length() <6){
                    TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Password must be 6 or more", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Login_Email).matches()){
                    TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Please enter a valid email address", TSnackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                    snackbar.setDuration(5000);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    return;
                }




                mAuth.signInWithEmailAndPassword(Login_Email, Login_Pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    if(mAuth.getCurrentUser().isEmailVerified()){
                                           finish();
                                           startActivity(new Intent(getApplicationContext(),EditImageActivity.class));
                                    }
                                    else{
                                        TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Email id not verified please verify", TSnackbar.LENGTH_LONG);
                                        View snackbarView = snackbar.getView();
                                        snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                                        snackbar.setDuration(5000);
                                        snackbar.setActionTextColor(Color.WHITE);
                                        snackbar.show();                                            FirebaseAuth.getInstance().signOut();
                                    }
                                } else {

                                    TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Password incorrect or email id not registered", TSnackbar.LENGTH_LONG);
                                    View snackbarView = snackbar.getView();
                                    snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                                    snackbar.setDuration(5000);
                                    snackbar.setActionTextColor(Color.WHITE);
                                    snackbar.show();

                                }

                                // ...
                            }
                        });


            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    mAuth.signOut();
                }
            }
        };



        //login skip
        LOGSKIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditImageActivity.class);
                finish();
                startActivity(intent);
                SharedPref.write(SharedPref.SKIP, 1);
            }
        });

        //Terms of Service
        TOU1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TermsOfUse.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });



        mAuth = FirebaseAuth.getInstance();

        createRequest();

        findViewById(R.id.google_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(user1 != null){

                }else {

                }
            }
        };

    }


    //facebook auth
    private void handleFacebookToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                } else {

                }
            }

        });
    }







    //facebook

    private void createRequest() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    protected void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

//account.getId()

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Signin as "+account.getEmail(), TSnackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorSB));
                snackbar.setDuration(5000);
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();
            //    Intent intent = new Intent(getApplicationContext(),EditImageActivity.class);
               // startActivity(intent);
                firebaseAuthWithGoogle(account.getIdToken());
           //     finish();
               // Intent intent = new Intent(getApplicationContext(),EditImageActivity.class);
              //   startActivity(intent);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Sorry authentication failed check connection", TSnackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                snackbar.setDuration(5000);
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();

                //  Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }


    private void firebaseAuthWithGoogle(String acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                      //      FirebaseUser user = mAuth.getCurrentUser();
                            //    finish();
                            Intent intent = new Intent(getApplicationContext(), EditImageActivity.class);
                            finish();
                            startActivity(intent);


                            // onBackPressed();

                            //    Intent intent = new Intent(getApplicationContext(),EditImageActivity.class);
                            // startActivity(intent);

                        } else {
                            TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Sorry  authentication failed check connection", TSnackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                            snackbar.setDuration(5000);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();


                        }


                    }
                });





    }



    public void registernow(View View){
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);


    }

    //facebook auth
      public void LoadFB(){
          LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
          LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
              @Override
              public void onSuccess(LoginResult loginResult) {
                  Log.d("djvbdfjbv", "facebook:onSuccess:" + loginResult);
                  handleFacebookAccessToken(loginResult.getAccessToken());
              }

              @Override
              public void onCancel() {
                  Log.d("djvbdfjbv", "facebook:onCancel");
                  // ...
              }

              @Override
              public void onError(FacebookException error) {
                  Log.d("djvbdfjbv", "facebook:onError", error);
                  // ...
              }
          });

      }



      private void handleFacebookAccessToken(AccessToken token) {
          Log.d("smdbcdbsc", "handleFacebookAccessToken:" + token);

          AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
          mAuth.signInWithCredential(credential)
                  .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                      @SuppressLint("WrongConstant")
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()) {
                              // Sign in success, update UI with the signed-in user's information
                              Log.d("smdbcdbsc", "signInWithCredential:success");
                              FirebaseUser user = mAuth.getCurrentUser();
                              finish();
                              startActivity(new Intent(LoginActivity.this, EditImageActivity.class));
                          } else {
                              // If sign in fails, display a message to the user.
                              Log.w("smdbcdbsc", "signInWithCredential:failure", task.getException());
                              TSnackbar snackbar = TSnackbar.make(LLRAYOUT, "Sorry authentication failed check connection", TSnackbar.LENGTH_LONG);
                              View snackbarView = snackbar.getView();
                              snackbarView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorLG));
                              snackbar.setDuration(5000);
                              snackbar.setActionTextColor(Color.WHITE);
                              snackbar.show();


                          }

                          // ...
                      }
                  });

      }
      //end facebook auth


    @Override
    public void onBackPressed() {
     super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
//FE:E1:1A:B4:A7:31:34:69:96:D9:A8:92:50:3E:C1:BE:F1:B0:19:98
