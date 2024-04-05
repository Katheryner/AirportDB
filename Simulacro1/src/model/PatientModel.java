package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patient;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {
    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Patient objPatient = (Patient) object;
        try {
            String sql = "INSERT INTO patients (name_patient,last_name_patient,birthday,document) VALUES(?,?,?,?);";

            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objPatient.getName_patient());
            objPrepare.setString(2,objPatient.getLast_name_patient());
            objPrepare.setString(3,objPatient.getBirthday());
            objPrepare.setString(4,objPatient.getDocument());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objPatient.setId_patient(objResult.getInt(1));

            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Patient insertion was successful");

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding patient" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPatient;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Patient objpatient = (Patient) object;

        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM patients WHERE id_patient = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objpatient.getId_patient());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows >0 ){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public boolean update(Object object) {
        Patient patient = (Patient) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE patients SET name_patient = ?,last_name_patient = ? ,birthday=?, document= ? WHERE id_patient = ?;";

        try {
            PreparedStatement prepares = connection.prepareStatement(sql);
            prepares.setString(1, patient.getName_patient());
            prepares.setString(2, patient.getLast_name_patient());
            prepares.setString(3, patient.getBirthday());
            prepares.setString(4, patient.getDocument());
            prepares.setInt(5, patient.getId_patient());
            int rows = prepares.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null,"patient update successfully");;
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

        List<Object>listPatients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM patients;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            while (objResult.next()){
                Patient objPatient = new Patient();
                objPatient.setId_patient(objResult.getInt("id_patient"));
                objPatient.setName_patient(objResult.getString("name_patient"));
                objPatient.setLast_name_patient(objResult.getString("last_name_patient"));
                objPatient.setBirthday(objResult.getString("birthday"));
                objPatient.setDocument(objResult.getString("document"));

                listPatients.add(objPatient);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data acquisition Error");
        }
        ConfigDB.closeConnection();
        return listPatients;
    }
    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient = null;
        try{
            String sql = "SELECT * FROM patients WHERE id_patient = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objPatient = new Patient();
                objPatient.setId_patient(objResult.getInt("id_patient"));
                objPatient.setName_patient(objResult.getString("name_patient"));
                objPatient.setLast_name_patient(objResult.getString("last_name_patient"));
                objPatient.setBirthday(objResult.getString("birthday"));
                objPatient.setDocument(objResult.getString("document"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPatient;
    }
}
