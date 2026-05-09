package org.proyectorefugio.model;

public class Voluntario extends Persona{
    public Voluntario(String dni, String nombre, String apellidos, String telefono, String correo, String direccion) {
        super(dni, nombre, apellidos, telefono, correo, direccion);
    }

    @Override
    public String toString() {
        return super.getNombre() + " es Voluntario";
    }
}
