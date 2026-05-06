package org.proyectorefugio.modelDAO;

import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Perro;

import java.util.ArrayList;
import java.util.List;

public class pruebasMain {
    public static void main(String[] args) {
        Animal animal = AnimalDAO.findByID(1);
        System.out.println(animal);

        System.out.println("---------------------------------");

        List<Perro> perros = PerroDAO.findAll();
        for(Perro p : perros){
            System.out.println(p);
        }


    }
}
