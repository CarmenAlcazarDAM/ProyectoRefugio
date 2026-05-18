package org.proyectorefugio.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//todo-> comentarios
public class Utils {

    /**
     * Metodo que valida si un DNI o NIE introducido es correcto.
     * Un DNI es correcto si tiene una cadena de caracteres de 8 números y 1 letra de A-Z.
     * Un NIE  es correcto si tiene una cadena de caracteres con 1 letra (X, Y o Z), 7 números y 1 letra de A-Z.
     * (Regular expression validadas con: regex101.com)
     *
     * @param dni --> dni o nie a validar pasado por parámetro.
     * @return --> devuelve true si el dni está correctamente escrito
     */
    public static boolean validarDNI(String dni) {

        if ((dni != null) && (dni.matches("[0-9]{8}[A-Z]") || dni.matches("[XYZ][0-9]{7}[A-Z]"))) {
            return true;
        }
        return false;
    }

    public static boolean validarTelefono(String telefono) {
        if (telefono != null) {
            //por si ponen espacios entre números
            String telefonoSinEspacios = telefono.replaceAll("[\\s-]", "");
            if (telefonoSinEspacios.matches("\\+?\\d{9,15}")) {
                return true;
            }
        }
        return false;
    }


    /**
     * Metodo de valida si un número de chip es correcto.
     * Para ello debe ser una cadena de caracteres con 15 números.
     *
     * @param chip --> número de chip a validar pasado por parámetro.
     * @return --> devuelve true si el dni está correctamente escrito
     */
    public static boolean validaChip(String chip) {
        if ((chip != null) && chip.matches("[0-9]{15}")) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que convierte el texto introducido por teclado a tipo de dato int
     *
     * @param convertir --> texto introducido por teclado
     * @return --> devuelve el texto convertido en int
     */
    public static int conversorInt(String convertir) {
        if (convertir == null || convertir.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(convertir);
    }

    /**
     * Metodo que convierte el texto introducido por teclado a tipo de dato double
     *
     * @param convertir --> texto introducido por teclado
     * @return --> devuelve el texto convertido en double
     */
    public static double conversorDouble(String convertir) {
        if (convertir == null || convertir.isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(convertir.trim());
    }

    /**
     * Metodo que valida la hora introducida que esté en el formato correcto
     *
     * @param hora --> hora a comprobar pasada por parámetro
     * @return --> devuelve la hora introducida como String y
     * la devuelve validada en formato LocalTime
     */
    public static LocalTime validarHora(String hora) {
        if (hora == null) {
            return null;
        }
        if (hora.matches("([01]?[0-9]|2[0-3]):([0-5][0-9])")) {
            return LocalTime.parse(hora, DateTimeFormatter.ofPattern("H:mm"));
        }
        return null;
    }

    /**
     * Metodo que valida una fecha introducida para que no sea posterior
     * al día actual del sistema
     *
     * @param fecha --> fecha a comprobar pasada por parámetro
     * @return --> devuelve true si la fecha introducida es correcta
     */
    public static boolean validarFecha(LocalDate fecha) {
        if (fecha == null) {
            return false;
        }
        return !fecha.isAfter(LocalDate.now());
    }
}
