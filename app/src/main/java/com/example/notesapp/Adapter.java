package com.example.notesapp;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Context context;
    ArrayList<Notes> arr;
    DataBaseHelper dataBaseHelper;

    Adapter(Context context, ArrayList<Notes> arr,DataBaseHelper dataBaseHelper) {
        this.context = context;
        this.arr = arr;
        this.dataBaseHelper= dataBaseHelper;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.note_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.texttitle.setText(arr.get(position).getTitle());
        holder.textcontent.setText(arr.get(position).getContent());


        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                deleteItem(holder.getAdapterPosition());


               return  true;
            }
        });
    }



    @Override
    public int getItemCount() {
        return arr.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView texttitle;
        TextView textcontent;
        LinearLayout llrow;

        public ViewHolder(View itemview) {
            super(itemview);
            texttitle = itemview.findViewById(R.id.texttitle);
            textcontent = itemview.findViewById(R.id.textcontent);
            llrow= itemview.findViewById(R.id.llrow);

        }
    }


    public void deleteItem(int adapterPosition) {
        AlertDialog.Builder dialog= new AlertDialog.Builder(context);
             dialog.setTitle("Delete This Note")
                       .setMessage("Are you sure")

        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataBaseHelper.noteDao().deleteNote(new Notes(arr.get(adapterPosition).getId(),
                        arr.get(adapterPosition).getTitle()  ,arr.get(adapterPosition).getContent()));
                ((MainActivity)context).show();
            }
        }).show();
    }
}
