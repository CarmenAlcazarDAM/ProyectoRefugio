package org.proyectorefugio.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegistroAnimalController {

    public TextField infoNombre;
    public CheckBox checkVariable;
    public Text peso;
    public TextField pesoField;

    public static String tipo;



    @FXML
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
