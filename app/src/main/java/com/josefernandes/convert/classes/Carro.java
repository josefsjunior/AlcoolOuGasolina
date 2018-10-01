package com.josefernandes.convert.classes;

import java.util.Comparator;

public class Carro implements Comparator{
    private String apelido;
    private double rendimentoGasolinaCidade;
    private double rendimentoGasolinaEstrada;
    private double rendimentoEtanolCidade;
    private double rendimentoEtanolEstrada;

    public Carro(String apelido, double rendimentoGasolinaCidade, double rendimentoGasolinaEstrada, double rendimentoEtanolCidade, double rendimentoEtanolEstrada) {
        this.apelido = apelido;
        this.rendimentoGasolinaCidade = rendimentoGasolinaCidade;
        this.rendimentoGasolinaEstrada = rendimentoGasolinaEstrada;
        this.rendimentoEtanolCidade = rendimentoEtanolCidade;
        this.rendimentoEtanolEstrada = rendimentoEtanolEstrada;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public double getRendimentoGasolinaCidade() {
        return rendimentoGasolinaCidade;
    }

    public void setRendimentoGasolinaCidade(double rendimentoGasolinaCidade) {
        this.rendimentoGasolinaCidade = rendimentoGasolinaCidade;
    }

    public double getRendimentoEtanolCidade() {
        return rendimentoEtanolCidade;
    }

    public void setRendimentoEtanolCidade(double rendimentoEtanolCidade) {
        this.rendimentoEtanolCidade = rendimentoEtanolCidade;
    }

    public double getRendimentoGasolinaEstrada() {
        return rendimentoGasolinaEstrada;
    }

    public void setRendimentoGasolinaEstrada(double rendimentoGasolinaEstrada) {
        this.rendimentoGasolinaEstrada = rendimentoGasolinaEstrada;
    }

    public double getRendimentoEtanolEstrada() {
        return rendimentoEtanolEstrada;
    }

    public void setRendimentoEtanolEstrada(double rendimentoEtanolEstrada) {
        this.rendimentoEtanolEstrada = rendimentoEtanolEstrada;
    }

    @Override
    public int compare(Object obj, Object other) {
        Carro car = (Carro) obj;
        Carro otherCar = (Carro) other;

        return car.getApelido().compareTo(otherCar.getApelido());
    }
}
