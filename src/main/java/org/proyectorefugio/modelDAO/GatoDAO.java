package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Animal;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.model.Sexo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GatoDAO {
    private final static String SQL_FIND_ALL = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, gato g WHERE a.id = g.idGato";
    private final static String SQL_FIND_ALL_NOT_ADOPTED = "SELECT a.id, a.nombre, a.raza, a.sexo FROM animal a, gato g WHERE a.id = g.idGato AND adoptado = 0";

    private final static String SQL_FIND_BY_NAME_NOT_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE nombre = ? AND adoptado = 0 AND id IN (SELECT idGato FROM gato)";
    private final static String SQL_FIND_BY_NAME_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE nombre = ? AND adoptado <> 0 AND id IN (SELECT idGato FROM gato)";

    private final static String SQL_FIND_BY_BREED_NOT_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE raza = ? AND adoptado = 0 AND id IN (SELECT idGato FROM gato)";
    private final static String SQL_FIND_BY_BREED_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE raza = ? AND adoptado <> 0 AND id IN (SELECT idGato FROM gato)";


    /**
     * Método que devuelve una lista con todos los gatos de la base de datos
     * @return --> lista con todos los gatos y sus datos.
     */
    public static List<Gato> findAll(){
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;
        try(ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)){
            while(rs.next()){
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                gato = new Gato(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaGatos.add(gato);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaGatos;
    }
    /**
     * Método que devuelve una lista con todos los gatos de la base de datos
     *
     * @return --> lista con todos los gatos y sus datos.
     */
    public static List<Gato> findAllNotAdopted() {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL_NOT_ADOPTED)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Animal datosAnimal = AnimalDAO.findByID(id);

                gato = new Gato(id, datosAnimal.getNombre(), datosAnimal.getRaza(), datosAnimal.getSexo());
                listaGatos.add(gato);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }

    /**
     * Método que busca a los gatos que tengan un nombre específico y estén en adopción
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan el nombre introducido por el usuario
     */
    public static List<Gato> findByNameNotAdopted(String name) {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME_NOT_ADOPTED)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                gato = new Gato(id, nombre, raza, sexo);

                listaGatos.add(gato);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }
    /**
     * Método que busca a los gatos que tengan un nombre específico y ya han sido adoptados
     *
     * @param name --> nombre a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan el nombre introducido por el usuario
     */
    public static List<Gato> findByNameAdopted(String name) {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME_ADOPTED)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                gato = new Gato(id, nombre, raza, sexo);

                listaGatos.add(gato);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }

    /**
     * Método que busca a los gatos que tengan una raza específica y no han sido adoptados
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan esa misma raza
     */
    public static List<Gato> findByBreedNotAdopted(String breed) {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_BREED_NOT_ADOPTED)) {
            ps.setString(1, breed);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                gato = new Gato(id, nombre, raza, sexo);

                listaGatos.add(gato);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }
    /**
     * Método que busca a los gatos que tengan una raza específica y han sido adoptados
     *
     * @param breed --> raza a buscar, introducido por el usuario
     * @return --> devuelve una lista con los gatos que tengan esa misma raza
     */
    public static List<Gato> findByBreedAdopted(String breed) {
        List<Gato> listaGatos = new ArrayList<>();
        Gato gato = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_BREED_ADOPTED)) {
            ps.setString(1, breed);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                gato = new Gato(id, nombre, raza, sexo);

                listaGatos.add(gato);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGatos;
    }

}
