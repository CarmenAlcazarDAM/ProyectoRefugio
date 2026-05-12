package org.proyectorefugio.modelDAO;

import org.proyectorefugio.dataAccess.ConnectionBD;
import org.proyectorefugio.model.Ayuda;
import org.proyectorefugio.model.Ubicacion;
import org.proyectorefugio.model.Voluntario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AyudaDAO {
    /**-------------------Sentencias SQL---------------------**/
    private final static String SQL_FIND_ALL = "SELECT * FROM ayuda";
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
     * Método que crea una lista con todas las ayudas de la clase Ayuda
     * @return --> devuelve una lista de tareas
     */
    public static List<Ayuda> findAll(){
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try(ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)){
            while(rs.next()){
                String dniVoluntario = rs.getString("dniVoluntario");
                int idUbicacion = rs.getInt("idUbicacion");
                LocalDate fecha = (LocalDate) rs.getObject("fecha");
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dniVoluntario, idUbicacion, fecha,tarea);
                tareasRelizadas.add(ayuda);
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }

    /**
     * Método que busca todas las tareas que ha realizado un Voluntario
     * @param dni --> dni del Voluntario a buscar pasado por parámetro
     * @return --> devuelve una lista con todas las veces que el Voluntario ha ayudado
     */
    public static List<Ayuda> findByDniVoluntario(String dni){
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DNI)){
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idUbicacion = rs.getInt("idUbicacion");
                LocalDate fecha = (LocalDate) rs.getObject("fecha");
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dni, idUbicacion, fecha,tarea);
                tareasRelizadas.add(ayuda);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }

    /**
     * Método que busca todas las tareas realizadas en una determianda ubicacion
     * @param idUbicacion --> id de la ubicacion que buscamos
     * @return --> devuelve una lista con todas las veces que se ha llevado a cabo una tarea en una Ubicacion determinada
     */
    public static List<Ayuda> findByIdUbicacion(int idUbicacion){
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_UBICACION)){
            ps.setInt(1, idUbicacion);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String dniVoluntario = rs.getString("dniVoluntario");
                LocalDate fecha = (LocalDate) rs.getObject("fecha");
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dniVoluntario, idUbicacion, fecha,tarea);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }

    /**
     * Método que busca que tareas se han realizado en una determinada fecha
     * @param fecha --> fecha que vamos a buscar pasada por parámetro
     * @return --> devuelve una lista con todas tareas realizadas en una determinada fecha
     */
    public static List<Ayuda> findByFecha(LocalDate fecha){
        List<Ayuda> tareasRelizadas = new ArrayList<>();
        Ayuda ayuda = null;
        try(PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_DATE)){
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String dniVoluntario = rs.getString("dniVoluntario");
                int idUbicacion = rs.getInt("idUbicacion");
                String tarea = rs.getString("tarea");
                ayuda = new Ayuda(dniVoluntario, idUbicacion, fecha,tarea);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return tareasRelizadas;
    }
}
