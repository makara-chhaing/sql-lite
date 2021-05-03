package com.example.sqllitedatabaseapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteAdapterViewHolder> {

    Context context;
    SqlDatabaseHelper db;
    public static List<Note> notesList;

    public NoteAdapter(Context context, SqlDatabaseHelper db) {
        this.context = context;
        this.db = db;
        notesList = db.fetchNote();
    }

    @NonNull
    @Override
    public NoteAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview, parent, false);
        return new NoteAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapterViewHolder holder, int position) {

//        holder.text.setText(notesList.get(position).getNote());
        holder.text.setText("Note: " + notesList.get(position).getNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateAndDeleteActivity.class);
                intent.putExtra("note", notesList.get(position).getNote());
                intent.putExtra("id", notesList.get(position).getNumber());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class NoteAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public NoteAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.note_element_id);
        }
    }
}
