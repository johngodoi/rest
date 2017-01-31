package com.johngodoi.casadocodigo.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by jgodoi on 30/01/2017.
 */
public class TestJ5 {
    @Test
    public void test(){
        assertThrows(ArithmeticException.class, ()->divideByZero());
    }

    private int divideByZero() {
        return 3/0;
    }
}
