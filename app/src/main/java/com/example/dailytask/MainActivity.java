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
        datalist.add("Elemento 4");
        datalist.add("Elemento 5");
        datalist.add("Elemento 6");


        customAdapter = new CustomAdapter(datalist, this);
        recyclerView.setAdapter(customAdapter);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        int id = item.getItemId();

        if  ( id== R.id.item1){
            Intent intent = new Intent(getApplicationContext(), MainActivity_empty.class);
            startActivity(intent);
        } else if (id==R.id.btnl) {
            abrirMapa();

        }
        return super.onOptionsItemSelected(item);



        public void  abrirMapa(){

        double latitude =14.656294;
        double longitud = -90.460636;

        String label = "micasita";
        String uriBegin = "geo:"+ latitude+","+longitud;
        String query = latitude + ","+longitud+"("+ label+")";
        String encodedQuery= Uri.encode(query);
        String uriString = uriBegin+"?q=" + encodedQuery+"&z=16";
        Uri uri = Uri.parse(uriString);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);


    }
}


