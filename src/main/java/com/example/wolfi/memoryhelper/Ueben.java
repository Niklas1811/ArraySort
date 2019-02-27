package com.example.wolfi.memoryhelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ueben extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ueben);
        db = new DBHelper(this);
        Anzeigen();
    }

    public void Anzeigen(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText("Bitte wählen Sie eine Übung aus");
        linearLayout.addView(textView);
        TextView t = new TextView(this);
        linearLayout.addView(t);
        Cursor cursor = db.getReadableDatabase().rawQuery("Select NameUebung from Uebung", new String[]{});
        int i = 1;
        if (cursor != null){
            cursor.moveToLast();
        }
        do {
            final String name = cursor.getString(0);
            Button b = new Button(this);
            b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            b.setId(i);
            b.setText(name);
            linearLayout.addView(b);
            i++;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Ueben.this, Uebung_leicht.class);
                    intent.putExtra("nameUebung", name );
                    startActivity(intent);
                    //openUebung_leicht();
                }
            });
        }while (cursor.moveToPrevious());
}
    public void openUebung_leicht(){
        Intent intent = new Intent (this, Uebung_leicht.class);
        startActivity(intent);
    }
}
