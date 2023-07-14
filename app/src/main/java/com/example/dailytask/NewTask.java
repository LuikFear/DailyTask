package com.example.dailytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewTask extends AppCompatActivity {
    public interface NewItemListener {
        void onNewItemAdded(String task1, String task2);
    }
    private NewItemListener newItemListener;
    public void setNewItemListener(NewItemListener listener) {
        this.newItemListener = listener;
    }
    private EditText editTextTitle;
    private EditText editTextDescription;
    private  DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private EditText Task1;
    private EditText Task2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Task1=findViewById(R.id.Task1);
        Task2=findViewById(R.id.Task2);


        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }});
    }


        private void saveTask() {
            String task = Task1.getText().toString().trim();
            String description = Task2.getText().toString().trim();

            if (!task.isEmpty()) {
                long newRowId = databaseHelper.insertTask(task, description);

                if (newRowId != -1) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Escribe Alguna tarea c:", Toast.LENGTH_SHORT).show();
            }
        }
    }















