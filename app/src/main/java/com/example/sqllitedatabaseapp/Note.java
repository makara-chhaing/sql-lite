package com.example.sqllitedatabaseapp;

public class Note {
    String note;
    int number;

    public String getNote() {
        return note;
    }

    public Note(String note) {
        this.note = note;
    }

    public int getNumber() {
        return number;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Note(String note, int number) {
        this.note = note;
        this.number = number;
    }
}
