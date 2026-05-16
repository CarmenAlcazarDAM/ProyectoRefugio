package org.proyectorefugio.model;

import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.interfaces.Acogible;
import org.proyectorefugio.view.Mensajes;

import java.util.Date;
import java.util.Objects;

public class Perro extends Animal implements Acogible {

    private int idPerro;
    private double peso;
    private boolean agresivo;

    public Perro(int id, String nombre, String raza, Sexo sexo,
                 String marcasDistintivas, String numeroChip, boolean esterilizado,
                 String historia, String observaciones, Date fechaIngreso,
                 int idUbicacion, double peso, boolean agresivo) {
        super(id, nombre, raza, sexo, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, fechaIngreso, idUbicacion);

        this.peso = peso;
        this.agresivo = agresivo;
    }

    public Perro(int id, String nombre, String raza, Sexo sexo) {
        super(id, nombre, raza, sexo);
    }
    public Perro(int id, String nombre, String raza, Sexo sexo, double peso, boolean agresivo) {
        super(id, nombre, raza, sexo);

        this.peso = peso;
        this.agresivo = agresivo;
    }


    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isAgresivo() {
        return agresivo;
    }

    public void setAgresivo(boolean agresivo) {
        this.agresivo = agresivo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Perro perro = (Perro) o;
        return idPerro == perro.idPerro && Double.compare(peso, perro.peso) == 0 && agresivo == perro.agresivo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idPerro, peso, agresivo);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    /**
     * Método que indica si un perro puede ser acogido no.
     * Si es agresivo no puede ser acogido
     *
     * Este método existe para una futura ampliación del programa
     * donde se contemple además de la Adopción, la Acogida de los animales
     * a la espera de una familia que los adopte.
     *
     * @return --> devuelve false si no puede ser acogido
     */
    public boolean puedeSerAcogido() {
        if(this.agresivo) {
            return false;
        }
        return true;
    }

    /**
     * Método que convierte en texto el boolean isAgresivo
     * @return --> devuelve una cadena de texto
     */
    public String isAgresivoTexto(){
        return Mensajes.afirmativoNegativo(this.agresivo);
    }
}
