package com.josefernandes.convert.utils;

public class Base64 {

    public static String codifica(String email){
        return android.util.Base64.encodeToString(email.getBytes(), android.util.Base64.DEFAULT);
    }
}
