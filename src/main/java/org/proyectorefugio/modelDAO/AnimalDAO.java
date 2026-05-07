package org.proyectorefugio.modelDAO;

import org.proyectorefugio.model.Animal;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Gato;
import org.proyectorefugio.model.Perro;
import org.proyectorefugio.model.Sexo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    /**
     * --------------------Sentencias SQL--------------------
     **/

    private final static String SQL_FIND_BY_ID = "SELECT * FROM animal WHERE id = ?";
    private final static String SQL_FIND_BY_CHIP = "SELECT * FROM animal WHERE numeroChip = ?";

    private final static String SQL_FIND_BY_NAME_NOT_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE nombre LIKE ? AND adoptado = 0";
    private final static String SQL_FIND_BY_NAME_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE nombre LIKE ? AND adoptado <> 0";

    private final static String SQL_FIND_BY_BREED_NOT_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE raza LIKE ? AND adoptado = 0";
    private final static String SQL_FIND_BY_BREED_ADOPTED = "SELECT id, nombre, raza, sexo FROM animal WHERE raza LIKE ? AND adoptado <> 0";

    private final static String SQL_FIND_BY_COLOUR = "SELECT id, nombre, raza, sexo FROM animal WHERE color LIKE ? AND id IN (SELECT idGato FROM gato)";

    private static final String SQL_INSERT_ANIMAL = "INSERT INTO animal (nombre, raza, sexo, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, idUbicacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_BY_ID = "DELETE animal WHERE id = ?";

    private static String SQL_UPDATE_CHIP = "UPDATE animal SET numeroChip = ? WHERE id = ?";

    /**------------------------------------------------------**/

    /////////////////////// FIND ///////////////////////
    /**
     * Método que busca y devuelve un objeto animal según su ID
     *
     * @param id --> id específica de cada animal
     * @return --> devuelve un objeto animal
     */
    public static Animal findByID(int id) {
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));

                animal = new Animal(id, nombre, raza, sexo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }

    public static Animal findByChip(int chip){
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CHIP)) {
            ps.setInt(1, chip);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));

                animal = new Animal(id, nombre, raza, sexo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }

    public static List<Animal> findByNameNotAdopted(String name) {
        List<Animal> listaAnimales = new ArrayList<>();
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME_NOT_ADOPTED)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                animal = new Animal(id, nombre, raza, sexo);

                listaAnimales.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimales;
    }

    public static List<Animal> findByNameAdopted(String name) {
        List<Animal> listaAnimales = new ArrayList<>();
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME_ADOPTED)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                animal = new Animal(id, nombre, raza, sexo);

                listaAnimales.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimales;
    }

    public static List<Animal> findByBreedNotAdopted(String breed) {
        List<Animal> listaAnimal = new ArrayList<>();
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_BREED_NOT_ADOPTED)) {
            ps.setString(1, "%" + breed + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                animal = new Animal(id, nombre, raza, sexo);

                listaAnimal.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimal;
    }

    public static List<Animal> findByBreedAdopted(String breed) {
        List<Animal> listaAnimal = new ArrayList<>();
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_BREED_ADOPTED)) {
            ps.setString(1, "%" + breed + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                animal = new Animal(id, nombre, raza, sexo);

                listaAnimal.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimal;
    }

    public static List<Animal> findByColour(String colour) {
        List<Animal> listaAnimal = new ArrayList<>();
        Animal animal = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_COLOUR)) {
            ps.setString(1, "%" + colour + "%"); // El símbolo % representa cualquier secuencia de caracteres para permitir búsquedas parciales con LIKE.
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                animal = new Animal(id, nombre, raza, sexo);

                listaAnimal.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimal;
    }

    /////////////////////// ADD ///////////////////////
    /**
     * Método que inserta un animal en la base de datos
     *
     * @param animal --> recibe el objeto Animal a insertar
     * @return --> devuelve un int que es el id del animal
     */
    public static Animal addAnimal(Animal animal) {
        Animal añadido = null;

        if ((animal != null)) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT_ANIMAL)) {
                ps.setString(1, animal.getNombre());
                ps.setString(2, animal.getRaza());
                ps.setString(3, animal.getSexo().toString().toLowerCase());
                ps.setString(4, animal.getMarcasDistintivas());
                ps.setInt(5, animal.getNumeroChip());
                ps.setBoolean(6, animal.isEsterilizado());
                ps.setString(7, animal.getHistoria());
                ps.setString(8, animal.getObservaciones());
                ps.setInt(9, animal.getIdUbicacion());

                ps.executeUpdate();

                añadido = findByID(animal.getId());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return añadido;
    }

    /////////////////////// DELETE ///////////////////////
    /**
     * Método que borra un objeto Animal de la base de datos animal,
     * sus respectivos objetos que heredan tambien se borraran ya que las FK son on delete cascade
     *
     * @param id --> id específica del animal que queremos borrar
     * @return --> devuelve true si se borra correctamente, false si no se borra
     */
    public static boolean deleteAnimalById(int id) {
        if (findByID(id) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE_BY_ID)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;

    }

    /// //////////////////// UPDATE ///////////////////////
// podemos hacer update del numero de chip, de observaciones, esterilizado, fecha de alta, idUbicacion y dniAdoptante
    public static boolean updateNumeroChip(Animal a, int numeroChip) {
        boolean updated = false;
        if ((a != null) && findByID(a.getId()) != null && findByChip(numeroChip) == null){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_CHIP)) {
                ps.setInt(1,numeroChip);
                ps.setInt(2, a.getId());
                ps.executeUpdate();

                updated = true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return updated;
    }
}
