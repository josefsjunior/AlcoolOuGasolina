package com.example.josefernandes.convert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CalculadoraTest {

    @Test
    public void abastecerComAlcool(){
        Calculadora calc = new Calculadora(4.099f, 2.599f);
        String resultado = calc.gasolinaOuAlcool();

        assertEquals("álcool", resultado);
    }

    @Test
    public void abastecerComGasolina(){
        Calculadora calc = new Calculadora(3.099f, 2.599f);
        String resultado = calc.gasolinaOuAlcool();

        assertEquals("gasolina", resultado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void gasolinaNaoPodeTerValorNegativo(){
        Calculadora calc = new Calculadora(-3.099f, 2.599f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void alcoolNaoPodeTerValorNegativo(){
        Calculadora calc = new Calculadora(3.099f, -2.599f);
    }
}
