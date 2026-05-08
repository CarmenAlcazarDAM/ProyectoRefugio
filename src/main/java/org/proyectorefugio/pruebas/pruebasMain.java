package org.proyectorefugio.pruebas;

import org.proyectorefugio.model.Perro;
import org.proyectorefugio.modelDAO.PerroDAO;

import java.util.List;

public class pruebasMain {
    public static void main(String[] args) {
//        System.out.println("----------------Prueba buscar por id-----------------");
//
//        Animal animal = AnimalDAO.findByID(1);
//        System.out.println(animal);
//
//        System.out.println("----------------Prueba listar perros-----------------");
//
//        List<Perro> perros = PerroDAO.findAll();
//        for(Perro p : perros){
//            System.out.println(p);
//        }

//        System.out.println("----------------Prueba listar perros en adopción-----------------");
//
//        List<Perro> perros = PerroDAO.findAllNotAdopted();
//        for(Perro p : perros){
//            System.out.println(p);
//        }
//
//        System.out.println("----------------Prueba listar gatos-----------------");
//
//        List<Gato> gatos = GatoDAO.findAll();
//        for(Gato g : gatos){
//            System.out.println(g);
//        }

//        System.out.println("----------------Prueba buscar perros por Nombre-----------------");
//        List<Perro> perros2 = PerroDAO.findByNameNotAdopted("Bianca");
//        for(Perro p : perros2){
//            System.out.println(p);   1200
//        }

//        System.out.println("----------------Prueba listar gatos por color-----------------");
//
//        List<Gato> gatos = GatoDAO.findByColour("gris");
//        for(Gato g : gatos){
//            System.out.println(g);
//        }

        System.out.println("----------------Prueba listar perros por nombre-----------------");

        List<Perro> perros = PerroDAO.findByNameNotAdopted("Chispa");
        for(Perro p : perros){
            System.out.println(p);
        }
    }
}
