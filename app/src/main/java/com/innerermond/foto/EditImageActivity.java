 package com.innerermond.foto;


//google ads
import com.CustomLoading;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.innerermond.foto.topsnackbar.TSnackbar;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.innerermond.foto.base.BaseActivity;
import com.innerermond.foto.filters.FilterListener;
import com.innerermond.foto.filters.FilterViewAdapter;
import com.innerermond.foto.tools.EditingToolsAdapter;
import com.innerermond.foto.tools.ToolType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.TextStyleBuilder;
import ja.burhanrashid52.photoeditor.ViewType;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


 public class  EditImageActivity extends BaseActivity implements OnPhotoEditorListener,
        View.OnClickListener,
        PropertiesBSFragment.Properties,
        EmojiBSFragment.EmojiListener,
        StickerBSFragment.StickerListener, EditingToolsAdapter.OnItemSelected, FilterListener {

     public static final String TAG = EditImageActivity.class.getSimpleName();
     public static final String EXTRA_IMAGE_PATHS = "extra_image_paths";
     public static final int CAMERA_REQUEST = 52;
     public static final int PICK_REQUEST = 53;
     public static int[] stickerifelse;
     public PhotoEditor mPhotoEditor;
     public PhotoEditorView mPhotoEditorView;
     public PropertiesBSFragment mPropertiesBSFragment;
     public EmojiBSFragment mEmojiBSFragment;
     public StickerBSFragment mStickerBSFragment;
     //public TextView mTxtCurrentTool;
     public Typeface mWonderFont;
     public RecyclerView mRvTools, mRvFilters;
     public EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);
     public FilterViewAdapter mFilterViewAdapter = new FilterViewAdapter(this);
     public ConstraintLayout mRootView;
     public ConstraintSet mConstraintSet = new ConstraintSet();
     public boolean mIsFilterVisible;
     public String s2;
     public String s1;
     public boolean a;
     private RewardedAd rewardedAd;
     private RewardedAd rewardedAd1;
     private RewardedAd rewardedAd2;
     private RewardedAd rewardedAd3;

     Uri image;

     private InterstitialAd interstitialAd;
     private InterstitialAd interstitialAd2;
     private InterstitialAd interstitialAd3;
     private InterstitialAd interstitialAd4;
     private InterstitialAd interstitialAd5;
     private InterstitialAd interstitialAd6, interstitialAd7, interstitialAd8, interstitialAd9, interstitialAd10, interstitialAd11, interstitialAd12;
     private boolean ADID, AD1ID, AD2ID, AD3ID, AD4ID, AD5ID, AD6ID, AD7ID, AD8ID, AD9ID, AD10ID, AD11ID, AD12ID;
     static int POINTS;
     static int POINTS1;
     static int POINTS2;
     static int POINTS3;
     static int ADSANDC;
     static int BGREMOVE;
     static int DSAGAIN;
     static int DSCAMERA;
     static int FTP;
     static int ISGAME;
     static int PRESSSPLUS;
     String ImgFormat = ".jpg";


     static int FTL;

     FirebaseRemoteConfig mFirebaseRemoteConfig1;
     String AD0;
     String AD1;
     String AD2;
     String AD3;
     String IADT;
     boolean LPD;
     boolean PLUSICON;


     // private boolean ADED1;
     // private boolean ADED2;
     // private boolean ADED3;
     // private boolean ADED4;

     //  private boolean ADED5;


     Button butons;

     //  public FontStyles fontstylesclass;

     public String A_BOLD = "beyond_wonderland.ttf";
     BottomSheetDialog tools2;

     // TextView hairs
     EditText FontText;
     String ok = "a";

     TextDialog fontstylesclass;
     BottomSheetDialog tools;
     PhotoEditorView BGMAIN;
     ConstraintLayout relative_layout_main;
     File f1;
     File file;

     File f2;
     File file2;
     boolean isEnabled = false;
     static boolean isGame = false;

     CustomLoading CLD;

     TextEditorDialogFragment FST;
     int GAMEMS;
     int GLASSMS;
     int ARTS;
     int ADNY;
     int PMGAMES;
     ImageView Cameraicon, Abouticon, Profileicon;


     @SuppressLint("WrongConstant")
     @Override
     protected void onStart() {

         if (isEnabled == false && BGREMOVE == 0) {

             saveImage2();
             isEnabled = true;
         }
         if (ADSANDC == 1 && POINTS == 1) {
             tools.show();
             SharedPref.write(SharedPref.ADSANDC, 0);
         }
         if (ADSANDC == 2 && POINTS1 == 1) {
             tools.show();
             SharedPref.write(SharedPref.ADSANDC, 0);

         }
         if (ADSANDC == 3 && POINTS2 == 1) {
             tools.show();
             SharedPref.write(SharedPref.ADSANDC, 0);

         }
         if (ADSANDC == 4 && POINTS3 == 1) {
             tools.show();
             SharedPref.write(SharedPref.ADSANDC, 0);

         }
         if (LPD == true) {
             TsnackDialog("If you want to remove the background please press and hold on the plus icon", 20000);

         }

//        if(BGREMOVE == 0 && BGMAIN ==null){
//            saveImage2();
//        }
         if (BGREMOVE == 1) {
//                TSnackbar snackbar = TSnackbar.make(relative_layout_main, "Background removed", TSnackbar.LENGTH_LONG);
//                View snackbarView = snackbar.getView();
//                snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                snackbar.setDuration(5000);
//                snackbar.setActionTextColor(Color.WHITE);
//                snackbar.show();
//                saveImage2();
//
             BGMAIN.setBackgroundColor(Color.BLACK);
//            if(BGMAIN.getBackground() != null || BGMAIN !=null)
//            {
//                saveImage2();
//                SharedPref.write(SharedPref.BGREMOVE, 0);
//
//            }

         }
//        if(PRESSSPLUS == 3){
//
//            TsnackDialog("If you want to remove the background please press and hold on the plus icon",20000);
//
//        }
         if (PRESSSPLUS == 1 && ISGAME == 1) {
             TsnackDialog("Hold down the plus icon to set a new background", 50000);

         }

         if (ISGAME == 1) {

             BGMAIN.setBackground(null);
             Cameraicon.setVisibility(View.GONE);
             Abouticon.setVisibility(View.GONE);
             Profileicon.setVisibility(View.GONE);
             tools.show();
             SharedPref.write(SharedPref.ISGAME, 0);


         }


         super.onStart();

     }


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         makeFullScreen();
         setContentView(R.layout.activity_edit_image);
         SharedPref.init(getApplicationContext());
         POINTS = SharedPref.read(SharedPref.POINTS, 0);
         POINTS1 = SharedPref.read(SharedPref.POINTS1, 0);
         POINTS2 = SharedPref.read(SharedPref.POINTS2, 0);
         POINTS3 = SharedPref.read(SharedPref.POINTS3, 0);
         FTL = SharedPref.read(SharedPref.FTL, 0);
         BGREMOVE = SharedPref.read(SharedPref.BGREMOVE, 0);
         DSAGAIN = SharedPref.read(SharedPref.DSAGAIN, 0);
         DSCAMERA = SharedPref.read(SharedPref.DSCAMERA, 0);
         ISGAME = SharedPref.read(SharedPref.ISGAME, 0);
         PRESSSPLUS = SharedPref.read(SharedPref.PRESSSPLUS, 0);
         ADSANDC = SharedPref.read(SharedPref.ADSANDC, 0);


         Cameraicon = (ImageView) findViewById(R.id.imgCamera);
         Abouticon = (ImageView) findViewById(R.id.imgClose);
         Profileicon = (ImageView) findViewById(R.id.userinfo);


         tools = new BottomSheetDialog(EditImageActivity.this);
         tools.setContentView(R.layout.stickers);
         tools.setCanceledOnTouchOutside(true);
         tools.setCancelable(false);


         IADT = "your internet connection is slow please try again";


         MobileAds.initialize(this, new OnInitializationCompleteListener() {
             @Override
             public void onInitializationComplete(InitializationStatus initializationStatus) {

             }
         });


         //my custom loading dialog
         CLD = new CustomLoading(EditImageActivity.this);
         FST = new TextEditorDialogFragment(EditImageActivity.this);
         relative_layout_main = (ConstraintLayout) findViewById(R.id.rootView);


         //Remote config
         mFirebaseRemoteConfig1 = FirebaseRemoteConfig.getInstance();

         //remote config firebase
         long cacheExpiration = 0;
         mFirebaseRemoteConfig1.fetch(cacheExpiration)
                 .addOnCompleteListener(EditImageActivity.this, new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if (task.isSuccessful()) {
//                            Toast.makeText(EditImageActivity.this, "Fetch Succeeded",
//                                    Toast.LENGTH_SHORT).show();
                             mFirebaseRemoteConfig1.activate();
//                             ADNY = 1;
//                             IADT = "AD is not loaded please try again later";

                         } else {
//                            Toast.makeText(EditImageActivity.this, "Fetch Failed",
//                                    Toast.LENGTH_SHORT).show();
//                             ADNY = 0;
//                             IADT = "please turn on the internet and try after restarting the app";
                         }
                         displayWelcomeMessage();
                     }

                     private void displayWelcomeMessage() {
//                         AD0 = mFirebaseRemoteConfig1.getString("AD0K");
//                         AD1 = mFirebaseRemoteConfig1.getString("AD1K");
//                         AD2 = mFirebaseRemoteConfig1.getString("AD2K");
//                         AD3 = mFirebaseRemoteConfig1.getString("AD3K");

                         LPD = mFirebaseRemoteConfig1.getBoolean("LPD");
                         PLUSICON = mFirebaseRemoteConfig1.getBoolean("PLUSICON");


//                         if (AD0 != null && AD1 != null && ADNY == 1 && FTL == 0) {
////                            Toast.makeText(EditImageActivity.this, "ssssss s",
////                                    Toast.LENGTH_SHORT).show();
//
//                             SharedPref.write(SharedPref.FTL, 10);
//                             Intent intent = getIntent();
//                             overridePendingTransition(0, 0);
//                             intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                             finish();
//                             overridePendingTransition(0, 0);
//                             startActivity(intent);
//
//                         }

//                         if (AD0 != null && !AD0.isEmpty()) {
//
//                             if (POINTS == 0) {
//                                 loadAd();
//                             }
//                         }
//
//                         if (AD1 != null && !AD1.isEmpty()) {
//                             if (POINTS1 == 0) {
//                                 loadAd1();
//                             }
//                         }
//                         if (AD2 != null && !AD2.isEmpty()) {
//                             if (POINTS2 == 0) {
//                                 loadAd2();
//                             }
//                         }
//
//                         if (AD3 != null && !AD3.isEmpty()) {
//                             if (POINTS3 == 0) {
//                                 loadAd3();
//                             }
//                         }
//
//
//                         if (AD0 == null || AD0.isEmpty() || ADNY == 0) {
//                             GLASSMS = 2000;
//
//                         } else {
//                             GLASSMS = 30000;
//
//                         }
//
//
//                         if (AD1 == null || AD1.isEmpty() || ADNY == 0) {
//                             GAMEMS = 2000;
//                         } else {
//                             GAMEMS = 30000;
//                         }
//
//
//                         if (AD2 == null || AD2.isEmpty() || ADNY == 0) {
//                             ARTS = 2000;
//                         } else {
//                             ARTS = 30000;
//
//                         }
//
//                         if (AD3 == null || AD3.isEmpty() || ADNY == 0) {
//                             PMGAMES = 2000;
//                         } else {
//                             PMGAMES = 30000;
//
//                         }
//
//
////                        if(POINTS == 0){
////                            loadAd();
////                        }
////                        if(POINTS1 == 0){
////                            loadAd1();
////                        }
//
                     }

                 });


         GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount.getEmail() == null){
         //   signInAccount.getDisplayName());

         //    Snackbar.make(getWindow().getDecorView().getRootView(), "Signin as "+signInAccount.getEmail(), Snackbar.LENGTH_SHORT).show();

         //      finish();
         //      Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
         //     startActivity(intent);


         //   }

         ImageView UserInfo = (ImageView) findViewById(R.id.userinfo);
         ImageView NewImg = (ImageView) findViewById(R.id.imgNew);
         BGMAIN = (PhotoEditorView) findViewById(R.id.photoEditorView);
         ImageView SETBG = (ImageView) findViewById(R.id.imgNew);


         FontText = (EditText) findViewById(R.id.add_text_edit_text);

         NewImg.setOnClickListener(new View.OnClickListener() {
             @SuppressLint("WrongConstant")
             @Override
             public void onClick(View view) {
//
//                Intent intent=new Intent(EditImageActivity.this,EditImageActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent);
//                finish();


                 if (ISGAME == 0) {
                     if (!mPhotoEditor.isCacheEmpty()) {
                         showSaveDialog1();
                     } else {

                         Intent intent = getIntent();
                         overridePendingTransition(0, 0);
                         intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                         finish();
                         overridePendingTransition(0, 0);
                         startActivity(intent);

                     }
                 }

                 if (ISGAME == 1) {
                     try {

                         tools.show();
                     } catch (Exception e) {

                     }
                 }

//                    else {
//                        Intent intent = getIntent();
//                        overridePendingTransition(0, 0);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        finish();
//                        overridePendingTransition(0, 0);
//                        startActivity(intent);
//                        SharedPref.write(SharedPref.ISGAME, 1);
//                        SharedPref.write(SharedPref.BGREMOVE, 0);
//                    }
//                    TSnackbar snackbar = TSnackbar.make(relative_layout_main, "You cannot use this option by using full size logo editing", TSnackbar.LENGTH_LONG);
//                    View snackbarView = snackbar.getView();
//                    snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                    snackbar.setDuration(3000);
//                    snackbar.setActionTextColor(Color.WHITE);
//                    snackbar.show();
                 // startActivity(new Intent(getApplicationContext(),EditImageActivity.class));


             }


         });


         NewImg.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View view) {


                 if (PLUSICON == true) {


                     Intent intent = getIntent();
                     overridePendingTransition(0, 0);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     finish();
                     overridePendingTransition(0, 0);
                     startActivity(intent);
                     SharedPref.write(SharedPref.ISGAME, 1);
                     SharedPref.write(SharedPref.PRESSSPLUS, 1);

                     if (PRESSSPLUS == 1 && ISGAME == 1) {
                         intent = getIntent();
                         overridePendingTransition(0, 0);
                         intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                         finish();
                         overridePendingTransition(0, 0);
                         startActivity(intent);
                         SharedPref.write(SharedPref.ISGAME, 1);
                         SharedPref.write(SharedPref.PRESSSPLUS, 2);
                     }
                 }
                 return false;
             }
         });


         //logout firebase
  /*      CameraOnWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
              //  Snackbar.make(getWindow().getDecorView().getRootView(), "wait for version 3.0", Snackbar.LENGTH_SHORT).show();

            }
        });*/


