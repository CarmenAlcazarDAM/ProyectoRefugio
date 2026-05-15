package org.proyectorefugio.controller;

import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FormularioPersonaYAdoptarController {
    //Cuando el correspondiente controlador inicie el formulario
    // le va a dar a persona un valor "voluntario" o "adoptante".
    public static String persona;
    public Text subtitulo;
    public Text titulo;
    public Text separador;
    public Text idAnimal;
    public Text asterisco;
    public TextField infoIdAnimal;

    public void initialize() {
        definirTipoPersona();
    }

    /**
     * Metodo que recibe que tipo de animal está abriendo el formulario
     */
    public void definirTipoPersona() {
        if ("adoptante".equals(persona)) {
            titulo.setText("ADOPTANTE");
            separador.setVisible(true);
            idAnimal.setVisible(true);
            asterisco.setVisible(true);
            infoIdAnimal.setVisible(true);

        } else if("voluntario".equals(persona)){
            titulo.setText("VOLUNTARIO");
        }

    }
}
