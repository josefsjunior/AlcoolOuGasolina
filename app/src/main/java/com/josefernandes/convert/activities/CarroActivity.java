package com.josefernandes.convert.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.josefernandes.convert.R;
import com.josefernandes.convert.classes.Carro;
import com.josefernandes.convert.config.ConfiguracaoFirebase;

import java.util.List;

public class CarroActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private List<Carro> carros = null;

    private TextView txtGasolinaCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        setTitle("Carros");
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
