package model;

import java.util.Date;

public class Animal {
    private int id;
    private String nombre;
    private String raza;
    private Sexo sexo;
    private String marcasDistintivas;
    private int numeroChip;
    private boolean esterilizado = false;
    private String historia;
    private String observaciones;
    private Date fechaIngreso;
    private boolean adoptado = false;
    private Date fechaAlta;

    public Animal( String nombre, String raza, Sexo sexo, String marcasDistintivas,
                  int numeroChip, boolean esterilizado, String historia, String observaciones, Date fechaIngreso) {
        id++;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.marcasDistintivas = marcasDistintivas;
        this.numeroChip = numeroChip;
        this.esterilizado = esterilizado;
        this.historia = historia;
        this.observaciones = observaciones;
        this.fechaIngreso = fechaIngreso;
    }
}
