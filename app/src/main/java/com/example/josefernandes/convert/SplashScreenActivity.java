package com.example.josefernandes.convert;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarTelaPrincipal();
            }
        }, 1500);
    }

    private void mostrarTelaPrincipal() {
        Intent intent = new Intent(SplashScreenActivity.this,
                ConvertActivity_.class);
        startActivity(intent);
        finish();
    }

}
