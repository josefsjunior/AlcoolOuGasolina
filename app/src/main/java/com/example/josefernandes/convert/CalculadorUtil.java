package com.example.josefernandes.convert;

import android.widget.TextView;

import static com.example.josefernandes.convert.ConvertActivityConstantes.ALCOOL;
import static com.example.josefernandes.convert.ConvertActivityConstantes.GASOLINA;

public class CalculadorUtil {



    public static void realizaCalculo(float precoGasolina, float precoAlcool, TextView textoConversao) {
        if(precoGasolina * 0.7 > precoAlcool){
            montaTexto(textoConversao, ALCOOL);
        } else {
            montaTexto(textoConversao, GASOLINA);
        }
    }

    private static void montaTexto(TextView textoConversao, String combustivel) {
        textoConversao.setText("");
        String texto;
        if(combustivel.equals(GASOLINA)){
            texto = String.valueOf(R.string.abasteca_gasolina);
        } else {
            texto = String.valueOf(R.string.abasteca_alcool);
        }
        textoConversao.setText(texto);
    }
}
