package model;

import database.CRUD;
import database.ConfigDB;
import entity.Doctor;
import entity.Patient;
import entity.Specialty;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements CRUD {
    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Doctor objDoctor = (Doctor) object;
        try {
            String sql = "INSERT INTO doctors (name_doctor,last_name_doctor,idSpecialty) VALUES(?,?,?);";

            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objDoctor.getName_doctor());
            objPrepare.setString(2,objDoctor.getLast_name_doctor());
            objPrepare.setInt(3,objDoctor.getIdSpecialty());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objDoctor.setId_doctor(objResult.getInt(1));

            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Doctor insertion was successful");

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding doctor" + e.getMessage());
        }
        ConfigDB.closeConnection();

        return objDoctor;
    }
    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Doctor objdoctor = (Doctor) object;

        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM doctors WHERE id_doctor = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objdoctor.getId_doctor());

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
        Doctor doctor = (Doctor) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE doctors SET name_doctor = ?,last_name_doctor = ? ,idSpecialty=? WHERE id_doctor = ?;";

        try {
            PreparedStatement prepares = connection.prepareStatement(sql);
            prepares.setString(1, doctor.getName_doctor());
            prepares.setString(2, doctor.getLast_name_doctor());
            prepares.setInt(3, doctor.getIdSpecialty());
            prepares.setInt(4, doctor.getId_doctor());
            int rows = prepares.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null,"doctor update successfully");;
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

        List<Object>listDoctors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM doctors;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            while (objResult.next()){
                Doctor objDoctor = new Doctor();
                objDoctor.setId_doctor(objResult.getInt("id_doctor"));
                objDoctor.setName_doctor(objResult.getString("name_doctor"));
                objDoctor.setLast_name_doctor(objResult.getString("last_name_doctor"));
                objDoctor.setIdSpecialty(objResult.getInt("idSpecialty"));

                listDoctors.add(objDoctor);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data acquisition Error");
        }
        return listDoctors;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Doctor objDoctor = null;
        try{
            String sql = "SELECT * FROM doctors WHERE id_doctor = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objDoctor = new Doctor();
                objDoctor.setId_doctor(objResult.getInt("id_doctor"));
                objDoctor.setName_doctor(objResult.getString("name_doctor"));
                objDoctor.setLast_name_doctor(objResult.getString("last_name_doctor"));
                objDoctor.setIdSpecialty(objResult.getInt("idSpecialty"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objDoctor;

    }
    public List<Object> showAllmedics(int id) {
        Connection connection = ConfigDB.openConnection();
        List<Object> doctors = new ArrayList<>();
        String sql = "select * from doctors inner join specialties on doctors.idSpecialty = specialties.id_specialty where specialties.id_specialty = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Specialty specialty = new Specialty(rs.getInt("id_specialty"),rs.getString("name_specialty"),rs.getString("description"));
                Doctor doctor = new Doctor(rs.getInt("id_doctor"), rs.getString("name_doctor"),rs.getString("last_name_doctor"), rs.getInt("idSpecialty"));
                doctor.setSpecialty(specialty);
                doctors.add(doctor);
            }

        } catch (SQLException e) {
            System.out.println("FindById: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return doctors;
    }
}