//
         UserInfo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                 startActivity(intent);
                 overridePendingTransition(R.anim.slide_in_right, R.anim.stay);


             }
         });
//
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//
//            }
//        });
//
//
//
//        //loadAd();
//
//        //interstitialAd
//        //ca-app-pub-3940256099942544/1033173712
//        interstitialAd = new InterstitialAd(this);
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd2 = new InterstitialAd(this);
//        interstitialAd2.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd2.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd3 = new InterstitialAd(this);
//        interstitialAd3.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd3.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd4 = new InterstitialAd(this);
//        interstitialAd4.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd4.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd5 = new InterstitialAd(this);
//        interstitialAd5.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd5.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd6 = new InterstitialAd(this);
//        interstitialAd6.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd6.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd7 = new InterstitialAd(this);
//        interstitialAd7.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd7.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd8 = new InterstitialAd(this);
//        interstitialAd8.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd8.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd9 = new InterstitialAd(this);
//        interstitialAd9.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd9.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd10 = new InterstitialAd(this);
//        interstitialAd10.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd10.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd11 = new InterstitialAd(this);
//        interstitialAd11.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd11.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd12 = new InterstitialAd(this);
//        interstitialAd12.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd12.loadAd(new AdRequest.Builder().build());
//
//
//
//        interstitialAd.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd2.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd2.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd3.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd3.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd4.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd4.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd5.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd5.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd6.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd6.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd7.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd7.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//
//        interstitialAd8.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd8.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//
//        interstitialAd9.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd9.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//
//        interstitialAd10.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd10.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd11.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd11.loadAd(new AdRequest.Builder().build());
//            }
//        });
//
//        interstitialAd12.setAdListener(new AdListener() {
//
//            public void onAdClosed() {
//
//                interstitialAd12.loadAd(new AdRequest.Builder().build());
//            }
//        });


         //a = false;
         //  hairs = (TextView)findViewById(R.id.coming);


         String s1 = getIntent().getStringExtra("name");
         s2 = s1;


         initViews();

         mWonderFont = Typeface.createFromAsset(getAssets(), "beyond_wonderland.ttf");

         fontstylesclass = new TextDialog();
         mEmojiBSFragment = new EmojiBSFragment();
         mPropertiesBSFragment = new PropertiesBSFragment();
         mEmojiBSFragment = new EmojiBSFragment();
         mStickerBSFragment = new StickerBSFragment();
         mStickerBSFragment.setStickerListener(this);
         mEmojiBSFragment.setEmojiListener(this);
         mPropertiesBSFragment.setPropertiesChangeListener(this);


         LinearLayoutManager llmTools = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
         mRvTools.setLayoutManager(llmTools);
         mRvTools.setAdapter(mEditingToolsAdapter);

         LinearLayoutManager llmFilters = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
         mRvFilters.setLayoutManager(llmFilters);
         mRvFilters.setAdapter(mFilterViewAdapter);

         //   fontstylesclass.add();


         //Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);
         //Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

         mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                 .setPinchTextScalable(true) // set flag to make text scalable when pinch
                 //.setDefaultTextTypeface(mTextRobotoTf)
                 //.setDefaultEmojiTypeface(mEmojiTypeFace)
                 .build(); // build photo editor sdk

         mPhotoEditor.setOnPhotoEditorListener(this);

         //Set Image Dynamically
         // mPhotoEditorView.getSource().setImageResource(R.drawable.color_palette);


     }

   /*     if (s1 != null) {
           if (s2.equals("hairs")) {
                mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
             stickerifelse   = new int[]{R.drawable.h1,R.drawable.h2,R.drawable.h3,
                       R.drawable.h4,R.drawable.h5,R.drawable.h6,
                       R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
                       R.drawable.h11,R.drawable.h12,R.drawable.h13,R.drawable.h14,
                       R.drawable.h15,R.drawable.h16,R.drawable.h17,R.drawable.h18,
                       R.drawable.h19,R.drawable.h20,R.drawable.h21,R.drawable.h22,
                       R.drawable.h23,R.drawable.h24,R.drawable.h25,R.drawable.h26,
                       R.drawable.h27,R.drawable.h28,R.drawable.h30,R.drawable.h31,
                       R.drawable.h32,R.drawable.h33,R.drawable.h34,R.drawable.h35,
                       R.drawable.h36,R.drawable.h37,R.drawable.h38,R.drawable.h39,
                       R.drawable.h40,R.drawable.h41,};

           }

            if(s2.equals("glasses")){

               mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                stickerifelse   = new int[]{R.drawable.g1,R.drawable.g2,R.drawable.g3,
                        R.drawable.g4,R.drawable.g5,R.drawable.g6,
                        R.drawable.g7,R.drawable.g8,R.drawable.g9,R.drawable.g10,
                        R.drawable.g11,R.drawable.g12,R.drawable.g13,R.drawable.g14,
                        R.drawable.g15,R.drawable.g16,R.drawable.g17,R.drawable.g18,
                        R.drawable.g19,R.drawable.g20,R.drawable.g22,
                        R.drawable.g23,R.drawable.g24,R.drawable.g25,R.drawable.g26,
                        R.drawable.g27,R.drawable.g28,R.drawable.g30,R.drawable.g31,
                        R.drawable.g32,R.drawable.g33,R.drawable.g34,R.drawable.g35,
                        R.drawable.g36,R.drawable.g37,R.drawable.g38,R.drawable.g39,
                        R.drawable.g40,R.drawable.g41, R.drawable.g42,R.drawable.g43,
                        R.drawable.g44,R.drawable.g45,R.drawable.g46,R.drawable.g47,
                        R.drawable.g48,R.drawable.g49,R.drawable.g50,};

            }

            if (s2.equals("hats")) {
                mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                stickerifelse   = new int[]{R.drawable.hat1,R.drawable.hat2,R.drawable.hat3,
                        R.drawable.hat4,R.drawable.hat5,R.drawable.hat6,
                        R.drawable.hat7,R.drawable.hat8,R.drawable.hat9,R.drawable.hat10,
                        R.drawable.hat11,R.drawable.hat12,R.drawable.hat13,R.drawable.hat14,
                        R.drawable.hat15,R.drawable.hat16,R.drawable.hat17,R.drawable.hat18,
                        R.drawable.hat19,R.drawable.hat20};

            }

            if(s2.equals("beards")){
                mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                stickerifelse   = new int[]{R.drawable.b1,R.drawable.b2,R.drawable.b3,
                        R.drawable.b4,R.drawable.b5,R.drawable.b6,
                        R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,
                        R.drawable.b11,R.drawable.b12,R.drawable.b13,R.drawable.b14,
                        R.drawable.b15,R.drawable.b16,R.drawable.b17,R.drawable.b18,
                        R.drawable.b19,R.drawable.b20,R.drawable.b21,R.drawable.b22,
                        R.drawable.b23,R.drawable.b24,R.drawable.b25,R.drawable.b26,
                        R.drawable.b27,R.drawable.b28,R.drawable.b30,R.drawable.b31,
                        R.drawable.b32,R.drawable.b33,R.drawable.b34,R.drawable.b35,
                        R.drawable.b36,R.drawable.b37,R.drawable.b38,R.drawable.b39,
                        R.drawable.b40,R.drawable.b41, R.drawable.b42,R.drawable.b43,
                        R.drawable.b44,R.drawable.b45,};
            }*/


     public void initViews() {
         ImageView imgUndo;
         ImageView imgRedo;
         ImageView imgCamera;
         //  ImageView imgGallery;
         ImageView imgSave;
         ImageView imgClose;
         //   ImageView imgPremium;

         mPhotoEditorView = findViewById(R.id.photoEditorView);
         //    mTxtCurrentTool = findViewById(R.id.txtCurrentTool);
         mRvTools = findViewById(R.id.rvConstraintTools);
         mRvFilters = findViewById(R.id.rvFilterView);
         mRootView = findViewById(R.id.rootView);

         imgUndo = findViewById(R.id.imgUndo);
         imgUndo.setOnClickListener(this);

         imgRedo = findViewById(R.id.imgRedo);
         imgRedo.setOnClickListener(this);

         imgCamera = findViewById(R.id.imgCamera);
         imgCamera.setOnClickListener(this);

         // imgGallery = findViewById(R.id.imgGallery);
         //  imgGallery.setOnClickListener(this);

         imgSave = findViewById(R.id.imgSave);
         imgSave.setOnClickListener(this);

         imgClose = findViewById(R.id.imgClose);
         imgClose.setOnClickListener(this);

         // imgPremium = findViewById(R.id.imgPremium);
         // imgPremium.setOnClickListener(this);


//        imgSave.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                showImgFormatDialog();
//                return false;
//            }
//        });

     }


     @Override
     public void onEditTextChangeListener(final View rootView, String text, int colorCode) {
         TextEditorDialogFragment textEditorDialogFragment =
                 TextEditorDialogFragment.show(this, text, colorCode);
         textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
             @Override
             public void onDone(String inputText, int colorCode) {
                 final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                 styleBuilder.withTextColor(colorCode);

                 mPhotoEditor.editText(rootView, inputText, styleBuilder);
                 //  mTxtCurrentTool.setText(R.string.label_text);
             }
         });
     }

     @Override
     public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {
         Log.d(TAG, "onAddViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + numberOfAddedViews + "]");
     }

     @Override
     public void onRemoveViewListener(ViewType viewType, int numberOfAddedViews) {
         Log.d(TAG, "onRemoveViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + numberOfAddedViews + "]");
     }

     @Override
     public void onStartViewChangeListener(ViewType viewType) {
         Log.d(TAG, "onStartViewChangeListener() called with: viewType = [" + viewType + "]");
     }

     @Override
     public void onStopViewChangeListener(ViewType viewType) {
         Log.d(TAG, "onStopViewChangeListener() called with: viewType = [" + viewType + "]");
     }

     @SuppressLint("WrongConstant")
     @Override
     public void onClick(View view) {
         switch (view.getId()) {

             case R.id.imgUndo:
                 mPhotoEditor.undo();
                 break;

             case R.id.imgRedo:
                 mPhotoEditor.redo();
                 break;

             case R.id.imgSave:
                 showImgFormatDialog("What format do you want to save the image to?\n\nWarning\nif you save as empty,it will not be saved");
                 break;

             case R.id.imgClose:
                 Intent in = new Intent(EditImageActivity.this, About.class);
                 startActivity(in);
                 overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

                 break;


//            case R.id.imgPremium:
//                Intent premium = new Intent(EditImageActivity.this, Premium.class);
//                startActivity(premium);
//                break;

             case R.id.imgCamera:
                 if (DSCAMERA == 0) {
                     showCameraDialog();

                 }
                 if (DSCAMERA == 1) {

                     BGMAIN.setBackground(null);

                     Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                     startActivityForResult(cameraIntent, CAMERA_REQUEST);
                     overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

                 } else {
                     TSnackbar snackbar = TSnackbar.make(relative_layout_main, "ImagSaved Successfully\n Saved to: " + file, TSnackbar.LENGTH_LONG);
                     View snackbarView = snackbar.getView();
                     snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
                     snackbar.setDuration(10000);
                     snackbar.setActionTextColor(Color.WHITE);
                     snackbar.show();

                 }

                 break;

//            case R.id.imgGallery:
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);
//                BGMAIN.setBackground(null);
//                break;
         }
     }

     @SuppressLint("MissingPermission")
     public void saveImage2() {
         if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


             File f2 = new File(Environment.getExternalStorageDirectory(), "/Android/data/com.innerermond.foto/files/bg");
             File file2 = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.innerermond.foto/files/bg"
                     + File.separator + ""
                     + "bg" + ImgFormat);

             if (f2.exists()) {

                 //   Snackbar.make(getWindow().getDecorView().getRootView(), "wai", Snackbar.LENGTH_SHORT).show();
             } else {
                 f2.mkdirs();

                 if (f2.isDirectory()) {
                     //  Snackbar.make(getWindow().getDecorView().getRootView(), "wai", Snackbar.LENGTH_SHORT).show();
                 }
             }
             try {
                 file2.createNewFile();

                 SaveSettings saveSettings = new SaveSettings.Builder()
                         .setClearViewsEnabled(true)
                         .setTransparencyEnabled(true)
                         .build();

                 mPhotoEditor.saveAsFile(file2.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                     @Override
                     public void onSuccess(@NonNull String imagePath) {
                         //  hideLoading();
                         //      showSnackbar("Please wait");
                         mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));
                     }

                     @Override
                     public void onFailure(@NonNull Exception exception) {
                         //  hideLoading();
                         saveImage2();
                         //    showSnackbar("Please wait");
                     }
                 });
             } catch (IOException e) {
                 e.printStackTrace();
                 //  hideLoading();
                 showSnackbar(e.getMessage());
             }
         }

     }

     @SuppressLint("MissingPermission")
     public void saveImage() {
         if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
             showLoading("Saving...");


             File f1 = new File(Environment.getExternalStorageDirectory(), "Foto");
             final File file = new File(Environment.getExternalStorageDirectory() + "/Foto"
                     + File.separator + ""
                     + System.currentTimeMillis() + ImgFormat);
             if (f1.exists()) {

                 //    Snackbar.make(getWindow().getDecorView().getRootView(), "wai", Snackbar.LENGTH_SHORT).show();
             } else {
                 f1.mkdirs();

                 if (f1.isDirectory()) {
                     // Snackbar.make(getWindow().getDecorView().getRootView(), "wai", Snackbar.LENGTH_SHORT).show();
                 }
             }
             try {
                 file.createNewFile();

                 SaveSettings saveSettings = new SaveSettings.Builder()
                         .setClearViewsEnabled(true)
                         .setTransparencyEnabled(true)
                         .build();

                 mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                     @SuppressLint("WrongConstant")
                     @Override
                     public void onSuccess(@NonNull String imagePath) {
                         hideLoading();
                         //      showSnackbar("Image Saved Successfully");
                         TSnackbar snackbar = TSnackbar.make(relative_layout_main, "Image saved Successfully\n Saved to: " + file, TSnackbar.LENGTH_LONG);
                         View snackbarView = snackbar.getView();
                         snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
                         snackbar.setDuration(10000);
                         snackbar.setActionTextColor(Color.WHITE);
                         snackbar.show();

                         mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));
                     }

                     @SuppressLint("WrongConstant")
                     @Override
                     public void onFailure(@NonNull Exception exception) {
                         hideLoading();
                         saveImage();
                         //  showSnackbar("try again");
                         TSnackbar snackbar = TSnackbar.make(relative_layout_main, "try again", TSnackbar.LENGTH_LONG);
                         snackbar.setActionTextColor(Color.WHITE);
                         View snackbarView = snackbar.getView();
                         snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
                         snackbar.setDuration(3000);
                         snackbar.show();

                     }
                 });
             } catch (IOException e) {
                 e.printStackTrace();
                 hideLoading();
                 showSnackbar(e.getMessage());
             }
         }
     }


     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (resultCode == RESULT_OK) {
             switch (requestCode) {
                 case CAMERA_REQUEST:
                     BGMAIN.setBackground(null);
                     mPhotoEditor.clearAllViews();
                     Bitmap photo = (Bitmap) data.getExtras().get("data");
                     ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                     photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                     mPhotoEditorView.getSource().setImageBitmap(photo);


//
                     break;
                 case PICK_REQUEST:
                     try {
                         mPhotoEditor.clearAllViews();
                         Uri uri = data.getData();
                         Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                         mPhotoEditorView.getSource().setImageBitmap(bitmap);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                     break;
             }
         }
     }

     @Override
     public void onColorChanged(int colorCode) {
         mPhotoEditor.setBrushColor(colorCode);
         //  mTxtCurrentTool.setText(R.string.label_brush);
     }

     @Override
     public void onOpacityChanged(int opacity) {
         mPhotoEditor.setOpacity(opacity);
         //  mTxtCurrentTool.setText(R.string.label_brush);
     }

     @Override
     public void onBrushSizeChanged(int brushSize) {
         mPhotoEditor.setBrushSize(brushSize);
         //   mTxtCurrentTool.setText(R.string.label_brush);
     }

     @Override
     public void onEmojiClick(String emojiUnicode) {
         mPhotoEditor.addEmoji(emojiUnicode);
         // mTxtCurrentTool.setText(R.string.label_emoji);
     }

     @Override
     public void onStickerClick(Bitmap bitmap) {
         mPhotoEditor.addImage(bitmap);
         //   mTxtCurrentTool.setText(R.string.label_sticker);
     }

     @Override
     public void isPermissionGranted(boolean isGranted, String permission) {
         if (isGranted) {
             saveImage2();
         } else {
             saveImage2();
         }
     }

     //save Dialog
     public void showSaveDialog(final boolean isGame, String msg) {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(msg);
         builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 if (isGame == false) {
                     showImgFormatDialog("What format do you want to save the image to?\n\nWarning\nif you save as empty,it will not be saved");

                 } else {
                     Intent intent = getIntent();
                     overridePendingTransition(0, 0);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     finish();
                     overridePendingTransition(0, 0);
                     startActivity(intent);
                     SharedPref.write(SharedPref.ISGAME, 1);
                     SharedPref.write(SharedPref.BGREMOVE, 0);
                 }
             }
         });
         builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

         builder.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 if (isGame == false) {
                     finish();

                 } else {
                     Intent intent = getIntent();
                     overridePendingTransition(0, 0);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     finish();
                     overridePendingTransition(0, 0);
                     startActivity(intent);
                     SharedPref.write(SharedPref.ISGAME, 1);
                     SharedPref.write(SharedPref.BGREMOVE, 0);
                 }
             }
         });
         builder.setCancelable(false);
         builder.create().show();

     }


     public void showImgFormatDialog(String msg) {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(msg);
         builder.setPositiveButton("jpg", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 ImgFormat = ImgFormat;
                 saveImage();
             }
         });
         builder.setNegativeButton("png", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 ImgFormat = ".png";
                 saveImage();
             }
         });

         builder.setNeutralButton("webp", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 ImgFormat = ".webp";
                 saveImage();
             }
         });
         builder.setCancelable(false);
         builder.create().show();

     }


     //BG Dialog
     public void showBgDialog() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(getString(R.string.msg_bg_remove));
         builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 SharedPref.write(SharedPref.BGREMOVE, 1);
                 Intent intent = getIntent();
                 overridePendingTransition(0, 0);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 finish();
                 overridePendingTransition(0, 0);
                 startActivity(intent);

             }
         });
         builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

         builder.setNeutralButton("Restore", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 //  isEnabled = false;
                 SharedPref.write(SharedPref.BGREMOVE, 0);
                 Intent intent = getIntent();
                 overridePendingTransition(0, 0);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 finish();
                 overridePendingTransition(0, 0);
                 startActivity(intent);


             }
         });
         builder.setCancelable(false);
         builder.create().show();


     }


     //GAMING DIALOG
     public void showGAMEINGDialog() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(getString(R.string.msg_bg_removeWarning));
         builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 SharedPref.write(SharedPref.DSAGAIN, 1);

                 dialog.dismiss();
             }
         });
