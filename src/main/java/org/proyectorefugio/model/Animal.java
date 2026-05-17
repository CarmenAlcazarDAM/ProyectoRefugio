package org.proyectorefugio.model;

import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.Mensajes;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Animal {
    private int id;
    private String nombre;
    private String raza;
    private Sexo sexo;
    private String color;
    private String edad;
    private String marcasDistintivas;
    private String numeroChip;
    private boolean esterilizado = false;
    private String historia;
    private String observaciones;
    private Date fechaIngreso;
    private boolean adoptado = false;
    private Date fechaAlta;
    private String dniAdoptante;
    private int idUbicacion;

    public Animal(int id, String nombre, String raza, Sexo sexo){
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
    }
    public Animal(int id, String nombre, String raza, Sexo sexo, String marcasDistintivas,
                  String numeroChip, boolean esterilizado, String historia,
                   String observaciones, Date fechaIngreso, int idUbicacion) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.marcasDistintivas = marcasDistintivas;
        this.numeroChip = numeroChip;
        this.esterilizado = esterilizado;
        this.historia = historia;
        this.observaciones = observaciones;
        this.fechaIngreso = fechaIngreso;
        this.idUbicacion = idUbicacion;
    }

    //este se usa en AnimalDAO
    public Animal(int id, String nombre, String raza, Sexo sexo, String color, String edad, String marcasDistintivas, String numeroChip, boolean esterilizado, String historia, String observaciones, Date fechaIngreso, boolean adoptado, Date fechaAlta, String dniAdoptante, int idUbicacion) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.edad = edad;
        this.marcasDistintivas = marcasDistintivas;
        this.numeroChip = numeroChip;
        this.esterilizado = esterilizado;
        this.historia = historia;
        this.observaciones = observaciones;
        this.fechaIngreso = fechaIngreso;
        this.adoptado = adoptado;
        this.fechaAlta = fechaAlta;
        this.dniAdoptante = dniAdoptante;
        this.idUbicacion = idUbicacion;
    }

    public Animal(String nombre, String raza, Sexo sexo, String color, String edad, String marcasDistintivas, String numeroChip, boolean esterilizado, String historia, String observaciones, int idUbicacion) {
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.edad = edad;
        this.marcasDistintivas = marcasDistintivas;
        this.numeroChip = numeroChip;
        this.esterilizado = esterilizado;
        this.historia = historia;
        this.observaciones = observaciones;
        this.idUbicacion = idUbicacion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getMarcasDistintivas() {
        return marcasDistintivas;
    }

    public void setMarcasDistintivas(String marcasDistintivas) {
        this.marcasDistintivas = marcasDistintivas;
    }

    public String getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(String numeroChip) {
        if(!Utils.validaChip(numeroChip)){
            throw new IllegalArgumentException("Número de chip no válido");
        }
        this.numeroChip = numeroChip;
    }

    public boolean isEsterilizado() {
        return esterilizado;
    }

    public void setEsterilizado(boolean esterilizado) {
        this.esterilizado = esterilizado;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isAdoptado() {
        return adoptado;
    }

    public void setAdoptado(boolean adoptado) {
        this.adoptado = adoptado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDniAdoptante() {
        return dniAdoptante;
    }

    public void setDniAdoptante(String dniAdoptante) {
        this.dniAdoptante = dniAdoptante;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && esterilizado == animal.esterilizado && adoptado == animal.adoptado && idUbicacion == animal.idUbicacion && Objects.equals(nombre, animal.nombre) && Objects.equals(raza, animal.raza) && sexo == animal.sexo && Objects.equals(color, animal.color) && Objects.equals(edad, animal.edad) && Objects.equals(marcasDistintivas, animal.marcasDistintivas) && Objects.equals(numeroChip, animal.numeroChip) && Objects.equals(historia, animal.historia) && Objects.equals(observaciones, animal.observaciones) && Objects.equals(fechaIngreso, animal.fechaIngreso) && Objects.equals(fechaAlta, animal.fechaAlta) && Objects.equals(dniAdoptante, animal.dniAdoptante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, raza, sexo, color, edad, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, fechaIngreso, adoptado, fechaAlta, dniAdoptante, idUbicacion);
    }

    @Override
    public String toString() {
        return nombre.toUpperCase() + " (Id: " + id + ", " + sexo + ", " + raza + ")";
    }

    /**
     * Método que convierte en texto el boolean isEsterilizado
     * @return --> devuelve una cadena de texto
     */
    public String isEsterilizadoTexto(){
        return Mensajes.afirmativoNegativo(this.esterilizado);
    }


}
