package org.proyectorefugio.model;


import org.proyectorefugio.utils.Utils;

import java.util.Objects;

//todo-> comentarios
public class Persona {
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;

    public Persona(String dni, String nombre, String apellidos, String telefono, String correo, String direccion) {
        setDni(dni);
        this.nombre = nombre;
        this.apellidos = apellidos;
        setTelefono(telefono);
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (!Utils.validarTelefono(telefono)) {

            throw new IllegalArgumentException("Número de teléfono inválido");
        }
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }


    public String getDireccion() {
        return direccion;
    }


    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
