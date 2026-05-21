package org.proyectorefugio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Persona;
import org.proyectorefugio.modelDAO.*;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.Mensajes;

import java.time.LocalDate;

/**
 * Controlador del formulario de registro de personas (voluntarios y adoptantes).
 * También gestiona el proceso de adopción vinculando una persona con un animal.
 */

public class FormularioPersonaYAdoptarController {
    @FXML
    /**
     * Tipo de persona que abre el formulario.
     * Cuando el correspondiente controlador inicie el formulario
     * le va a dar a persona un valor "voluntario" o "adoptante".
     */
    public static String persona;

    @FXML
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

    /**
     * Metodo de inicialización
     * Se encarga de llevar a cabo las primeras acciones que
     * aparecerán al cargar el archivo fxml
     */
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
     * Metodo que recibe y configura que tipo de persona está abriendo el formulario
     * Si es "adoptante" muestra los campos adicionales para introducir el ID del animal.
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

    /**
     * Metodo que recoge y valida los datos introducidos en el formulario.
     * Muestra una alerta si algún campo obligatorio está vacío.
     * @return --> devuelve un objeto Persona obtenida gracias a los datos introducidos
     */
    public Persona obtenerInformacionPersonaDelFormulario() {
        try {
            String nombre = infoNombre.getText();
            String apellidos = infoApellidos.getText();
            String dni = infoDNI.getText();
            String telefono = infoTelefono.getText();
            String correo = infoCorreo.getText();
            String domicilio = infoDireccion.getText();


            if (nombre.trim().isEmpty() || apellidos.trim().isEmpty() ||
                    dni.trim().isEmpty() || telefono.trim().isEmpty()||
                    correo.trim().isEmpty() || domicilio.trim().isEmpty()) { //.trim() --> elimina los espacios del principio y del final, no los entre palabras

                Mensajes.aletaObligatoriosCamposVacios("Debe completar todos los campos obligatorios");

                return null;
            }
            return new Persona(dni, nombre, apellidos, telefono, correo, domicilio);

        } catch (Exception e) {
            Mensajes.alertaErrorDeRegistro(e.getMessage());
            return null;
        }
    }


    @FXML
    /**
     * Metodo que guarda en la base de datos la información de los voluntarios o adoptantes
     * Si la persona ya existe, actualiza sus datos; si no, la registra.
     * En caso de adoptante, también vincula la persona con el animal indicado y registra la fecha de adopción.
     * @param event --> evento que ocurre cuando pulsas el botón
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

        limpiarCampos();

        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
        Mensajes.operacionCompletada("Registro completado");

    }

    /**
     * Metodo que limpia los campos de texto del formulario
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
     * Metodo que cierra la ventana cuando pulsamos cancelar sin guardar ningún dato
     */
    public void accionCancelar(ActionEvent event) {
        Stage stage = (Stage)botonCancelar.getScene().getWindow();
        stage.close();
    }
}
