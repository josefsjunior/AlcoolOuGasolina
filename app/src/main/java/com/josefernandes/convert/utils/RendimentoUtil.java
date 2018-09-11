package com.josefernandes.convert.utils;

import com.josefernandes.convert.classes.Carro;

public class RendimentoUtil {
    public static void verificarEconomia(double gasolina, double etanol, double quantidadeLitros, Carro carro){
        double rendimentoGasolina = (carro.getRendimentoGasolinaCidade() * quantidadeLitros) / gasolina;
        double rendimentoEtanol = (carro.getRendimentoEtanolCidade() * quantidadeLitros) / etanol;

        double diferencaAutonomia;
        if (rendimentoGasolina > rendimentoEtanol){
            diferencaAutonomia = rendimentoGasolina - rendimentoEtanol;
        } else {
            diferencaAutonomia = rendimentoEtanol - rendimentoGasolina;
        }
    }
}
