package model;

import java.sql.Time;

public class Ubicacion {
    private int id;
    private Ubicaciones tipo;
    private Time horaRecreo;
    private int minutosRecreo;
    private int capacidad;

    public Ubicacion(int id, Ubicaciones tipo, Time horaRecreo, int minutosRecreo, int capacidad) {
        this.id = id;
        this.tipo = tipo;
        this.horaRecreo = horaRecreo;
        this.minutosRecreo = minutosRecreo;
        this.capacidad = capacidad;
    }




}
