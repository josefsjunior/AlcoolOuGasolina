package com.josefernandes.convert;

import android.widget.TextView;

import static com.josefernandes.convert.ConvertActivityConstantes.ALCOOL;
import static com.josefernandes.convert.ConvertActivityConstantes.ETANOL;
import static com.josefernandes.convert.ConvertActivityConstantes.GASOLINA;

public class CalculadorUtil {

    public static void realizaCalculo(float precoGasolina, float precoAlcool, TextView textoConversao) {
        if (precoGasolina * 0.725 > precoAlcool) {
            montaTexto(textoConversao, ALCOOL);
        } else {
            montaTexto(textoConversao, GASOLINA);
        }
    }

    private static void montaTexto(TextView textoConversao, String combustivel) {
        textoConversao.setText("");
        String texto;
        if (combustivel.equals(GASOLINA)) {
            texto = "Abasteça com " + GASOLINA;
        } else {
            texto = "Abasteça com " + ETANOL;
        }
        textoConversao.setText(texto);
    }
}
