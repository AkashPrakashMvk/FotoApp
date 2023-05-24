package com.innerermond.foto;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.innerermond.foto.base.BaseActivity;

import java.util.Locale;

public class About extends BaseActivity {

    LinearLayout FOINSTA;
    LinearLayout FOTWITTER;
    LinearLayout FOFB;
    LinearLayout UNKNOWN;
    LinearLayout PP;
    LinearLayout TOU;
    LinearLayout CS;
    LinearLayout RU;
    LinearLayout WAR;
    LinearLayout VC;
    ImageView ia;


    //15-01-2021
    String FOINSTAU;
    String RUAR;
    String share;
    String PPU;
    String TOUU;
    String CSU;
    String RUU;
    String WARU;
    String versionName;
    String deviceModel;
    String osVersion;
    String locale;
    String countryCodeValue;
    String DeviceLang;
    String  LocaleCountry;

    String VersionCheck;

    LinearLayout aboutshare;

    FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        setContentView(R.layout.activity_about);

        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String packageName = info.packageName;
            int versionCode = info.versionCode;
            versionName = info.versionName;
            deviceModel = android.os.Build.MODEL;
            osVersion = android.os.Build.VERSION.RELEASE;
            locale = getResources().getConfiguration().locale.getCountry();
            TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            countryCodeValue = tm.getNetworkCountryIso();
           DeviceLang =  Locale.getDefault().getDisplayLanguage();
           LocaleCountry = Locale.getDefault().getDisplayCountry();
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
        }


       aboutshare  = (LinearLayout) findViewById(R.id.shareabout);
        FOINSTA  = (LinearLayout) findViewById(R.id.foinsta);
        PP  = (LinearLayout) findViewById(R.id.pp);
        TOU  = (LinearLayout) findViewById(R.id.tou);
        CS  = (LinearLayout) findViewById(R.id.cs);
        RU  = (LinearLayout) findViewById(R.id.ru);
        WAR  = (LinearLayout) findViewById(R.id.war);
        VC  = (LinearLayout) findViewById(R.id.versioncheck);


        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        VersionCheck = "this is the latest version";




        // cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
// will use fetch data from the Remote Config service, rather than cached parameter values,
// if cached parameter values are more than cacheExpiration seconds old.
// See Best Practices in the README for more information.

        //remote config
        long cacheExpiration = 0;
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(About.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(About.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();

                            mFirebaseRemoteConfig.activate();
                        } else {

                        }
                        displayWelcomeMessage();
                    }

                    private void displayWelcomeMessage() {
                        VersionCheck = mFirebaseRemoteConfig.getString("versioncheck");
                        FOINSTAU  = mFirebaseRemoteConfig.getString("instagramurl");;
                        WARU = mFirebaseRemoteConfig.getString("wrateareview");
                        share = mFirebaseRemoteConfig.getString("share");
                        RUAR = mFirebaseRemoteConfig.getString("rateus");;


//
                    }

                });


        VC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(VersionCheck.isEmpty())
                {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "This is the current version", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    Snackbar.make(getWindow().getDecorView().getRootView(), VersionCheck, Snackbar.LENGTH_SHORT).show();
                }
            }
        });


//        tr = (ImageView)findViewById(R.id.twitter);
//        ia = (ImageView)findViewById(R.id.instgram);
//        ye = (ImageView)findViewById(R.id.youtube);
//
//
//         //instagram
        FOINSTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(FOINSTAU.isEmpty())
                {
                    Uri uri = Uri.parse("https://www.instagram.com/innerermond");
                    Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

                }
                else
                {
                    Uri uri = Uri.parse(FOINSTAU);
                    Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

                }

            }
        });



        //rate us
        RU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(RUAR.isEmpty()) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.innerermond.foto");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                }
                else
                {
                    Uri uri = Uri.parse(RUAR);
                    Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

                }
            }
        });

//        //write a review
        WAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(WARU.isEmpty()) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.innerermond.foto");
                    Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

                }
                else
                {
                    Uri uri = Uri.parse(WARU);
                    Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

                }
            }



        });
//

//
////        //twitter
//        FOTWITTER.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse(FOTWITTERU);
//                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);
//            }
//        });
////
////
////
////        //facebook
//        FOFB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse(FOFBU);
//                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);
//            }
//        });
////
////
////
//       /*unknown
//        ye.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse("https://bit.ly/3mF0Zo");
//                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);
//            }
//        });*/
//
//
//       //privacy policy
        PP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

            }
        });
//
////        //terms of use
        TOU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TermsOfUse.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

            }
        });
////
////
////
////        //contact us
        CS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"innerermond.contact@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Foto User Feedback");
                intent.putExtra(Intent.EXTRA_TEXT,"App Version: "+versionName+"\nDevice Model: "+deviceModel+"\nOS Version: "+osVersion+"\nCountry Code: "+countryCodeValue +"\nLanguage: "+DeviceLang+"\n\nTell us a bit more about the issue you encountered"+"\n\n\nहमें आपके द्वारा सामना की गई समस्या के बारे में थोड़ा और बताएं।");
                intent.setPackage("com.google.android.gm");
                if (intent.resolveActivity(getPackageManager())!=null)
                    startActivity(intent);
                else
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Failed", Snackbar.LENGTH_SHORT).show();
            }
        });
////
////
////

//
        aboutshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(share.isEmpty()){
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Try my favorite photo editor and logo maker Download:\nhttps://play.google.com/store/apps/details?id=com.innerermond.foto";
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Foto Photo editor and logo maker");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
                else
                {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Try my favorite photo editor and logo maker Download:\n"+share;
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Foto Photo editor and logo maker");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }

            }
        });
//
//
//
//



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}