//         builder.setNegativeButton("Don't show again", new DialogInterface.OnClickListener() {
//             @Override
//             public void onClick(DialogInterface dialog, int which) {
//                 SharedPref.write(SharedPref.DSAGAIN, 2);
//                 dialog.dismiss();
//                 Intent intent = getIntent();
//                 overridePendingTransition(0, 0);
//                 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                 finish();
//                 overridePendingTransition(0, 0);
//                 startActivity(intent);
//
//             }
//         });

         builder.setNeutralButton("Don't show again", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 SharedPref.write(SharedPref.DSAGAIN, 2);
                 dialog.dismiss();
                 Intent intent = getIntent();
                 overridePendingTransition(0, 0);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 finish();
                 overridePendingTransition(0, 0);
                 startActivity(intent);

             }
         });

         builder.setCancelable(false);
         builder.create().show();

     }


     public void showCameraDialog() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(getString(R.string.msg_sorry_camera));
         builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 BGMAIN.setBackground(null);
                 Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivityForResult(cameraIntent, CAMERA_REQUEST);
                 overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

             }

         });

         builder.setNeutralButton("Don't show again", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 SharedPref.write(SharedPref.DSCAMERA, 1);
                 Intent intent = getIntent();
                 overridePendingTransition(0, 0);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 finish();
                 overridePendingTransition(0, 0);
                 startActivity(intent);

             }
         });
         builder.setCancelable(false);
         builder.create().show();


     }


     //save dialog 1 imgNew
     public void showSaveDialog1() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(getString(R.string.msg_save_image));
         builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
             @SuppressLint("MissingPermission")
             @Override
             public void onClick(DialogInterface dialog, int which) {


                 if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                     showLoading("Saving...");


                     File f1 = new File(Environment.getExternalStorageDirectory(), "Foto");
                     File file = new File(Environment.getExternalStorageDirectory() + "/Foto"
                             + File.separator + ""
                             + System.currentTimeMillis() + ImgFormat);

                     if (f1.exists()) {

                         Snackbar.make(getWindow().getDecorView().getRootView(), "wai", Snackbar.LENGTH_SHORT).show();
                     } else {
                         f1.mkdirs();

                         if (f1.isDirectory()) {
                             Snackbar.make(getWindow().getDecorView().getRootView(), "wai", Snackbar.LENGTH_SHORT).show();
                         }
                     }
                     try {
                         file.createNewFile();

                         SaveSettings saveSettings = new SaveSettings.Builder()
                                 .setClearViewsEnabled(true)
                                 .setTransparencyEnabled(true)
                                 .build();

                         mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                             @SuppressLint("WrongConstant")
                             @Override
                             public void onSuccess(@NonNull String imagePath) {
                                 hideLoading();
                                 // showSnackbar("Image Saved Successfully");
                                 TSnackbar snackbar = TSnackbar.make(relative_layout_main, "Image Saved Successfully", TSnackbar.LENGTH_LONG);
                                 View snackbarView = snackbar.getView();
                                 snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
                                 snackbar.setDuration(3000);
                                 snackbar.setActionTextColor(Color.WHITE);
                                 snackbar.show();
                                 mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));

                                 Intent intent = getIntent();
                                 overridePendingTransition(0, 0);
                                 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                 finish();
                                 overridePendingTransition(0, 0);
                                 startActivity(intent);
                             }

                             @SuppressLint("WrongConstant")
                             @Override
                             public void onFailure(@NonNull Exception exception) {
                                 hideLoading();
                                 saveImage();
                                 //showSnackbar("try again");
                                 TSnackbar snackbar = TSnackbar.make(relative_layout_main, "failed to save please try again", TSnackbar.LENGTH_LONG);
                                 View snackbarView = snackbar.getView();
                                 snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
                                 snackbar.setDuration(3000);
                                 snackbar.setActionTextColor(Color.WHITE);
                                 snackbar.show();
                             }
                         });
                     } catch (IOException e) {
                         e.printStackTrace();
                         hideLoading();
                         showSnackbar(e.getMessage());
                     }
                 }


             }
         });
         builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

         builder.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 Intent intent = getIntent();
                 overridePendingTransition(0, 0);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                 finish();
                 overridePendingTransition(0, 0);
                 startActivity(intent);

             }
         });
         builder.create().show();
         builder.create().setCancelable(false);

     }


     @Override
     public void onFilterSelected(PhotoFilter photoFilter) {
         mPhotoEditor.setFilterEffect(photoFilter);
     }

     @SuppressLint("WrongConstant")
     @Override
     public void onToolSelected(ToolType toolType) {
         switch (toolType) {
             case BRUSH:

//                if(BGREMOVE == 1){
//                    TSnackbar snackbar = TSnackbar.make(relative_layout_main, "You cannot use this option by removing the background", TSnackbar.LENGTH_LONG);
//                    View snackbarView = snackbar.getView();
//                    snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                    snackbar.setDuration(3000);
//                    snackbar.setActionTextColor(Color.WHITE);
//                    snackbar.show();
//
//
//                }
                 if (ISGAME == 1) {
                     TSnackbar snackbar = TSnackbar.make(relative_layout_main, "You will not be able to use this option when using the full size game logo", TSnackbar.LENGTH_LONG);
                     View snackbarView = snackbar.getView();
                     snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
                     snackbar.setDuration(9000);
                     snackbar.setActionTextColor(Color.WHITE);
                     snackbar.show();
                 } else {
                     mPhotoEditor.setBrushDrawingMode(true);
                     //     mTxtCurrentTool.setText(R.string.label_brush);
                     mPropertiesBSFragment.show(getSupportFragmentManager(), mPropertiesBSFragment.getTag());
                 }

                 break;
             //akash
             case TEXT:
                 TextEditorDialogFragment textEditorDialogFragment = TextEditorDialogFragment.show(this);
                 textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
                     @Override
                     public void onDone(String inputText, int colorCode) {

                         final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                         styleBuilder.withTextColor(colorCode);
                         styleBuilder.withTextFont(Typeface.createFromAsset(getAssets(), A_BOLD));


                         // mPhotoEditor;
                         mPhotoEditor.addText(inputText, styleBuilder);


                         //  mTxtCurrentTool.setText(R.string.label_text);
                     }
                 });
                 break;
             case ERASER:
                 mPhotoEditor.brushEraser();
                 // mTxtCurrentTool.setText(R.string.label_eraser_mode);
                 break;
             case FILTER:
                 // mTxtCurrentTool.setText(R.string.label_filter);
                 showFilter(true);
                 break;
             case EMOJI:
                 mEmojiBSFragment.show(getSupportFragmentManager(), mEmojiBSFragment.getTag());
                 break;
             case GALLERY:
                 Intent intent = new Intent();
                 intent.setType("image/*");
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);
                 BGMAIN.setBackground(null);
                 break;

             case STICKER:
                 try {
                     if (ISGAME != 1) {
                         tools.show();
                     } else {
                         TSnackbar snackbar = TSnackbar.make(relative_layout_main, "Please click on the plus icon", TSnackbar.LENGTH_LONG);
                         snackbar.setActionTextColor(Color.WHITE);
                         View snackbarView = snackbar.getView();
                         snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
                         snackbar.setDuration(9000);
                         snackbar.show();
                     }
                 } catch (Exception e) {

                 }


                 //   mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                 //  Intent in = new Intent(EditImageActivity.this,Stickers.class);
                 //   Intent glasses = new Intent(EditImageActivity.this,Glasses.class);
                 // startActivity(in);
                 //setContentView(R.layout.stickers);

               /* mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                stickerifelse   = new int[]{R.drawable.g1,R.drawable.g2,R.drawable.g3,
                        R.drawable.g4,R.drawable.g5,R.drawable.g6,
                        R.drawable.g7,R.drawable.g8,R.drawable.g9,R.drawable.g10,
                        R.drawable.g11,R.drawable.g12,R.drawable.g13,R.drawable.g14,
                        R.drawable.g15,R.drawable.g16,R.drawable.g17,R.drawable.g18,
                        R.drawable.g19,R.drawable.g20,R.drawable.g22,
                        R.drawable.g23,R.drawable.g24,R.drawable.g25,R.drawable.g26,
                        R.drawable.g27,R.drawable.g28,R.drawable.g30,R.drawable.g31,
                        R.drawable.g32,R.drawable.g33,R.drawable.g34,R.drawable.g35,
                        R.drawable.g36,R.drawable.g37,R.drawable.g38,R.drawable.g39,
                        R.drawable.g40,R.drawable.g41, R.drawable.g42,R.drawable.g43,
                        R.drawable.g44,R.drawable.g45,R.drawable.g46,R.drawable.g47,
                        R.drawable.g48,R.drawable.g49,R.drawable.g50,R.drawable.b1,R.drawable.b2,R.drawable.b3,
                        R.drawable.b4,R.drawable.b5,R.drawable.b6,
                        R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,
                        R.drawable.b11,R.drawable.b12,R.drawable.b13,R.drawable.b14,
                        R.drawable.b15,R.drawable.b16,R.drawable.b17,R.drawable.b18,
                        R.drawable.b19,R.drawable.b20,R.drawable.b21,R.drawable.b22,
                        R.drawable.b23,R.drawable.b24,R.drawable.b25,R.drawable.b26,
                        R.drawable.b27,R.drawable.b28,R.drawable.b30,R.drawable.b31,
                        R.drawable.b32,R.drawable.b33,R.drawable.b34,R.drawable.b35,
                        R.drawable.b36,R.drawable.b37,R.drawable.b38,R.drawable.b39,
                        R.drawable.b40,R.drawable.b41, R.drawable.b42,R.drawable.b43,
                        R.drawable.b44,R.drawable.b45,R.drawable.h1,R.drawable.h2,R.drawable.h3,
                        R.drawable.h4,R.drawable.h5,R.drawable.h6,
                        R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
                        R.drawable.h11,R.drawable.h12,R.drawable.h13,R.drawable.h14,
                        R.drawable.h15,R.drawable.h16,R.drawable.h17,R.drawable.h18,
                        R.drawable.h19,R.drawable.h20,R.drawable.h21,R.drawable.h22,
                        R.drawable.h23,R.drawable.h24,R.drawable.h25,R.drawable.h26,
                        R.drawable.h27,R.drawable.h28,R.drawable.h30,R.drawable.h31,
                        R.drawable.h32,R.drawable.h33,R.drawable.h34,R.drawable.h35,
                        R.drawable.h36,R.drawable.h37,R.drawable.h38,R.drawable.h39,
                        R.drawable.h40,R.drawable.h41,R.drawable.hat1,R.drawable.hat2,R.drawable.hat3,
                        R.drawable.hat4,R.drawable.hat5,R.drawable.hat6,
                        R.drawable.hat7,R.drawable.hat8,R.drawable.hat9,R.drawable.hat10,
                        R.drawable.hat11,R.drawable.hat12,R.drawable.hat13,R.drawable.hat14,
                        R.drawable.hat15,R.drawable.hat16,R.drawable.hat17,R.drawable.hat18,
                        R.drawable.hat19,R.drawable.hat20};

*/


                 break;
         }

     }


     void showFilter(boolean isVisible) {
         mIsFilterVisible = isVisible;
         mConstraintSet.clone(mRootView);

         if (isVisible) {
             mConstraintSet.clear(mRvFilters.getId(), ConstraintSet.START);
             mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.START,
                     ConstraintSet.PARENT_ID, ConstraintSet.START);
             mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.END,
                     ConstraintSet.PARENT_ID, ConstraintSet.END);
         } else {
             mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.START,
                     ConstraintSet.PARENT_ID, ConstraintSet.END);
             mConstraintSet.clear(mRvFilters.getId(), ConstraintSet.END);
         }

         ChangeBounds changeBounds = new ChangeBounds();
         changeBounds.setDuration(350);
         changeBounds.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
         TransitionManager.beginDelayedTransition(mRootView, changeBounds);

         mConstraintSet.applyTo(mRootView);
     }

     //  @Override
