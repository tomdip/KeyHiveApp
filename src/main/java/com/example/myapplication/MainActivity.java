package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button createProfileButton;
    private Button createKeyButton;
    private Button viewProfileButton;
    private Button searchButton;
    private Button btnConnection;
    private Button searchButtonkey;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createProfileButton = findViewById(R.id.create_profile_button);
        createKeyButton = findViewById(R.id.create_key_button);
        searchButton = findViewById(R.id.search_button);
        btnConnection = findViewById(R.id.btn_connection);
        searchButtonkey = findViewById(R.id.search_button_key);


        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConnectionActivity.class);
                startActivity(intent);
            }
        });

        createProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Createprofil.class);
                startActivity(intent);
            }
        });

        createKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateKey.class);
                startActivity(intent);
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListeProfile.class);
                startActivity(intent);
            }
        });
        searchButtonkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listekey.class);
                startActivity(intent);
            }
        });
    }

}

