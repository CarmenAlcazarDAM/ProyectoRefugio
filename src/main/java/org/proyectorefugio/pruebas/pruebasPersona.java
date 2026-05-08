package org.proyectorefugio.pruebas;

import org.proyectorefugio.model.Persona;
import org.proyectorefugio.modelDAO.PersonaDAO;

import java.util.List;

public class pruebasPersona {
    public static void main(String[] args) {

//        System.out.println("\n-----------------------Prueba findAll-------------------------------");
//        List<Persona> personas = PersonaDAO.findAll();
//        for (Persona p : personas) {
//            System.out.println(p);
//        }
//
//        System.out.println("\n-----------------------Prueba findByDni-------------------------------");
//        Persona p = PersonaDAO.findByDni("12345678Z");
//        System.out.println(p);

//        System.out.println("\n-----------------------Prueba findByName-------------------------------");
//        List<Persona> personas2 = PersonaDAO.findByName("maria");
//        for (Persona p2 : personas2) {
//            System.out.println(p2);
//        }

        System.out.println("\n-----------------------Prueba findByLastName-------------------------------");
        List<Persona> personas3 = PersonaDAO.findByLastName("ocana");
        for (Persona p3 : personas3) {
            System.out.println(p3);
        }

    }
}