//    public void onBackPressed() {
//        if(tools != null){
//            tools.cancel();
//
//        }
//        if (mIsFilterVisible) {
//            showFilter(false);
//            //  mTxtCurrentTool.setText(R.string.app_name);
//        } else if (!mPhotoEditor.isCacheEmpty()) {
//            showSaveDialog(false,"Are you want to exit without saving image");
//        } else {
//
//        }
//
//
//    }




   /* public void sendMessage(View vi){
        mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
        stickerifelse   = new int[]{R.drawable.h1,R.drawable.h2,R.drawable.h3,
                R.drawable.h4,R.drawable.h5,R.drawable.h6,
                R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
                R.drawable.h11,R.drawable.h12,R.drawable.h13,R.drawable.h14,
                R.drawable.h15,R.drawable.h16,R.drawable.h17,R.drawable.h18,
                R.drawable.h19,R.drawable.h20,R.drawable.h21,R.drawable.h22,
                R.drawable.h23,R.drawable.h24,R.drawable.h25,R.drawable.h26,
                R.drawable.h27,R.drawable.h28,R.drawable.h30,R.drawable.h31,
                R.drawable.h32,R.drawable.h33,R.drawable.h34,R.drawable.h35,
                R.drawable.h36,R.drawable.h37,R.drawable.h38,R.drawable.h39,
                R.drawable.h40,R.drawable.h41,};

    }*/

