package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;
import entity.Doctor;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object create(Object object) {
        Appointment objAppointment = (Appointment) object;
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "INSERT INTO appointments (date_appointment, hour_appointment, reason, idPatient, idDoctor) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAppointment.getDate_appointment());
            objPrepare.setString(2,objAppointment.getHour_appointment());
            objPrepare.setString(3,objAppointment.getReason());
            objPrepare.setInt(4,objAppointment.getIdPatient());
            objPrepare.setInt(5,objAppointment.getIdDoctor());

            objPrepare.execute();
            ResultSet objresult = objPrepare.getGeneratedKeys();

            while(objresult.next()){
                objAppointment.setId_appointment(objresult.getInt(1));
            }objPrepare.close();
            JOptionPane.showMessageDialog(null,"Appointment insertion was successful");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error adding appointment "+ e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAppointment;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Appointment objAppointment = (Appointment) object;

        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM appointments WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objAppointment.getId_appointment());

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
        Appointment appointment = (Appointment) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE appointments SET date_appointment = ?,hour_appointment = ?,reason=?,idPatient=?,idDoctor=? WHERE id_appointment = ?;";

        try {
            PreparedStatement prepares = connection.prepareStatement(sql);
            prepares.setString(1, appointment.getDate_appointment());
            prepares.setString(2, appointment.getHour_appointment());
            prepares.setString(3, appointment.getReason());
            prepares.setInt(4, appointment.getIdPatient());
            prepares.setInt(5, appointment.getIdDoctor());
            int rows = prepares.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null,"appointment update successfully");;
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
        List<Object>listAppointment = new ArrayList<>();

        try{ String sql = "SELECT * FROM appointments;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            while (objResult.next()){
                Appointment objAppointment = new Appointment();

                objAppointment.setId_appointment(objResult.getInt("id_appointment"));
                objAppointment.setDate_appointment(objResult.getString("date_appointment"));
                objAppointment.setHour_appointment(objResult.getString("hour_appointment"));
                objAppointment.setReason(objResult.getString("reason"));
                objAppointment.setIdDoctor(objResult.getInt("idDoctor"));
                objAppointment.setIdPatient(objResult.getInt("idPatient"));

                listAppointment.add(objAppointment);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error");

        }
        ConfigDB.closeConnection();
        return listAppointment;

    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Appointment objAppointment = null;
        try{
            String sql = "SELECT * FROM appointment WHERE id_appointment = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objAppointment = new Appointment();
                objAppointment.setId_appointment(objResult.getInt("id_appointment"));
                objAppointment.setDate_appointment(objResult.getString("date_appointment"));
                objAppointment.setHour_appointment(objResult.getString("hour_appointment"));
                objAppointment.setReason(objResult.getString("reason"));
                objAppointment.setIdPatient(objResult.getInt("idPatient"));
                objAppointment.setIdDoctor(objResult.getInt("idDoctor"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAppointment;
    }

    public List<Object> findByDate(String date){
        Connection connection = ConfigDB.openConnection();
        List<Object>listDate = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE date_appointment = ?";
        try{
            PreparedStatement prepares = connection.prepareStatement(sql);
            prepares.setString(1,date);
            ResultSet resultSet =prepares.executeQuery();
            while (resultSet.next()){
                listDate.add(new Appointment(resultSet.getInt("id_appointment"),
                        resultSet.getString("date_appointment"),
                        resultSet.getString("hour_appointment"),
                        resultSet.getString("reason"),
                        resultSet.getInt("idPatient"),
                        resultSet.getInt("idDoctor")));

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"error database"+ e.getMessage());
        }
        return listDate;
    }
}
