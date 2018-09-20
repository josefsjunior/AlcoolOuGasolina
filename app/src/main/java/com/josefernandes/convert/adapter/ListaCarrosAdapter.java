package com.josefernandes.convert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.josefernandes.convert.R;
import com.josefernandes.convert.classes.Carro;

import java.util.List;

public class ListaCarrosAdapter extends BaseAdapter {

    private final List<Carro> carros;
    private final Context context;

    public ListaCarrosAdapter(Context context, List<Carro> carros) {
        this.context = context;
        this.carros = carros;
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int i) {
        return carros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.activity_carro, viewGroup, false);

        TextView txtNome = viewCriada.findViewById(R.id.item_carro_apelido);
        TextView txtGasolinaCidade = viewCriada.findViewById(R.id.item_texto_gasolina_cidade);
        TextView txtGasolinaEstrada = viewCriada.findViewById(R.id.item_carro_gasolina_estrada);
        TextView txtEtanolCidade = viewCriada.findViewById(R.id.item_carro_etanol_cidade);
        TextView txtEtanolEstrada = viewCriada.findViewById(R.id.item_carro_etanol_estrada);

        //txtNome.setText("Corola");
        //txtGasolinaCidade.setText("9");
        //txtGasolinaEstrada.setText("9");
        //txtEtanolCidade.setText("9");
        //txtEtanolEstrada.setText("9");

        return viewCriada;
    }
}
