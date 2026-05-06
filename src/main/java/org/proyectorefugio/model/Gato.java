package org.proyectorefugio.model;

import java.util.Date;

public class Gato extends Animal{
    private boolean leucemiaFelina;

    public Gato(int id, String nombre, String raza, Sexo sexo){
        super(id,nombre, raza,sexo);

    }
    public Gato(String nombre, String raza, Sexo sexo, String marcasDistintivas,
                int numeroChip, boolean esterilizado, String historia, String observaciones,
                Date fechaIngreso, boolean leucemiaFelina) {
        super(nombre, raza, sexo, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, fechaIngreso);
        this.leucemiaFelina = leucemiaFelina;
    }

    public boolean isLeucemiaFelina() {
        return leucemiaFelina;
    }

    public void setLeucemiaFelina(boolean leucemiaFelina) {
        this.leucemiaFelina = leucemiaFelina;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
