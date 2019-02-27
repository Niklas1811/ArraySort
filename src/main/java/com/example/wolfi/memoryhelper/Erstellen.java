package com.example.wolfi.memoryhelper;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Erstellen extends AppCompatActivity {
    Button best;
    Button bild;
    Button neuBild;
    Button fertig;
    EditText name;
    EditText nameHinzuf;
    private static int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView bildI;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellen);
        best = (Button) findViewById(R.id.best);
        bild = (Button) findViewById(R.id.bildAusw);
        neuBild = (Button) findViewById(R.id.nochBild);
        fertig = (Button) findViewById(R.id.fertig);
        nameHinzuf = (EditText) findViewById(R.id.nameHinzuf);
        bild.setEnabled(false);
        neuBild.setEnabled(false);
        fertig.setEnabled(false);
        name = (EditText) findViewById(R.id.Name);
        bildI = (ImageView) findViewById(R.id.bildI);
        db = new DBHelper(this);

        best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(name) == false){
                    bild.setEnabled(true);
                    neuBild.setEnabled(true);
                    best.setEnabled(false);
                    name.setEnabled(false);
                }
            }
        });
        neuBild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(nameHinzuf) == true){

                }else {
                    db.insertData(name.getText().toString(), imageUri.toString(), nameHinzuf.getText().toString() );
                    nameHinzuf.setText("");
                    fertig.setEnabled(true);

                }
            }
        });

        bild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUeben();
            }
        });

    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            bildI.setImageURI(imageUri);
        }
    }

    public void openUeben(){
        Intent intent = new Intent (this, Ueben.class);

        startActivity(intent);
    }

    public boolean isEmpty (EditText editText){
        String sUsername = editText.getText().toString();
        if (sUsername.matches("")) {
            Toast.makeText(this, "Geben Sie einen Namen an!", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return false;
        }
    }
}
