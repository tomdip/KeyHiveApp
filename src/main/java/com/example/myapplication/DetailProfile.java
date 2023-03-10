package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailProfile extends AppCompatActivity {


    EditText nom,prenom,numerocarte,email;
    Button mod, sup,back ,clef,nfcButton;
    String id,keyIds;
    NfcAdapter nfcAdapter;
    String tagSerial;
    Helper h=new Helper( DetailProfile.this);

    @Override
    protected void onResume() {
        super.onResume();
        // Vérifie si l'application est enregistrée pour les tags NFC
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter[] intentFilters = new IntentFilter[]{new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Obtention du tag NFC
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        // Obtention du numéro de série du tag
        byte[] tagId = tag.getId();
        tagSerial = byteArrayToHexString(tagId);
        // Affichage du numéro de série
        Toast.makeText(this, "Numéro de série du tag : " + tagSerial, Toast.LENGTH_SHORT).show();
    }

    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


        @Override
        protected void onCreate(Bundle savedlnstanceState) {
            super.onCreate(savedlnstanceState);
            setContentView(R.layout.detailprofil1);

            nom=findViewById(R.id.nom);
            prenom=findViewById(R.id.prenom);
            email=findViewById(R.id.email);
            mod=findViewById(R.id.mod);
            sup=findViewById(R.id.sup);
            back=findViewById(R.id.back);
            clef= findViewById(R.id.add_key_button2);

            id=getIntent().getStringExtra( "_id");
            Profile p=h.getOneProfile(Integer.parseInt(id));

            nom.setText(p.getnom());
            prenom.setText(p.getprenom());
            numerocarte.setText(p.getNumerocarte());
            email.setText(p.getEmail());

            //Définir le clic du bouton NFC
        nfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailProfile.this);
                builder.setTitle("Scanner une carte NFC");
                builder.setMessage("Veuillez placer la carte NFC près du téléphone pour la numériser.");
                builder.setPositiveButton("Scanner", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Démarrer la détection NFC
                        nfcAdapter.enableForegroundDispatch(DetailProfile.this, PendingIntent.getActivity(
                                DetailProfile.this, 0, new Intent(DetailProfile.this, DetailProfile.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0), null, null);
                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();


            }

        });

        //bouton pour modifier l'attribution des clefs
        clef.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                Cursor keyCursor = h.getAllKey();

                String[] keyNames = new String[keyCursor.getCount()];
                final boolean[] keySelected = new boolean[keyCursor.getCount()];
                int i = 0;
                while (keyCursor.moveToNext()) {
                    keyNames[i] = keyCursor.getString(keyCursor.getColumnIndex("keyname"));
                    keySelected[i] = false;
                    i++;
                }
                keyCursor.close();
                // Affichage de la liste des clés
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailProfile.this);
                builder.setTitle("Sélectionner les clés à attribuer");
                builder.setMultiChoiceItems(keyNames, keySelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        keySelected[which] = isChecked;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void
                    onClick(DialogInterface dialog, int which) {
                        // Création du profil avec les clés sélectionnées
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < keySelected.length; i++) {
                            if (keySelected[i]) {
                                sb.append(i + ",");
                            }
                        }
                        String keyIds = sb.toString();
                        if (keyIds.endsWith(",")) {
                            keyIds = keyIds.substring(0, keyIds.length() - 1); // Enlever la dernière virgule
                        }
                    }
                });
                builder.setNegativeButton("Annuler", null);
                builder.show();
            }
        });

        //Bouton de modification des information des clefs
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile pr = new Profile(Integer.parseInt(id),nom.getText().toString(),prenom.getText().toString(),numerocarte.getText().toString(),email.getText().toString(),keyIds);

                h.UpdateProfile(pr);
                Intent i=new Intent(DetailProfile.this,ListeProfile.class);
                startActivity(i);
            }
        });
        //Bouton de suppression des information des clefs
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h.DeleteProfile(Integer.parseInt(id));
                Intent i=new Intent(DetailProfile.this,ListeProfile.class);
                startActivity(i);
            }
        });

        //Boutton de retour en arrière
        back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    onBackPressed();
                }
        }));
    }
}