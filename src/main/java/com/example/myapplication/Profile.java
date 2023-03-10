package com.example.myapplication;

import java.util.ArrayList;

public class Profile {

    int id ;
    String prenom;
    String nom;
    String numerocarte;
    String email;

    String keyIds;
    private ArrayList<Clef> assignedKeys;

    public Profile(int id, String prenom, String nom, String numerocarte, String email, String keyIds) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.numerocarte = numerocarte;
        this.email = email;
        this.keyIds = keyIds;
        this.assignedKeys = new ArrayList<Clef>();
    }

    public Profile(String prenom, String nom, String numerocarte, String email,String keyIds) {
        this.prenom = prenom;
        this.nom = nom;
        this.numerocarte = numerocarte;
        this.email = email;
        this.keyIds = keyIds;
        this.assignedKeys = new ArrayList<Clef>();
    }

    public Profile(int anInt, String string) {
    }

    public void setAssignedKeys(ArrayList<Clef> keys) {
        this.assignedKeys = keys;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getprenom() {
        return prenom;
    }

    public void setprenom(String prenom) {
        this.prenom = prenom;
    }

    public String getnom() {
        return nom;
    }

    public void setLastName(String nom) {
        this.nom = nom;
    }

    public String getNumerocarte() {
        return numerocarte;
    }

    public void setNumerocarte(String numerocarte) {
        this.numerocarte = numerocarte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getkeyIds() {
        return keyIds;
    }

    public void setkeyIds(String keyIds) {
        this.keyIds = keyIds;
    }



    public void removeKey(String keyName) {
        this.assignedKeys.remove(keyName);
    }

}