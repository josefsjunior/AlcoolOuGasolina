package com.josefernandes.convert.classes;

public class Carro {
    private String apelido;
    private double rendimentoGasolina;
    private double rendimentoEtanol;

    public Carro(String apelido, double rendimentoGasolina, double rendimentoEtanol) {
        this.apelido = apelido;
        this.rendimentoGasolina = rendimentoGasolina;
        this.rendimentoEtanol = rendimentoEtanol;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public double getRendimentoGasolina() {
        return rendimentoGasolina;
    }

    public void setRendimentoGasolina(double rendimentoGasolina) {
        this.rendimentoGasolina = rendimentoGasolina;
    }

    public double getRendimentoEtanol() {
        return rendimentoEtanol;
    }

    public void setRendimentoEtanol(double rendimentoEtanol) {
        this.rendimentoEtanol = rendimentoEtanol;
    }
}
