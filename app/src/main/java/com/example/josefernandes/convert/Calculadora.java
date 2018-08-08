package com.example.josefernandes.convert;

public class Calculadora {
    private float gasolina;
    private float alcool;

    public Calculadora(float gasolina, float alcool) {
        if(gasolina < 0 || alcool < 0){
            throw new IllegalArgumentException("Combustível não pode ter valor regativo");
        }
        this.gasolina = gasolina;
        this.alcool = alcool;
    }

    public float getGasolina() {
        return gasolina;
    }

    public void setGasolina(float gasolina) {
        this.gasolina = gasolina;
    }

    public float getAlcool() {
        return alcool;
    }

    public void setAlcool(float alcool) {
        this.alcool = alcool;
    }

    /**
     * true para álcool, false para gasolina
     * @return
     */
    public String gasolinaOuAlcool(){
        return (gasolina*0.7 > alcool)? "álcool" : "gasolina";
    }
}
