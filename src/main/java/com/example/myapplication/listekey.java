package com.example.myapplication;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.content.Context ;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class listekey extends AppCompatActivity {
    ListView ls;

    Helper h=new Helper(listekey.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_key);
        ls=findViewById(R.id.lsk);

        Cursor d=h.getAllKey();

        //fonction pour afficher la liste des clef existante
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(listekey.this, R.layout.item_key, d,
                new String[]{"_id", "keyname", "emplacement"},
                new int[]{R.id._id, R.id.keyname, R.id.emplacement}, 1);
        ls.setAdapter(adapter);


        //fonction pour acceder a la fiche de la clef quand quelqu'un appuie sur la ligne de la clef
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView t = view.findViewById(R.id._id);

                Intent x = new Intent(listekey.this,DetailKey.class);
                x.putExtra("_id",t.getText().toString());
                startActivity(x);

            }
        });
    }
}




