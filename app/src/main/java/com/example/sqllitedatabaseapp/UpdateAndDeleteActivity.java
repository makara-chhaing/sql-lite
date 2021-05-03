package com.example.sqllitedatabaseapp;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UpdateAndDeleteActivity extends AppCompatActivity {
EditText editText;
int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        editText = findViewById(R.id.note_view_et_id);
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("note", "");
        id = bundle.getInt("id", 0);
        if(!text.equals("")){
            editText.setText(text);
        }
    }

    public void update(View v){
        SqlDatabaseHelper db = Util.DATABASE_INSTANCE;
        if(id==0){
            return;
        }
        String newText = editText.getText().toString();
        if(id >0 && !newText.equals("")){
            db.update(id,newText);
            Toast.makeText(this, "Note:" + id +" Updated", Toast.LENGTH_LONG).show();
            NoteAdapter.notesList = db.fetchNote();
            startActivity(new Intent(this, ShowNotesActivity.class));
            finish();
        }

    }


    public void delete(View v){
        SqlDatabaseHelper db = Util.DATABASE_INSTANCE;
        if(id==0){
            return;
        }
        db.delete(id);
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show();
        NoteAdapter.notesList = db.fetchNote();
        startActivity(new Intent(this, ShowNotesActivity.class));
        finish();
    }

}