package org.proyectorefugio.model;

/**
 * Representa a una persona voluntaria del refugio.
 * Extiende Persona sin añadir atributos propios, diferenciándola
 * del resto de personas en el sistema.
 */
public class Voluntario extends Persona{

    /**
     * Crea un nuevo voluntario con los datos personales indicados.
     *
     * @param dni --> NI o NIE del voluntario
     * @param nombre --> nombre del voluntario
     * @param apellidos --> apellidos del voluntario
     * @param telefono --> teléfono de contacto
     * @param correo --> dirección de correo electrónico
     * @param direccion --> domicilio del voluntario
     */
    public Voluntario(String dni, String nombre, String apellidos, String telefono, String correo, String direccion) {
        super(dni, nombre, apellidos, telefono, correo, direccion);
    }

    @Override
    /**
     * Devuelve una representación textual del voluntario.
     * @return --> cadena de texto con el formato heredado Persona
     */
    public String toString() {
        return super.toString();
    }
}
