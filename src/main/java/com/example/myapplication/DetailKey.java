package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DetailKey extends AppCompatActivity {


    EditText keyname,emplacement;
    Button mod, sup,back;
    String id;
    Helper h=new Helper( DetailKey.this);

    @Override
    protected void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.detailkey1);

        keyname=findViewById(R.id.keyname);
        emplacement=findViewById(R.id.emplacement);
        mod=findViewById(R.id.mod);
        sup=findViewById(R.id.sup);
        back=findViewById(R.id.back);


        id=getIntent().getStringExtra( "_id");
        Clef k=h.getOneKey(Integer.parseInt(id));

        keyname.setText(k.getkeyname());
        emplacement.setText(k.getemplacement());


        //Bouton de modification des information des clefs
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Clef kr = new Clef(Integer.parseInt(id),keyname.getText().toString(),emplacement.getText().toString());

                h.UpdateKey(kr);
                Intent i=new Intent(DetailKey.this,listekey.class);
                startActivity(i);
            }
        });
        //Bouton de Suppression des information des clefs
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                h.DeleteKey(Integer.parseInt(id));
                Intent i=new Intent(DetailKey.this,listekey.class);
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