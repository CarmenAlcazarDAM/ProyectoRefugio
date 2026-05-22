package org.proyectorefugio.model;


import org.proyectorefugio.utils.Utils;

import java.util.Objects;

/**
 * Representa a una persona registrada en el sistema del refugio.
 * Es la clase base de Voluntario y Adoptante.
 */
public class Persona {
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;

    /**
     * Crea una nueva persona con todos sus datos personales.
     * Valida el DNI o NIE y el teléfono antes de asignarlos.
     *
     * @param dni --> DNI o NIE de la persona
     * @param nombre --> nombre de la persona
     * @param apellidos --> apellidos de la persona
     * @param telefono --> número de teléfono de contacto
     * @param correo --> dirección de correo electrónico
     * @param direccion --> domicilio de la persona
     */
    public Persona(String dni, String nombre, String apellidos, String telefono, String correo, String direccion) {
        setDni(dni);
        this.nombre = nombre;
        this.apellidos = apellidos;
        setTelefono(telefono);
        this.correo = correo;
        this.direccion = direccion;
    }

    /**
     * Devuelve el DNI o NIE de la persona.
     * @return --> DNI o NIE en mayúsculas
     */
    public String getDni() {
        return dni;
    }

    /**
     * Asigna el DNI o NIE de la persona tras validar su formato.
     * El valor se convierte a mayúsculas y se eliminan los espacios.
     *
     * @param dni --> DNI o NIE a asignar
     */
    public void setDni(String dni) {

        String dniMayuscula = dni.toUpperCase().trim();
        if (dniMayuscula.length() != 9) {
            throw new IllegalArgumentException("DNI/NIE no válido: debe tener 9 caracteres");
        }

        if (!Utils.validarDNI(dniMayuscula)) {
            throw new IllegalArgumentException("DNI/NIE no válido");
        }
        this.dni = dniMayuscula;
    }

    /**
     * Devuelve el nombre de la persona.
     * @return --> nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre de la persona.
     * @param nombre --> nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve los apellidos de la persona.
     * @return --> apellidos de la persona
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Devuelve el número de teléfono de la persona.
     * @return --> teléfono de contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el número de teléfono de la persona tras validar su formato.
     * @param telefono --> teléfono a asignar
     */
    public void setTelefono(String telefono) {
        if (!Utils.validarTelefono(telefono)) {

            throw new IllegalArgumentException("Número de teléfono inválido");
        }
        this.telefono = telefono;
    }

    /**
     * Devuelve la dirección de correo electrónico de la persona.
     * @return --> correo electrónico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Devuelve el domicilio de la persona.
     * @return --> dirección postal
     */
    public String getDireccion() {
        return direccion;
    }


    @Override
    /**
     * Devuelve una representación textual de la persona con nombre y apellidos.
     * @return --> cadena de texto
     */
    public String toString() {
        return nombre + " " + apellidos;
    }

    @Override
    /**
     * Compara esta persona con otro objeto por igualdad de DNI.
     * Dos personas son iguales si tienen el mismo DNI.
     *
     * @param o --> objeto a comparar
     * @return --> devuelve true si los DNI son iguales, false en caso contrario
     */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    /**
     * Devuelve el código hash de la persona basado únicamente en su DNI.
     * @return --> código hash
     */
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
