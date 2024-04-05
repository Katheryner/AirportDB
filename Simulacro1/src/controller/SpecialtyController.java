package controller;

import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class SpecialtyController {

    SpecialtyModel objSpecialtyModel = new SpecialtyModel();

    public SpecialtyController(){
        //Crear una instancia del model
        this.objSpecialtyModel =new SpecialtyModel();
    }

    public void create(){
        Specialty objSpecialty = new Specialty();

        String name = JOptionPane.showInputDialog(null,"Insert Specialty's name");
        String description = JOptionPane.showInputDialog(null,"Insert description");
        objSpecialty.setName_specialty(name);
        objSpecialty.setDescription(description);

        objSpecialty= (Specialty) this.objSpecialtyModel.create(objSpecialty);
        JOptionPane.showMessageDialog(null, objSpecialty.toString());
    }

    public void getAll() {

        String list = this.getAll(this.objSpecialtyModel.findAll());
        //Mostramos toda lista
        JOptionPane.showMessageDialog(null, list);

    }
    public String getAll(List<Object> listObject){
        String list = "....:::List Specialties:::....\n";
        //Iteramos sobre la lista que devuelve el metodo findAll

        for (Object obj : listObject){
            //Convertimos o casteamos el objeto tipo Object a specialty
            Specialty objSpecialty = (Specialty) obj;
            //Concatenamos la informacion

            list += objSpecialty.toString()+ "\n";
        }
        //Mostramos toda la lista

        return list;
    }
    public void delete(){
        String listSpecialties = ".....::Specialties list::.....\n";
        for (Object obj:this.objSpecialtyModel.findAll()){
            Specialty objspecialty= (Specialty) obj;
            listSpecialties += objspecialty.toString()+"\n";
        }
        int confirm = 1;
        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpecialties+"Enter de ID of the specialty to remove"));
        Specialty objspecialty = (Specialty) this.objSpecialtyModel.findById(isDelete);

        if (objspecialty== null){
            JOptionPane.showMessageDialog(null,"Specialty no found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete Specialty");
            if (confirm ==0){
                this.objSpecialtyModel.delete(objspecialty);
            }
        }
    }
    public void update() {
        //Listamos
        String listSpecialties = this.getAll(this.objSpecialtyModel.findAll());

        //Pedimos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listSpecialties +"\nEnter the ID of the specialty to edit"));

        //Verificar el id
        Specialty objSpecilaty = (Specialty) this.objSpecialtyModel.findById(idUpdate);

        if  (objSpecilaty == null){
            JOptionPane.showMessageDialog(null, "Specialty not found.");
        }else {
            String name = JOptionPane.showInputDialog(null,"Enter new name "+ objSpecilaty.getName_specialty());
            String description = JOptionPane.showInputDialog(null,"Enter new description " +objSpecilaty.getDescription());
             objSpecilaty.setName_specialty(name);
             objSpecilaty.setDescription(description);

            this.objSpecialtyModel.update(objSpecilaty);
        }
    }

}
