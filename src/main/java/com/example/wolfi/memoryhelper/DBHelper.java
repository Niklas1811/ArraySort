package com.example.wolfi.memoryhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String DB_Name = "Memory";
    public static String TB1_Name = "Uebung";
    public static String Column1 = "NameUebung";
    public static String TB2_Name = "Bild";
    public static String Column2 = "id";
    public static String Column3 = "src";
    public static String Column4 = "NamePerson";

    public static final String CREATE_TB1 = "Create table " + TB1_Name + " ( " + Column1 + " varchar(50) primary key);";
    public static final String CREATE_TB2 = "Create table " + TB2_Name + " ( " + Column2 + " Integer primary key autoincrement, "
            + Column3 + " varchar(50) not null, " + Column4 + " varchar(50) not null, "  + Column1 + " varchar(50) not null);";

    public DBHelper(Context context) {
        super(context, DB_Name, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB1);
        db.execSQL(CREATE_TB2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '" + TB1_Name + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TB2_Name+ "'");
        onCreate(db);
    }


    public void insertData(String nameUebung, String src, String namePerson){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues nameVal = new ContentValues();
        nameVal.put(Column1, nameUebung);
        db.insert(TB1_Name, null, nameVal);

        ContentValues values = new ContentValues();
        values.put(Column3, src);
        values.put(Column4, namePerson);
        values.put(Column1, nameUebung);
        long id = db.insertWithOnConflict(TB2_Name, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }
}
