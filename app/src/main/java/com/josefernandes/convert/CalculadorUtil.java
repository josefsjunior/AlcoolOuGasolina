package com.josefernandes.convert;

import android.widget.TextView;

import static com.josefernandes.convert.ConvertActivityConstantes.ALCOOL;
import static com.josefernandes.convert.ConvertActivityConstantes.GASOLINA;

public class CalculadorUtil {

    public static void realizaCalculo(float precoGasolina, float precoAlcool, TextView textoConversao) {
        float porcentagemFloat = precoAlcool / precoGasolina;
        porcentagemFloat *= 100;
        int porcentagem = (int) porcentagemFloat;
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
            texto = "Abasteça com Gasolina";
        } else {
            texto = "Abasteça com Álcool";
        }
        textoConversao.setText(texto);
    }
}
