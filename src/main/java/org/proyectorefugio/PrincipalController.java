package org.proyectorefugio;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class PrincipalController {
    public Text bienvenidos;
    @FXML
    private Label pestañaBienvenida;
    @FXML
    private Label pestañaAnimales;

    @FXML
    protected void onHelloButtonClick() {
        pestañaBienvenida.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void entrarAnimales() {bienvenidos.setText("Hola");}
}
