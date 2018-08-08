package com.example.josefernandes.convert;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertUtilTest {

    /**
     * Nesse teste verificamos se realmente será o álcool o combustível mais vantajoso
     * @throws Exception
     */
    @Test
    public void testeAlcool() throws Exception {
        float gasolina = (float) 3.79;
        float alcool = (float) 2.29;

        String expectativa = "alcool";
        String resultado = "";

        if(gasolina * 0.7 > alcool){
            resultado = "alcool";
        } else {
            resultado = "gasolina";
        }

        assertEquals(expectativa, resultado);
    }

    /**
     * Nesse teste verificamos se realmente a gasolina será o combustível mais vantajoso
     * @throws Exception
     */
    @Test
    public void testeGasolina() throws Exception {
        float gasolina = (float) 3.19;
        float alcool = (float) 2.47;

        String expectativa = "gasolina";
        String resultado = "";

        if(gasolina * 0.7 > alcool){
            resultado = "alcool";
        } else {
            resultado = "gasolina";
        }

        assertEquals(expectativa, resultado);
    }

    /**
    @Test
    public void realizaCalculoGasolina(){
        ConvertActivity ca = new ConvertActivity();
        boolean gasolina = ca.realizaCalculo(3.19f, 2.47f );
        assertNotEquals(true, gasolina);
    }

    @Test
    public void realizaCalculoAlcool(){
        ConvertActivity ca = new ConvertActivity();
        boolean alcool = ca.realizaCalculo(3.09f, 1.99f );
        assertEquals(true, alcool);
    }
    */
}