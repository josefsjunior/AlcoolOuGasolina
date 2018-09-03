package com.josefernandes.convert.utils;

import android.util.Log;

public class Base64 {

    public static String codifica(String email){
        Log.i("EMAILCODIFICA", android.util.Base64.encodeToString(email.getBytes(), android.util.Base64.DEFAULT));
        return android.util.Base64.encodeToString(email.getBytes(), android.util.Base64.DEFAULT);
    }
}
