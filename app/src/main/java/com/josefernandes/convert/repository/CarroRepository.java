package com.josefernandes.convert.repository;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.josefernandes.convert.classes.Carro;
import com.josefernandes.convert.config.ConfiguracaoFirebase;
import com.josefernandes.convert.contratos.ICarroRepository;

import java.util.ArrayList;
import java.util.List;

public class CarroRepository implements ICarroRepository {

    DatabaseReference reference;

    List<Carro> carros;

    @Override
    public void salvar(Carro carro) {
        if(carro == null){
            //TODO fazer logica quando o carro nao existe
        } else {
            reference = ConfiguracaoFirebase.getFirebase();
            reference.child(FirebaseAuth.getInstance().getUid()).child("carros").child(carro.getId()).setValue(carro);
        }
    }

    @Override
    public void deletar() {
        //TODO fazer chamada ao Firebase para excluir
    }

    @Override
    public List<Carro> buscarCarrosDoUsuario(String firebaseId) {
        reference = ConfiguracaoFirebase.getFirebase().child(firebaseId).child("carros");
        Log.i("FIREBASE-PATH", reference.toString());
        carros = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                carros.clear();
                Log.i("FIREBASE", "Limpando lista..");
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    //String nome = (String) dataSnapshot.child("apelido").getValue();
                    String nome = (String) item.child("apelido").getValue();
                    String gasolinaCidade = item.child("gasolinaCidade").getValue().toString();
                    String gasolinaEstrada = item.child("gasolinaEstrada").getValue().toString();
                    String etanolCidade = item.child("etanolCidade").getValue().toString();
                    String etanolEstrada = item.child("etanolEstrada").getValue().toString();

                    Carro carro = new Carro(
                            nome,
                            Double.parseDouble(gasolinaCidade),
                            Double.parseDouble(gasolinaEstrada),
                            Double.parseDouble(etanolCidade),
                            Double.parseDouble(etanolEstrada)
                    );

                    //Carro carro = item.getValue(Carro.class);
                    carros.add(carro);
                    Log.i("FIREBASE", "Adicionando carro na lista..");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.addValueEventListener(valueEventListener);
        //wait();
        Log.i("FIREBASE", "retornando lista..");
        return carros;
    }
}
