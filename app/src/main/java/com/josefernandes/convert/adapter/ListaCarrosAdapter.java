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

    private List<Carro> carros;
    private LayoutInflater inflater;

    public ListaCarrosAdapter(Context context, List<Carro> carros) {
        inflater = LayoutInflater.from(context);
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
        //View viewCriada = LayoutInflater.from(context).inflate(R.layout.layout_item_carro, viewGroup, false);
        View viewCriada = inflater.inflate( R.layout.layout_item_carro, viewGroup, false );

        Carro carro = carros.get( i );

        TextView txtNome = viewCriada.findViewById(R.id.item_carro_apelido);
        txtNome.setText(carro.getApelido());

        TextView txtGasolinaCidade = viewCriada.findViewById(R.id.item_texto_gasolina_cidade);
        txtGasolinaCidade.setText(String.valueOf(carro.getRendimentoGasolinaCidade()));

        TextView txtGasolinaEstrada = viewCriada.findViewById(R.id.item_carro_gasolina_estrada);
        txtGasolinaEstrada.setText(String.valueOf(carro.getRendimentoGasolinaEstrada()));

        TextView txtEtanolCidade = viewCriada.findViewById(R.id.item_carro_etanol_cidade);
        txtEtanolCidade.setText(String.valueOf(carro.getRendimentoEtanolCidade()));

        TextView txtEtanolEstrada = viewCriada.findViewById(R.id.item_carro_etanol_estrada);
        txtEtanolEstrada.setText(String.valueOf(carro.getRendimentoEtanolEstrada()));

        return viewCriada;
    }
}
