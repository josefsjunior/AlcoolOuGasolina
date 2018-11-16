package com.josefernandes.convert.contratos;

import com.josefernandes.convert.classes.Carro;

import java.util.List;

public interface ICarroRepository {
    void salvar(Carro carro);
    void deletar();
    List<Carro> buscarCarrosDoUsuario(String firebaseId) throws InterruptedException;
}
