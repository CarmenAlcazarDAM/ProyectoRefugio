package org.proyectorefugio.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.modelDAO.AnimalDAO;
import org.proyectorefugio.modelDAO.PerroDAO;
import org.proyectorefugio.utils.Utils;
import org.proyectorefugio.view.Mensajes;
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
        adoptado.setSelected(false);
        tablaPerros();
        ocultarTodosPaneles();
        mostrarInformacionAdicional();
    }

    /**
     * Se encarga de ocultar todos los AnchorPane que puedan estar visibles
     */
    private void ocultarTodosPaneles() {
        ventanaBuscar.setVisible(false);
        panelModificacion.setVisible(false);

    }


    @FXML
    /**
     * Selecciona el check de No adoptados y desmarca el de adoptados.
     * Recarga la tabla.
     */
    public void seleccionNoAdoptado() {
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
    public void seleccionAdoptado() {
        if (adoptado.isSelected()) {
            noAdoptado.setSelected(false);
        } else {
            noAdoptado.setSelected(true);
        }
        tablaPerros();
    }

    /**
     * Metodo que extrae los datos de los perros de la base de datos y clasifica la
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
     * Metodo que muestra toda la información del Perro cuando seleccionas sobre él en la tabla
     * La información aparece en un recuadro Label que aparece cuando das el primer click.
     */
    public void mostrarInformacionAdicional() {
        ventanaBuscar.setVisible(false);
        tablaPerros.getSelectionModel().selectedItemProperty().addListener(
                (observable, anterior, seleccionado) -> {
                    if (seleccionado != null) {
                        Perro p = PerroDAO.findByID(seleccionado.getId());
                        if (p == null) return;

                        if (panelModificacion.isVisible()) return;

                        informacionAdicional.setVisible(true);
                        ventanaBuscar.setVisible(false);

                        String datosMostrar = "Id: " + p.getId() + "\n" +
                                "Nombre: " + p.getNombre() + "\n" +
                                "Edad: " + p.getEdad() + "\n" +
                                "Sexo: " + p.getSexo() + "\n" +
                                "Raza: " + p.getRaza() + "\n" +
                                "Color: " + p.getColor() + "\n" +
                                "Peso: " + p.getPeso() + " Kg \n";

                        if (p.getNumeroChip() != null) {
                            datosMostrar += "Número Chip:  " + p.getNumeroChip() + "\n";
                        }
                        datosMostrar += "Esterilizado: " + p.isEsterilizadoTexto() + "\n" +
                                "Fecha Ingreso: " + p.getFechaIngreso() + "\n";
                        if (p.getObservaciones() != null) {
                            datosMostrar += "Observaciones: " + p.getObservaciones() + "\n";
                        }
                        if (p.getHistoria() != null) {
                            datosMostrar += "Historia: " + p.getHistoria() + "\n";
                        }
                        datosMostrar += "Agresivo: " + p.isAgresivoTexto() + "\n";

                        if (p.getFechaAlta() != null) {
                            datosMostrar += "Fecha Alta: " + p.getFechaAlta();
                        }
                        informacionAdicional.setText(datosMostrar);
                    }
                });
    }


    //region---------------INSERTAR-------------------

    /**
     * Metodo que cuando al pulsar el botón Añadir abrirá el formulario correspondiente
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
     * Metodo que cuando al pulsar el botón Adoptar abrirá el formulario correspondiente
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

    @FXML
    /**
     * Oculta el panel de información adicional y muestra el panel de búsqueda.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonBusqueda(ActionEvent event) {
        tablaPerros();
        panelModificacion.setVisible(false);
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(true);
    }

    /**
     * Busca perros en la base de datos según los filtros introducidos (id, chip, nombre, raza, color).
     * Prioriza la búsqueda por id y por chip al ser identificadores únicos.
     * Elimina duplicados usando un HashSet antes de devolver los resultados.
     *
     * @return lista de perros que coinciden con los criterios de búsqueda
     */
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
        if (idAnimalTexto.isEmpty() && chipAnimal.isEmpty() &&
                nombreAnimal.isEmpty() && razaAnimal.isEmpty() && colorAnimal.isEmpty()) {
            Mensajes.aletaObligatoriosCamposVacios("Introduce al menos un criterio de búsqueda");
            tablaPerros();
            return null;
        }

        List<Perro> resultadosEncontrados = new ArrayList<>();

        if (idAnimalTexto != null && !idAnimalTexto.isEmpty() && idAnimal != 0) {
            Perro p = PerroDAO.findByID(idAnimal);
            if (p != null) {
                resultadosEncontrados.add(p);
                return resultadosEncontrados;
            }else {
                Mensajes.alertaNoExiste("No existe ningún perro con el ID: " + idAnimalTexto);
                return new ArrayList<>();
            }
        }
        if (chipAnimal != null && !chipAnimal.isEmpty()) {
            Perro p = PerroDAO.findByChip(chipAnimal);
            if (p != null) {
                resultadosEncontrados.add(p);
                return resultadosEncontrados;
            }else {
                Mensajes.alertaNoExiste("El número de chip " + chipAnimal + "no existe en nuestros registros");
                return new ArrayList<>();
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

        if (busquedaFinal.isEmpty()) {
            Mensajes.alertaErrorDeRegistro("No se encontraron resultados en la base de datos");
            return new ArrayList<>();
        }

        return new ArrayList<>(busquedaFinal);
    }

    @FXML
    /**
     * Metodo que ejecuta la búsqueda y actualiza la tabla con los resultados obtenidos.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonContinuarBusqueda(ActionEvent event) {
        ObservableList<Perro> resultados =
                FXCollections.observableArrayList(buscarAnimal());

        tablaPerros.setItems(resultados);
    }

    //endregion

    //region---------------MODIFICAR-------------------

    @FXML
/**
 * Muestra el panel de modificación, oculta los demás paneles
 * e inicializa el Spinner de ubicación.
 * @param event --> acción que se va a llevar a cabo
 */
    public void botonModificar(ActionEvent event) {
        panelModificacion.setVisible(true);
        ventanaBuscar.setVisible(false);
        informacionAdicional.setVisible(false);
        modificarUbicacion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));
        tablaPerros();
    }

    /**
     * Recoge los campos del panel de modificación y actualiza en la base de datos
     * únicamente los campos que han sido rellenados.
     * Valida que haya un perro seleccionado y que al menos un campo esté relleno.
     *
     * @return true si al menos un campo fue actualizado correctamente, false en caso contrario
     */
    public boolean modificarAnimal() {
        informacionAdicional.setVisible(false);
        Perro pSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        Animal aSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();

        if (pSeleccionado == null || aSeleccionado == null) {
            Mensajes.alertaNoSeleccionado("Por favor, seleccione un perro");
            return false;
        }

        String chip = modificarChip.getText();
        LocalDate fecha = modificarFecha.getValue();
        String observaciones = modificarObservaciones.getText();
        String dniAdoptante = modificarDniAdoptante.getText();
        int ubicacion = (int) modificarUbicacion.getValue();
        Double peso = Utils.conversorDouble(modificarPeso.getText());

        if ((chip == null || chip.isEmpty()) && fecha == null && (observaciones == null || observaciones.isEmpty())
                && (dniAdoptante == null || dniAdoptante.isEmpty()) && ubicacion == 0 && (peso == null || peso == 0.0) &&
                !modificarEsterilizado.isSelected() && !modificarAgresivo.isSelected()) {
            Mensajes.aletaObligatoriosCamposVacios("Debe completar todos los campos obligatorios");
            return false;
        }

        boolean actualizado = false;

        if (chip != null && !chip.trim().isEmpty()) {
            if (AnimalDAO.findByChip(chip) == null) {
                if (AnimalDAO.updateNumeroChip(aSeleccionado, chip)) {
                    Mensajes.actualizacionCorrecta("Número de chip actualizado con éxito");
                    actualizado = true;
                } else {
                    Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido completar, compruebe la información");
                }
            } else {
                Mensajes.alertaYaExiste("El número de chip " + chip + " ya está en uso");
            }
        }

        if (fecha != null) {
            if (fecha.isAfter(LocalDate.now())) {
                Mensajes.alertaErrorDeRegistro("La fecha de registro no puede ser posterior a la fecha actual");
                return false;
            } else if (AnimalDAO.updateFechaAlta(aSeleccionado, fecha)) {
                Mensajes.actualizacionCorrecta("Fecha actualizada con éxito");

                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la información");
            }
        }

        if (observaciones != null && !observaciones.trim().isEmpty()) {
            if (AnimalDAO.updateObservaciones(aSeleccionado, observaciones)) {
                Mensajes.actualizacionCorrecta("Observaciones actualizadas con éxito");
                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la información");
            }
        }

        if (!aSeleccionado.isEsterilizado() && modificarEsterilizado.isSelected()) {
            if (AnimalDAO.updateEsterilizado(aSeleccionado, true)) {
                Mensajes.actualizacionCorrecta("Información actualizada con éxito");
                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la información");
            }
        } // todo -> esto debería cambiar si cargo los datos

        if (!pSeleccionado.isAgresivo() && modificarAgresivo.isSelected()) {
            if (PerroDAO.updateAgresivo(pSeleccionado, true)) {
                Mensajes.actualizacionCorrecta("Información actualizada con éxito");
                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la información");
            }
        } // todo -> esto debería cambiar si cargo los datos

        if (dniAdoptante != null && !dniAdoptante.trim().isEmpty()) {
            if (AnimalDAO.updateAdoptante(aSeleccionado, dniAdoptante)) {
                Mensajes.actualizacionCorrecta("Información del adoptante actualizada con éxito");
                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar la información del adoptante");
            }
        }

        if (ubicacion > 0) {
            if (AnimalDAO.updateUbicacion(aSeleccionado, ubicacion)) {
                Mensajes.actualizacionCorrecta("Ubicación cambiada correctamente");
                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar ubicación");
            }
        }

        if (peso != null && peso > 0) {
            if (PerroDAO.updatePeso(pSeleccionado, peso)) {
                Mensajes.actualizacionCorrecta("Peso cambiado correctamente");
                actualizado = true;
            } else {
                Mensajes.actualizacionIncorrecta("Lo sentimos, no se ha podido actualizar el peso");
            }
        }
        return actualizado;
    }

    /**
     * Limpia todos los campos del formulario de modificación dejándolos en su estado inicial.
     */
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

    @FXML
    /**
     * Guarda los cambios del formulario de modificación, limpia los campos y recarga la tabla.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonGuardarModificacion(ActionEvent event) {
        modificarAnimal();
        limpiarCampos();
        panelModificacion.setVisible(false);
        tablaPerros();
        informacionAdicional.setVisible(false);
    }
    //endregion


    //region---------------ELIMINAR PERRO -------------------

    @FXML
    /**
     * Elimina de la base de datos el perro seleccionado en la tabla,
     * verificando previamente que existe en PerroDAO.
     * Si no hay ningún perro seleccionado, cancela la operación.
     * @param event --> acción que se va a llevar a cabo
     */
    public void botonEliminar(ActionEvent event) {
        informacionAdicional.setVisible(false);
        ventanaBuscar.setVisible(false);
        Animal animalSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();

        if (animalSeleccionado == null) {
            Mensajes.alertaNoSeleccionado("No hay ningún animal seleccionado");
            return;
        }

        if (PerroDAO.findByID(animalSeleccionado.getId()) != null) {
            if (Mensajes.confirmarEliminar("El gato seleccionado será eliminado permanentemente.")) {
                AnimalDAO.deleteAnimalById(animalSeleccionado.getId());
            }
        } else {
            Mensajes.alertaNoSeleccionado("Debe seleccionar un perro");
        }
        initialize();
    }
    //endregion
}


