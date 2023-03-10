package com.example.notesapp;

import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {
    @PrimaryKey(autoGenerate = true)
   private int id;
    @ColumnInfo(name = "Title")
   private  String title;
    @ColumnInfo (name = "Content")
   private  String content;

    public Notes(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    @Ignore
    public Notes(String title, String content){
        this.title=title;
        this.content=content;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
