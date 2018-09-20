package com.josefernandes.convert.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.josefernandes.convert.R;
import com.josefernandes.convert.adapter.ListaCarrosAdapter;
import com.josefernandes.convert.classes.Carro;

import java.util.ArrayList;
import java.util.List;

public class CarroActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private List<Carro> carros;

    private TextView txtGasolinaCidade;
    private ListaCarrosAdapter adapter;
    private ListView listViewCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        setTitle("Carros");

        listViewCarros = findViewById(R.id.carro_listview);

        Carro carro = new Carro("Duster", 16.1, 15, 12, 11);
        carros = new ArrayList<>();
        carros.add(carro);

        adapter = new ListaCarrosAdapter(this,  carros);
        listViewCarros.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_carros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_carro_novo:
                Toast.makeText(this, "Clicou para adiconar um novo carro", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, FormularioCarroActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
