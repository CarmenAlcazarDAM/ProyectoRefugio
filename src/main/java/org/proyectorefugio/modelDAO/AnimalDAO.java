package org.proyectorefugio.modelDAO;

import org.proyectorefugio.model.Animal;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.enums.Sexo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AnimalDAO {

    /**
     * --------------------Sentencias SQL--------------------
     **/

    private final static String SQL_FIND_BY_ID = "SELECT * FROM animal WHERE id = ?";
    private final static String SQL_FIND_BY_CHIP = "SELECT * FROM animal WHERE numeroChip = ?";

    private final static String SQL_FIND_BY_NAME = "SELECT * FROM animal WHERE nombre LIKE ? AND adoptado = ?";

    private final static String SQL_FIND_BY_BREED = "SELECT * FROM animal WHERE raza LIKE ? AND adoptado = ?";

    private final static String SQL_FIND_BY_COLOUR = "SELECT * FROM animal WHERE color LIKE ? AND adoptado = ? AND id IN (SELECT idGato FROM gato)";

    private final static String SQL_FIND_BY_UBICACION_AND_ALTA = "SELECT * FROM animal WHERE idUbicacion = ? AND fechaALTA IS NULL";


    private static final String SQL_INSERT_ANIMAL = "INSERT INTO animal (nombre, raza, sexo,color, marcasDistintivas, numeroChip, esterilizado, historia, observaciones, idUbicacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_BY_ID = "DELETE animal FROM animal WHERE id = ?";

    private static String SQL_UPDATE_CHIP = "UPDATE animal SET numeroChip = ? WHERE id = ?";
    private static String SQL_UPDATE_OBSERVACIONES = "UPDATE animal SET observaciones = ? WHERE id = ?";
    private static String SQL_UPDATE_ESTERILIZADO = "UPDATE animal SET esterilizado = ? WHERE id = ?";
    private static String SQL_UPDATE_FECHA_ALTA = "UPDATE animal SET fechaAlta = ? WHERE id = ?";
    private static String SQL_UPDATE_UBICACION = "UPDATE animal SET idUbicacion = ? WHERE id = ?";
    private static String SQL_UPDATE_ADOPTANTE = "UPDATE animal SET dniAdoptante = ? WHERE id = ?";


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
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                String color = rs.getString("color");
                String edad = rs.getString("edad");
                String marcasDistintivas = rs.getString("marcasDistintivas");
                String numeroChip = rs.getString("numeroChip");
                Boolean esterilizado = rs.getBoolean("esterilizado");
                String historia = rs.getString("historia");
                String observaciones = rs.getString("observaciones");
                Date fechaIngreso = rs.getDate("fechaIngreso");
                Boolean adoptado = rs.getBoolean("adoptado");
                Date fechaAlta = rs.getDate("fechaAlta");
                String dniAdoptante = rs.getString("dniAdoptante");
                int idUbicacion = rs.getInt("idUbicacion");


                animal = new Animal(id, nombre, raza, sexo, color, edad, marcasDistintivas, numeroChip,
                        esterilizado, historia, observaciones, fechaIngreso, adoptado, fechaAlta,
                        dniAdoptante, idUbicacion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }

    /**
     * Método que busca un Animal por su numero de chip en caso de que lo tenga,
     * el número de chip de cada animal es una secuencia de 15 números e irrepetible
     *
     * @param chip --> número de chip pasado por parámetro
     * @return --> devuelve al Animal con dicho número de chip en caso de encontrarlo
     */
    public static Animal findByChip(String chip) {
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CHIP)) {
            ps.setString(1, chip);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                animal = findByID(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }

    /**
     * Método que busca los Animales que compartan el mismo nombre
     *
     * @param name      --> nombre a buscar
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con todos los Animales encontrados
     */
    public static List<Animal> findByName(String name, boolean isAdopted) {
        List<Animal> listaAnimales = new ArrayList<>();
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)) {
            ps.setString(1, "%" + name + "%");
            ps.setBoolean(2, isAdopted);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                animal = findByID(id);

                listaAnimales.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimales;
    }

    /**
     * Método que busca los Animales que compartan la misma raza
     *
     * @param breed     --> raza a buscar
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con todos los Animales encontrados
     */
    public static List<Animal> findByBreed(String breed, boolean isAdopted) {
        List<Animal> listaAnimal = new ArrayList<>();
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_BREED)) {
            ps.setString(1, "%" + breed + "%");
            ps.setBoolean(2, isAdopted);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");

                animal = findByID(id);

                listaAnimal.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimal;
    }

    /**
     * Método que busca los Animales que compartan el mismo color
     *
     * @param colour    --> color a buscar
     * @param isAdopted --> filtra si estamos buscando los que están adoptados o no
     * @return --> devuelve una lista con todos los Animales encontrados
     */
    public static List<Animal> findByColour(String colour, boolean isAdopted) {
        List<Animal> listaAnimal = new ArrayList<>();
        Animal animal = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_COLOUR)) {
            ps.setString(1, "%" + colour + "%"); // El símbolo % representa cualquier secuencia de caracteres para permitir búsquedas parciales con LIKE.
            ps.setBoolean(2, isAdopted);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");

                animal = findByID(id);

                listaAnimal.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAnimal;
    }

    /**
     * Método que busca todos los animales que están en una ubicacion determinada
     * y no se han dado de Alta en el registro es decir, aún se encuentran
     * en el refugio
     *
     * @param idUbicacion --> id de la ubicación a buscar
     * @return --> devuelve una lista con los animales que habitan en una ubicacion
     * en este momento.
     */
    public static List<Animal> findByUbicacion(int idUbicacion) {
        List<Animal> listaAnimal = new ArrayList<>();
        Animal animal = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_UBICACION_AND_ALTA)) {
            ps.setInt(1, idUbicacion);


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");

                animal = findByID(id);

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

        if ((animal != null) && findByChip(animal.getNumeroChip()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT_ANIMAL)) {
                ps.setString(1, animal.getNombre());
                ps.setString(2, animal.getRaza());
                ps.setString(3, animal.getSexo().toString().toLowerCase());
                ps.setString(4, animal.getColor());
                ps.setString(5, animal.getMarcasDistintivas());
                ps.setString(6, animal.getNumeroChip());
                ps.setBoolean(7, animal.isEsterilizado());
                ps.setString(8, animal.getHistoria());
                ps.setString(9, animal.getObservaciones());
                ps.setInt(10, animal.getIdUbicacion());

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
    /**
     * Método que actualiza la informacion del numero de chip de un animal
     *
     * @param a          --> animal al que vamos a actualizar la información
     * @param numeroChip --> número del chip pasado por parámetro (dos animales no pueden tener el mismo número de chip)
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateNumeroChip(Animal a, String numeroChip) {
        boolean updated = false;
        if ((a != null) && findByID(a.getId()) != null && findByChip(numeroChip) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_CHIP)) {
                ps.setString(1, numeroChip);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza la informacion de Observaciones del animal
     *
     * @param a             --> animal al que vamos a actualizar la información
     * @param observaciones --> observacion que vamos a añadir
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateObservaciones(Animal a, String observaciones) {
        boolean updated = false;
        if ((a != null) && findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_OBSERVACIONES)) {
                ps.setString(1, observaciones);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza si se esteriliza a un animal
     *
     * @param a          --> animal al que vamos a actualizar la información
     * @param estelizado --> información de esterilizacion pasada por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateEsterilizado(Animal a, boolean estelizado) {
        boolean updated = false;
        if ((a != null) && findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_ESTERILIZADO)) {
                ps.setBoolean(1, estelizado);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza la fecha de alta de un animal
     *
     * @param a         --> animal al que vamos a actualizar la información
     * @param fechaAlta --> fecha en la que se dará de alta el animal
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateFechaAlta(Animal a, LocalDate fechaAlta) {
        boolean updated = false;
        if ((a != null) && findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_FECHA_ALTA)) {
                ps.setDate(1, Date.valueOf(fechaAlta));
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza la informacion de la ubicación del animal
     *
     * @param a           --> animal al que vamos a actualizar la información
     * @param idUbicacion --> id de la ubicacion pasado por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateUbicacion(Animal a, int idUbicacion) {
        boolean updated = false;
        if ((a != null) && findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_UBICACION)) {
                ps.setInt(1, idUbicacion);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Método que actualiza la informacion del adoptante
     *
     * @param a            --> animal al que vamos a actualizar la información
     * @param dniAdoptante --> dni del adoptante pasado por parámetro
     * @return --> devuelve true si se actualiza correctamente, false si no lo hace
     */
    public static boolean updateAdoptante(Animal a, String dniAdoptante) {
        boolean updated = false;
        if ((a != null) && findByID(a.getId()) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_ADOPTANTE)) {
                ps.setString(1, dniAdoptante);
                ps.setInt(2, a.getId());

                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }
}
