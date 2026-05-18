package org.proyectorefugio.view;

public class Mensajes {

    /**
     * Metodo que convierte en un Sí o un No el boolean introducido
     * @param pregunta --> boolean pasado por parámetro
     * @return --> devuelve un String
     */
    public static String afirmativoNegativo(boolean pregunta){
        if(pregunta){
            return "Sí";
        }
        return "No";
    }

    /**
     * Metodo que convierte en un Positivo o un Negativo el boolean introducido
     * @param pregunta --> boolean pasado por parámetro
     * @return --> devuelve un String
     */
    public static String positivoNegativo(boolean pregunta){
        if(pregunta){
            return "Positivo";
        }
        return "Negativo";
    }
}
