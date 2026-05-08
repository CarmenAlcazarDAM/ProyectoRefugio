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

//        System.out.println("\n-----------------------Prueba findByLastName-------------------------------");
//        List<Persona> personas3 = PersonaDAO.findByLastName("ocana");
//        for (Persona p3 : personas3) {
//            System.out.println(p3);
//        }

        System.out.println("\n-----------------------Prueba INSERT-------------------------------");
        Persona p4 = new Persona("12345678X", "Lucía", "Méndez Ortiz", "611223344", "lucia.mendez@email.com", "Calle Real 10, Puente Genil");
//        PersonaDAO.addPersona(p4);

//        System.out.println("\n-----------------------Prueba updateTelefono-------------------------------");
//        PersonaDAO.updateTelefono(p4, "611223355");

//        System.out.println("\n-----------------------Prueba updateCorreo-------------------------------");
//        PersonaDAO.updateCorreo(p4, "lucia.mendez@email.es");

//        System.out.println("\n-----------------------Prueba updateAdress-------------------------------");
//        PersonaDAO.updateAdress(p4, "Calle Real s/n, Puente Genil");

        System.out.println("\n-----------------------Prueba DELETE-------------------------------");
        PersonaDAO.deletePersona(p4.getDni());



    }
}
