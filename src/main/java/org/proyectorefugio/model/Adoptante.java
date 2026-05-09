package org.proyectorefugio.model;

public class Adoptante extends Persona{
    public Adoptante(String dni, String nombre, String apellidos, String telefono, String correo, String direccion) {
        super(dni, nombre, apellidos, telefono, correo, direccion);
    }

    public String toString() {
        return super.getNombre() + " es Adoptante";
    }
}
