package org.proyectorefugio;

import javafx.application.Application;
import org.proyectorefugio.view.PrincipalApplication;

/**
 * Punto de entrada de la aplicación.
 * Delega el arranque en  PrincipalApplication para evitar
 * conflictos con el módulo de JavaFX al ejecutar desde un JAR.
 */
public class Launcher {
    /**
     * Metodo principal que lanza la aplicación JavaFX.
     */
    public static void main(String[] args) {
        Application.launch(PrincipalApplication.class, args);
    }

}
