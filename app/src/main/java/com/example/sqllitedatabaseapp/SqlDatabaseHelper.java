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
    public SqlDatabaseHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, Util.DATABASE_NAME, factory, Util.DATABASE_VERSION);
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

    public void update(int id, String text){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Util.TABLE_NAME + " SET " +  Util.NOTE + " = '" + text + "' WHERE " + Util.NOTE_NUMBER + " = '" + id + "'";
        db.execSQL(query);

        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + Util.TABLE_NAME + " WHERE " + Util.NOTE_NUMBER + " = '" + id + "'";
        db.execSQL(query);

        db.close();
    }

    public List<Note> fetchNote(){
        List<Note> nodeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select " + Util.NOTE_NUMBER +" , " + Util.NOTE + " From " + Util.TABLE_NAME, null);

        if(c.moveToNext()){
            do{
                int number = c.getInt(0);
                String note = c.getString(1);

                if(number!=0 && !note.equals("")){
                    Note n = new Note(note);
                    n.setNumber(number);
                    nodeList.add(n);
                }
            }while (c.moveToNext());
        }

        c.close();
        db.close();
        return nodeList;
    }
}
