package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.GatoDAO;
import org.proyectorefugio.modelDAO.PerroDAO;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.Mensajes;

import java.sql.Date;
import java.time.LocalDate;


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
    public TextArea infoMarcas;
    public Button botonGuardar;
    @FXML
    public Button botonCancelar;

    double pesoNumero = 0.0;


    @FXML
    /**
     * Metodo que inicia el formulario.
     * Dependiendo del tipo que sea mostrará los datos a rellenar propios de Perro
     * o de Gato
     */
    public void initialize() {
        asignarOpcionesSexo();
        definirTipoAnimal();
    }

    /**
     * Metodo que asigna las opciones de sexo al ComboBox
     */
    public void asignarOpcionesSexo() {
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Hembra",
                "Macho"
        );
        opcionesSexo.setItems(opciones);
    }

    /**
     * Metodo que recibe que tipo de animal está abriendo el formulario
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
     * Metodo que va a recoger toda la información procedente de del formulario
     */

    public Animal obtenerInformacionGenericaDelFormulario() {
        String nombre = infoNombre.getText();
        String opSexo = opcionesSexo.getValue().toString().toLowerCase();
        Sexo sexo = null;
        if (opSexo.equals("hembra")) {
            sexo = Sexo.hembra;
        } else if (opSexo.equals("macho")) {
            sexo = Sexo.macho;
        }
        String raza = infoRaza.getText();
        String color = infoColor.getText();
        String edad = infoEdad.getText();
        String marcasDistintivas = infoMarcas.getText();
        String numeroChip = infoChip.getText();
        LocalDate fechaIngreso = infoFecha.getValue();
        if (fechaIngreso == null) {
            fechaIngreso = LocalDate.now();
        }

        Boolean esterilizado;
        if (infoEsterilizado.isSelected()) {
            esterilizado = true;
        } else {
            esterilizado = false;
        }

        int idUbicacion = 0;
        String textoUbi = infoUbicacion.getText();
        if (!textoUbi.trim().isEmpty()) {
            idUbicacion = Utils.conversorInt(textoUbi);
        }

        String observaciones = infoObservaciones.getText();
        String historia = infoHistoria.getText();
        String pesoTexto = pesoField.getText();
        if (!pesoTexto.trim().isEmpty()) {
            pesoNumero = Utils.conversorDouble(pesoTexto);
        }

        return new Animal(nombre, raza, sexo, color, edad, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, idUbicacion, fechaIngreso);
    }

    public Animal insertarAnimal(){

        Animal animal = obtenerInformacionGenericaDelFormulario();
        if (animal == null) {
            Mensajes.alertaErrorDeRegistro("Por favor, completa todos los campos obligatorios.");
            return null;
        }

        int idAsignado = AnimalDAO.addAnimal(animal);
        if (idAsignado <= 0) {
            Mensajes.alertaErrorDeRegistro("No se pudo insertar el animal.");
            return null;
        }

        animal.setId(idAsignado);
        return animal;
    }

    @FXML
    /**
     * Metodo que guarda en la base de datos la información de los animales
     * @param event --> evento que ocurre cuando pulsas el boton
     */
    public void guardarInformacion(ActionEvent event) {
        boolean variable = checkVariable.isSelected();

    Animal animal = insertarAnimal();
        if (animal == null) {
            Mensajes.alertaErrorDeRegistro("No se ha podido completar el registro");
            return;
        }


        if ("perro".equals(tipo)) {
            double pesoPerro = pesoNumero;

            Perro perroInsertar = new Perro(animal.getId(), animal.getNombre(),
                    animal.getRaza(), animal.getSexo(), pesoPerro, variable);

            PerroDAO.addPerro(perroInsertar, animal);
            Mensajes.operacionCompletada("El perro ha sido registrado correctamente");
        } else if ("gato".equals(tipo)) {
            Gato gatoInsertar = new Gato(animal.getId(), animal.getNombre(),
                    animal.getRaza(), animal.getSexo(), variable);

            GatoDAO.addGato(gatoInsertar, animal);
            Mensajes.operacionCompletada("El gato ha sido registrado correctamente");

        } else {
            Mensajes.alertaErrorDeRegistro("No se ha podido completar el registro");
            return;
        }
        limpiarCampos();
    }

    /**
     * Metodo que limpia los campos del formulario
     */
    public void limpiarCampos() {
        infoNombre.clear();
        checkVariable.setSelected(false);
        pesoField.clear();
        opcionesSexo.setValue(null);
        infoRaza.clear();
        infoColor.clear();
        infoEdad.clear();
        infoChip.clear();
        infoFecha.setValue(null);
        infoEsterilizado.setSelected(false);
        infoUbicacion.clear();
        infoObservaciones.clear();
        infoHistoria.clear();
        infoMarcas.clear();

    }

    @FXML
    /**
     * Metodo para cerrar la ventana cuando pulsamos cancelar
     */
    public void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }
}
