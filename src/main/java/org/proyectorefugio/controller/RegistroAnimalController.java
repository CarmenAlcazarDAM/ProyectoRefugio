package org.proyectorefugio.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegistroAnimalController {

    //Cuando el correspondiente controlador inicie el formulario
    // le va a dar a tipo un valor "perro" o "gato".
    public static String tipo;

    @FXML
    public TextField infoNombre;
    public CheckBox checkVariable;
    public Text peso;
    public TextField pesoField;


    @FXML
    /**
     * Método que inicia el formulario.
     * Dependiendo del tipo que sea mostrará los datos a rellenar propios de Perro
     * o de Gato
     */
    public void initialize() {
        if ("perro".equals(tipo)) {
            checkVariable.setText("¿Es agresivo?");
            peso.setVisible(true);
            pesoField.setVisible(true);

        } else if ("gato".equals(tipo)) {
            checkVariable.setText("¿Leucemia?");
        }
    }


}
