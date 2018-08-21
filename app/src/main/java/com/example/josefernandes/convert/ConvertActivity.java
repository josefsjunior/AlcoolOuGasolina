package com.example.josefernandes.convert;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.example.josefernandes.convert.ConvertActivityConstantes.ALCOOL;
import static com.example.josefernandes.convert.ConvertActivityConstantes.GASOLINA;
import static com.example.josefernandes.convert.ConvertActivityConstantes.ZERO;

@EActivity(R.layout.activity_convert)
public class ConvertActivity extends Activity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;

    @ViewById
    AdView adView;
    @ViewById
    TextView convert_gasolina;
    @ViewById
    TextView convert_alcool;
    @ViewById
    EditText convert_value_gasolina;
    @ViewById
    EditText convert_value_alcool;
    @ViewById
    Button convert_button;
    @ViewById
    TextView convert_text_porcentagem;
    @ViewById
    TextView convert_text_after_button;

    @Override
    protected void onStart() {
        super.onStart();
        inserirTextoCombustiveis();
        inicializarAnalytics();
        inicializarAnuncios();
    }

    private void inicializarAnuncios() {
        MobileAds.initialize(this, getString(R.string.id_banner_admob));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void inicializarAnalytics() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    private void inserirTextoCombustiveis() {
        convert_gasolina.setText(GASOLINA);
        convert_alcool.setText(ALCOOL);
    }

    @Click
    public void convert_button(){
        if( verificaValorEmBranco() ){
            Toast.makeText(this, R.string.preencha_todos_os_campos, Toast.LENGTH_SHORT).show();
        } else if(verificaValorNegativo() || verificaValorInvalido()){
            Toast.makeText(this, R.string.valores_invalidos, Toast.LENGTH_SHORT).show();
        } else {
            float precoGasolina = Float.parseFloat(convert_value_gasolina.getText().toString());
            float precoAlcool = Float.parseFloat(convert_value_alcool.getText().toString());
            CalculadorUtil.realizaCalculo(precoGasolina, precoAlcool, convert_text_after_button, convert_text_porcentagem);
        }
    }

    private boolean verificaValorInvalido() {
        return convert_value_gasolina.getText().toString().startsWith(".") || convert_value_alcool.getText().toString().startsWith(".");
    }

    private boolean verificaValorNegativo() {
        return Float.parseFloat(convert_value_gasolina.getText().toString()) < ZERO || Float.parseFloat(convert_value_alcool.getText().toString()) < ZERO;
    }

    private boolean verificaValorEmBranco() {
        return convert_value_gasolina.getText().toString().isEmpty() || convert_value_alcool.getText().toString().isEmpty();
    }

}
