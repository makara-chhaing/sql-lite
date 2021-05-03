package com.example.sqllitedatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqlDatabaseHelper extends SQLiteOpenHelper {
    public SqlDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " (" + Util.NOTE_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.NOTE + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        db.execSQL(DROP_TABLE);

        onCreate(db);
    }

    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE, note.getNote());
        long newRow = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRow;
    }

    public List<Note> fetchNote(){
        List<Note> nodeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,new String[]{Util.NOTE_NUMBER, Util.NOTE}, Util.NOTE,null, null, null, null);
        Cursor c = db.rawQuery("Select " + Util.NOTE_NUMBER +" , " + Util.NOTE + " From " + Util.TABLE_NAME, null);

        if(c.moveToNext()){
            do{
                String number = c.getString(0);
                String note = c.getString(1);
                if(!number.equals("") && !note.equals("")){
                    nodeList.add(new Note(note));
                }
            }while (c.moveToNext());
        }

        cursor.close();
        c.close();
        db.close();
        return nodeList;
    }
}
