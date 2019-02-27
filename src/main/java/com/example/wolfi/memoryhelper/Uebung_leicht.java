package com.example.wolfi.memoryhelper;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Uebung_leicht extends AppCompatActivity {
    ImageView imageView;
    Button button;
    Button button2;
    Button button3;
    Cursor cursor;
    DBHelper db;
    String name1 = "Kajetan";
    String name2 = "Joshua";
    String nameUebung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebung_leicht);
        Intent intent = getIntent();
        nameUebung = intent.getStringExtra("nameUebung");
        Toast.makeText(this, nameUebung, Toast.LENGTH_SHORT).show();
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        db = new DBHelper(this);
        cursor = db.getReadableDatabase().rawQuery("select src, namePerson from bild where nameUebung = '"  + nameUebung + "'", new String[]{} );
        start();
    }

    public void start(){
        if (cursor != null){
            cursor.moveToFirst();
            final String src = cursor.getString(0);
            final String namePerson = cursor.getString(1);
            int i = (int) Math.floor(Math.random() * 3) - 1;
            if (i == 0){
                button.setText(namePerson);
                button2.setText(name1);
                button3.setText(name2);
            }else if (i == 1){
                button3.setText(namePerson);
                button.setText(name1);
                button2.setText(name2);
            }else if (i == 2){
                button2.setText(namePerson);
                button3.setText(name1);
                button.setText(name2);
            }



        }
    }
}
