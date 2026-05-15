package org.proyectorefugio.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Persona;
import org.proyectorefugio.modelDAO.AdoptanteDAO;
import org.proyectorefugio.modelDAO.PersonaDAO;
import org.proyectorefugio.modelDAO.VoluntarioDAO;

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

            return new Persona(dni, nombre, apellidos, telefono, correo, domicilio);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar guardar el registro: " + e.getMessage());
            e.printStackTrace();
            //todo -> alertas de error
        }
    }

    /**
     * Metodo que guarda en la base de datos la información de los voluntarios o adoptantes
     *
     * @param event --> evento que ocurre cuando pulsas el boton
     */
    public void botonGuardarInformacion(ActionEvent event) {
        Persona registrar = obtenerInformacionPersonaDelFormulario();
        PersonaDAO.addPersona(registrar);
        if ("adoptante".equals(persona)) {
            AdoptanteDAO.addAdoptante(registrar);

        } else if ("voluntario".equals(persona)) {
            VoluntarioDAO.addVoluntario(registrar);

        }
        // todo ->  añadir un Alert flotante confirmando el éxito y cierre el formulario
        limpiarCampos();

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
    }
}
