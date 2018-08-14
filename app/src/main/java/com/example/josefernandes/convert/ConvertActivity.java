package com.example.josefernandes.convert;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;

import static com.example.josefernandes.convert.ConvertActivityConstantes.ALCOOL;
import static com.example.josefernandes.convert.ConvertActivityConstantes.GASOLINA;

@EActivity(R.layout.activity_convert)
public class ConvertActivity extends Activity {

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
    TextView convert_text_after_button;

    @Override
    protected void onStart() {
        super.onStart();
        convert_gasolina.setText(GASOLINA);
        convert_alcool.setText(ALCOOL);
    }

    @Click
    public void convert_button(){
        if( verificaValorEmBranco() ){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else if( verificaValorNegativo() ){
            Toast.makeText(this, "Preços não podem ser negativos!", Toast.LENGTH_SHORT).show();
        } else {
            float precoGasolina = Float.parseFloat(convert_value_gasolina.getText().toString());
            float precoAlcool = Float.parseFloat(convert_value_alcool.getText().toString());
            CalculadorUtil.realizaCalculo(precoGasolina, precoAlcool, convert_text_after_button);
        }
    }

    private boolean verificaValorNegativo() {
        return Float.parseFloat(convert_value_gasolina.getText().toString()) < 0.0 || Float.parseFloat(convert_value_alcool.getText().toString()) < 0.0;
    }

    private boolean verificaValorEmBranco() {
        return convert_value_gasolina.getText().toString().isEmpty() || convert_value_alcool.getText().toString().isEmpty();
    }

}
