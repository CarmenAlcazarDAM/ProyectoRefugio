package org.proyectorefugio.model;

import org.proyectorefugio.enums.Ubicaciones;

import java.time.LocalTime;

/**
 * Representa una ubicación física del refugio donde se alojan los animales,
 * con información sobre su tipo, horario de recreo y capacidad.
 */
public class Ubicacion {
    private int id;
    private Ubicaciones tipo;
    private LocalTime horaRecreo;
    private int minutosRecreo;
    private int capacidad;

    /**
     * Constructor para registrar una nueva ubicación sin ID asignado todavía.
     * El ID lo genera la base de datos al insertar el registro.
     *
     * @param tipo --> tipo de ubicación
     * @param horaRecreo --> hora de salida a recreo (puede ser {@code null})
     * @param minutosRecreo --> duración del recreo en minutos
     * @param capacidad --> capacidad máxima de la ubicación
     */
    public Ubicacion(Ubicaciones tipo, LocalTime horaRecreo, int minutosRecreo, int capacidad) {

        this.tipo = tipo;
        this.horaRecreo = horaRecreo;
        this.minutosRecreo = minutosRecreo;
        this.capacidad = capacidad;
    }

    /**
     * Constructor completo usado por UbicacionDAO para reconstruir
     * una ubicación con todos sus datos desde la base de datos.
     *
     * @param id --> identificador único de la ubicación
     * @param tipo --> tipo de ubicación
     * @param horaRecreo --> hora de salida a recreo (puede ser {@code null})
     * @param minutosRecreo --> duración del recreo en minutos
     * @param capacidad -->capacidad máxima de la ubicación
     */
    public Ubicacion(int id, Ubicaciones tipo, LocalTime horaRecreo, int minutosRecreo, int capacidad) {
        this.id = id;
        this.tipo = tipo;
        this.horaRecreo = horaRecreo;
        this.minutosRecreo = minutosRecreo;
        this.capacidad = capacidad;
    }

    /**
     * Devuelve el identificador único de la ubicación.
     * @return --> id de la ubicación
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el identificador único de la ubicación.
     * @param id --> identificador a asignar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el tipo de ubicación.
     * @return --> tipo de ubicación
     */
    public Ubicaciones getTipo() {
        return tipo;
    }

    /**
     * Devuelve la hora de salida a recreo de los animales de esta ubicación.
     * @return hora de recreo o null si no tiene recreo programado
     */
    public LocalTime getHoraRecreo() {
        return horaRecreo;
    }

    /**
     * Metodo que convierte el tipo de dato LocalTime en String
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

    /**
     * Devuelve la duración del recreo en minutos.
     * @return --> minutos de recreo
     */
    public int getMinutosRecreo() {
        return minutosRecreo;
    }

    /**
     * Devuelve la capacidad máxima de la ubicación.
     * @return --> número máximo de animales que puede albergar
     */
    public int getCapacidad() {
        return capacidad;
    }

    @Override
    /**
     * Devuelve una representación textual de la ubicación con todos sus campos.
     * @return --> cadena de texto con los datos de la ubicación
     */
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
