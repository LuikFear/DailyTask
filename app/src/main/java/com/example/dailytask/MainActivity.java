package com.example.dailytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> datalist;

    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Obtén una referencia a la Toolbar por su ID
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Establece la Toolbar como la barra de acción de la actividad
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.RVall);
        // Resto del código...


        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        datalist = new ArrayList<>();
        datalist.add("Elemento 1");
        datalist.add("Elemento 2");
        datalist.add("Elemento 3");
        datalist.add("Elemento 1");
        datalist.add("Elemento 2");
        datalist.add("Elemento 3");
        datalist.add("Elemento 1");
        datalist.add("Elemento 2");
        datalist.add("Elemento 3");

        customAdapter = new CustomAdapter(datalist, this);
        recyclerView.setAdapter(customAdapter);

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


