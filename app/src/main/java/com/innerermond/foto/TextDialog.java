package com.innerermond.foto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class TextDialog extends AppCompatActivity {

    EditText fontText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_text_dialog);


        fontText =(EditText)findViewById(R.id.add_text_edit_text);
        fontText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getWindow().getDecorView().getRootView(), "wai", Snackbar.LENGTH_SHORT).show();

            }
        });


    }
}