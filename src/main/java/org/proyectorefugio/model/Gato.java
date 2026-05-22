package org.proyectorefugio.model;

import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.view.Mensajes;

import java.time.LocalDate;

/**
 * Representa un gato del refugio.
 * Extiende de Animal, añadiendo el atributo específico de leucemia felina.
 */
public class Gato extends Animal {
    private boolean leucemiaFelina;

    /**
     * Constructor mínimo usado para operaciones básicas de referencia.
     *
     * @param id            --> identificador único del gato
     * @param nombre        --> nombre del gato
     * @param raza          --> raza del gato
     * @param sexo          --> sexo del gato
     * @param lucemiaFelina --> indica si el gato tiene leucemia felina
     */
    public Gato(int id, String nombre, String raza, Sexo sexo, boolean lucemiaFelina) {
        super(id, nombre, raza, sexo);
        this.leucemiaFelina = lucemiaFelina;

    }

    /**
     * Constructor completo usado por GatoDAO para reconstruir
     * un gato con todos sus datos desde la base de datos.
     *
     * @param id                --> identificador único del gato
     * @param nombre            --> nombre del gato
     * @param raza              --> raza del gato
     * @param sexo              --> sexo del gato
     * @param color             --> color del pelaje
     * @param edad              --> edad del gato
     * @param marcasDistintivas --> marcas o señales identificativas
     * @param numeroChip        --> número de chip
     * @param esterilizado      --> indica si el gato está esterilizado
     * @param historia          --> historia o procedencia del gato
     * @param observaciones     --> observaciones veterinarias o de comportamiento
     * @param fechaIngreso      --> fecha en que ingresó al refugio
     * @param idUbicacion       --> identificador de la ubicación asignada
     * @param leucemiaFelina    --> indica si el gato tiene leucemia felina
     */
    public Gato(int id, String nombre, String raza, Sexo sexo,
                String color, String edad,
                String marcasDistintivas, String numeroChip, boolean esterilizado,
                String historia, String observaciones, LocalDate fechaIngreso,
                int idUbicacion, boolean leucemiaFelina) {
        super(id, nombre, raza, sexo, color, edad, marcasDistintivas, numeroChip,
                esterilizado, historia, observaciones, fechaIngreso, false, null, null, idUbicacion);
        this.leucemiaFelina = leucemiaFelina;
    }

    /**
     * Indica si el gato ha dado positivo en leucemia felina.
     *
     * @return --> devuelve true si tiene leucemia felina, false en caso contrario
     */
    public boolean isLeucemiaFelina() {
        return leucemiaFelina;
    }


    @Override
    /**
     * Devuelve una representación textual del gato.
     * @return --> devuelve "Positivo" si tiene leucemia felina, "Negativo" en caso contrario
     */
    public String toString() {
        return super.toString();
    }

    /**
     * Metodo que convierte en texto el boolean isLeucemiaFelina
     * @return --> devuelve una cadena de texto
     */
    public String isLeucemiaFelinaTexto() {
        return Mensajes.positivoNegativo(this.leucemiaFelina);
    }
}
