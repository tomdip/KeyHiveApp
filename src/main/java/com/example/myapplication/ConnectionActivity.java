package com.example.myapplication;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConnectionActivity extends AppCompatActivity {


    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connection);
        back=findViewById(R.id.back);
    }


    public void click(View view) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID(); // récupère le SSID du wifi auxquel le télephone est connecté
        ssid = ssid.replaceAll("[\"]", "");
        String bssid = "KeyHive";//On vérifie que le télephone est bien connecté au bon WiFi


        //Bouton permettant d'aller aux paramètre de connection
        Toast.makeText(this, "Connection", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Connection");
        builder.setMessage("voulez vous vous connectez?");
        builder.setCancelable(true);
        builder.setNeutralButton("Annuler", null);
        builder.setPositiveButton("paramètre WiFi", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                startActivity(intent);
            }
        });
        builder.show();

    }
    //Bouton de vérification
    public void click2(View view){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        ssid = ssid.replaceAll("[\"]", "");
        String bssid = "keyhive";



        if (null != networkInfo) {

            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Retour a la page d'accueuil", Toast.LENGTH_SHORT).show();

            } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI ) {

                if (ssid.equals(bssid)){
                    Toast.makeText(this, "Vous êtes connécté au bon WiFi" + bssid, Toast.LENGTH_SHORT).show();
                }
                else if (!ssid.equals(bssid)){
                    Toast.makeText(this, "Mauvais wifi " + ssid +bssid, Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("nouvel tentative");
                    builder.setMessage("voulez vous vous connectez?");
                    builder.setCancelable(true);
                    builder.setNeutralButton("Annuler", null);
                    builder.setPositiveButton("paramètre WiFi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                            startActivity(intent);
                        }
                    });
                    builder.show();


                }
            }

        }

        back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }));

    }
}


