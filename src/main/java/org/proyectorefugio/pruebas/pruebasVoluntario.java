package org.proyectorefugio.pruebas;

import org.proyectorefugio.model.Persona;
import org.proyectorefugio.model.Voluntario;
import org.proyectorefugio.modelDAO.PersonaDAO;
import org.proyectorefugio.modelDAO.VoluntarioDAO;

import java.util.List;

public class pruebasVoluntario {
    public static void main(String[] args) {
//        System.out.println("\n-----------------------Prueba findAll-------------------------------");
//        List<Voluntario> voluntarios = VoluntarioDAO.findAll();
//        for (Voluntario v : voluntarios) {
//            System.out.println(v);
//        }
//
//        System.out.println("\n-----------------------Prueba findByDni-------------------------------");
//       Voluntario v = VoluntarioDAO.findByDni("44444444D");
//        System.out.println(v);

//        System.out.println("\n-----------------------Prueba findByName-------------------------------");
//        List<Voluntario> voluntarios2 = VoluntarioDAO.findByName("maria");
//        for (Voluntario v2 : voluntarios2) {
//            System.out.println(v2);
//        }

//        System.out.println("\n-----------------------Prueba findByName-------------------------------");
//        List<Voluntario> voluntarios3 = VoluntarioDAO.findByLastName("Navarro");
//        for (Voluntario v3 : voluntarios3) {
//            System.out.println(v3);
//        }

        System.out.println("\n-----------------------Prueba Add-------------------------------");

        Persona p = PersonaDAO.findByDni("12345678Z");
        VoluntarioDAO.addVoluntario(p);



    }
}
