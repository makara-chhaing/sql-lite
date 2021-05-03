package com.example.sqllitedatabaseapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowNotesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SqlDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        recyclerView = findViewById(R.id.recylcerview_id);
        db = Util.DATABASE_INSTANCE;

        NoteAdapter noteAdapter = new NoteAdapter(this, db);
        recyclerView.setAdapter(noteAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}