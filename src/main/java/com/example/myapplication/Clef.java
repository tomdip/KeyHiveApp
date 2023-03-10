package com.example.myapplication;

public class Clef {


    int id;
    String keyname;
    String emplacement;


    public Clef(int id, String keyname,String emplacement) {
        this.id = id;
        this.keyname = keyname;
        this.emplacement = emplacement;

    }

    public Clef(String keyname,String emplacement) {
        this.keyname = keyname;
        this.emplacement = emplacement;

    }

    public Clef(int anInt, String string) {
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getkeyname() {
        return keyname;
    }



    public String getemplacement() {
        return emplacement;
    }

    public void setemplacement(String emplacement) {
        this.emplacement = emplacement;

    }
}