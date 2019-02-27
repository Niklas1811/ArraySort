package com.example.wolfi.memoryhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button erstellen;
    Button ueben;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ueben = (Button) findViewById(R.id.ueben);
        ueben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUeben();
            }
        });
        erstellen = (Button) findViewById(R.id.erstellen);
        erstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openErstellen();
            }
        });
    }
    public void openUeben(){
        Intent intent = new Intent (this, Ueben.class);

        startActivity(intent);
    }
    public void openErstellen(){
        Intent intent = new Intent (this, Erstellen.class);
        startActivity(intent);
    }
}
