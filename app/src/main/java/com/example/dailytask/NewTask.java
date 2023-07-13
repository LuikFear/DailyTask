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
    private List<String> taskList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Task1=findViewById(R.id.Task1);
        Task2=findViewById(R.id.Task2);
        editTextTitle = findViewById(R.id.Task1);
        editTextDescription = findViewById(R.id.Task2);

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        Button addButton = findViewById(R.id.addButton);
        Button LeerBtn = findViewById(R.id.btnLeer);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTitle.getText().toString().trim();
                String des = editTextDescription.getText().toString().trim();
                if (!task.isEmpty()){
                    addItem(task);
                    Toast.makeText(NewTask.this, "la tarea se agrego c:", Toast.LENGTH_SHORT).show();
                    Task1.setText("");
                }else {
                    Toast.makeText(NewTask.this, "Ingrese su tarea", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LeerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //readTask();
            }
        });







    }














    private void addItem(String Task1){

        String task1 = editTextTitle.getText().toString();
        String task2 = editTextDescription.getText().toString();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TASK, task1);
        //Items.item=task1;
        //Items.item2=task2;
        database.insert(DatabaseHelper.TABLE_NAME, null, values);

        values.clear();
        values.put(DatabaseHelper.COLUMN_TASK, task2);

        database.insert(DatabaseHelper.TABLE_NAME, null, values);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        }
    }
