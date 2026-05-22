package org.proyectorefugio.model;

import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.Mensajes;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un animal del refugio con todos sus datos identificativos.
 */
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
    private LocalDate fechaIngreso;
    private boolean adoptado = false;
    private LocalDate fechaAlta;
    private String dniAdoptante;
    private int idUbicacion;

    /**
     * Constructor mínimo con los datos básicos de identificación.
     * Usado principalmente para operaciones simples de búsqueda o referencia.
     *
     * @param id --> identificador único del animal
     * @param nombre --> nombre del animal
     * @param raza --> raza del animal
     * @param sexo --> sexo del animal
     */
    public Animal(int id, String nombre, String raza, Sexo sexo) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
    }


    /**
     * Constructor completo usado por AnimalDAO para reconstruir
     * un animal con todos sus datos desde la base de datos.
     *
     * @param id --> identificador único del animal
     * @param nombre --> nombre del animal
     * @param raza --> raza del animal
     * @param sexo --> sexo del animal
     * @param color --> color del pelaje
     * @param edad --> edad del animal
     * @param marcasDistintivas --> marcas o señales identificativas
     * @param numeroChip --> número de chip
     * @param esterilizado --> indica si el animal está esterilizado
     * @param historia --> historia o procedencia del animal
     * @param observaciones --> observaciones veterinarias o de comportamiento
     * @param fechaIngreso --> fecha en que ingresó al refugio
     * @param adoptado --> indica si el animal ha sido adoptado
     * @param fechaAlta --> fecha en que causó baja en el refugio
     * @param dniAdoptante --> DNI del adoptante
     * @param idUbicacion --> identificador de la ubicación asignada
     */
    public Animal(int id, String nombre, String raza, Sexo sexo, String color, String edad,
                  String marcasDistintivas, String numeroChip, boolean esterilizado, String historia,
                  String observaciones, LocalDate fechaIngreso, boolean adoptado,
                  LocalDate fechaAlta, String dniAdoptante, int idUbicacion) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.edad = edad;
        this.marcasDistintivas = marcasDistintivas;
        setNumeroChip(numeroChip);
        this.esterilizado = esterilizado;
        this.historia = historia;
        this.observaciones = observaciones;
        setFechaIngreso(fechaIngreso);
        this.adoptado = adoptado;
        setFechaAlta(fechaAlta);
        setDniAdoptante(dniAdoptante);
        this.idUbicacion = idUbicacion;
    }

    /**
     * Constructor para registrar un nuevo animal sin ID asignado todavía.
     * El ID lo genera la base de datos al insertar el registro.
     *
     * @param nombre --> nombre del animal
     * @param raza --> raza del animal
     * @param sexo --> sexo del animal
     * @param color --> color del pelaje
     * @param edad --> edad del animal
     * @param marcasDistintivas --> marcas o señales identificativas
     * @param numeroChip --> número de chip
     * @param esterilizado --> indica si el animal está esterilizado
     * @param historia --> historia o procedencia del animal
     * @param observaciones --> observaciones veterinarias o de comportamiento
     * @param fechaIngreso --> fecha en que ingresó al refugio
     */
    public Animal(String nombre, String raza, Sexo sexo, String color, String edad, String marcasDistintivas, String numeroChip, boolean esterilizado, String historia, String observaciones, int idUbicacion,LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.edad = edad;
        this.marcasDistintivas = marcasDistintivas;
        setNumeroChip(numeroChip);
        this.esterilizado = esterilizado;
        this.historia = historia;
        this.observaciones = observaciones;
        this.idUbicacion = idUbicacion;
        setFechaIngreso(fechaIngreso);
    }

    /**
     * Devuelve el identificador único del animal.
     * @return --> id del animal
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el identificador único del animal.
     * @param id --> identificador a asignar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del animal.
     * @return --> nombre del animal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del animal.
     * @param nombre --> nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la raza del animal.
     * @return --> raza del animal
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Asigna la raza del animal.
     * @param raza --> raza a asignar
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Devuelve el sexo del animal.
     * @return --> sexo del animal
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * Asigna el sexo del animal.
     * @param sexo --> sexo a asignar
     */
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    /**
     * Devuelve el color del pelaje del animal.
     * @return -> color del animal
     */
    public String getColor() {
        return color;
    }

    /**
     * Asigna el color del pelaje del animal.
     * @param color --> color a asignar
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Devuelve la edad del animal.
     * @return --> edad del animal
     */
    public String getEdad() {
        return edad;
    }

    /**
     * Devuelve las marcas o señales identificativas del animal.
     * @return --> marcas distintivas
     */
    public String getMarcasDistintivas() {
        return marcasDistintivas;
    }

    /**
     * Devuelve el número de microchip del animal.
     * @return --> número de chip, o  null si no tiene
     */
    public String getNumeroChip() {
        return numeroChip;
    }

    /**
     * Asigna el número de chip del animal tras validar su formato.
     * Si el valor es null o está vacío, se almacena como  null.
     * @param numeroChip --> número de chip a asignar (15 dígitos)
     */
    public void setNumeroChip(String numeroChip) {
        if(numeroChip==null|| numeroChip.trim().isEmpty()){
            this.numeroChip = null;
            return;
        }
        if (!Utils.validaChip(numeroChip)) {
            throw new IllegalArgumentException("Número de chip no válido");
        }
        this.numeroChip = numeroChip;
    }

    /**
     * Indica si el animal está esterilizado.
     * @return --> true si está esterilizado, false si no.
     */
    public boolean isEsterilizado() {
        return esterilizado;
    }

    /**
     * Devuelve la historia o procedencia del animal.
     * @return --> historia del animal
     */
    public String getHistoria() {
        return historia;
    }

    /**
     * Devuelve las observaciones veterinarias o de comportamiento del animal.
     * @return --> observaciones del animal
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Devuelve la fecha en que el animal ingresó al refugio.
     * @return --> fecha de ingreso
     */
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Asigna la fecha de ingreso del animal al refugio.
     * No puede ser null ni posterior al día actual.
     *
     * @param fechaIngreso --> fecha de ingreso a asignar
     */
    public void setFechaIngreso(LocalDate fechaIngreso) {
        if (fechaIngreso == null) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser nula");
        }

        if (!Utils.validarFecha(fechaIngreso)) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser posterior al día actual");
        }
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Devuelve la fecha en que el animal causó baja en el refugio.
     * @return --> fecha de alta, o null si sigue en el refugio
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Asigna la fecha de baja del animal en el refugio.
     * Si el valor es null, se elimina la fecha de alta.
     * No puede ser posterior al día actual.
     *
     * @param fechaAlta --> fecha de salida del refugio
     */
    public void setFechaAlta(LocalDate fechaAlta) {
        if(fechaAlta == null){
            this.fechaAlta = null;
            return;
        }

        if (!Utils.validarFecha(fechaAlta)) {
            throw new IllegalArgumentException("La fecha de alta no puede ser posterior al día actual");
        }
        this.fechaAlta = fechaAlta;
    }

    /**
     * Asigna el DNI del adoptante al animal tras validar su formato.
     * Si el valor es null o está vacío, se almacena como null.
     * El DNI se convierte a mayúsculas antes de almacenarse.
     *
     * @param dniAdoptante --> DNI o NIE del adoptante
     */
    public void setDniAdoptante(String dniAdoptante) {
        if (dniAdoptante == null || dniAdoptante.trim().isEmpty()) {
            this.dniAdoptante = null;
            return;
        }

        String dniMayuscula = dniAdoptante.toUpperCase().trim();

        if (dniMayuscula.length() != 9) {
            throw new IllegalArgumentException("DNI/NIE no válido: debe tener 9 caracteres");
        }

        if (!Utils.validarDNI(dniMayuscula)) {
            throw new IllegalArgumentException("DNI/NIE no válido");
        }
        this.dniAdoptante = dniMayuscula;
    }

    /**
     * Devuelve el identificador de la ubicación asignada al animal.
     * @return --> id de la ubicación
     */
    public int getIdUbicacion() {
        return idUbicacion;
    }

    @Override
    /**
     * Compara este animal con otro objeto por igualdad de todos sus campos.
     * @param o --> objeto a comparar
     * @return --> devuelve true si todos los campos son iguales, false en caso contrario
     */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && esterilizado == animal.esterilizado && adoptado == animal.adoptado && idUbicacion == animal.idUbicacion && Objects.equals(nombre, animal.nombre) && Objects.equals(raza, animal.raza) && sexo == animal.sexo && Objects.equals(color, animal.color) && Objects.equals(edad, animal.edad) && Objects.equals(marcasDistintivas, animal.marcasDistintivas) && Objects.equals(numeroChip, animal.numeroChip) && Objects.equals(historia, animal.historia) && Objects.equals(observaciones, animal.observaciones) && Objects.equals(fechaIngreso, animal.fechaIngreso) && Objects.equals(fechaAlta, animal.fechaAlta) && Objects.equals(dniAdoptante, animal.dniAdoptante);
    }

    @Override
    /**
     * Devuelve el código hash del animal basado en todos sus campos.
     * @return --> código hash
     */
    public int hashCode() {
        return Objects.hash(id, nombre, raza, sexo, color, edad, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, fechaIngreso, adoptado, fechaAlta, dniAdoptante, idUbicacion);
    }

    @Override
    /**
     * Devuelve una representación textual del animal con su nombre, ID, sexo y raza.
     * @return --> cadena de texto
     */
    public String toString() {
        return nombre.toUpperCase() + " (Id: " + id + ", " + sexo + ", " + raza + ")";
    }

    /**
     * Metodo que convierte en texto el boolean isEsterilizado
     * @return --> devuelve "Sí" si está esterilizado, "No" en caso contrario
     */
    public String isEsterilizadoTexto() {
        return Mensajes.afirmativoNegativo(this.esterilizado);
    }


}
