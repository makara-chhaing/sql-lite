package com.example.sqllitedatabaseapp;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CreateNoteActivity extends AppCompatActivity {

    EditText inputText;
    SqlDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        inputText = findViewById(R.id.note_et_id);
        db = Util.DATABASE_INSTANCE;
    }

    public void saveNote(View v){
        String text = inputText.getText().toString();
        if (!text.equals("")){
            long value = db.addNote(new Note(text));
            Toast.makeText(this, "Added note content: " + value, Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}