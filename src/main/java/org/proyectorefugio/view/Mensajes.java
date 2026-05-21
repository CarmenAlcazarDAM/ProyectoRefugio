package org.proyectorefugio.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Clase utilitaria que centraliza la creación y presentación de alertas
 * y diálogos de confirmación en la interfaz de usuario.
 */
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

    //region----------MENSAJES CONFIRMACIONES--------------
    /**
     * Muestra una alerta de confirmación indicando que una operación se completó con éxito.
     *
     * @param mensaje --> texto personalizado
     */
    public static void operacionCompletada(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Operación completada");
        alerta.setHeaderText("¡Completado con éxito!");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    //endregion

    //region----------MENSAJES ALERTA ERRORES--------------
    /**
     * Muestra una alerta de error indicando que hay campos obligatorios sin rellenar.
     *
     * @param mensaje --> texto personalizado
     */
    public static void aletaObligatoriosCamposVacios(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("Campo obligatorio vacío");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Muestra una alerta de error indicando que no se pudo completar un registro.
     *
     * @param mensaje --> texto personalizado
     */
    public static void alertaErrorDeRegistro(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("No se ha podido completar el registro");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    //endregion

    //region----------MENSAJES BÚSQUEDAS--------------
    /**
     * Muestra una alerta de aviso indicando que la búsqueda no produjo resultados.
     *
     * @param mensaje --> texto personalizado
     */
    public static void alertaNoExiste(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("Búsqueda no encontrada");
        alerta.setContentText(mensaje);
        alerta.showAndWait();

    }

    /**
     * Muestra una alerta de aviso indicando que no hay ningún elemento seleccionado.
     *
     * @param mensaje --> texto personalizado
     */
    public static void alertaNoSeleccionado(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("Ningún elemento seleccionado");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Muestra una alerta de aviso indicando que el elemento ya existe en la base de datos.
     *
     * @param mensaje --> texto personalizado
     */
    public static void alertaYaExiste(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ATENCIÓN");
        alerta.setHeaderText("Elemento duplicado");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    //endregion

    //region----------MENSAJES ACTUALIZACIONES--------------

    /**
     * Muestra una alerta de confirmación indicando que una modificación se realizó con éxito.
     *
     * @param mensaje --> texto personalizado
     */
    public static void actualizacionCorrecta(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("ÉXITO");
        alerta.setHeaderText("Modificación completada");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Muestra una alerta de error indicando que la modificación no pudo completarse.
     *
     * @param mensaje --> texto personalizado
     */
    public static void actualizacionIncorrecta(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("¡No se ha podido completar!");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    //endregion

    //region----------CONFIRMACIÓN ELIMINAR--------------
    /**
     * Muestra un diálogo de confirmación antes de eliminar un elemento.
     * Presenta los botones "Eliminar" y "Cancelar" al usuario.
     *
     * @param mensaje --> texto personalizado
     * @return --> devuelve true si el usuario pulsa "Eliminar", false si cancela
     */
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
    /**
     * Muestra un diálogo de aviso cuando no es posible eliminar a una persona
     * por tener registros asociados (animales adoptados o tareas de voluntariado).
     *
     * @param mensaje --> texto personalizado
     * @return --> devuelve true si el usuario pulsa "Eliminar", false si cancela
     */
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
