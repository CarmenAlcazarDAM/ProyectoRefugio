package org.proyectorefugio.model;

import java.time.LocalDate;

/**
 * Representa una tarea de ayuda realizada por un voluntario en una ubicación
 * concreta del refugio en una fecha determinada.
 */
public class Ayuda {
    private String dniVoluntario;
    private int idUbicacion;
    private LocalDate fecha;
    private String tarea;

    /**
     * Crea una nueva tarea de ayuda con todos sus datos.
     *
     * @param dniVoluntario --> DNI o NIE del voluntario que realiza la tarea
     * @param idUbicacion --> identificador de la ubicación asignada
     * @param fecha --> fecha de realización de la tarea
     * @param tarea --> descripción de la tarea
     */
    public Ayuda(String dniVoluntario, int idUbicacion, LocalDate fecha, String tarea) {
        this.dniVoluntario = dniVoluntario;
        this.idUbicacion = idUbicacion;
        this.fecha = fecha;
        this.tarea = tarea;
    }

    /**
     * Devuelve el DNI o NIE del voluntario que realiza la tarea.
     * @return --> DNI o NIE del voluntario
     */
    public String getDniVoluntario() {
        return dniVoluntario;
    }


    /**
     * Devuelve el identificador de la ubicación donde se realiza la tarea.
     * @return --> id de la ubicación
     */
    public int getIdUbicacion() {
        return idUbicacion;
    }

    /**
     * Devuelve la fecha de realización de la tarea.
     * @return --> fecha de la tarea
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Asigna la fecha de realización de la tarea.
     * @param fecha --> nueva fecha a asignar
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve la descripción de la tarea.
     * @return --> descripción de la tarea
     */
    public String getTarea() {
        return tarea;
    }


    @Override
    /**
     * Devuelve una representación textual de la tarea con todos sus campos.
     * @return c--> adena con los datos de la tarea
     */
    public String toString() {
        return "Tarea{" +
                "dniVoluntario='" + dniVoluntario + '\'' +
                ", idUbicacion=" + idUbicacion +
                ", fecha=" + fecha +
                ", tarea='" + tarea + '\'' +
                '}';
    }
}
