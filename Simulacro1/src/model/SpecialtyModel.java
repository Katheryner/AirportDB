package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyModel implements CRUD {
    @Override
    public Object create(Object object) {

        Connection objConnection = ConfigDB.openConnection();

        Specialty objSpecialty = (Specialty) object;
        try {
            String sql = "INSERT INTO specialties(name_specialty,description) VALUES(?,?);";

            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objSpecialty.getName_specialty());
            objPrepare.setString(2,objSpecialty.getDescription());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objSpecialty.setId_specialty(objResult.getInt(1));

            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Specialty insertion was successful");

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding specialty" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objSpecialty;
    }

    @Override
    public boolean delete(Object object) {

        Specialty objspecialty = (Specialty) object;
        Connection connection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM specialties WHERE id_specialty = ?;";
            PreparedStatement objprepare = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objprepare.setInt(1,objspecialty.getId_specialty());
            int TotalRows = objprepare.executeUpdate();

            if (TotalRows >0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    @Override
    public boolean update(Object object) {
        Specialty specialty = (Specialty) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE specialties SET name_specialty = ?,description = ? WHERE id_specialty = ?;";

        try {
            PreparedStatement prepares = connection.prepareStatement(sql);
            prepares.setString(1, specialty.getName_specialty());
            prepares.setString(2, specialty.getDescription());
            prepares.setInt(3, specialty.getId_specialty());
            int rows = prepares.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null,"specialty update successfully");;
                isUpdated = true;
            }

        } catch (SQLException e) {
            System.out.println("Update: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listSpecialty = new ArrayList<>();

        try{ String sql = "SELECT * FROM specialties ORDER BY specialties.id_specialty ASC;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            while (objResult.next()){
                Specialty objSpecialty = new Specialty();

                objSpecialty.setId_specialty(objResult.getInt("id_specialty"));
                objSpecialty.setName_specialty(objResult.getString("name_specialty"));
                objSpecialty.setDescription(objResult.getString("description"));

                listSpecialty.add(objSpecialty);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error");

        }
        ConfigDB.closeConnection();
        return listSpecialty;
    }

    @Override
    public Object findById(int id_specialty) {

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Specialty objSpecialty = null;
        try {
            //2. Sentencia SQL
            String sql  = "SELECT * FROM specialties WHERE  id_specialty = ?;";
            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4. Damos valor al ?
            objPrepare.setInt(1,id_specialty);
            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()){
                objSpecialty = new Specialty();
                objSpecialty.setId_specialty(objResult.getInt("id_specialty"));
                objSpecialty.setName_specialty(objResult.getString("name_specialty"));
                objSpecialty.setDescription(objResult.getString("description"));

            }

        }catch (Exception e)  {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objSpecialty;
    }
}
