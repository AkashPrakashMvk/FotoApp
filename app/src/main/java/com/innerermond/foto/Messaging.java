package com.innerermond.foto;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;

public class Messaging extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        Log.d("TAG",s);
        super.onNewToken(s);
        //D/TAG: cufbah0cNOo:APA91bEJiZruLm0hkfRKV5C1Oo92BjLS77X8f5Fhp6CKAGu67O3IgQTbX4f5piFUPx6Eah0q890a3t8zMh1Sfy7oScpLi-H8ftp1E4xAMtONe0_A9zwLn1PkbCKiBmlrC_MtjsX_SIaj
    }
}
