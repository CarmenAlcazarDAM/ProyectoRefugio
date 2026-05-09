package org.proyectorefugio.model;

import java.sql.Time;

public class Ubicacion {
    private int id;
    private Ubicaciones tipo;
    private Time horaRecreo;
    private int minutosRecreo;
    private int capacidad;

    public Ubicacion( Ubicaciones tipo, Time horaRecreo, int minutosRecreo, int capacidad) {

        this.tipo = tipo;
        this.horaRecreo = horaRecreo;
        this.minutosRecreo = minutosRecreo;
        this.capacidad = capacidad;
    }
    public Ubicacion(int id, Ubicaciones tipo, Time horaRecreo, int minutosRecreo, int capacidad) {
        this.id = id;
        this.tipo = tipo;
        this.horaRecreo = horaRecreo;
        this.minutosRecreo = minutosRecreo;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ubicaciones getTipo() {
        return tipo;
    }

    public void setTipo(Ubicaciones tipo) {
        this.tipo = tipo;
    }

    public Time getHoraRecreo() {
        return horaRecreo;
    }

    public void setHoraRecreo(Time horaRecreo) {
        this.horaRecreo = horaRecreo;
    }

    public int getMinutosRecreo() {
        return minutosRecreo;
    }

    public void setMinutosRecreo(int minutosRecreo) {
        this.minutosRecreo = minutosRecreo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", horaRecreo=" + horaRecreo +
                ", minutosRecreo=" + minutosRecreo +
                ", capacidad=" + capacidad +
                '}';
    }
}
