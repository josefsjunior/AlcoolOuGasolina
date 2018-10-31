package com.josefernandes.convert.utils;

import java.text.DecimalFormat;

public class PorcentagemUtil {

    public static String ajustaPorcentagem(String txtPrecoGasolina) {
        DecimalFormat format = new DecimalFormat("#.000");
        float precoGasolina = Float.parseFloat(txtPrecoGasolina);
        double precoGasolinaSetentaPorcento = precoGasolina * 0.725;
        String precoGasolinaSetentaPorcentoTexto = String.valueOf(precoGasolinaSetentaPorcento);
        precoGasolinaSetentaPorcentoTexto.replace(",", ".");
        precoGasolinaSetentaPorcento = Double.parseDouble(precoGasolinaSetentaPorcentoTexto);
        return format.format(precoGasolinaSetentaPorcento);
    }
}
