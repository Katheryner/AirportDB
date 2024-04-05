package controller;


import entity.Doctor;
import entity.Patient;
import entity.Specialty;
import model.DoctorModel;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;


public class DoctorController {

    DoctorModel objDoctorModel = new DoctorModel();

    public DoctorController(){
        this.objDoctorModel= new DoctorModel();
    }
    public void create(){
        Doctor objDoctor = new Doctor();
        SpecialtyModel specialtyModel = new SpecialtyModel();


        String name = JOptionPane.showInputDialog(null,"Insert name");
        String last_name = JOptionPane.showInputDialog(null,"Insert last name");
        int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert id of specialty"));
        objDoctor.setName_doctor(name);
        objDoctor.setLast_name_doctor(last_name);
        objDoctor.setIdSpecialty(idSpecialty);
        Specialty specialty = (Specialty) specialtyModel.findById(idSpecialty);
        objDoctor.setSpecialty(specialty);



        objDoctor= (Doctor) this.objDoctorModel.create(objDoctor);
        JOptionPane.showMessageDialog(null, objDoctor.toString());
    }

    public void getAll(){
        String list = this.getAll(this.objDoctorModel.findAll());
        JOptionPane.showMessageDialog(null,list);
    }
    public String getAll(List<Object>listObject){
        String list = "Doctors List\n";
        for(Object obj: listObject){
            Doctor objdoctor = (Doctor)obj;
            list += objdoctor.toString()+"\n";
        }
        return list;
    }
    public void delete(){
        String listDoctors = "....::Doctors list::....";

        for (Object obj: this.objDoctorModel.findAll()){
            Doctor objDoctor = (Doctor) obj;
            listDoctors += objDoctor.toString() + "\n";
        }
        int confirm = 1;
        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listDoctors + "Enter the ID of the doctor to delete"));
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(isDelete);
        if (objDoctor == null){
            JOptionPane.showMessageDialog(null, "Doctor not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the doctor?");
            if (confirm == 0){
                this.objDoctorModel.delete(objDoctor);
            }
        }
    }
    public void update() {
        SpecialtyController objspecialty = new SpecialtyController();
        SpecialtyModel objSpecialty = new SpecialtyModel();
        //Listamos
        String listDoctors = this.getAll(this.objDoctorModel.findAll());

        //Pedimos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listDoctors +"\nEnter the ID of the doctor to edit"));

        //Verificar el id
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idUpdate);

        if  (objDoctor == null){
            JOptionPane.showMessageDialog(null, "Doctor not found.");
        }else {
            String name = JOptionPane.showInputDialog(null,"Enter new name "+ objDoctor.getName_doctor());
            String last = JOptionPane.showInputDialog(null,"Enter new last name " +objDoctor.getLast_name_doctor());
            int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null,objspecialty.getAll(objSpecialty.findAll())+"Enter id Specialty"+objDoctor.getIdSpecialty()));


            objDoctor.setName_doctor(name);
            objDoctor.setLast_name_doctor(last);
            objDoctor.setIdSpecialty(idSpecialty);

            this.objDoctorModel.update(objDoctor);
        }
    }
    public String showAllDoctorsBySpecialty() {
        String list = "List Doctors";
        list += "....All Doctors by specialty....";
        try{
            SpecialtyController objspecialty = new SpecialtyController();
            SpecialtyModel objspecialtymodel = new SpecialtyModel();
            int found = Integer.parseInt(JOptionPane.showInputDialog(null,objspecialty.getAll(objspecialtymodel.findAll())+"Enter specialty id to find"));
            List<Object> listDoctors = objDoctorModel.showAllmedics(found);
            if (!listDoctors.isEmpty()) {
                for (Object object : listDoctors)  {
                    Doctor doctor = (Doctor) object;
                    list += "\nID: "+ doctor.getId_doctor()+
                            "\nName: "+ doctor.getName_doctor()+
                            "\nLast name: "+doctor.getLast_name_doctor();
                }
                return list;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
        return list = "\nThere are no doctors in this list";
    }
}
