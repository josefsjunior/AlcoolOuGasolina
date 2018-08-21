package com.example.josefernandes.convert;

import android.content.res.Resources;
import android.graphics.Color;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.josefernandes.convert.ConvertActivityConstantes.ALCOOL;
import static com.example.josefernandes.convert.ConvertActivityConstantes.GASOLINA;

public class CalculadorUtil {



    public static void realizaCalculo(float precoGasolina, float precoAlcool, TextView textoConversao, TextView textoPorcentagem) {
        float porcentagemFloat = precoAlcool / precoGasolina;
        porcentagemFloat *= 100;
        int porcentagem = (int) porcentagemFloat;
        textoPorcentagem.setText(String.valueOf(porcentagem) + " %");
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
