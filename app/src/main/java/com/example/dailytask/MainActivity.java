package com.example.dailytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    static List<ListItem> itemList = new ArrayList<ListItem>();
    private CustomAdapter customAdapter;
    private  DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.RVall);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        if (!TextUtils.isEmpty(Items.item) && !TextUtils.isEmpty(Items.item2)) {
            itemList.add(new ListItem(Items.item, Items.item2));
        }



        customAdapter = new CustomAdapter(itemList);
        recyclerView.setAdapter(customAdapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        itemAnimator.setChangeDuration(500);
        itemAnimator.setMoveDuration(500);
        recyclerView.setItemAnimator(itemAnimator);



        Button removeButton = findViewById(R.id.removeButton);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem();
            }
        });


        ImageButton buttonPLUS;
        buttonPLUS = findViewById(R.id.imageButton);
        buttonPLUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewTask.class);

                startActivity(intent);
            }
        });




    }


    public void readTask () {
        itemList.clear();
        try {

            String[] col = {DatabaseHelper.COLUMN_TASK, DatabaseHelper.COLUMN_DESCRIPTION};
            Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, col, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String task = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TASK));
                    String des = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
                    //itemList.add(task);
                    //CustomAdapter.notifyItemInserted(itemList.size()-1);
                    Toast.makeText(MainActivity.this, task, Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            } cursor.close();

        }    catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void removeItem(){
        if (!itemList.isEmpty())
        {
            int lastIndex = itemList.size()-1;
            itemList.remove(lastIndex);
            customAdapter.notifyItemRemoved(lastIndex);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent intent = new Intent(getApplicationContext(), NewTask.class);
            startActivity(intent);
        } else if (id == R.id.btnl) {
            taskNew();
        }
        return super.onOptionsItemSelected(item);
    }

    public void taskNew() {
        Intent intent = new Intent(getApplicationContext(), NewTask.class);
        startActivity(intent);
    }
}


