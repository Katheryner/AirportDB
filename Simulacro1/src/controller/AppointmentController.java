package controller;

import entity.Appointment;
import entity.Patient;
import model.AppointmentModel;
import model.DoctorModel;
import model.PatientModel;

import javax.print.Doc;
import javax.swing.*;
import java.util.List;

public class AppointmentController {

    AppointmentModel objAppointmentModel = new AppointmentModel();
    PatientController objPatientController = new PatientController();
    PatientModel objPatientModel = new PatientModel();
    DoctorController objdoctorcontroller = new DoctorController();
    DoctorModel objDoctorModel = new DoctorModel();
    public void create(){
        Appointment objAppointment = new Appointment();

        String date = JOptionPane.showInputDialog(null,"Insert date(Ejm: 2024-06-01)");
        String hour = JOptionPane.showInputDialog(null,"Insert hour(Ejm: 12:30:00)");
        String reason = JOptionPane.showInputDialog(null,"Reason");
        int idPatient = Integer.parseInt(JOptionPane.showInputDialog(null,objPatientController.getAll(objPatientModel.findAll())+"Insert id patient"));
        int idDoctor = Integer.parseInt(JOptionPane.showInputDialog(null,objdoctorcontroller.getAll(objDoctorModel.findAll())+"Insert doctor"));

        objAppointment.setDate_appointment(date);
        objAppointment.setHour_appointment(hour);
        objAppointment.setReason(reason);
        objAppointment.setIdPatient(idPatient);
        objAppointment.setIdDoctor(idDoctor);

        objAppointment = (Appointment) this.objAppointmentModel.create(objAppointment);
    }
    public void delete(){
        String listAppointment = "Doctors list";

        for (Object obj: this.objAppointmentModel.findAll()){
            Appointment objAppointment = (Appointment) obj;
            listAppointment += objAppointment.toString() + "\n";
        }
        int confirm = 1;
        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listAppointment + "Enter the ID of the appointment to delete"));
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(isDelete);
        if (objAppointment == null){
            JOptionPane.showMessageDialog(null, "appointment not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the appointment?");
            if (confirm == 0){
                this.objAppointmentModel.delete(objAppointment);
            }
        }
    }
    public void getAll(){
        String list = this.getAll(this.objAppointmentModel.findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public String getAll(List<Object> listObject){
        String list = "Appointment List\n";
        for (Object obj: listObject){
            Appointment objAppointment = (Appointment)obj;
            list += objAppointment.toString()+"\n";
        }
        return list;
    }
    public void update() {

        //Listamos
        String listAppointment = this.getAll(this.objAppointmentModel.findAll());

        //Pedimos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAppointment +"\nEnter the ID of the patient to edit"));

        //Verificar el id
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idUpdate);

        if  (objAppointment == null){
            JOptionPane.showMessageDialog(null, "Appointment not found.");
        }else {
            String name = JOptionPane.showInputDialog(null,"Enter new date "+ objAppointment.getDate_appointment());
            String last = JOptionPane.showInputDialog(null,"Enter new hour " +objAppointment.getHour_appointment());
            String reason = JOptionPane.showInputDialog(null,"Enter reason"+objAppointment.getReason());
            int idPatient = Integer.parseInt(JOptionPane.showInputDialog(null,objPatientController.getAll(objPatientModel.findAll())+"Enter id Patient"+ objAppointment.getIdPatient()));
            int idDoctor = Integer.parseInt(JOptionPane.showInputDialog(null,objdoctorcontroller.getAll(objDoctorModel.findAll())+"Enter id Doctor"+ objAppointment.getIdPatient()));

            objAppointment.setDate_appointment(name);
            objAppointment.setHour_appointment(last);
            objAppointment.setReason(reason);
            objAppointment.setIdPatient(idPatient);
            objAppointment.setIdDoctor(idDoctor);

            this.objAppointmentModel.update(objAppointment);
        }
    }
    public  String findByDate(){
        String list = "List Date";
        try {
            String date = JOptionPane.showInputDialog(null,"Enter date to find");
            List<Object> dates = objAppointmentModel.findByDate(date);
            if (!dates.isEmpty()){
                for (Object obj : dates){
                    Appointment appointment = (Appointment) obj;
                    list += "\nID "+ appointment.getId_appointment()+
                            "\nDate "+ appointment.getDate_appointment()+
                            "\nHour "+ appointment.getHour_appointment()+
                            "\nAirplane ID "+appointment.getReason()
                    ;
                }
                return list;
            } else {
                JOptionPane.showMessageDialog(null, "This date doesn't exist");
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error"+e.getMessage());
        }
        return list = "There are not dates";
    }

}
