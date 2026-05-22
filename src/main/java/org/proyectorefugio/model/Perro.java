package org.proyectorefugio.model;

import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.interfaces.Acogible;
import org.proyectorefugio.view.Mensajes;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Representa un perro del refugio.
 * Extiende Animal añadiendo peso, agresividad e implementando
 * Acogible para una futura funcionalidad de acogida temporal.
 */
public class Perro extends Animal implements Acogible {

    private int idPerro;
    private double peso;
    private boolean agresivo;

    /**
     * Constructor completo usado por PerroDAO para reconstruir
     * un perro con todos sus datos desde la base de datos.
     *
     * @param id --> identificador único del perro
     * @param nombre --> nombre del perro
     * @param raza --> raza del perro
     * @param sexo --> sexo del perro
     * @param color --> color del pelaje
     * @param edad --> edad del perro
     * @param marcasDistintivas --> marcas o señales identificativas
     * @param numeroChip --> número de chip
     * @param esterilizado --> indica si el perro está esterilizado
     * @param historia --> historia o procedencia del perro
     * @param observaciones --> observaciones veterinarias o de comportamiento
     * @param fechaIngreso --> fecha en que ingresó al refugio
     * @param idUbicacion --> identificador de la ubicación asignada
     * @param peso --> peso del perro en kilogramos
     * @param agresivo --> indica si el perro tiene comportamiento agresivo
     */
    public Perro(int id, String nombre, String raza, Sexo sexo,
                 String color, String edad, String marcasDistintivas,
                 String numeroChip, boolean esterilizado, String historia,
                 String observaciones, LocalDate fechaIngreso, int idUbicacion,
                 double peso, boolean agresivo) {

        super(id, nombre, raza, sexo, color, edad, marcasDistintivas, numeroChip,
                esterilizado, historia, observaciones, fechaIngreso, false, null, null, idUbicacion);
        this.peso = peso;
        this.agresivo = agresivo;
    }
    /**
     * Constructor mínimo con solo los datos básicos de identificación.
     * Usado principalmente para operaciones simples de búsqueda o referencia.
     *
     * @param id --> identificador único del perro
     * @param nombre --> nombre del perro
     * @param raza --> raza del perro
     * @param sexo --> sexo del perro
     */
    public Perro(int id, String nombre, String raza, Sexo sexo) {
        super(id, nombre, raza, sexo);
    }

    /**
     * Constructor reducido con datos básicos más peso y agresividad.
     * Usado al registrar un nuevo perro antes de asignarle ID definitivo.
     *
     * @param id --> identificador único del perro
     * @param nombre --> nombre del perro
     * @param raza --> raza del perro
     * @param sexo --> sexo del perro
     * @param peso --> peso del perro en kilogramos
     * @param agresivo  --> indica si el perro tiene comportamiento agresivo
     */
    public Perro(int id, String nombre, String raza, Sexo sexo, double peso, boolean agresivo) {
        super(id, nombre, raza, sexo);

        this.peso = peso;
        this.agresivo = agresivo;
    }

    /**
     * Devuelve el peso del perro en kilogramos.
     * @return --> peso del perro
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Indica si el perro tiene comportamiento agresivo.
     * @return --> devuelve true si es agresivo, false en caso contrario
     */
    public boolean isAgresivo() {
        return agresivo;
    }


    @Override
    /**
     * Compara este perro con otro objeto por igualdad de todos sus campos,
     * incluyendo los heredados de Animal.
     *
     * @param o --> objeto a comparar
     * @return --> devuelve true si todos los campos son iguales, false en caso contrario
     */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Perro perro = (Perro) o;
        return idPerro == perro.idPerro && Double.compare(peso, perro.peso) == 0 && agresivo == perro.agresivo;
    }

    @Override
    /**
     * Devuelve el código hash del perro basado en sus campos propios
     * y los heredados de Animal.
     *
     * @return --> código hash
     */
    public int hashCode() {
        return Objects.hash(super.hashCode(), idPerro, peso, agresivo);
    }

    @Override
    /**
     * Devuelve una representación textual del perro.
     * @return --> cadena con el formato heredado de Animal.
     */
    public String toString() {
        return super.toString();
    }

    @Override
    /**
     * Metodo que indica si un perro puede ser acogido no.
     * Si es agresivo no puede ser acogido
     *
     * Este metodo existe para una futura ampliación del programa
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
     * Metodo que convierte en texto el boolean isAgresivo
     * @return --> develve "Sí" si es agresivo, "No" en caso contrario
     */
    public String isAgresivoTexto(){
        return Mensajes.afirmativoNegativo(this.agresivo);
    }
}
