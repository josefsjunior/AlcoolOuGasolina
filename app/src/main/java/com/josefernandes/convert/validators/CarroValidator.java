package com.josefernandes.convert.validators;

public class CarroValidator {

    private String precoGasolina;
    private String precoEtanol;

    public CarroValidator(String precoGasolina, String precoEtanol) {
        this.precoGasolina = precoGasolina;
        this.precoEtanol = precoEtanol;
        verificaValorInvalido();
    }

    public void verificaValorInvalido() {
        if(precoGasolina.startsWith(".") || precoEtanol.startsWith(".")){

        }
    }
}
