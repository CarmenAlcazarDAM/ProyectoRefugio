package org.proyectorefugio.model;

/**
 * Representa a una persona que ha adoptado al menos un animal del refugio.
 * Extiende de Persona sin añadir atributos propios, diferenciándola
 * del resto de personas en el sistema.
 */
public class Adoptante extends Persona{

    /**
     * Crea un nuevo adoptante con los datos personales indicados.
     *
     * @param dni --> DNI o NIE del adoptante
     * @param nombre --> nombre del adoptante
     * @param apellidos --> apellidos del adoptante
     * @param telefono --> teléfono de contacto
     * @param correo --> correo electrónico
     * @param direccion --> domicilio del adoptante
     */
    public Adoptante(String dni, String nombre, String apellidos, String telefono, String correo, String direccion) {
        super(dni, nombre, apellidos, telefono, correo, direccion);
    }

    /**
     * @return --> cadena con el nombre del adoptante y su rol
     */
    public String toString() {
        return super.getNombre() + " es Adoptante";
    }
}
