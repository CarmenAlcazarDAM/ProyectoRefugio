//package org.proyectorefugio.controller;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import org.proyectorefugio.model.Animal;
//import org.proyectorefugio.model.Perro;
//import org.proyectorefugio.modelDAO.AnimalDAO;
//import org.proyectorefugio.modelDAO.PerroDAO;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class BuscarController {
//    public static String tipo;
//
//    public static boolean estaAdoptado;
//
//    @FXML
//    private void initialize() {
//        organizadorBusquedas();
//    }
//
//    public void organizadorBusquedas() {
//        if ("animal".equals(tipo)) {
//            buscarAnimal(estaAdoptado);
//        }
//    }
//
//
//    /// //////////// BUSCAR ANIMAL //////////////////////////
//    @FXML
//    public TextField buscarId;
//    public TextField buscarNombre;
//    public TextField buscarChip;
//    public TextField buscarRaza;
//    public TextField buscarColor;
//
//    public static List<Perro> listaFinal = new ArrayList<>();
//
//
//    /**
//     * Método que devuelve una lista con los resultados obtenidos al rellenar los campo
//     * @return --> devuelve una lista de animales que coincidan con los campos rellenos
//     */
//    public List<Perro> buscarAnimal(Boolean adoptado) {
//        String idAnimalTexto = buscarId.getText();
//        int idAnimal = -1;
//        if(idAnimalTexto!=null){idAnimal = conversorInt(idAnimalTexto);};
//        String nombreAnimal = buscarNombre.getText();
//        String chipAnimal = buscarChip.getText();
//        String razaAnimal = buscarRaza.getText();
//        String colorAnimal = buscarColor.getText();
//
//
//
//        List<Perro> resultadosEncontrados = new ArrayList<>();
//
//
//        if (idAnimalTexto !=null && !idAnimalTexto.isEmpty() && idAnimal != 0) {
//            Animal a = AnimalDAO.findByID(idAnimal);
//            if (a != null) {
//                resultadosEncontrados.clear();
//                resultadosEncontrados.add((Perro) a);
//                return resultadosEncontrados;
//            }
//        }
//        if (chipAnimal != null && !chipAnimal.isEmpty()) {
//            Animal a = AnimalDAO.findByChip(chipAnimal);
//            if (a != null) {
//                resultadosEncontrados.clear();
//                resultadosEncontrados.add((Perro) a);
//                return resultadosEncontrados;
//            }
//        }
//
//        if (nombreAnimal != null && !nombreAnimal.isEmpty()) {
//            resultadosEncontrados.addAll(PerroDAO.findByName(nombreAnimal, adoptado));
//        }
//
//        if (razaAnimal != null && !razaAnimal.isEmpty()) {
//            resultadosEncontrados.addAll(PerroDAO.findByBreed(razaAnimal, adoptado));
//
//        }
//        if (colorAnimal != null && !colorAnimal.isEmpty()) {
//            resultadosEncontrados.addAll(PerroDAO.findByColour(colorAnimal, adoptado));
//
//        }
//
//
//        //todo-> comprobar si filtra por campos rellenos
//
//        //el HashSet va a filtar la List para que no haya repetidos
//        HashSet<Perro> busquedaFinal = new HashSet<>(resultadosEncontrados);
//        //por si se cuela algún null
//        busquedaFinal.remove(null);
//
//        //Creo un parametro static que poder llamar desde otra clase
//        listaFinal.addAll(busquedaFinal);
//
//        return new ArrayList<>(busquedaFinal);
//
//    }
//
//    /**
//     * Metodo que convierte el texto introducido por teclado a tipo de dato int
//     *
//     * @param convertir --> texto introducido por teclado
//     * @return --> devuelve el texto convertido en int
//     */
//    public static int conversorInt(String convertir) {
//        if (convertir == null || convertir.isEmpty()) {
//            return 0;
//        }
//        return Integer.parseInt(convertir);
//    }
//
//}
