package org.proyectorefugio.view;

import javafx.scene.control.Alert;

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

    //region----------MENSAJES ALERTA--------------
    //todo-> comentar los métodos

    public static void aletaObligatoriosCamposVacios(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error en el registro");
        alerta.setHeaderText("Campo obligatorio vacío");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void alertaErrorDeRegistro(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("No se ha podido completar el registro");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }




}
