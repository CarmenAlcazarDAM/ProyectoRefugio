package DAO;

import model.Animal;

import dataAccess.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDAO {
    private final static String SQL_FIND_BY_ID = "SELECT * FROM animal WHERE id = ?";

    /**
     * Método que busca un objeto animal según su ID
     * @param id --> id específica de cada animal
     * @return --> devuelve un objeto animal
     */
    public static Animal findByID(int id){
        Animal animal = null;

        try(PreparedStatement ps = ConnectionBD.getInstance().getConnection().prepareStatement(SQL_FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
