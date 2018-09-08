package com.josefernandes.convert.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.josefernandes.convert.R;

public class FormularioCarroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_carro);

        setTitle("Novo Carro");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_carro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_carro_ok:
                Toast.makeText(this, "Clicou no botao pronto", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CarroActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
