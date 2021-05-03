package com.example.sqllitedatabaseapp;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.DATABASE_INSTANCE =  new SqlDatabaseHelper(this, null);

    }

    public void createNote(View v){
        startActivity(new Intent(MainActivity.this, CreateNoteActivity.class));
    }

    public void showNotes(View v){
        startActivity(new Intent(MainActivity.this, ShowNotesActivity.class));
    }
}