package org.proyectorefugio.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    /**
     * Método que valida si un DNI o NIE introducido es correcto.
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

    /**
     * Método de valida si un número de chip es correcto.
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

    public static LocalTime validarHora (String hora){
        if(hora==null){return null;}
        if (hora.matches("([01]?[0-9]|2[0-3]):([0-5][0-9])")) {
            return LocalTime.parse(hora, DateTimeFormatter.ofPattern("H:mm"));
        }
        return null;
    }
}
