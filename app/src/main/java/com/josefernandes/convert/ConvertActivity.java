package com.josefernandes.convert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;

import static com.josefernandes.convert.ConvertActivityConstantes.ETANOL;
import static com.josefernandes.convert.ConvertActivityConstantes.GASOLINA;
import static com.josefernandes.convert.ConvertActivityConstantes.ZERO;

@EActivity(R.layout.activity_convert)
public class ConvertActivity extends Activity {

    private float precoGasolina;
    private float precoAlcool;
    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;

    @ViewById
    AdView adView;
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
    TextView convert_alertdialog_porque;
    @ViewById
    TextView convert_text_after_button;
    @ViewById
    TextView convert_text_versao_autor;

    @Override
    protected void onStart() {
        super.onStart();
        inserirTextoCombustiveis();
        inicializarAnalytics();
        inicializarAnuncios();
        mostraVersaoEAutor();
    }

    private void mostraVersaoEAutor() {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            String version = pInfo.versionName;
            //int verCode = pInfo.versionCode;
            String autor = getString(R.string.autor);

            String montaTexto = "v" + version + " - " + autor;
            convert_text_versao_autor.setText(montaTexto);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void inicializarAnuncios() {
        MobileAds.initialize(this, getString(R.string.id_banner_admob));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void inicializarAnalytics() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    private void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void inserirTextoCombustiveis() {
        convert_gasolina.setText(GASOLINA);
        convert_alcool.setText(ETANOL);
    }

    @Click
    public void convert_button() {
        hideSoftKeyboard();
        if (verificaValorEmBranco()) {
            Toast.makeText(this, R.string.preencha_todos_os_campos, Toast.LENGTH_SHORT).show();
        } else if (verificaValorNegativo() || verificaValorInvalido()) {
            Toast.makeText(this, R.string.valores_invalidos, Toast.LENGTH_SHORT).show();
        } else {
            precoGasolina = Float.parseFloat(convert_value_gasolina.getText().toString());
            precoAlcool = Float.parseFloat(convert_value_alcool.getText().toString());
            CalculadorUtil.realizaCalculo(precoGasolina, precoAlcool, convert_text_after_button);
            convert_alertdialog_porque.setVisibility(View.VISIBLE);
        }
    }

    @Click
    public void convert_alertdialog_porque() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_menu_help);
        builder.setTitle(R.string.porque);
        String mensagem = "De acordo com lei 13.033/14, fixou-se em 27,5% o percentual de etanol na gasolina." +
                "\nEntão para compensar, o preço de 72,5% do litro da gasolina tem que ser inferior ao preço do litro do etanol." +
                "\nSeus cálculos:\n" +
                "Gasolina: R$ " + formataPrecoGasolina(convert_value_gasolina.getText().toString()) +
                "\nEtanol: R$ " + formataPrecoAlcool(convert_value_alcool.getText().toString()) +
                "\n72,5% de 1L de gasolina: R$ " + ajustaPorcentagem();
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    @NonNull
    private String formataPrecoAlcool(String precoAlcool) {
        return precoAlcool.replace(".", ",");
    }

    @NonNull
    private String formataPrecoGasolina(String precoGasolina) {
        return precoGasolina.replace(".", ",");
    }

    private String ajustaPorcentagem() {
        DecimalFormat format = new DecimalFormat("#.000");
        double precoGasolinaSetentaPorcento = precoGasolina * 0.725;
        String precoGasolinaSetentaPorcentoTexto = String.valueOf(precoGasolinaSetentaPorcento);
        precoGasolinaSetentaPorcentoTexto.replace(",", ".");
        precoGasolinaSetentaPorcento = Double.parseDouble(precoGasolinaSetentaPorcentoTexto);
        return format.format(precoGasolinaSetentaPorcento);
    }

    private boolean verificaValorInvalido() {
        return convert_value_gasolina.getText().toString().startsWith(".") || convert_value_alcool.getText().toString().startsWith(".");
    }

    private boolean verificaValorNegativo() {
        return Float.parseFloat(convert_value_gasolina.getText().toString()) < ZERO || Float.parseFloat(convert_value_alcool.getText().toString()) < ZERO;
    }

    private boolean verificaValorEmBranco() {
        return convert_value_gasolina.getText().toString().isEmpty() || convert_value_alcool.getText().toString().isEmpty();
    }

}
