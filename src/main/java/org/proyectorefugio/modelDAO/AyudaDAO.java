package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Ayuda;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AyudaDAO {
    /**
     * -------------------Sentencias SQL---------------------
     **/
    private final static String SQL_FIND_ALL = "SELECT * FROM ayuda ORDER BY fecha desc";
    private final static String SQL_FIND_SINGLE = "SELECT * FROM ayuda WHERE dniVoluntario = ? AND idUbicacion = ? AND fecha = ?";
    private final static String SQL_FIND_BY_DNI = "SELECT * FROM ayuda WHERE dniVoluntario = ?";
    private final static String SQL_FIND_BY_UBICACION = "SELECT * FROM ayuda WHERE idUbicacion = ?";
    private final static String SQL_FIND_BY_DATE = "SELECT * FROM ayuda WHERE fecha = ?";

    private final static String SQL_INSERT = "INSERT INTO ayuda (dniVoluntario, idUbicacion,fecha, tarea) VALUES (?, ?, ?, ?)";

    private final static String SQL_DELETE = "DELETE FROM ayuda WHERE dniVoluntario = ? AND idUbicacion = ? AND fecha = ?";

    private final static String SQL_UPDATE_UBICACION = "UPDATE ayuda SET idUbicacion = ? WHERE dniVoluntario = ? AND idUbicacion = ? AND fecha = ?";
    private final static String SQL_UPDATE_FECHA = "UPDATE ayuda SET fecha = ? WHERE dniVoluntario = ? AND idUbicacion = ? AND fecha = ?";
    private final static String SQL_UPDATE_TAREA = "UPDATE ayuda SET tarea = ? WHERE dniVoluntario = ? AND idUbicacion = ? AND fecha = ?";

    /**------------------------------------------------------**/

    /////////////////////// FIND ///////////////////////
    /**
     * Metodo que crea una lista con todas las ayudas de la clase Ayuda
     *
     * @return --> devuelve una lista de tareas
     */
    public static List<Ayuda> findAll() {
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                String dniVoluntario = rs.getString("dniVoluntario");
                int idUbicacion = rs.getInt("idUbicacion");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dniVoluntario, idUbicacion, fecha, tarea);
                tareasRelizadas.add(ayuda);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }

    /**
     * Metodo que busca una única tarea por su clave primaria.
     *
     * @param dniVoluntario --> dni del voluntario a buscar
     * @param idUbicacion   --> ubicacion donde se realiza la accion
     * @param fecha         --> fecha en la que se realiza una acción
     * @return
     */
    public static Ayuda findSingle(String dniVoluntario, int idUbicacion, LocalDate fecha) {
        Ayuda ayuda = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_SINGLE)) {
            ps.setString(1, dniVoluntario);
            ps.setInt(2, idUbicacion);
            ps.setDate(3, Date.valueOf(fecha));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dniVoluntario, idUbicacion, fecha, tarea);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ayuda;
    }

    /**
     * Metodo que busca todas las tareas que ha realizado un Voluntario
     *
     * @param dni --> dni del Voluntario a buscar pasado por parámetro
     * @return --> devuelve una lista con todas las veces que el Voluntario ha ayudado
     */
    public static List<Ayuda> findByDniVoluntario(String dni) {
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DNI)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idUbicacion = rs.getInt("idUbicacion");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dni, idUbicacion, fecha, tarea);
                tareasRelizadas.add(ayuda);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }

    /**
     * Metodo que busca todas las tareas realizadas en una determinada ubicacion
     *
     * @param idUbicacion --> id de la ubicacion que buscamos
     * @return --> devuelve una lista con todas las veces que se ha llevado a cabo una tarea en una Ubicacion determinada
     */
    public static List<Ayuda> findByIdUbicacion(int idUbicacion) {
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_UBICACION)) {
            ps.setInt(1, idUbicacion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dniVoluntario = rs.getString("dniVoluntario");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dniVoluntario, idUbicacion, fecha, tarea);
                tareasRelizadas.add(ayuda);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }

    /**
     * Metodo que busca que tareas se han realizado en una determinada fecha
     *
     * @param fecha --> fecha que vamos a buscar pasada por parámetro
     * @return --> devuelve una lista con todas tareas realizadas en una determinada fecha
     */
    public static List<Ayuda> findByFecha(LocalDate fecha) {
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DATE)) {
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dniVoluntario = rs.getString("dniVoluntario");
                int idUbicacion = rs.getInt("idUbicacion");
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dniVoluntario, idUbicacion, fecha, tarea);
                tareasRelizadas.add(ayuda);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }
    /////////////////////// INSERT ///////////////////////
    /**
     * Metodo que inserta una ayuda en la base de datos
     * @param a --> objeto Ayuda pasado por parámetro
     * @return --> devuelve el objeto Ayuda si se ha insertado correctamente
     */
    public static Ayuda addAyuda(Ayuda a){
        Ayuda añadida = null;
        if(a!=null){
            try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setString(1, a.getDniVoluntario());
                ps.setInt(2, a.getIdUbicacion());
                ps.setDate(3, Date.valueOf(a.getFecha()));
                ps.setString(4, a.getTarea());

                ps.executeUpdate();

                añadida = findSingle(a.getDniVoluntario(),a.getIdUbicacion(), a.getFecha());

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return  añadida;
    }

    /// //////////////////// DELETE ///////////////////////
    /**
     * Metodo que borra una ayuda de la base de datos
     * @param dniVoluntario --> dni del voluntario a buscar
     * @param idUbicacion --> ubicacion donde se realiza la accion
     * @param fecha --> fecha en la que se realiza una acción
     * @return -> devuelve true si se ha borrado correctamente
     */
    public static boolean deleteAyuda(String dniVoluntario, int idUbicacion, LocalDate fecha) {
        Ayuda ayuda = findSingle(dniVoluntario, idUbicacion, fecha);
        if (ayuda != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setString(1, dniVoluntario);
                ps.setInt(2, idUbicacion);
                ps.setDate(3, Date.valueOf(fecha));
                ps.executeUpdate();
                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /////////////////////// UPDATE ///////////////////////

    /**
     * Metodo que actualiza la ubicacion de una Ayuda
     * @param a --> Ayuda que vamos a actualizar pasada porparámetro
     * @param idUbicacion --> id de la nueva ubicacion a la que queremos cambiar
     * @return --> devuelve true si se actualiza correctamente
     */
    public static boolean updateUbicacion(Ayuda a, int idUbicacion) {
        boolean updated = false;
        if ((a != null) && findSingle(a.getDniVoluntario(),a.getIdUbicacion(), a.getFecha()) != null ){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_UBICACION)) {
                ps.setInt(1,idUbicacion);
                ps.setString(2, a.getDniVoluntario());
                ps.setInt(3, a.getIdUbicacion());
                ps.setDate(4, Date.valueOf(a.getFecha()));


                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Metodo que actualiza la fecha de una Ayuda
     * @param a --> Ayuda que vamos a actualizar pasada por parámetro
     * @param fecha --> nueva fecha que queremos actualizar
     * @return --> devuelve true si se actualiza correctamente
     */
    public static boolean updateFecha(Ayuda a, LocalDate fecha) {
        boolean updated = false;
        if ((a != null) && findSingle(a.getDniVoluntario(),a.getIdUbicacion(), a.getFecha()) != null ){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_FECHA)) {
                ps.setDate(1,Date.valueOf(fecha));
                ps.setString(2, a.getDniVoluntario());
                ps.setInt(3, a.getIdUbicacion());
                ps.setDate(4, Date.valueOf(a.getFecha()));


                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    /**
     * Metodo que actualiza la informacion de la tarea de una Ayuda
     * @param a --> Ayuda que vamos a actualizar pasada porparámetro
     * @param tarea --> nueva información que vamos a actualizar
     * @return --> devuelve true si se actualiza correctamente
     */
    public static boolean updateTarea(Ayuda a, String tarea) {
        boolean updated = false;
        if ((a != null) && findSingle(a.getDniVoluntario(),a.getIdUbicacion(), a.getFecha()) != null ){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE_TAREA)) {
                ps.setString(1,tarea);
                ps.setString(2, a.getDniVoluntario());
                ps.setInt(3, a.getIdUbicacion());
                ps.setDate(4, Date.valueOf(a.getFecha()));


                int filasAfectadas = ps.executeUpdate();
                updated = (filasAfectadas > 0);

                updated = true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

}
