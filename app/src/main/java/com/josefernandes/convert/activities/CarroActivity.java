package com.josefernandes.convert.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.josefernandes.convert.R;
import com.josefernandes.convert.adapter.CarroAdapter;
import com.josefernandes.convert.classes.Carro;

import java.util.ArrayList;
import java.util.List;

public class CarroActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    //private List<Carro> carros;


    private ListView listViewCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        setTitle("Carros");

        listViewCarros = findViewById(R.id.carro_listview);

        List<Carro> carros = gerarListaDeCarros();

        CarroAdapter adapter = new CarroAdapter(this,  carros);
        listViewCarros.setAdapter(adapter);

    }

    private List<Carro> gerarListaDeCarros(){
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro("Duster", 16.1, 15, 12, 11));
        carros.add(new Carro("Corolla", 6.1, 5, 12.3, 10));
        return carros;
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
