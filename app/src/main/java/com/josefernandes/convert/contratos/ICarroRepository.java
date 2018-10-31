package com.josefernandes.convert.contratos;

import com.josefernandes.convert.classes.Carro;

public interface ICarroRepository {
    void salvar(Carro carro);
    void deletar();
}
