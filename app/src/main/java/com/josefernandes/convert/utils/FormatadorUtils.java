package com.josefernandes.convert.utils;

import android.support.annotation.NonNull;

public class FormatadorUtils {

    @NonNull
    public static String formataPrecoAlcool(String precoAlcool) {
        return precoAlcool.replace(".", ",");
    }

    @NonNull
    public static String formataPrecoGasolina(String precoGasolina) {
        return precoGasolina.replace(".", ",");
    }
}