/*RewardedAd

    private void loadAd() {
        this.rewardedAd = new RewardedAd(this, getString(R.string.rewarded_ad_unit_id));
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();


            }

            @Override
            public void onRewardedAdFailedToLoad(int i) {
                super.onRewardedAdFailedToLoad(i);


            }
        };

      this.rewardedAd.loadAd(new AdRequest.Builder().build(),adLoadCallback);

    }



    private void showAd() {
        if (this.rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                  //  isUnlocked = true;
                   // suss = "loaded successfully please open again";
                  //  Toast.makeText(getApplicationContext(), "unlocked", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onRewardedAdOpened() {
                    super.onRewardedAdOpened();

                }

                @Override
                public void onRewardedAdClosed() {
                    super.onRewardedAdClosed();


                }

                @Override
                public void onRewardedAdFailedToShow(int i) {

                    super.onRewardedAdFailedToShow(i);


                }
            };
            this.rewardedAd.show(this, adCallback);
        } else {

        }

    }*/


     public void hairs(View v) {
         tools.cancel();

         mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
         stickerifelse = new int[]{R.drawable.h1, R.drawable.h2, R.drawable.h3,
                 R.drawable.h4, R.drawable.h5, R.drawable.h6,
                 R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
                 R.drawable.h11, R.drawable.h12, R.drawable.h13, R.drawable.h14,
                 R.drawable.h15, R.drawable.h16, R.drawable.h17, R.drawable.h18,
                 R.drawable.h19, R.drawable.h20, R.drawable.h21, R.drawable.h22,
                 R.drawable.h23, R.drawable.h24, R.drawable.h25, R.drawable.h26,
                 R.drawable.h27, R.drawable.h28, R.drawable.h30, R.drawable.h31,
                 R.drawable.h32, R.drawable.h33, R.drawable.h34, R.drawable.h35,
                 R.drawable.h36, R.drawable.h37, R.drawable.h38, R.drawable.h39,
                 R.drawable.h40, R.drawable.h41,};
//        if (interstitialAd.isLoaded()) {
//            interstitialAd.show();
         // ADID = true;

         // }

     }


    public void glasses(View v) {
//         if (POINTS == 1) {
//             CLD.startLoadingDialog();
//             tools.cancel();
//         }
//         Handler handler = new Handler();
//         handler.postDelayed(new Runnable() {
//             @SuppressLint("WrongConstant")
//             @Override
//             public void run() {
//                 if (POINTS == 0) {
//                     CLD.dismissDialog();
//
//                     if (POINTS == 0 && rewardedAd != null) {
//                         if (rewardedAd.isLoaded()) {
//                             CLD.dismissDialog();
//                             showAd();
//                         }
//                     }
//                     try {
//                         if (AD0.isEmpty() || AD0 == null || ADNY == 0) {
//                             if (ISGAME == 1) {
//                                 tools.show();
//                             }
//
//                             CLD.dismissDialog();
//                             // Toast.makeText(EditImageActivity.this, IADT, Toast.LENGTH_LONG).show();
//                             TSnackbar snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                             snackbar.setActionTextColor(Color.WHITE);
//                             View snackbarView = snackbar.getView();
//                             snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                             snackbar.setDuration(9000);
//                             snackbar.show();
//                         }
//                     } catch (NullPointerException e) {
//                         TSnackbar snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                         snackbar.setActionTextColor(Color.WHITE);
//                         View snackbarView = snackbar.getView();
//                         snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                         snackbar.setDuration(9000);
//                         snackbar.show();
//                         CLD.dismissDialog();
//
//                     }
//
//
//                 }
//             }
//         }, GLASSMS);
//


//        if (POINTS == 0) {

            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
            stickerifelse = new int[]{R.drawable.g1, R.drawable.g2, R.drawable.g3,
                    R.drawable.g4, R.drawable.g5, R.drawable.g6,
                    R.drawable.g7, R.drawable.g8, R.drawable.g9, R.drawable.g10,
                    R.drawable.g11, R.drawable.g12, R.drawable.g13, R.drawable.g14,
                    R.drawable.g15, R.drawable.g16, R.drawable.g17, R.drawable.g18,
                    R.drawable.g19, R.drawable.g20, R.drawable.g22,
                    R.drawable.g23, R.drawable.g24, R.drawable.g25, R.drawable.g26,
                    R.drawable.g27, R.drawable.g28, R.drawable.g30, R.drawable.g31,
                    R.drawable.g32, R.drawable.g33, R.drawable.g34, R.drawable.g35,
                    R.drawable.g36, R.drawable.g37, R.drawable.g38, R.drawable.g39,
                    R.drawable.g40, R.drawable.g41, R.drawable.g42, R.drawable.g43,
                    R.drawable.g44, R.drawable.g45, R.drawable.g46, R.drawable.g47,
                    R.drawable.g48, R.drawable.g49, R.drawable.g50,};
//        }
//        else {
//            hideLoading();
//            Snackbar.make(getWindow().getDecorView().getRootView(),"first time ad  is not loaded please try again",Snackbar.LENGTH_LONG).show();
//            }
////        if (interstitialAd2.isLoaded()) {
////            interstitialAd2.show();
////            AD1ID = true;
//
//        }
    }

        public void hats (View v){
            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
            stickerifelse = new int[]{R.drawable.hat1, R.drawable.hat2, R.drawable.hat3,
                    R.drawable.hat4, R.drawable.hat5, R.drawable.hat6,
                    R.drawable.hat7, R.drawable.hat8, R.drawable.hat9, R.drawable.hat10,
                    R.drawable.hat11, R.drawable.hat12, R.drawable.hat13, R.drawable.hat14,
                    R.drawable.hat15, R.drawable.hat16, R.drawable.hat17, R.drawable.hat18,
                    R.drawable.hat19, R.drawable.hat20,};
//        if (interstitialAd3.isLoaded()) {
//            interstitialAd3.show();
//            AD2ID = true;
//        }


        }

        public void beards (View v){
            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
            stickerifelse = new int[]{R.drawable.b1, R.drawable.b2, R.drawable.b3,
                    R.drawable.b4, R.drawable.b5, R.drawable.b6,
                    R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10,
                    R.drawable.b11, R.drawable.b12, R.drawable.b13, R.drawable.b14,
                    R.drawable.b15, R.drawable.b16, R.drawable.b17, R.drawable.b18,
                    R.drawable.b19, R.drawable.b20, R.drawable.b21, R.drawable.b22,
                    R.drawable.b23, R.drawable.b24, R.drawable.b25, R.drawable.b26,
                    R.drawable.b27, R.drawable.b28, R.drawable.b30, R.drawable.b31,
                    R.drawable.b32, R.drawable.b33, R.drawable.b34, R.drawable.b35,
                    R.drawable.b36, R.drawable.b37, R.drawable.b38, R.drawable.b39,
                    R.drawable.b40, R.drawable.b41, R.drawable.b42, R.drawable.b43,
                    R.drawable.b44, R.drawable.b45,};
//        if (interstitialAd4.isLoaded()) {
//            interstitialAd4.show();
//            AD3ID = true;
//        }

        }


        public void gaming (View v){

//        if(POINTS1 == 2){
//            if(DSAGAIN == 0 || DSAGAIN == 1){
//                showGAMEINGDialog();
//            }
//
//        }


//         if (POINTS1 == 0) {
//             tools.cancel();
//             CLD.startLoadingDialog();
//         }
//
//
//         Handler handler = new Handler();
//         handler.postDelayed(new Runnable() {
//             @SuppressLint("WrongConstant")
//             @Override
//             public void run() {
//                 if (POINTS1 == 0) {
//                     CLD.dismissDialog();
//                     TSnackbar snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                     snackbar.setActionTextColor(Color.WHITE);
//                     View snackbarView = snackbar.getView();
//                     snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                     snackbar.setDuration(9000);
//                     snackbar.show();
//                     if (rewardedAd1 != null && POINTS1 == 0) {
//                         if (rewardedAd1.isLoaded()) {
//                             CLD.dismissDialog();
//                             //   snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000);
//                             //   snackBarView = snackbar.getView();
//                             //  snackBarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorAccent));
//                             snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                             snackbar.setActionTextColor(Color.WHITE);
//                             snackbarView = snackbar.getView();
//                             snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                             snackbar.setDuration(9000);
//                             snackbar.show();
//                             showAd1();
//                         }
//                     }
//                     try {
//                         if (AD1.isEmpty() || AD1 == null || ADNY == 0) {
//                             CLD.dismissDialog();
//                             //   Toast.makeText(EditImageActivity.this, IADT, Toast.LENGTH_LONG).show();
//                             //  Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000).show();
//                             if (ISGAME == 1) {
//                                 tools.show();
//                             }
//
//                             snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                             snackbar.setActionTextColor(Color.WHITE);
//                             snackbarView = snackbar.getView();
//                             snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                             snackbar.setDuration(9000);
//                             snackbar.show();
//                             showAd1();
//
//
////                                snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000);
////                                 snackBarView = snackbar.getView();
////                                snackBarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorAccent));
////                                snackbar.show();
//                         }
//                     } catch (NullPointerException e) {
//                         //  Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).show();
//                         CLD.dismissDialog();
//
//                     }
//                 }
//             }
//         }, GAMEMS);
//

//            if (POINTS1 == 1) {
                tools.cancel();
                try {

                    mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                    stickerifelse = new int[]{R.drawable.chicken1, R.drawable.chicken2, R.drawable.chicken3,
                            R.drawable.chicken4, R.drawable.chicken5, R.drawable.chicken6,
                            R.drawable.chicken7, R.drawable.chicken8, R.drawable.chicken9, R.drawable.chicken10,
                            R.drawable.dragon1, R.drawable.dragon2, R.drawable.dragon3, R.drawable.dragon4,
                            R.drawable.dragon5, R.drawable.dragon6, R.drawable.dragon7, R.drawable.dragon8,
                            R.drawable.dragon9, R.drawable.dragon10, R.drawable.eagle1, R.drawable.eagle2,
                            R.drawable.eagle3, R.drawable.eagle4, R.drawable.eagle5, R.drawable.eagle6,
                            R.drawable.eagle7, R.drawable.eagle8, R.drawable.eagle9, R.drawable.eagle10
                            , R.drawable.ghost1, R.drawable.ghost2, R.drawable.ghost3,
                            R.drawable.ghost4, R.drawable.ghost5, R.drawable.ghost6,
                            R.drawable.ghost7, R.drawable.ghost8, R.drawable.ghost9, R.drawable.ghost10,
                            R.drawable.joker1, R.drawable.joker2, R.drawable.joker3,
                            R.drawable.joker4, R.drawable.joker5, R.drawable.joker6,
                            R.drawable.joker7, R.drawable.joker8, R.drawable.joker9, R.drawable.joker10,
                            R.drawable.magic1, R.drawable.magic2, R.drawable.magic3,
                            R.drawable.magic4, R.drawable.magic5, R.drawable.magic6,
                            R.drawable.magic7, R.drawable.magic8, R.drawable.magic9, R.drawable.magic10,
                            R.drawable.monkey1, R.drawable.monkey2, R.drawable.monkey3,
                            R.drawable.monkey4, R.drawable.monkey5, R.drawable.monkey6,
                            R.drawable.monkey7, R.drawable.monkey9, R.drawable.monkey10,
                            R.drawable.ninja1, R.drawable.ninja2, R.drawable.ninja3,
                            R.drawable.ninja4, R.drawable.ninja5, R.drawable.ninja6,
                            R.drawable.ninja7, R.drawable.ninja8, R.drawable.ninja9, R.drawable.ninja10,
                            R.drawable.panda1, R.drawable.panda2, R.drawable.panda3,
                            R.drawable.panda4, R.drawable.panda5, R.drawable.panda6,
                            R.drawable.panda7, R.drawable.panda8, R.drawable.panda9, R.drawable.panda10,
                            R.drawable.shooter1, R.drawable.shooter2, R.drawable.shooter3,
                            R.drawable.shooter4, R.drawable.shooter5, R.drawable.shooter6,
                            R.drawable.shooter7, R.drawable.shooter8, R.drawable.shooter9, R.drawable.shooter10,
                            R.drawable.toxic1, R.drawable.toxic2, R.drawable.toxic3,
                            R.drawable.toxic4, R.drawable.toxic5, R.drawable.toxic6,
                            R.drawable.toxic7, R.drawable.toxic8, R.drawable.toxic9, R.drawable.toxic10,
                            R.drawable.viper1, R.drawable.viper2, R.drawable.viper3,
                            R.drawable.viper4, R.drawable.viper5, R.drawable.viper6,
                            R.drawable.viper7, R.drawable.viper8, R.drawable.viper9, R.drawable.viper10,
                            R.drawable.wolf1, R.drawable.wolf2, R.drawable.wolf3,
                            R.drawable.wolf4, R.drawable.wolf5, R.drawable.wolf6,
                            R.drawable.wolf7, R.drawable.wolf8, R.drawable.wolf9, R.drawable.wolf10,};
                } catch (Exception e)
            {

            }

            }
