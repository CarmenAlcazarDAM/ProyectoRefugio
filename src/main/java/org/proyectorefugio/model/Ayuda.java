package org.proyectorefugio.model;


import java.time.LocalDate;

public class Ayuda {
    private String dniVoluntario;
    private int idUbicacion;
    private LocalDate fecha;
    private String tarea;

    public Ayuda(String dniVoluntario, int idUbicacion, LocalDate fecha, String tarea) {
        this.dniVoluntario = dniVoluntario;
        this.idUbicacion = idUbicacion;
        this.fecha = fecha;
        this.tarea = tarea;
    }

    public String getDniVoluntario() {
        return dniVoluntario;
    }


    public int getIdUbicacion() {
        return idUbicacion;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTarea() {
        return tarea;
    }


    @Override
    public String toString() {
        return "Tarea{" +
                "dniVoluntario='" + dniVoluntario + '\'' +
                ", idUbicacion=" + idUbicacion +
                ", fecha=" + fecha +
                ", tarea='" + tarea + '\'' +
                '}';
    }
}
