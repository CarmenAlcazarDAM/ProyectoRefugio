package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.proyectorefugio.enums.Sexo;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.GatoDAO;
import org.proyectorefugio.modelDAO.PerroDAO;

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
    public static TextField pesoField;
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
    //todo --> este método se puede refactorizar
    //todo -> validaciones de entrada
    public void obtenerInformacionGenericaDelFormulario() {
        String nombre = infoNombre.getText();
        String opSexo = opcionesSexo.getValue().toString();
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
        Animal animal = new Animal(nombre,raza,sexo,color,edad,marcasDistintivas,numeroChip,esterilizado,historia,observaciones,idUbicacion);
        AnimalDAO.addAnimal(animal);
        if ("perro".equals(tipo)) {
            double peso = obtenerInformacionPerroDelFormulario();
            Perro perroInsertar = new Perro(animal.getId(), animal.getNombre(), animal.getRaza(), animal.getSexo(),peso,variable);
            PerroDAO.addPerro(perroInsertar,animal);
        }else if("gato".equals(tipo)){
            Gato gatoInsertar = new Gato(animal.getId(), animal.getNombre(), animal.getRaza(), animal.getSexo(), variable);
            GatoDAO.addGato(gatoInsertar,animal);
        }
    }

    /**
     * Método que va a recoger la información específica del peso
     */
    public static Double obtenerInformacionPerroDelFormulario() {
        Double peso;
        String pesoTexto = pesoField.getText();
        if (pesoTexto.isEmpty()) {

            return Double.parseDouble(pesoTexto);
        }
        return null;
    }


}
