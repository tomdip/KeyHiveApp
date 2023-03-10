package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateKey extends AppCompatActivity {


    EditText keyname, emplacement;
    Button b,back;
    Helper h = new Helper(CreateKey.this);
    @Override
    protected void onCreate(Bundle savedlnstanceState){

        super.onCreate(savedlnstanceState);
        setContentView(R.layout.creationkey1);
        keyname = findViewById(R.id.keyname);
        emplacement=findViewById(R.id.emplacement);
        b=findViewById(R.id.add);
        back= findViewById(R.id.back);


        //Bouton de sauvegarde des clefs
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clef k = new Clef(keyname.getText().toString(),emplacement.getText().toString());

                h.insertKey(k);
                Intent i = new Intent(CreateKey.this,listekey.class);
                startActivity(i);
            }


        });

        //Bouton de retour en arrière
        back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }));
    }
}
