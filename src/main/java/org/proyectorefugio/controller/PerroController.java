package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Ayuda;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.AyudaDAO;
import org.proyectorefugio.modelDAO.PerroDAO;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.SceneManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class PerroController {
    @FXML
    public TableView<Perro> tablaPerros;
    @FXML
    public TableColumn<Perro, String> nombreCol;
    @FXML
    public TableColumn<Perro, Integer> idCol;
    @FXML
    public TableColumn<Perro, String> sexoCol;
    @FXML
    public TableColumn<Perro, String> razaCol;

    @FXML
    public Label informacionAdicional;
    public CheckBox noAdoptado;
    public CheckBox adoptado;
    public Button añadirPerroBD;
    public AnchorPane ventanaBuscar;

    //region---------------FMXL BUSCAR-------------------


    @FXML
    public TextField buscarId;
    public TextField buscarNombre;
    public TextField buscarChip;
    public TextField buscarRaza;
    public TextField buscarColor;
    //endregion

    //region---------------FMXL MODIFICAR-------------------

    @FXML
    public AnchorPane panelModificacion;
    public TextField modificarChip;
    public DatePicker modificarFecha;
    public TextArea modificarObservaciones;
    public CheckBox modificarEsterilizado;
    public TextField modificarDniAdoptante;
    public Spinner modificarUbicacion;
    public TextField modificarPeso;
    public CheckBox modificarAgresivo;

    //endregion

    @FXML
    /**
     * Metodo que inicia la vista del fxml cuando abrimos la ventana
     */
    private void initialize() {
        noAdoptado.setSelected(true);
        tablaPerros();
        mostrarInformacionAdicional();
    }

    @FXML
    /**
     * Selecciona el check de No adoptados y desmarca el de adoptados.
     * Recarga la tabla.
     */
    private void seleccionNoAdoptado() {
        if (noAdoptado.isSelected()) {
            adoptado.setSelected(false);
        } else {
            adoptado.setSelected(true);
        }
        tablaPerros();
    }

    @FXML
    /**
     * Selecciona el check de Adoptados y desmarca el de no adoptados.
     * Mantiene siempre la opción de No Adoptado activa y recarga la tabla
     */
    private void seleccionAdoptado() {
        if (adoptado.isSelected()) {
            noAdoptado.setSelected(false);
        } else {
            noAdoptado.setSelected(true);
        }
        tablaPerros();
    }

    /**
     * Método que extrae los datos de los perros de la base de datos y clasifica la
     * información por columnas en una tabla.
     */
    public void tablaPerros() {
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sexoCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        razaCol.setCellValueFactory(new PropertyValueFactory<>("raza"));

        boolean buscarAdoptados = adoptado.isSelected();

        ObservableList<Perro> listaPerros =
                FXCollections.observableArrayList(PerroDAO.findAll(buscarAdoptados));

        tablaPerros.setItems(listaPerros);
    }


    /**
     * Método que muestra toda la información del Perro cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        ventanaBuscar.setVisible(false);
        tablaPerros.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionado) -> {
                    if (seleccionado != null) {

                        String datosMostrar = "Id: " + seleccionado.getId() + "\n" +
                                "Nombre: " + seleccionado.getNombre() + "\n" +
                                "Edad: " + seleccionado.getEdad() + "\n" +
                                "Sexo: " + seleccionado.getSexo() + "\n" +
                                "Raza: " + seleccionado.getRaza() + "\n" +
                                "Color: " + seleccionado.getColor() + "\n" +
                                "Peso: " + seleccionado.getPeso() + " Kg \n";

                        if (seleccionado.getNumeroChip() != null) {
                            datosMostrar += "Número Chip:  " + seleccionado.getNumeroChip() + "\n";
                        }
                        datosMostrar += "Esterilizado: " + seleccionado.isEsterilizadoTexto() + "\n" +
                                "Fecha Ingreso: " + seleccionado.getFechaIngreso() + "\n";
                        if (seleccionado.getObservaciones() != null) {
                            datosMostrar += "Observaciones: " + seleccionado.getObservaciones() + "\n";
                        }
                        if (seleccionado.getHistoria() != null) {
                            datosMostrar += "Historia: " + seleccionado.getHistoria() + "\n";
                        }
                        datosMostrar += "Agresivo: " + seleccionado.isAgresivoTexto() + "\n";

                        if (seleccionado.getFechaAlta() != null) {
                            datosMostrar += "Fecha Alta: " + seleccionado.getFechaAlta();
                        }
                        informacionAdicional.setText(datosMostrar);
                    }
                });
    }


    //region---------------INSERTAR-------------------

    /**
     * Método que cuando al pulsar el botón Añadir abrirá el formulario correspondiente
     *
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonInsertarPerro(ActionEvent event) {
        RegistroAnimalController.tipo = "perro";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/registroAnimal-view.fxml", "Formulario de Registro");
    }

    //endregion

    //region---------------ADOPTAR-------------------

    /**
     * Método que cuando al pulsar el botón Adoptar abrirá el formulario correspondiente
     *
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonAdoptar(ActionEvent event) {
        FormularioPersonaYAdoptarController.persona = "adoptante";
        SceneManager.abrirVentanaEmergente("/org/proyectorefugio/formularioPersonaYAdoptar-view.fxml", "Formulario de Registro");
        tablaPerros();
    }

    //endregion

    //region---------------BUSCAR ANIMAL-------------------

    public void botonBusqueda(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    public List<Perro> buscarAnimal() {
        String idAnimalTexto = buscarId.getText();
        int idAnimal = 0;
        if (idAnimalTexto != null) {
            idAnimal = Utils.conversorInt(idAnimalTexto);
        }
        String nombreAnimal = buscarNombre.getText();
        String chipAnimal = buscarChip.getText();
        String razaAnimal = buscarRaza.getText();
        String colorAnimal = buscarColor.getText();

        boolean buscarNoAdoptados = noAdoptado.isSelected();

        List<Perro> resultadosEncontrados = new ArrayList<>();

        if (idAnimalTexto != null && !idAnimalTexto.isEmpty() && idAnimal != 0) {
            Perro p = PerroDAO.findByID(idAnimal);
            if (p != null) {
                resultadosEncontrados.add(p);
                return resultadosEncontrados;
            }
        }
        if (chipAnimal != null && !chipAnimal.isEmpty()) {
            Perro p = PerroDAO.findByChip(chipAnimal);
            if (p != null) {
                resultadosEncontrados.add(p);
                return resultadosEncontrados;
            }
        }
        if (nombreAnimal != null && !nombreAnimal.isEmpty()) {
            resultadosEncontrados.addAll(PerroDAO.findByName(nombreAnimal, buscarNoAdoptados));
        }

        if (razaAnimal != null && !razaAnimal.isEmpty()) {
            resultadosEncontrados.addAll(PerroDAO.findByBreed(razaAnimal, buscarNoAdoptados));

        }
        if (colorAnimal != null && !colorAnimal.isEmpty()) {
            resultadosEncontrados.addAll(PerroDAO.findByColour(colorAnimal, buscarNoAdoptados));

        }

        HashSet<Perro> busquedaFinal = new HashSet<>(resultadosEncontrados);
        busquedaFinal.remove(null);
        return new ArrayList<>(busquedaFinal);
        //todo --> alertas
    }

    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Perro> resultados =
                FXCollections.observableArrayList(buscarAnimal());

        tablaPerros.setItems(resultados);
    }
    //endregion

    //region---------------MODIFICAR-------------------

    public void botonModificar(ActionEvent event) {
        panelModificacion.setVisible(true);
        ventanaBuscar.setVisible(false);
        informacionAdicional.setVisible(false);
        modificarUbicacion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
        //todo --> cargar datos
    }
    
    public boolean modificarAnimal(){
        Perro pSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        Animal aSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();


        String chip = modificarChip.getText();
        LocalDate fecha = modificarFecha.getValue();
        String observaciones = modificarObservaciones.getText();
        String dniAdoptante = modificarDniAdoptante.getText();
        int ubicacion = (int)modificarUbicacion.getValue();
        Double peso = Utils.conversorDouble(modificarPeso.getText());

        if ((chip == null || chip.isEmpty()) && fecha == null && (observaciones == null || observaciones.isEmpty())
                && (dniAdoptante == null || dniAdoptante.isEmpty()) && ubicacion == 0 && (peso == null || peso == 0.0) &&
                !modificarEsterilizado.isSelected() &&  !modificarAgresivo.isSelected()) {
            //todo --> alerta de campos vacíos
            return false;
        }

        boolean actualizado = false;

        if (chip != null && !chip.trim().isEmpty()) {
            if (AnimalDAO.findByChip(chip) == null) {
                if (AnimalDAO.updateNumeroChip(aSeleccionado, chip)) {
                    // todo -> alerta éxito — chip actualizado correctamente
                    actualizado = true;
                } else {
                    // todo -> alerta error — no se pudo actualizar el chip
                }
            } else {
                // todo -> alerta error — ya existe un animal con ese chip
            }
        }

        if (fecha != null) {
            if (fecha.isAfter(LocalDate.now())) {
                // todo -> alerta error — la fecha no puede ser futura
                return false;
            } else if (AnimalDAO.updateFechaAlta(aSeleccionado, fecha)) {
                // todo -> alerta éxito — fecha actualizada correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar la fecha
            }
        }

        if (observaciones != null && !observaciones.trim().isEmpty()) {
            if (AnimalDAO.updateObservaciones(aSeleccionado, observaciones)) {
                // todo -> alerta éxito — observaciones actualizadas correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar las observaciones
            }
        }

        if (!aSeleccionado.isEsterilizado() && modificarEsterilizado.isSelected()) {
            if (AnimalDAO.updateEsterilizado(aSeleccionado, true)) {
                // todo -> alerta éxito — esterilizado actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar esterilizado
            }
        } // todo -> esto debería cambiar si cargo los datos

        if (!pSeleccionado.isAgresivo() && modificarAgresivo.isSelected()) {
            if (PerroDAO.updateAgresivo(pSeleccionado, true)) {
                // todo -> alerta éxito — agresivo actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar agresivo
            }
        } // todo -> esto debería cambiar si cargo los datos

        if (dniAdoptante != null && !dniAdoptante.trim().isEmpty()) {
            if (AnimalDAO.updateAdoptante(aSeleccionado, dniAdoptante)) {
                // todo -> alerta éxito — adoptante actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar el adoptante
            }
        }

        if (ubicacion > 0) {
            if (AnimalDAO.updateUbicacion(aSeleccionado, ubicacion)) {
                // todo -> alerta éxito — ubicación actualizada correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar la ubicación
            }
        }

        if (peso != null && peso > 0) {
            if (PerroDAO.updatePeso(pSeleccionado, peso)) {
                // todo -> alerta éxito — peso actualizado correctamente
                actualizado = true;
            } else {
                // todo -> alerta error — no se pudo actualizar el peso
            }
        }

        return actualizado;

    }
    public void limpiarCampos() {
        modificarChip.clear();
        modificarFecha.setValue(null);
        modificarObservaciones.clear();
        modificarDniAdoptante.clear();
        modificarUbicacion.getValueFactory().setValue(0);
        modificarPeso.clear();
        modificarEsterilizado.setSelected(false);
        modificarAgresivo.setSelected(false);
    }


    public void botonGuardarModificacion(ActionEvent event) {
        modificarAnimal();
        limpiarCampos();
        tablaPerros();
    }
    //endregion



    //region---------------ELIMINAR PERRO -------------------


    public void botonEliminar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        Animal animalSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        // todo -> confirmacion de alerta de si quiere borrar o no

        if (animalSeleccionado == null) {
            // todo -> alerta: selecciona un elemento primero
            return;
        }

        //todo-> confirmacion
        AnimalDAO.deleteAnimalById(animalSeleccionado.getId());
        initialize();
    }
    //endregion
}


