package org.proyectorefugio.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

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
    //todo-> comentar los métodos
    //region----------MENSAJES CONFIRMACIONES--------------

    public static void operacionCompletada(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Operación completada");
        alerta.setHeaderText("¡Completado con éxito!");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    //endregion

    //region----------MENSAJES ALERTA ERRORES--------------
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

    //endregion

    //region----------MENSAJES BÚSQUEDAS--------------
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
    public static void alertaYaExiste(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("Elemento duplicado");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    //endregion

    //region----------MENSAJES ACTUALIZACIONES--------------
    public static void actualizacionCorrecta(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("ÉXITO");
        alerta.setHeaderText("Modificación completada");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public static void actualizacionIncorrecta(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("¡No se ha podido completar!");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    //endregion

    //region----------CONFIRMACIÓN ELIMINAR--------------
    public static boolean confirmarEliminar(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("¿Deseas eliminar este elemento?");
        alerta.setContentText(mensaje);

        ButtonType btnEliminar = new ButtonType("Eliminar");
        ButtonType btnCancelar = new ButtonType("Cancelar");

        alerta.getButtonTypes().setAll(btnEliminar, btnCancelar);

        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == btnEliminar;
    }
    public static boolean noPuedeEliminar(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("No puedes eliminar esta persona");
        alerta.setContentText(mensaje);

        ButtonType btnEliminar = new ButtonType("Eliminar");
        ButtonType btnCancelar = new ButtonType("Cancelar");

        alerta.getButtonTypes().setAll(btnEliminar, btnCancelar);

        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == btnEliminar;
    }
    //endregion



}
