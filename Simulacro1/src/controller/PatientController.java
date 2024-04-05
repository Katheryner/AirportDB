package controller;

import entity.Patient;
import model.PatientModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;


public class PatientController {
    PatientModel objPatientModel = new PatientModel();
    public PatientController(){
        //Crear una instancia del model
        this.objPatientModel =new PatientModel();
    }
    public void create(){
        Patient objPatient = new Patient();

        String name = JOptionPane.showInputDialog(null,"Insert name");
        String last_name = JOptionPane.showInputDialog(null,"Insert last name");
        String birthday= JOptionPane.showInputDialog(null,"Insert birthday");
        String document = JOptionPane.showInputDialog(null,"Insert identification document");
        objPatient.setName_patient(name);
        objPatient.setLast_name_patient(last_name);
        objPatient.setBirthday(birthday);
        objPatient.setDocument(document);


        objPatient= (Patient) this.objPatientModel.create(objPatient);
        JOptionPane.showMessageDialog(null, objPatient.toString());
    }
    public void getAll(){
        String list = this.getAll(this.objPatientModel.findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public String getAll(List<Object> listObject){
        String list = ".....::Patients List::....\n";
        for (Object obj: listObject){
            Patient objPatient = (Patient)obj;
            list += objPatient.toString()+"\n";
        }
      return list;
    }

    public void delete(){
        String listPatients = ".....::Patients list::.....";

        for (Object obj: this.objPatientModel.findAll()){
            Patient objPatient = (Patient) obj;
            listPatients += objPatient.toString() + "\n";
        }
        int confirm = 1;
        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listPatients + "Enter the ID of the patient to delete"));
        Patient objPatient = (Patient) this.objPatientModel.findById(isDelete);
        if (objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the patient?");
            if (confirm == 0){
                this.objPatientModel.delete(objPatient);
            }
        }
    }
    public void update() {
        //Listamos
        String listPatients = this.getAll(this.objPatientModel.findAll());

        //Pedimos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPatients +"\nEnter the ID of the patient to edit"));

        //Verificar el id
        Patient objPatient = (Patient) this.objPatientModel.findById(idUpdate);

        if  (objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient not found.");
        }else {
            String name = JOptionPane.showInputDialog(null,"Enter new name "+ objPatient.getName_patient());
            String last = JOptionPane.showInputDialog(null,"Enter new description " +objPatient.getLast_name_patient());
            String birthday = JOptionPane.showInputDialog(null,"Enter birthday"+objPatient.getBirthday());
            String document = JOptionPane.showInputDialog(null,"Enter document"+ objPatient.getDocument());

            objPatient.setName_patient(name);
            objPatient.setLast_name_patient(last);
            objPatient.setBirthday(birthday);
            objPatient.setDocument(document);

            this.objPatientModel.update(objPatient);
        }
    }
    public void findPatientbyID(){
        this.getAll();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the ID of the patient"));
        Patient objPatient = (Patient)objPatientModel.findById(id);
        JOptionPane.showMessageDialog(null,objPatient.toString());
    }
}
