package org.proyectorefugio.modelDAO;

import org.proyectorefugio.model.Animal;

public class pruebasMain {
    public static void main(String[] args) {
        Animal animal = AnimalDAO.findByID(2);
        System.out.println(animal);


    }
}
