package com.josefernandes.convert.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.josefernandes.convert.R;
import com.josefernandes.convert.classes.Carro;
import com.josefernandes.convert.config.ConfiguracaoFirebase;
import com.josefernandes.convert.repository.CarroRepository;

public class FormularioCarroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtGasolinaCidade;
    private EditText edtGasolinaEstrada;
    private EditText edtEtanolCidade;
    private EditText edtEtanolEstrada;

    private DatabaseReference referenciaDatabase;
    private Carro carro;
    private CarroRepository carroRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_carro);

        setTitle("Novo Carro");

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        edtNome = findViewById(R.id.formulario_carro_edittext_nome);
        edtGasolinaCidade = findViewById(R.id.formulario_carro_edittext_gasolina_cidade);
        edtGasolinaEstrada = findViewById(R.id.formulario_carro_edittext_gasolina_estrada);
        edtEtanolCidade = findViewById(R.id.formulario_carro_edittext_etanol_cidade);
        edtEtanolEstrada = findViewById(R.id.formulario_carro_edittext_etanol_estrada);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_carro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_carro_ok:
                if (!preencheuFormulario()) {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    referenciaDatabase = ConfiguracaoFirebase.getFirebase();
                    referenciaDatabase.child(FirebaseAuth.getInstance().getUid()).child("carros").setValue(carro);
                    carroRepository = new CarroRepository();
                    carroRepository.salvar(carro);
                    startActivity(new Intent(this, CarroActivity.class));
                    Toast.makeText(this, "Carro " + edtNome.getText().toString() + " adicionado", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean preencheuFormulario() {
        if (edtNome.getText().toString().isEmpty() || edtGasolinaCidade.getText().toString().isEmpty()
                || edtGasolinaEstrada.getText().toString().isEmpty() || edtEtanolCidade.getText().toString().isEmpty()
                || edtEtanolEstrada.getText().toString().isEmpty()) {
            return false;
        } else {
            carro = new Carro(edtNome.getText().toString().trim(),
                    Double.parseDouble(edtGasolinaCidade.getText().toString().trim()),
                    Double.parseDouble(edtGasolinaEstrada.getText().toString().trim()),
                    Double.parseDouble(edtEtanolCidade.getText().toString().trim()),
                    Double.parseDouble(edtEtanolEstrada.getText().toString().trim()));
            return true;
        }
    }
}
