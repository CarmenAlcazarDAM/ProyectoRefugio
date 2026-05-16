package org.proyectorefugio.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.modelDAO.AnimalDAO;

import java.util.ArrayList;
import java.util.List;

public class BuscarController {
    public static String tipo;
    public static boolean estaAdoptado;

    @FXML
    private void initialize() {
        organizadorBusquedas();
    }

    public void organizadorBusquedas() {
        if ("animal".equals(tipo)) {
            buscarAnimal();
        }
    }


    /// //////////// BUSCAR ANIMAL //////////////////////////
    @FXML
    public TextField buscarId;
    public TextField buscarNombre;
    public TextField buscarChip;
    public TextField buscarRaza;
    public TextField buscarColor;
    public TextField buscarUbicacion;

    public void buscarAnimal() {
        int idAnimal = conversorInt(buscarId.getText());
        String nombreAnimal = buscarNombre.getText();
        String chipAnimal = buscarChip.getText();
        String razaAnimal = buscarRaza.getText();
        String colorAnimal = buscarColor.getText();
        int ubicacionAnimal = conversorInt(buscarUbicacion.getText());

        List<Animal> resultadosEncontrados = new ArrayList<>();

        if(idAnimal != 0){
            resultadosEncontrados.add(AnimalDAO.findByID(idAnimal));
        }
        if(nombreAnimal.isEmpty() && nombreAnimal != null){
            List<Animal> nombresEncontrados = AnimalDAO.findByName(nombreAnimal, estaAdoptado);
            for( Animal a : nombresEncontrados){
                resultadosEncontrados.add(a);
            }
        }

        //todo-> me he quedado por aqui

    }

    /**
     * Metodo que convierte el texto introducido por teclado a tipo de dato int
     *
     * @param convertir --> texto introducido por teclado
     * @return --> devuelve el texto convertido en int
     */
    public static int conversorInt(String convertir) {
        return Integer.parseInt(convertir);
    }

    /*
    if (pesoTexto.isEmpty()) {
            pesoNumero =  Double.parseDouble(pesoTexto);
        }
     */
}
