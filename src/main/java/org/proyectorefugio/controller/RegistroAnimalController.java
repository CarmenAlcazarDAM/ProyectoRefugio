package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.proyectorefugio.enums.Sexo;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class RegistroAnimalController {

    //Cuando el correspondiente controlador inicie el formulario
    // le va a dar a tipo un valor "perro" o "gato".
    public static String tipo;

    @FXML
    public TextField infoNombre;
    public CheckBox checkVariable;
    public Text peso;
    public TextField pesoField;
    public ComboBox opcionesSexo;
    public TextField infoRaza;
    public TextField infoColor;
    public TextField infoEdad;
    public TextField infoChip;
    public DatePicker infoFecha;
    public CheckBox infoEsterilizado;
    public TextField infoUbicacion;
    public TextArea infoObservaciones;
    public TextArea infoHistoria;


    @FXML
    /**
     * Método que inicia el formulario.
     * Dependiendo del tipo que sea mostrará los datos a rellenar propios de Perro
     * o de Gato
     */
    public void initialize() {
        asignarOpcionesSexo();
        definirTipoAnimal();


    }

    /**
     * Método que asigna las opciones de sexo al ComboBox
     */
    public void asignarOpcionesSexo() {
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Hembra",
                "Macho"
        );
        opcionesSexo.setItems(opciones);
    }

    /**
     * Método que recibe que tipo de animal está abriendo el formulario
     */
    public void definirTipoAnimal() {
        if ("perro".equals(tipo)) {
            checkVariable.setText("¿Es agresivo?");
            peso.setVisible(true);
            pesoField.setVisible(true);

        } else if ("gato".equals(tipo)) {
            checkVariable.setText("¿Leucemia?");
        }
    }

    /**
     * Método que va a recoger toda la información procedente de del formulario
     */
    public void obtenerInformacionGenericaDelFormulario() {
        String nombre = infoNombre.getText();
        String opSexo = opcionesSexo.getValue().toString();
        Sexo sexo;
            if (opSexo.equals("hembra")) {
                sexo = Sexo.hembra;
            } else if (opSexo.equals("macho")) {
                sexo = Sexo.macho;
            }
        String raza = infoRaza.getText();
        String color = infoColor.getText();
        String edad = infoEdad.getText();
        String numeroChip = infoChip.getText();
        LocalDate fechaIngreso = infoFecha.getValue();

        Boolean esterilizado;
            if(infoEsterilizado.isSelected()){
                esterilizado = true;
            }else{
                esterilizado = false;
            }

        int idUbicacion;
        String textoUbi = infoUbicacion.getText();
        idUbicacion = Integer.parseInt(textoUbi);

        String observaciones = infoObservaciones.getText();
        String historia = infoHistoria.getText();
        Boolean variable;

        //Independientemente de la información que pida, si está seleccionado será true.
        if(checkVariable.isSelected()){
            variable = true;
        }else{
            variable = false;
        }

        if ("perro".equals(tipo)) {obtenerInformacionPerroDelFormulario();}
    }

    /**
     * Método que va a recoger la información específica del peso
     */
    public void obtenerInformacionPerroDelFormulario() {
        Double peso;
        String pesoTexto = pesoField.getText();
        if (pesoTexto.isEmpty()) {

            peso = Double.parseDouble(pesoTexto);
        }
    }


}
