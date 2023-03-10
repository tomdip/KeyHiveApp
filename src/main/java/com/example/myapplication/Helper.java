package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context ;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper{

    public Helper(@Nullable Context context){
        super(context,"keyhive", null,1 );
    }

    //Création des TABLES dans la base de donnée
    @Override
    public void onCreate(SQLiteDatabase db ) {

        db.execSQL("CREATE TABLE PROFILE ( _id INTEGER PRIMARY KEY,prenom TEXT,nom TEXT,numerocarte TEXT,email TEXT,keyIds TEXT )");
        db.execSQL("CREATE TABLE  CLEF( _id INTEGER PRIMARY KEY,keyname TEXT,emplacement TEXT)");
    }

    //Verification de la présence de base de donnée pour les mettre a jours si besoin
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il){

        db.execSQL("DROP TABLE IF EXISTS PROFILE");
        db.execSQL("DROP TABLE IF EXISTS CLEF");
        onCreate(db);
    }

    //Fonction pour insérer un nouveau profil dans la bdd
    public void insertProfile (Profile p)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nom",p.getnom());
        cv.put("prenom",p.getprenom());
        cv.put("numerocarte",p.getNumerocarte());
        cv.put("email",p.getEmail());
        cv.put("keyIds",p.getkeyIds());


        db.insert("PROFILE",null,cv);
        db.close();

    }
    //Fonction pour insérer une nouvelle clef dans la bdd
    public void insertKey(Clef k)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cvk = new ContentValues();
        cvk.put("keyname",k.getkeyname());
        cvk.put("emplacement",k.getemplacement());


        db.insert("CLEF",null,cvk);
        db.close();

    }
    //Fonction pour modifer les informations d'un profil dans la bdd
    public void UpdateProfile (Profile p )
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("nom",p.getnom());
        cv.put("prenom",p.getprenom());
        cv.put("numerocarte",p.getNumerocarte());
        cv.put("email",p.getEmail());
        cv.put("keyIds",p.getkeyIds());


        db.update("PROFILE",cv,"_id=?",new String[]{String.valueOf(p.getId())});
        db.close();

    }
    //Fonction pour modifer les informations d'une clef dans la bdd
    public void UpdateKey (Clef k  )
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cvk=new ContentValues();
        cvk.put("keyname",k.getkeyname());
        cvk.put("emplacement",k.getemplacement());

        db.update("CLEF",cvk,"_id=?",new String[]{String.valueOf(k.getId())});
        db.close();

    }
    //fonction pour supprimer un profil
    public void DeleteProfile (int id )
    {

        SQLiteDatabase db=this.getWritableDatabase();

        db.delete("PROFILE","_id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    //fonction pour supprimer une clef
    public void DeleteKey (int id )
    {

        SQLiteDatabase db=this.getWritableDatabase();

        db.delete("CLEF","_id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    //fonction permettant de récuperer tous les profils
    public Cursor getAllProfile()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM PROFILE",null);

        return c ;
    }
    //fonction permettant de récuperer toutes les clefs
    public Cursor getAllKey()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor d = db.rawQuery("SELECT * FROM CLEF",null);

        return d ;
    }
    //fonction permettant de récuperer un profil
    public Profile getOneProfile(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c = db.query("PROFILE",new String[]{"_id","nom","prenom","numerocarte","email","keyIds"},
                "_id=?", new String[]{String.valueOf(id)},null,null,null)
                ;
        c.moveToFirst();
        Profile p = new Profile(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
        return p ;


    }
    //fonction permettant de récuperer une clef
    public Clef getOneKey(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor d = db.query("CLEF",new String[]{"_id","keyname","emplacement"},
                "_id=?", new String[]{String.valueOf(id)},null,null,null)
                ;
        d.moveToFirst();
        Clef k = new Clef(d.getInt(0),d.getString(1),d.getString(2));
        return k ;


    }
}
