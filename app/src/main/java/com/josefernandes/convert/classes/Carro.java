package com.josefernandes.convert.classes;

import java.util.Comparator;

public class Carro implements Comparator{
    private String apelido;
    private double gasolinaCidade;
    private double gasolinaEstrada;
    private double etanolCidade;
    private double etanolEstrada;

    public Carro(String apelido, double gasolinaCidade, double gasolinaEstrada, double etanolCidade, double etanolEstrada) {
        this.apelido = apelido;
        this.gasolinaCidade = gasolinaCidade;
        this.gasolinaEstrada = gasolinaEstrada;
        this.etanolCidade = etanolCidade;
        this.etanolEstrada = etanolEstrada;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public double getGasolinaCidade() {
        return gasolinaCidade;
    }

    public void setGasolinaCidade(double gasolinaCidade) {
        this.gasolinaCidade = gasolinaCidade;
    }

    public double getEtanolCidade() {
        return etanolCidade;
    }

    public void setEtanolCidade(double etanolCidade) {
        this.etanolCidade = etanolCidade;
    }

    public double getGasolinaEstrada() {
        return gasolinaEstrada;
    }

    public void setGasolinaEstrada(double gasolinaEstrada) {
        this.gasolinaEstrada = gasolinaEstrada;
    }

    public double getEtanolEstrada() {
        return etanolEstrada;
    }

    public void setEtanolEstrada(double etanolEstrada) {
        this.etanolEstrada = etanolEstrada;
    }

    @Override
    public int compare(Object obj, Object other) {
        Carro car = (Carro) obj;
        Carro otherCar = (Carro) other;

        return car.getApelido().compareTo(otherCar.getApelido());
    }
}
