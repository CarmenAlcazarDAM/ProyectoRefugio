package org.proyectorefugio.model;

import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.view.Mensajes;

import java.util.Date;

public class Gato extends Animal{
    private boolean leucemiaFelina;

    public Gato(int id, String nombre, String raza, Sexo sexo){
        super(id,nombre, raza,sexo);

    }
    public Gato(int id, String nombre, String raza, Sexo sexo, String marcasDistintivas,
                String numeroChip, boolean esterilizado, String historia, String observaciones,
                Date fechaIngreso, int idUbicacion, boolean leucemiaFelina) {
        super(id,nombre, raza, sexo, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, fechaIngreso, idUbicacion);
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

    /**
     * Método que convierte en texto el boolean isLeucemiaFelina
     * @return --> devuelve una cadena de texto
     */
    public String isLeucemiaFelinaTexto(){
        return Mensajes.positivoNegativo(this.leucemiaFelina);
    }
}
