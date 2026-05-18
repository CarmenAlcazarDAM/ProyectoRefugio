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

    //region----------MENSAJES ALERTA ERRORES--------------
    //todo-> comentar los métodos

    public static void aletaObligatoriosCamposVacios(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ATENCIÓN");
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

    public static void alertaNoExiste(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("Búsqueda no encontrada");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void alertaNoSeleccionado(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("Ningún elemento seleccionado");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    //endregion

    //region----------MENSAJES CONFIRMACIONES--------------

    public static void operacionCompletada(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Operación completada");
        alerta.setHeaderText("¡Completado con éxito!");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    //endregion


    //region----------MENSAJES BÚSQUEDAS--------------

    //endregion
}
