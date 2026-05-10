package org.proyectorefugio.model;

import jdk.jshell.execution.Util;
import org.proyectorefugio.utils.Utils;

public class Persona {
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;

    public Persona(String dni, String nombre, String apellidos, String telefono, String correo, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {

        dni = dni.toUpperCase(); //Para que siempre se guarde con la letra en mayúscula

       if(!Utils.validarDNI(dni)){
           throw new IllegalArgumentException("DNI/NIE no válido");
       }
        this.dni = dni;
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

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}
