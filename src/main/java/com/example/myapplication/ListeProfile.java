package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.content.Context ;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListeProfile extends AppCompatActivity {

    ListView ls;
    Button back;
    Helper h=new Helper(ListeProfile.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_profile);
        ls=findViewById(R.id.lst);
        back=findViewById(R.id.back);

        back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }));

        Cursor c=h.getAllProfile();
        //fonction pour afficher la liste des profils existants
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(ListeProfile.this,R.layout.item,c,
                new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2),c.getColumnName(3),c.getColumnName(4),c.getColumnName(5)},
                new int[]{R.id._id,R.id.nom,R.id.prenom,R.id.numerocarte,R.id.email,R.id.keyIds},1);
        ls.setAdapter(adapter);

        //fonction pour acceder a la fiche d'un profil quand quelqu'un appuie sur la ligne de ce profil
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView t = view.findViewById(R.id._id);

                Intent x = new Intent(ListeProfile.this,DetailProfile.class);
                x.putExtra("_id",t.getText().toString());
                startActivity(x);

            }
    });


        }

}