//        }
//     Intent intent = getIntent();
//     overridePendingTransition(0, 0);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//     finish();
//     overridePendingTransition(0, 0);
//     startActivity(intent);
//                SharedPref.write(SharedPref.ISGAME, 1);
//                SharedPref.write(SharedPref.BGREMOVE, 0);
//            else {
//                tools.cancel();
//                GAMEMENU();
//            }


        //       if (POINTS1 == 2) {


        public void GAMEMENU () {


//            if (POINTS1 == 1) {
//                if (DSAGAIN == 0 || DSAGAIN == 1) {
//                    showGAMEINGDialog();
//                }
//
//            }


//             if(POINTS1 == 0){
//                 tools.cancel();
//                 CLD.startLoadingDialog();
//             }
//
//
//
//
//
//
//             Handler handler = new Handler();
//             handler.postDelayed(new Runnable() {
//                 @SuppressLint("WrongConstant")
//                 @Override
//                 public void run() {
//                     if (POINTS1 == 0) {
//                         CLD.dismissDialog();
//                         TSnackbar snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                         snackbar.setActionTextColor(Color.WHITE);
//                         View snackbarView = snackbar.getView();
//                         snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                         snackbar.setDuration(9000);
//                         snackbar.show();
//                         if (rewardedAd1 != null && POINTS1 == 0) {
//                             if (rewardedAd1.isLoaded()) {
//                                 CLD.dismissDialog();
//                                 //   snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000);
//                                 //   snackBarView = snackbar.getView();
//                                 //  snackBarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorAccent));
//                                 snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                                 snackbar.setActionTextColor(Color.WHITE);
//                                 snackbarView = snackbar.getView();
//                                 snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                                 snackbar.setDuration(9000);
//                                 snackbar.show();
//                                 showAd1();
//                             }
//                         }
//                         try {
//                             if (AD1.isEmpty() || AD1 == null || ADNY == 0) {
//                                 CLD.dismissDialog();
//                                 //   Toast.makeText(EditImageActivity.this, IADT, Toast.LENGTH_LONG).show();
//                                 //  Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000).show();
//
//                                 snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                                 snackbar.setActionTextColor(Color.WHITE);
//                                 snackbarView = snackbar.getView();
//                                 snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                                 snackbar.setDuration(9000);
//                                 snackbar.show();
//                                 showAd1();
////                                snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000);
////                                 snackBarView = snackbar.getView();
////                                snackBarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorAccent));
////                                snackbar.show();
//                             }
//                         } catch (NullPointerException e) {
//                             //  Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).show();
//                             CLD.dismissDialog();
//
//                         }
//                     }
//                 }
//             }, GAMEMS);
//

            if (POINTS1 == 1) {
                try {


                    mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                    stickerifelse = new int[]{R.drawable.chicken1, R.drawable.chicken2, R.drawable.chicken3,
                            R.drawable.chicken4, R.drawable.chicken5, R.drawable.chicken6,
                            R.drawable.chicken7, R.drawable.chicken8, R.drawable.chicken9, R.drawable.chicken10,
                            R.drawable.dragon1, R.drawable.dragon2, R.drawable.dragon3, R.drawable.dragon4,
                            R.drawable.dragon5, R.drawable.dragon6, R.drawable.dragon7, R.drawable.dragon8,
                            R.drawable.dragon9, R.drawable.dragon10, R.drawable.eagle1, R.drawable.eagle2,
                            R.drawable.eagle3, R.drawable.eagle4, R.drawable.eagle5, R.drawable.eagle6,
                            R.drawable.eagle7, R.drawable.eagle8, R.drawable.eagle9, R.drawable.eagle10
                            , R.drawable.ghost1, R.drawable.ghost2, R.drawable.ghost3,
                            R.drawable.ghost4, R.drawable.ghost5, R.drawable.ghost6,
                            R.drawable.ghost7, R.drawable.ghost8, R.drawable.ghost9, R.drawable.ghost10,
                            R.drawable.joker1, R.drawable.joker2, R.drawable.joker3,
                            R.drawable.joker4, R.drawable.joker5, R.drawable.joker6,
                            R.drawable.joker7, R.drawable.joker8, R.drawable.joker9, R.drawable.joker10,
                            R.drawable.magic1, R.drawable.magic2, R.drawable.magic3,
                            R.drawable.magic4, R.drawable.magic5, R.drawable.magic6,
                            R.drawable.magic7, R.drawable.magic8, R.drawable.magic9, R.drawable.magic10,
                            R.drawable.monkey1, R.drawable.monkey2, R.drawable.monkey3,
                            R.drawable.monkey4, R.drawable.monkey5, R.drawable.monkey6,
                            R.drawable.monkey7, R.drawable.monkey9, R.drawable.monkey10,
                            R.drawable.ninja1, R.drawable.ninja2, R.drawable.ninja3,
                            R.drawable.ninja4, R.drawable.ninja5, R.drawable.ninja6,
                            R.drawable.ninja7, R.drawable.ninja8, R.drawable.ninja9, R.drawable.ninja10,
                            R.drawable.panda1, R.drawable.panda2, R.drawable.panda3,
                            R.drawable.panda4, R.drawable.panda5, R.drawable.panda6,
                            R.drawable.panda7, R.drawable.panda8, R.drawable.panda9, R.drawable.panda10,
                            R.drawable.shooter1, R.drawable.shooter2, R.drawable.shooter3,
                            R.drawable.shooter4, R.drawable.shooter5, R.drawable.shooter6,
                            R.drawable.shooter7, R.drawable.shooter8, R.drawable.shooter9, R.drawable.shooter10,
                            R.drawable.toxic1, R.drawable.toxic2, R.drawable.toxic3,
                            R.drawable.toxic4, R.drawable.toxic5, R.drawable.toxic6,
                            R.drawable.toxic7, R.drawable.toxic8, R.drawable.toxic9, R.drawable.toxic10,
                            R.drawable.viper1, R.drawable.viper2, R.drawable.viper3,
                            R.drawable.viper4, R.drawable.viper5, R.drawable.viper6,
                            R.drawable.viper7, R.drawable.viper8, R.drawable.viper9, R.drawable.viper10,
                            R.drawable.wolf1, R.drawable.wolf2, R.drawable.wolf3,
                            R.drawable.wolf4, R.drawable.wolf5, R.drawable.wolf6,
                            R.drawable.wolf7, R.drawable.wolf8, R.drawable.wolf9, R.drawable.wolf10,};
                } catch (Exception e) {

                }
            }


        }


        //  tools.cancel();


//        if (interstitialAd5.isLoaded()) {
//            interstitialAd5.show();
//            AD4ID = true;
//
//        }


        public void premiumGM (View v){

//         if (POINTS3 == 1) {
//             tools.cancel();
//             CLD.startLoadingDialog();
//         }
//
//
//         Handler handler = new Handler();
//         handler.postDelayed(new Runnable() {
//             @SuppressLint("WrongConstant")
//             @Override
//             public void run() {
//                 if (POINTS3 == 0) {
//                     CLD.dismissDialog();
//                     TSnackbar snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                     snackbar.setActionTextColor(Color.WHITE);
//                     View snackbarView = snackbar.getView();
//                     snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                     snackbar.setDuration(9000);
//                     snackbar.show();
//                     if (rewardedAd3 != null && POINTS3 == 0) {
//                         if (rewardedAd3.isLoaded()) {
//                             CLD.dismissDialog();
//                             //   snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000);
//                             //   snackBarView = snackbar.getView();
//                             //  snackBarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorAccent));
//                             snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                             snackbar.setActionTextColor(Color.WHITE);
//                             snackbarView = snackbar.getView();
//                             snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                             snackbar.setDuration(9000);
//                             snackbar.show();
//                             showAd3();
//                         }
//                     }
//                     try {
//                         if (AD3.isEmpty() || AD3 == null || ADNY == 0) {
//                             CLD.dismissDialog();
//                             //   Toast.makeText(EditImageActivity.this, IADT, Toast.LENGTH_LONG).show();
//                             //  Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000).show();
//                             if (ISGAME == 1) {
//                                 tools.show();
//                             }
//
//                             snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                             snackbar.setActionTextColor(Color.WHITE);
//                             snackbarView = snackbar.getView();
//                             snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                             snackbar.setDuration(9000);
//                             snackbar.show();
//                             showAd1();
//
//
////                                snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).setDuration(9000);
////                                 snackBarView = snackbar.getView();
////                                snackBarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorAccent));
////                                snackbar.show();
//                         }
//                     } catch (NullPointerException e) {
//                         //  Snackbar.make(getWindow().getDecorView().getRootView(), IADT, Snackbar.LENGTH_SHORT).show();
//                         CLD.dismissDialog();
//
//                     }
//                 }
//             }
//         }, PMGAMES);


//            if (POINTS3 == 0) {
                tools.cancel();
                try {

//        if(DSAGAIN == 0 || DSAGAIN == 1){
//            showGAMEINGDialog();
//        }

                    tools.cancel();

                    mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                    stickerifelse = new int[]{R.drawable.game0, R.drawable.game1, R.drawable.game2, R.drawable.game3,
                            R.drawable.game4, R.drawable.game5, R.drawable.game6,
                            R.drawable.game7, R.drawable.game8, R.drawable.game9, R.drawable.game10,
                            R.drawable.game11, R.drawable.game12, R.drawable.game13, R.drawable.game14,
                            R.drawable.game15, R.drawable.game16, R.drawable.game17, R.drawable.game18,
                            R.drawable.game19, R.drawable.game20, R.drawable.game21, R.drawable.game22,
                            R.drawable.game23, R.drawable.game24, R.drawable.game25, R.drawable.game26,
                            R.drawable.game27, R.drawable.game28, R.drawable.game29, R.drawable.game30,
                            R.drawable.game31, R.drawable.game32, R.drawable.game33, R.drawable.game34,
                            R.drawable.game35};

//        if (interstitialAd6.isLoaded()) {
//            interstitialAd6.show();
//            AD5ID = true;
//        }
                } catch (Exception e) {

                }
            }
