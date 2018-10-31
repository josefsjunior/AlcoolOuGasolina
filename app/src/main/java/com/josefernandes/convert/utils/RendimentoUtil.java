package com.josefernandes.convert.utils;

import com.josefernandes.convert.classes.Carro;

public class RendimentoUtil {
    public static void verificarEconomia(double gasolina, double etanol, double quantidadeLitros, Carro carro){
        double rendimentoGasolina = (carro.getGasolinaCidade() * quantidadeLitros) / gasolina;
        double rendimentoEtanol = (carro.getEtanolCidade() * quantidadeLitros) / etanol;

        double diferencaAutonomia;
        if (rendimentoGasolina > rendimentoEtanol){
            diferencaAutonomia = rendimentoGasolina - rendimentoEtanol;
        } else {
            diferencaAutonomia = rendimentoEtanol - rendimentoGasolina;
        }
    }
}
