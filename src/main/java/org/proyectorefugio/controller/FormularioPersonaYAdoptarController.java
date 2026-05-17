package org.proyectorefugio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.model.Persona;
import org.proyectorefugio.modelDAO.*;
import org.proyectorefugio.utils.Utils;

import java.time.LocalDate;

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
    public TextField infoNombre;
    public TextField infoApellidos;
    public TextField infoDNI;
    public TextField infoTelefono;
    public TextField infoCorreo;
    public TextField infoDireccion;
    @FXML
    public Button botonCancelar;

    public void initialize() {
        definirTipoPersona();
        infoDNI.textProperty().addListener((observable, oldValue, newValue) -> {
            Persona p =PersonaDAO.findByDni(newValue);
            if(p!=null){
                    infoNombre.setText(p.getNombre());
                    infoApellidos.setText(p.getApellidos());
                    infoTelefono.setText(p.getTelefono());
                    infoCorreo.setText(p.getCorreo());
                    infoDireccion.setText(p.getDireccion());
            }
        });
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

        } else if ("voluntario".equals(persona)) {
            titulo.setText("VOLUNTARIO");
        }
    }

    public Persona obtenerInformacionPersonaDelFormulario() {
        try {
            String nombre = infoNombre.getText();
            String apellidos = infoApellidos.getText();
            String dni = infoDNI.getText();
            String telefono = infoTelefono.getText();
            String correo = infoCorreo.getText();
            String domicilio = infoDireccion.getText();


            if (nombre.trim().isEmpty() || apellidos.trim().isEmpty() ||
                    dni.trim().isEmpty() || telefono.trim().isEmpty()) { //.trim() --> elimina los espacios del principio y del final, no los entre palabras
               //todo --> aqui puedo añadir alertas de campos obligatorios
                return null;
            }
            return new Persona(dni, nombre, apellidos, telefono, correo, domicilio);

        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar guardar el registro: " + e.getMessage());

            //todo -> alertas de error
        }
        return null;
    }


    @FXML
    /**
     * Metodo que guarda en la base de datos la información de los voluntarios o adoptantes
     *
     * @param event --> evento que ocurre cuando pulsas el boton
     */
    public void guardarInformacion(ActionEvent event) {
        Persona registrar = obtenerInformacionPersonaDelFormulario();
        if(registrar==null){
            return;
        }
        if (PersonaDAO.findByDni(registrar.getDni()) != null) {
            PersonaDAO.updatePerson(registrar);
        } else {
            PersonaDAO.addPersona(registrar);
        }

        if ("adoptante".equals(persona)) {
            if (AdoptanteDAO.findByDni(registrar.getDni()) == null) {
                AdoptanteDAO.addAdoptante(registrar);
            }

            Animal a = AnimalDAO.findByID(Utils.conversorInt(infoIdAnimal.getText()));
            if (a != null) {
                AnimalDAO.updateAdoptante(a, registrar.getDni());
                AnimalDAO.updateFechaAlta(a, LocalDate.now());
            }
        }


        if ("voluntario".equals(persona)) {
            if (VoluntarioDAO.findByDni(registrar.getDni()) == null) {
                VoluntarioDAO.addVoluntario(registrar);
            }
        }
        // todo ->  añadir un Alert flotante confirmando el éxito y cierre el formulario
        limpiarCampos();

        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();

    }

    /**
     * Metodo que limpia los campos del formulario
     */
    public void limpiarCampos() {
        infoNombre.clear();
        infoApellidos.clear();
        infoDNI.clear();
        infoTelefono.clear();
        infoCorreo.clear();
        infoDireccion.clear();
        infoIdAnimal.clear();
    }

    @FXML
    /**
     * Metodo para cerrar la ventana cuando pulsamos cancelar
     */
    public void accionCancelar(ActionEvent event) {
        Stage stage = (Stage)botonCancelar.getScene().getWindow();
        stage.close();
    }
}