//        }


        public void bgImages (View v){
            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
            stickerifelse = new int[]{R.drawable.bg_lg1, R.drawable.bg_lg2, R.drawable.bg_lg3,
                    R.drawable.bg_lg4, R.drawable.bg_lg5, R.drawable.bg_lg6,
                    R.drawable.bg_lg7, R.drawable.bg_lg8, R.drawable.bg_lg9, R.drawable.bg_lg10,
                    R.drawable.bg_lg11, R.drawable.bg_lg12, R.drawable.bg_lg13, R.drawable.bg_lg14,
                    R.drawable.bg_lg15, R.drawable.bg_lg16, R.drawable.bg_lg17, R.drawable.bg_lg18,
                    R.drawable.bg_lg19, R.drawable.bg_lg20, R.drawable.bg_lg22,
                    R.drawable.bg_lg23, R.drawable.bg_lg24, R.drawable.bg_lg25, R.drawable.bg_lg26,
                    R.drawable.bg_lg27, R.drawable.bg_lg28, R.drawable.bg_lg30, R.drawable.bg_lg31,
                    R.drawable.bg_lg32, R.drawable.bg_lg33, R.drawable.bg_lg34, R.drawable.bg_lg35,
                    R.drawable.bg_lg36, R.drawable.bg_lg37, R.drawable.bg_lg38, R.drawable.bg_lg39,
                    R.drawable.bg_lg40, R.drawable.bg_lg41, R.drawable.bg_lg42, R.drawable.bg_lg43,
                    R.drawable.bg_lg44, R.drawable.bg_lg45, R.drawable.bg_lg46, R.drawable.bg_lg47,
                    R.drawable.bg_lg48, R.drawable.bg_lg49, R.drawable.bg_lg50, R.drawable.bg_lg51, R.drawable.bg_lg52, R.drawable.bg_lg53,
                    R.drawable.bg_lg54, R.drawable.bg_lg55, R.drawable.bg_lg56,
                    R.drawable.bg_lg57, R.drawable.bg_lg58, R.drawable.bg_lg59, R.drawable.bg_lg60,
                    R.drawable.bg_lg61, R.drawable.bg_lg62, R.drawable.bg_lg63, R.drawable.bg_lg64,
                    R.drawable.bg_lg65, R.drawable.bg_lg66, R.drawable.bg_lg67, R.drawable.bg_lg68,
                    R.drawable.bg_lg69, R.drawable.bg_lg70, R.drawable.bg_lg71, R.drawable.bg_lg72, R.drawable.bg_lg73, R.drawable.bg_lg74,};

//        if (interstitialAd7.isLoaded()) {
//            interstitialAd7.show();
//            AD6ID = true;
//        }


        }

        public void word (View v){
            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
            stickerifelse = new int[]{R.drawable.a1, R.drawable.a2, R.drawable.a3,
                    R.drawable.a4, R.drawable.a5, R.drawable.a6,
                    R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
                    R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14,
                    R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18,
                    R.drawable.a19, R.drawable.a20, R.drawable.a22,
                    R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26,
                    R.drawable.a27, R.drawable.a28, R.drawable.a30, R.drawable.a31,
                    R.drawable.a32, R.drawable.a33, R.drawable.a34, R.drawable.a35,
                    R.drawable.a36, R.drawable.a37, R.drawable.a38, R.drawable.a39,
                    R.drawable.a40, R.drawable.a41, R.drawable.a42, R.drawable.a43,
                    R.drawable.a44, R.drawable.a45, R.drawable.a46, R.drawable.a47,
                    R.drawable.a48, R.drawable.a49, R.drawable.a50, R.drawable.a51, R.drawable.a52, R.drawable.a53,
                    R.drawable.a54, R.drawable.a55, R.drawable.a56,
                    R.drawable.a57, R.drawable.a58, R.drawable.a59, R.drawable.a60,
                    R.drawable.a61, R.drawable.a62, R.drawable.a63, R.drawable.a64,
                    R.drawable.a65, R.drawable.a66, R.drawable.a67, R.drawable.a68,
                    R.drawable.a69, R.drawable.a70, R.drawable.a71, R.drawable.a72, R.drawable.a73, R.drawable.a74,
                    R.drawable.a75, R.drawable.a76, R.drawable.a77, R.drawable.a78,
                    R.drawable.a79, R.drawable.a80, R.drawable.a81, R.drawable.a82, R.drawable.a83, R.drawable.a84,
                    R.drawable.a85, R.drawable.a86, R.drawable.a87, R.drawable.a88,
                    R.drawable.a89, R.drawable.a90, R.drawable.a91, R.drawable.a92, R.drawable.a93, R.drawable.a94,
                    R.drawable.a95, R.drawable.a96, R.drawable.a97, R.drawable.a98,
                    R.drawable.a99, R.drawable.a100,};

//        if (interstitialAd8.isLoaded()) {
//            interstitialAd8.show();
//            AD7ID = true;
//        }

        }


        public void art (View v){

//        if(POINTS2 == 0){
//            CLD.startLoadingDialog();
//            tools.cancel();
//        }
//        Handler   handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void run() {
//                if(POINTS2 == 0) {
//                    CLD.dismissDialog();
//                    if (POINTS2 == 0 && rewardedAd2 != null ) {
//                        if (rewardedAd2.isLoaded()) {
//                            CLD.dismissDialog();
//                            showAd2();
//                        }
//                    }
//                    try {
//                        if (AD2.isEmpty() || AD2 == null || ADNY == 0) {
//                            CLD.dismissDialog();
//                            if (ISGAME == 1)
//                            {
//                                tools.show();
//                            }
//
//                            //  Toast.makeText(EditImageActivity.this, IADT, Toast.LENGTH_LONG).show();
//                            TSnackbar snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                            snackbar.setActionTextColor(Color.WHITE);
//                            View snackbarView = snackbar.getView();
//                            snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                            snackbar.setDuration(9000);
//                            snackbar.show();
//                        }
//                    }
//                    catch(NullPointerException e)
//                    {
//                        TSnackbar snackbar = TSnackbar.make(relative_layout_main, IADT, TSnackbar.LENGTH_LONG);
//                        snackbar.setActionTextColor(Color.WHITE);
//                        View snackbarView = snackbar.getView();
//                        snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
//                        snackbar.setDuration(9000);
//                        snackbar.show();
//                        CLD.dismissDialog();
//
//                    }
//
//
//                }
//            }
//        },ARTS);


//            if (POINTS2 == 1) {
                tools.cancel();
                mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                stickerifelse = new int[]{R.drawable.art_lg1, R.drawable.art_lg2, R.drawable.art_lg3,
                        R.drawable.art_lg4, R.drawable.art_lg5, R.drawable.art_lg6,
                        R.drawable.art_lg7, R.drawable.art_lg8, R.drawable.art_lg9, R.drawable.art_lg10,
                        R.drawable.art_lg11, R.drawable.art_lg12, R.drawable.art_lg13, R.drawable.art_lg14,
                        R.drawable.art_lg15, R.drawable.art_lg16, R.drawable.art_lg17, R.drawable.art_lg18,
                        R.drawable.art_lg19, R.drawable.art_lg20, R.drawable.art_lg22,
                        R.drawable.art_lg23, R.drawable.art_lg24, R.drawable.art_lg25, R.drawable.art_lg26,
                        R.drawable.art_lg27, R.drawable.art_lg28, R.drawable.art_lg30, R.drawable.art_lg31,
                        R.drawable.art_lg32, R.drawable.art_lg33, R.drawable.art_lg34, R.drawable.art_lg35,
                        R.drawable.art_lg36, R.drawable.art_lg37, R.drawable.art_lg38, R.drawable.art_lg39,
                        R.drawable.art_lg40, R.drawable.art_lg41, R.drawable.art_lg42, R.drawable.art_lg43,
                        R.drawable.art_lg44, R.drawable.art_lg45, R.drawable.art_lg46, R.drawable.art_lg47,
                        R.drawable.art_lg48, R.drawable.art_lg49, R.drawable.art_lg50, R.drawable.art_lg51, R.drawable.art_lg52, R.drawable.art_lg53,
                        R.drawable.art_lg54, R.drawable.art_lg55, R.drawable.art_lg56,
                        R.drawable.art_lg57, R.drawable.art_lg58, R.drawable.art_lg59, R.drawable.art_lg60,
                        R.drawable.art_lg61, R.drawable.art_lg62, R.drawable.art_lg63, R.drawable.art_lg64,
                        R.drawable.art_lg65, R.drawable.art_lg66, R.drawable.art_lg67, R.drawable.art_lg68,
                        R.drawable.art_lg69, R.drawable.art_lg70, R.drawable.art_lg71, R.drawable.art_lg72, R.drawable.art_lg73, R.drawable.art_lg74,
                        R.drawable.art_lg75, R.drawable.art_lg76, R.drawable.art_lg77, R.drawable.art_lg78,
                        R.drawable.art_lg79, R.drawable.art_lg80, R.drawable.art_lg81,};

//        if (interstitialAd9.isLoaded()) {
//            interstitialAd9.show();
//            AD8ID = true;
//        }
            }

//        }


        public void wolf (View v){
            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());

            stickerifelse = new int[]{R.drawable.howlingwolf_lg1, R.drawable.howlingwolf_lg2, R.drawable.howlingwolf_lg3,
                    R.drawable.howlingwolf_lg4, R.drawable.howlingwolf_lg5, R.drawable.howlingwolf_lg6,
                    R.drawable.howlingwolf_lg7, R.drawable.howlingwolf_lg8, R.drawable.howlingwolf_lg9, R.drawable.howlingwolf_lg10,
                    R.drawable.howlingwolf_lg11, R.drawable.howlingwolf_lg12, R.drawable.howlingwolf_lg13, R.drawable.howlingwolf_lg14,
                    R.drawable.howlingwolf_lg15, R.drawable.howlingwolf_lg16, R.drawable.howlingwolf_lg17, R.drawable.howlingwolf_lg18,
                    R.drawable.howlingwolf_lg19, R.drawable.howlingwolf_lg20, R.drawable.howlingwolf_lg22,
                    R.drawable.howlingwolf_lg23, R.drawable.howlingwolf_lg24, R.drawable.howlingwolf_lg25, R.drawable.howlingwolf_lg26,
                    R.drawable.howlingwolf_lg27,};
//
//        if (interstitialAd10.isLoaded()) {
//            interstitialAd10.show();
//            AD9ID = true;
//        }


        }


        public void lg3d (View v){
            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());

            stickerifelse = new int[]{R.drawable.lg_3d1, R.drawable.lg_3d2, R.drawable.lg_3d3,
                    R.drawable.lg_3d4, R.drawable.lg_3d5, R.drawable.lg_3d6,
                    R.drawable.lg_3d7, R.drawable.lg_3d8, R.drawable.lg_3d9, R.drawable.lg_3d10,
                    R.drawable.lg_3d11, R.drawable.lg_3d12, R.drawable.lg_3d13, R.drawable.lg_3d14,
                    R.drawable.lg_3d15, R.drawable.lg_3d16, R.drawable.lg_3d17, R.drawable.lg_3d18,
                    R.drawable.lg_3d19, R.drawable.lg_3d20, R.drawable.lg_3d22,
                    R.drawable.lg_3d23, R.drawable.lg_3d24, R.drawable.lg_3d25, R.drawable.lg_3d26,
                    R.drawable.lg_3d27, R.drawable.lg_3d28, R.drawable.lg_3d30, R.drawable.lg_3d31,
                    R.drawable.lg_3d32, R.drawable.lg_3d33, R.drawable.lg_3d34, R.drawable.lg_3d35,
                    R.drawable.lg_3d36, R.drawable.lg_3d37, R.drawable.lg_3d38, R.drawable.lg_3d39,
                    R.drawable.lg_3d40, R.drawable.lg_3d41, R.drawable.lg_3d42, R.drawable.lg_3d43,
                    R.drawable.lg_3d44, R.drawable.lg_3d45, R.drawable.lg_3d46, R.drawable.lg_3d47,
                    R.drawable.lg_3d48, R.drawable.lg_3d49, R.drawable.lg_3d50, R.drawable.lg_3d51, R.drawable.lg_3d52, R.drawable.lg_3d53,
                    R.drawable.lg_3d54, R.drawable.lg_3d55, R.drawable.lg_3d56,
                    R.drawable.lg_3d57, R.drawable.lg_3d58, R.drawable.lg_3d59, R.drawable.lg_3d60,
                    R.drawable.lg_3d61, R.drawable.lg_3d62, R.drawable.lg_3d63, R.drawable.lg_3d64,
                    R.drawable.lg_3d65, R.drawable.lg_3d66, R.drawable.lg_3d67, R.drawable.lg_3d68,
                    R.drawable.lg_3d69, R.drawable.lg_3d70, R.drawable.lg_3d71, R.drawable.lg_3d72, R.drawable.lg_3d73, R.drawable.lg_3d74,
                    R.drawable.lg_3d75, R.drawable.lg_3d76, R.drawable.lg_3d77, R.drawable.lg_3d78,
                    R.drawable.lg_3d79, R.drawable.lg_3d80, R.drawable.lg_3d81, R.drawable.lg_3d82, R.drawable.lg_3d83, R.drawable.lg_3d84,
                    R.drawable.lg_3d85, R.drawable.lg_3d86, R.drawable.lg_3d87, R.drawable.lg_3d88,};

//        if (interstitialAd11.isLoaded()) {
//            interstitialAd11.show();
//            AD10ID = true;
//        }

        }

        public void sale (View v){
            tools.cancel();

            mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
            stickerifelse = new int[]{R.drawable.sale_lg1, R.drawable.sale_lg2, R.drawable.sale_lg3,
                    R.drawable.sale_lg4, R.drawable.sale_lg5, R.drawable.sale_lg6,
                    R.drawable.sale_lg7, R.drawable.sale_lg8, R.drawable.sale_lg9, R.drawable.sale_lg10,
                    R.drawable.sale_lg11, R.drawable.sale_lg12, R.drawable.sale_lg13, R.drawable.sale_lg14,
                    R.drawable.sale_lg15, R.drawable.sale_lg16, R.drawable.sale_lg17, R.drawable.sale_lg18,
                    R.drawable.sale_lg19, R.drawable.sale_lg20, R.drawable.sale_lg22,
                    R.drawable.sale_lg23, R.drawable.sale_lg24, R.drawable.sale_lg25, R.drawable.sale_lg26,
                    R.drawable.sale_lg27, R.drawable.sale_lg28, R.drawable.sale_lg30, R.drawable.sale_lg31,
                    R.drawable.sale_lg32, R.drawable.sale_lg33, R.drawable.sale_lg34, R.drawable.sale_lg35,
                    R.drawable.sale_lg36, R.drawable.sale_lg37, R.drawable.sale_lg38, R.drawable.sale_lg39,
                    R.drawable.sale_lg40, R.drawable.sale_lg41, R.drawable.sale_lg42, R.drawable.sale_lg43,
                    R.drawable.sale_lg44, R.drawable.sale_lg45, R.drawable.sale_lg46, R.drawable.sale_lg47,
                    R.drawable.sale_lg48, R.drawable.sale_lg49, R.drawable.sale_lg50, R.drawable.sale_lg51, R.drawable.sale_lg52, R.drawable.sale_lg53,
                    R.drawable.sale_lg54, R.drawable.sale_lg55, R.drawable.sale_lg56,
                    R.drawable.sale_lg57, R.drawable.sale_lg58, R.drawable.sale_lg59, R.drawable.sale_lg60,
                    R.drawable.sale_lg61, R.drawable.sale_lg62, R.drawable.sale_lg63,};

//        if (interstitialAd12.isLoaded()) {
//            interstitialAd12.show();
//        }


        }

        //rewarded ad
