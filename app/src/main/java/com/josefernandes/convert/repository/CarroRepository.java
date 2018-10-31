package com.josefernandes.convert.repository;

import com.google.firebase.database.DatabaseReference;
import com.josefernandes.convert.classes.Carro;
import com.josefernandes.convert.config.ConfiguracaoFirebase;
import com.josefernandes.convert.contratos.ICarroRepository;

public class CarroRepository implements ICarroRepository {


    @Override
    public void salvar(Carro carro) {
        if(carro == null){
            //TODO fazer logica quando o carro nao existe
        } else {
            DatabaseReference reference = ConfiguracaoFirebase.getFirebase();
            reference.child("carros").setValue(carro);
        }
    }

    @Override
    public void deletar() {
        //TODO fazer chamada ao Firebase para excluir
    }
}
