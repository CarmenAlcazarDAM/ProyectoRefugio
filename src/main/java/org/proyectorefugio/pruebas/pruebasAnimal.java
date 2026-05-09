package org.proyectorefugio.pruebas;

import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Sexo;
import org.proyectorefugio.modelDAO.AnimalDAO;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalTime.now;

public class pruebasAnimal {
    public static void main(String[] args) {
        Animal animal = null;

//        System.out.println("----------------Prueba buscar por id-----------------");
//
//         animal = AnimalDAO.findByID(1);
//        System.out.println(animal);

//        System.out.println("----------------Prueba buscar por chip-----------------");
//        animal = AnimalDAO.findByChip("981098102345678");
//        System.out.println(animal);

//        System.out.println("----------------Prueba findByNameNotAdopted-----------------");
//        List<Animal> lista = AnimalDAO.findByNameNotAdopted("Bianca");
//        for(Animal a : lista){
//            System.out.println(a);
//        }
//        System.out.println("----------------Prueba findByNameAdopted-----------------");
//        List<Animal> lista = AnimalDAO.findByNameAdopted("curie");
//        for(Animal a : lista){
//            System.out.println(a);
//        }

//        System.out.println("----------------Prueba findByBreedNotAdopted-----------------");
//        List<Animal> lista = AnimalDAO.findByBreedNotAdopted("angora");
//        for(Animal a : lista){
//            System.out.println(a);
//        }

//        System.out.println("----------------Prueba findByBreedAdopted-----------------");
//        List<Animal> lista = AnimalDAO.findByBreedAdopted("comun");
//        for(Animal a : lista){
//            System.out.println(a);
//        }

//        System.out.println("----------------Prueba findByColour-----------------");
//        List<Animal> lista = AnimalDAO.findByColour("gris");
//        for(Animal a : lista){
//            System.out.println(a);
//        }

//        System.out.println("----------------Prueba add-----------------");
//        Animal a3 = new Animal(
//                "Pipo",
//                "Mestizo",
//                Sexo.macho,
//                "negro",
//                null,
//                "900123123123123",
//                false,
//                "Encontrado en una caja",
//                "En tratamiento por desnutrición",
//                15
//        );
//        AnimalDAO.addAnimal(a3);

//        System.out.println("----------------Prueba delete-----------------");
//        AnimalDAO.deleteAnimalById(9);
//        AnimalDAO.deleteAnimalById(10);
//        AnimalDAO.deleteAnimalById(11);
//
//        System.out.println("----------------Prueba updateNumeroChip-----------------");
//        animal = AnimalDAO. findByID(8);
//        AnimalDAO.updateNumeroChip(animal, "900123123123124");
//
//        System.out.println("----------------Prueba updateObservaciones-----------------");
//        animal = AnimalDAO. findByID(8);
//        AnimalDAO.updateObservaciones(animal, "Medicación");
//
//        System.out.println("----------------Prueba updateEsterilizado-----------------");
//        animal = AnimalDAO. findByID(8);
//        AnimalDAO.updateEsterilizado(animal, false);

        System.out.println("----------------Prueba updateFechaAlta-----------------");
        animal = AnimalDAO. findByID(8);
        LocalDate hoy = LocalDate.now();
        AnimalDAO.updateFechaAlta(animal, hoy);

        System.out.println("----------------Prueba updateUbicacion-----------------");
        animal = AnimalDAO. findByID(8);
        AnimalDAO.updateUbicacion(animal, 4);

        System.out.println("----------------Prueba updateAdoptante-----------------");
        animal = AnimalDAO. findByID(8);
        AnimalDAO.updateAdoptante(animal, "55443322L");



    }

}