//    private void loadAd() {
//        this.rewardedAd = new RewardedAd(this, AD0);
//        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
//
//            @Override
//            public void onRewardedAdLoaded() {
//                super.onRewardedAdLoaded();
//
//
//            }
//
//            @Override
//            public void onRewardedAdFailedToLoad(int i) {
//                super.onRewardedAdFailedToLoad(i);
//
//
//            }
//        };
//
//        this.rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//
//    }
//
//    private void showAd() {
//        if (this.rewardedAd.isLoaded()) {
//            RewardedAdCallback adCallback = new RewardedAdCallback() {
//                @Override
//                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                 //   Snackbar.make(getWindow().getDecorView().getRootView(),"Unlocked",Snackbar.LENGTH_LONG).show();
//                    SharedPref.write(SharedPref.POINTS, 1);
//                    SharedPref.write(SharedPref.ADSANDC, 1);
//
//
//
//
//
//
//
//
//
//
//                }
//
//                @Override
//                public void onRewardedAdOpened() {
//                    super.onRewardedAdOpened();
//
//                }
//
//                @Override
//                public void onRewardedAdClosed() {
////                    Intent intent = getIntent();
////                    finish();
////                    startActivity(new Intent(MainActivity.this,MainActivity.class));
//
//                    Intent intent = getIntent();
//                    overridePendingTransition(0, 0);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    finish();
//                    overridePendingTransition(0, 0);
//                    startActivity(intent);
//
//                    if (ISGAME == 1)
//                    {
//                        intent = getIntent();
//                        overridePendingTransition(0, 0);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        finish();
//                        overridePendingTransition(0, 0);
//                        startActivity(intent);
//                        SharedPref.write(SharedPref.ISGAME, 1);
//                    }
//
//                    super.onRewardedAdClosed();
//                    loadAd();
//
//
//
//
//                }
//
//                @Override
//                public void onRewardedAdFailedToShow(int i) {
//
//                    super.onRewardedAdFailedToShow(i);
//
//                    //  Toast.makeText(getApplicationContext()," ad failed to show",Toast.LENGTH_SHORT).show();
//
//                }
//            };
//            this.rewardedAd.show(this, adCallback);
//        } else {
//        }
//
//    }
//
//
//
////end rewarded ad and start rewardedad 1
//
//
//    private void loadAd1() {
//        this.rewardedAd1 = new RewardedAd(this,AD1);
//        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
//
//            @Override
//            public void onRewardedAdLoaded() {
//                super.onRewardedAdLoaded();
//
//
//            }
//
//            @Override
//            public void onRewardedAdFailedToLoad(int i) {
//                super.onRewardedAdFailedToLoad(i);
//
//
//            }
//        };
//
//        this.rewardedAd1.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//
//    }
//
//
//
//    private void showAd1() {
//        if (this.rewardedAd1.isLoaded()) {
//            RewardedAdCallback adCallback = new RewardedAdCallback() {
//                @Override
//                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                 //   Snackbar.make(getWindow().getDecorView().getRootView(),"Unlocked",Snackbar.LENGTH_LONG).show();
//                    SharedPref.write(SharedPref.POINTS1, 1);
//                    SharedPref.write(SharedPref.ADSANDC, 2);
//
//
//
//
//
//
//
//                }
//
//                @Override
//                public void onRewardedAdOpened() {
//                    super.onRewardedAdOpened();
//
//                }
//
//                @Override
//                public void onRewardedAdClosed() {
////                    Intent intent = getIntent();
////                    finish();
////                    startActivity(new Intent(MainActivity.this,MainActivity.class));
//
//                    Intent intent = getIntent();
//                    overridePendingTransition(0, 0);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    finish();
//                    overridePendingTransition(0, 0);
//                    startActivity(intent);
//
//                    super.onRewardedAdClosed();
//                    loadAd1();
//
//                    if (ISGAME == 1)
//                    {
//                        intent = getIntent();
//                        overridePendingTransition(0, 0);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        finish();
//                        overridePendingTransition(0, 0);
//                        startActivity(intent);
//                        SharedPref.write(SharedPref.ISGAME, 1);
//                    }
//
//
//
//                }
//
//                @Override
//                public void onRewardedAdFailedToShow(int i) {
//
//                    super.onRewardedAdFailedToShow(i);
//
//                    //  Toast.makeText(getApplicationContext()," ad failed to show",Toast.LENGTH_SHORT).show();
//
//                }
//            };
//            this.rewardedAd1.show(this, adCallback);
//        } else {
//        }
//
//    }
//
////end rewarded ad
//
//
//
//
//     //end rewarded ad1 and start rewarded ad2
//
//
//     private void loadAd2() {
//         this.rewardedAd2 = new RewardedAd(this,AD2);
//         RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
//
//             @Override
//             public void onRewardedAdLoaded() {
//                 super.onRewardedAdLoaded();
//
//
//             }
//
//             @Override
//             public void onRewardedAdFailedToLoad(int i) {
//                 super.onRewardedAdFailedToLoad(i);
//
//
//             }
//         };
//
//         this.rewardedAd2.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//
//     }
//
//
//
//     private void showAd2() {
//         if (this.rewardedAd2.isLoaded()) {
//             RewardedAdCallback adCallback = new RewardedAdCallback() {
//                 @Override
//                 public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                     //   Snackbar.make(getWindow().getDecorView().getRootView(),"Unlocked",Snackbar.LENGTH_LONG).show();
//                     SharedPref.write(SharedPref.POINTS2, 1);
//                     SharedPref.write(SharedPref.ADSANDC, 3);
//
//
//
//
//
//
//
//                 }
//
//                 @Override
//                 public void onRewardedAdOpened() {
//                     super.onRewardedAdOpened();
//
//                 }
//
//                 @Override
//                 public void onRewardedAdClosed() {
////                    Intent intent = getIntent();
////                    finish();
////                    startActivity(new Intent(MainActivity.this,MainActivity.class));
//
//                     Intent intent = getIntent();
//                     overridePendingTransition(0, 0);
//                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                     finish();
//                     overridePendingTransition(0, 0);
//                     startActivity(intent);
//
//                     super.onRewardedAdClosed();
//                     loadAd2();
//
//                     if (ISGAME == 1)
//                     {
//                         intent = getIntent();
//                         overridePendingTransition(0, 0);
//                         intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                         finish();
//                         overridePendingTransition(0, 0);
//                         startActivity(intent);
//                         SharedPref.write(SharedPref.ISGAME, 1);
//                     }
//
//
//
//                 }
//
//                 @Override
//                 public void onRewardedAdFailedToShow(int i) {
//
//                     super.onRewardedAdFailedToShow(i);
//
//                     //  Toast.makeText(getApplicationContext()," ad failed to show",Toast.LENGTH_SHORT).show();
//
//                 }
//             };
//             this.rewardedAd2.show(this, adCallback);
//         } else {
//         }
//
//     }
//
////end rewarded ad
//
//
//
//
//
//
//     //end rewarded ad2 and start rewarded ad3
//
//
//    private void loadAd3() {
//        this.rewardedAd3 = new RewardedAd(this,AD3);
//        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
//
//            @Override
//            public void onRewardedAdLoaded() {
//                super.onRewardedAdLoaded();
//
//
//            }
//
//            @Override
//            public void onRewardedAdFailedToLoad(int i) {
//                super.onRewardedAdFailedToLoad(i);
//
//
//            }
//        };
//
//        this.rewardedAd3.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//
//    }
//
//
//
//    private void showAd3() {
//        if (this.rewardedAd3.isLoaded()) {
//            RewardedAdCallback adCallback = new RewardedAdCallback() {
//                @Override
//                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                 //   Snackbar.make(getWindow().getDecorView().getRootView(),"Unlocked",Snackbar.LENGTH_LONG).show();
//                    SharedPref.write(SharedPref.POINTS3, 1);
//                    SharedPref.write(SharedPref.ADSANDC, 4);
//
//
//
//
//
//
//
//
//                }
//
//                @Override
//                public void onRewardedAdOpened() {
//                    super.onRewardedAdOpened();
//
//                }
//
//                @Override
//                public void onRewardedAdClosed() {
////                    Intent intent = getIntent();
////                    finish();
////                    startActivity(new Intent(MainActivity.this,MainActivity.class));
//
//                    Intent intent = getIntent();
//                    overridePendingTransition(0, 0);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    finish();
//                    overridePendingTransition(0, 0);
//                    startActivity(intent);
//
//                    super.onRewardedAdClosed();
//                    loadAd3();
//
//                    if (ISGAME == 1)
//                    {
//                        intent = getIntent();
//                        overridePendingTransition(0, 0);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        finish();
//                        overridePendingTransition(0, 0);
//                        startActivity(intent);
//                        SharedPref.write(SharedPref.ISGAME, 1);
//                    }
//
//
//
//                }
//
//                @Override
//                public void onRewardedAdFailedToShow(int i) {
//
//                    super.onRewardedAdFailedToShow(i);
//
//                    //  Toast.makeText(getApplicationContext()," ad failed to show",Toast.LENGTH_SHORT).show();
//
//                }
//            };
//            this.rewardedAd3.show(this, adCallback);
//        } else {
//        }
//
//    }
//
////end rewarded ad
//
//
//


//    public void coming1(View v) {
//
//        Snackbar.make(getWindow().getDecorView().getRootView(), "wait for next update", Snackbar.LENGTH_SHORT).show();
//
//    }

    public void fontlayout(View v) {
        //v.getId();
        tools2 = new BottomSheetDialog(EditImageActivity.this);
        tools2.setContentView(R.layout.activity_font_styles);
        tools2.setCanceledOnTouchOutside(true);
        tools2.setCancelable(false);
        tools2.show();
    }


    public void f1(View v) {

        A_BOLD = "font1.ttf";
        //     FontStyles.fontstyles.setText("a");
      //  Snackbar.make(getWindow().getDecorView().getRootView(),FST.fst1, Snackbar.LENGTH_SHORT).show();


        tools2.hide();



    }

    public void f2(View v) {

        A_BOLD = "font2.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f3(View v) {

        A_BOLD = "font3.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();




    }

    public void f4(View v) {

        A_BOLD = "font4.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f5(View v) {

        A_BOLD = "font5.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();





    }

    public void f6(View v) {

        A_BOLD = "font6";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f7(View v) {

        A_BOLD = "font7.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();




    }

    public void f8(View v) {


        A_BOLD = "font8.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f9(View v) {

        A_BOLD = "font9.TTF";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();




    }

    public void f10(View v) {

        A_BOLD = "font10.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();




    }

    public void f11(View v) {

        A_BOLD = "font11";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();




    }

    public void f12(View v) {

        A_BOLD = "font12.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f13(View v) {

        A_BOLD = "font13.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();




    }

    public void f14(View v) {

        A_BOLD = "font14.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f15(View v) {

        A_BOLD = "font15.ttf";
        tools2.hide();




    }

    public void f16(View v) {

        A_BOLD = "font16.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f17(View v) {

        A_BOLD = "font17.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();




    }

    public void f18(View v) {

        A_BOLD = "font18.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();



    }

    public void f19(View v) {

        A_BOLD = "font19.ttf";
        //     FontStyles.fontstyles.setText("a");
        tools2.hide();


    }

    public void f20(View v) {

        A_BOLD = "font20.ttf";
        //     FontStyles.fontstyles.setText("a");
        //  fontstylesclass.TextDialog();

        tools2.hide();


    }

    public void  showcolors(View v){

        ColorPickerAdapter.isEnabled = false;

    }

    public void TsnackDialog(String msg,int TMS){
        TSnackbar snackbar = TSnackbar.make(relative_layout_main, msg, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(EditImageActivity.this, R.color.colorSB));
        snackbar.setDuration(TMS);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }



 public void onBackPressed() {



        if(ISGAME == 1) {


            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            SharedPref.write(SharedPref.ISGAME, 0);
        }
        if (mIsFilterVisible) {
            showFilter(false);
            //  mTxtCurrentTool.setText(R.string.app_name);
        } else if (!mPhotoEditor.isCacheEmpty()) {
            showSaveDialog(false,"Are you want to exit without saving image");
        } else {

          finish();

        }
    }


    }















