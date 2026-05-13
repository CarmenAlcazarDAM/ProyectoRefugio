package org.proyectorefugio.model;

import org.proyectorefugio.enums.Ubicaciones;

import java.time.LocalTime;

public class Ubicacion {
    private int id;
    private Ubicaciones tipo;
    private LocalTime horaRecreo;
    private int minutosRecreo;
    private int capacidad;

    public Ubicacion(Ubicaciones tipo, LocalTime horaRecreo, int minutosRecreo, int capacidad) {

        this.tipo = tipo;
        this.horaRecreo = horaRecreo;
        this.minutosRecreo = minutosRecreo;
        this.capacidad = capacidad;
    }

    public Ubicacion(int id, Ubicaciones tipo, LocalTime horaRecreo, int minutosRecreo, int capacidad) {
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

    public LocalTime getHoraRecreo() {
        return horaRecreo;
    }

    /**
     * Método que convierte el tipo de dato LocalTime en String
     * para poder gestionarlo cuando tiene valor null. Además la ubicacion
     * de AGRESIVOS no salen al recreo sino que son paseados con correa uno por uno.
     * @return --> devuelve la hora pasada a texto
     */
    public String getHoraEnTexto() {
        if (this.horaRecreo == null) {
            if(this.tipo == Ubicaciones.AGRESIVOS){
                return "Paseo con correa";
            }

            return "Sin recreo";
        }
        return this.horaRecreo.toString();
    }

    public void setHoraRecreo(LocalTime horaRecreo) {
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
