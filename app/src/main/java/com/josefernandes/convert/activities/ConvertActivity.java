package com.josefernandes.convert.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.josefernandes.convert.R;
import com.josefernandes.convert.utils.CalculadorUtil;

import java.text.DecimalFormat;

import static com.josefernandes.convert.activities.ConvertActivityConstantes.ETANOL;
import static com.josefernandes.convert.activities.ConvertActivityConstantes.GASOLINA;
import static com.josefernandes.convert.activities.ConvertActivityConstantes.ZERO;

public class ConvertActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AdView mAdView;

    private AdView adView;
    private TextView txtGasolina;
    private TextView txtEtanol;
    private EditText edtPrecoGasolina;
    private EditText edtPrecoEtanol;
    private Button btnConverter;
    private TextView txtPorque;
    private TextView txtConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAuth = FirebaseAuth.getInstance();
        Log.i("EMAIL", mAuth.getCurrentUser().getEmail());
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(ConvertActivity.this, LoginActivity.class));
                }
            }
        };

        inicializarComponentes();
        inserirTextoCombustiveis();
        inicializarAnalytics();
        inicializarAnuncios();

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motraTextoConversao();
            }
        });

        txtPorque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostraCaixaDeDialogo();
            }
        });
    }

    private void sairDoApp() {
        mAuth.getInstance().signOut();
        startActivity(new Intent(ConvertActivity.this, LoginActivity.class));
    }

    private void inicializarComponentes() {
        adView = findViewById(R.id.adView);
        txtGasolina = findViewById(R.id.convert_gasolina);
        txtEtanol = findViewById(R.id.convert_alcool);
        edtPrecoGasolina = findViewById(R.id.convert_value_gasolina);
        edtPrecoEtanol = findViewById(R.id.convert_value_alcool);
        btnConverter = findViewById(R.id.convert_button);
        txtPorque = findViewById(R.id.convert_alertdialog_porque);
        txtConverter = findViewById(R.id.convert_text_after_button);
    }

    private void motraTextoConversao() {
        hideSoftKeyboard();
        if (verificaValorEmBranco()) {
            Toast.makeText(ConvertActivity.this, R.string.preencha_todos_os_campos, Toast.LENGTH_SHORT).show();
        } else if (verificaValorNegativo() || verificaValorInvalido()) {
            Toast.makeText(ConvertActivity.this, R.string.valores_invalidos, Toast.LENGTH_SHORT).show();
        } else {
            float precoGasolina = Float.parseFloat(edtPrecoGasolina.getText().toString());
            float precoAlcool = Float.parseFloat(edtPrecoEtanol.getText().toString());
            CalculadorUtil.realizaCalculo(precoGasolina, precoAlcool, txtConverter);
            txtPorque.setVisibility(View.VISIBLE);
        }
    }

    private void mostraCaixaDeDialogo() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ConvertActivity.this);
        builder.setIcon(android.R.drawable.ic_menu_help);
        builder.setTitle(R.string.porque);
        String mensagem = "De acordo com lei 13.033/14, fixou-se em 27,5% o percentual de etanol na gasolina." +
                "\nEntão para compensar, o preço de 72,5% do litro da gasolina tem que ser inferior ao preço do litro do etanol." +
                "\nSeus cálculos:\n" +
                "Gasolina: R$ " + formataPrecoGasolina(edtPrecoGasolina.getText().toString()) +
                "\nEtanol: R$ " + formataPrecoAlcool(edtPrecoEtanol.getText().toString()) +
                "\n72,5% de 1L de gasolina: R$ " + ajustaPorcentagem();
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    private void mostraSairDoApp() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ConvertActivity.this);
        builder.setIcon(android.R.drawable.ic_menu_help);
        builder.setTitle("SAIR");
        String mensagem = "Tem certeza que deseja sair do app?";
        builder.setMessage(mensagem);
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sairDoApp();
            }
        }).setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_carro:
                startActivity(new Intent(ConvertActivity.this, CarroActivity.class));
                break;
            case R.id.menu_posto:
                Toast.makeText(this, "Clicou no botão conversão", Toast.LENGTH_SHORT).show();
                //vaiParaTelaDePosto
                break;
            case R.id.menu_sair:
                mostraSairDoApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializarAnuncios() {
        MobileAds.initialize(this, getString(R.string.id_banner_admob));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void inicializarAnalytics() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    private void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void inserirTextoCombustiveis() {
        txtGasolina.setText(GASOLINA);
        txtEtanol.setText(ETANOL);
    }

    @NonNull
    private String formataPrecoAlcool(String precoAlcool) {
        return precoAlcool.replace(".", ",");
    }

    @NonNull
    private String formataPrecoGasolina(String precoGasolina) {
        return precoGasolina.replace(".", ",");
    }

    private String ajustaPorcentagem() {
        DecimalFormat format = new DecimalFormat("#.000");
        float precoGasolina = Float.parseFloat(edtPrecoGasolina.getText().toString());
        double precoGasolinaSetentaPorcento = precoGasolina * 0.725;
        String precoGasolinaSetentaPorcentoTexto = String.valueOf(precoGasolinaSetentaPorcento);
        precoGasolinaSetentaPorcentoTexto.replace(",", ".");
        precoGasolinaSetentaPorcento = Double.parseDouble(precoGasolinaSetentaPorcentoTexto);
        return format.format(precoGasolinaSetentaPorcento);
    }

    private boolean verificaValorInvalido() {
        return edtPrecoGasolina.getText().toString().startsWith(".") || edtPrecoEtanol.getText().toString().startsWith(".");
    }

    private boolean verificaValorNegativo() {
        return Float.parseFloat(edtPrecoGasolina.getText().toString()) < ZERO || Float.parseFloat(edtPrecoEtanol.getText().toString()) < ZERO;
    }

    private boolean verificaValorEmBranco() {
        return edtPrecoGasolina.getText().toString().isEmpty() || edtPrecoEtanol.getText().toString().isEmpty();
    }
}
