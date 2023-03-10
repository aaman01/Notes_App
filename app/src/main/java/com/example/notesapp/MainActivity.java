package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvnotes;
    FloatingActionButton fbtn;
    Button btn;
    DataBaseHelper dataBaseHelper;
     LinearLayout LL;

    ArrayList<Notes>arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize_Value();
        show();

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialogbox);
                EditText title = dialog.findViewById(R.id.title);
                EditText notes = dialog.findViewById(R.id.note);
                Button addbtn = dialog.findViewById(R.id.addbtn);

                addbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Title = title.getText().toString();
                        String Notes = notes.getText().toString();


                        if (!Notes.equals("")) {
                            if(Title.equals("")){ // if title is null put context first word
                              Title=Notes.substring(0,1);
                            }
                            dataBaseHelper.noteDao().addNotes(new Notes(Title,Notes));
                            show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Something ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbtn.performClick(); // work done when pressed fbtn same will be done here

            }
        });


    }

    public void show() {

        arr= (ArrayList<Notes>) dataBaseHelper.noteDao().getNotes();

        if(arr.size()>0){
            rvnotes.setVisibility(View.VISIBLE);
            LL.setVisibility(View.INVISIBLE);
            rvnotes.setAdapter( new Adapter(this,arr,dataBaseHelper)); // for managing db in adapter (for delete purpose)


        }else{
            rvnotes.setVisibility(View.INVISIBLE);
            LL.setVisibility(View.VISIBLE);
        }
    }

    private void Initialize_Value() {
        rvnotes = findViewById(R.id.rvnotes);
        fbtn = findViewById(R.id.fbadd);
        btn = findViewById(R.id.btn);

        rvnotes.setLayoutManager(new GridLayoutManager(this, 2));//no. of columns -2
        dataBaseHelper=DataBaseHelper.getInstance(this);
        LL=findViewById(R.id.llnotes);
    }
}