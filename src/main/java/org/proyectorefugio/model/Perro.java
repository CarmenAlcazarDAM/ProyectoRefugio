package org.proyectorefugio.model;

import java.util.Date;

public class Perro extends Animal{

    private double peso;
    private boolean agresivo;

    public Perro(String nombre, String raza, Sexo sexo, String marcasDistintivas, int numeroChip,
                 boolean esterilizado, String historia, String observaciones, Date fechaIngreso,
                 double peso, boolean agresivo) {
        super(nombre, raza, sexo, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, fechaIngreso);
        this.peso = peso;
        this.agresivo = agresivo;
    }
    public Perro(int id, String nombre, String raza, Sexo sexo){
        super(id,nombre, raza,sexo);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
