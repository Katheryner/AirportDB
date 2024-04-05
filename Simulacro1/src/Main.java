import controller.AppointmentController;
import controller.DoctorController;
import controller.PatientController;
import controller.SpecialtyController;
import model.AppointmentModel;
import model.DoctorModel;
import model.SpecialtyModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SpecialtyController objSpecialtyController = new SpecialtyController();
        PatientController objPatientController = new PatientController();
        DoctorController objDoctorController = new DoctorController();
        AppointmentController objAppointmentController = new AppointmentController();
        String opcionGeneral;
        String messageGeneral = """
                ......::MENU::.....
                1. Specialties menu
                2. Patients menu
                3. Doctor menu
                4. Appointment menu
                5. Exit
                """;
        do {
            opcionGeneral= JOptionPane.showInputDialog(null,messageGeneral);
            if (opcionGeneral == null){
                break;
            }
            switch (opcionGeneral){
                case "1":
                    String option;
                    String message = """
                            ....::MENU SPECIALTIES::..
                            1.Add specialty
                            2.Remove specialty
                            3.Update specialty
                            4.Show specialties
                            5.Exit""";
                    do {
                        option = JOptionPane.showInputDialog(null,message);
                        if (option==null){
                            break;
                        }
                        switch (option){
                            case "1":
                                objSpecialtyController.create();
                                break;
                            case "2":
                                objSpecialtyController.delete();
                                break;
                            case "3":
                                objSpecialtyController.update();
                                break;
                            case "4":
                                objSpecialtyController.getAll();
                                break;
                        }
                    } while (!option.equals("5"));
                    break;
                case "2":
                    String option1;
                    String message1 = """
                        .....::MENU PATIENTS::....
                        1.Add patient
                        2.Remove patient
                        3.Update patient
                        4.Show patients
                        5.Find patient for id
                        6.Exit""";
                            do {
                                option1 = JOptionPane.showInputDialog(null,message1);
                                if (option1==null){
                                    break;
                                }
                                switch (option1){
                                    case "1":
                                        objPatientController.create();
                                        break;
                                    case "2":
                                        objPatientController.delete();
                                        break;
                                    case "3":
                                        objPatientController.update();
                                        break;
                                    case "4":
                                        objPatientController.getAll();
                                        break;
                                    case "5":
                                        objPatientController.findPatientbyID();
                                        break;
                                }
                            } while (!option1.equals("6"));
                            break;
                case "3":
                String option2;
                String message2 = """
                    .....::MENU DOCTORS::....
                    1.Add doctors
                    2.Remove doctors
                    3.Update doctors
                    4.Show doctors
                    5.Find doctors for specialty
                    6.Exit""";
                        do {
                            option2 = JOptionPane.showInputDialog(null,message2);
                            if (option2==null){
                                break;
                            }
                            switch (option2){
                                case "1":
                                    objDoctorController.create();
                                    break;
                                case "2":
                                    objDoctorController.delete();
                                    break;
                                case "3":
                                    objDoctorController.update();
                                    break;
                                case "4":
                                    objDoctorController.getAll();
                                    break;
                                case "5":
                                    JOptionPane.showMessageDialog(null,objDoctorController.showAllDoctorsBySpecialty());
                                    break;
                            }
                        } while (!option2.equals("6"));
                        break;            
                case "4":
                String option3;
                String message3 = """
                    .....::MENU APPOINTMENT::....
                    1.Add appointment
                    2.Remove appointment
                    3.Update appointment
                    4.Show appointment
                    5.Find appointment for date
                    6.Exit""";
                        do {
                            option3 = JOptionPane.showInputDialog(null,message3);
                            if (option3==null){
                                break;
                            }
                            switch (option3){
                                case "1":
                                    objAppointmentController.create();
                                    break;
                                case "2":
                                    objAppointmentController.delete();
                                    break;
                                case "3":
                                    objAppointmentController.update();
                                    break;
                                case "4":
                                    objAppointmentController.getAll();
                                    break;
                                case "5":
                                    JOptionPane.showMessageDialog(null,objAppointmentController.findByDate());
                                    break;
                            }
                        } while (!option3.equals("6"));
                        break;            
                }
            } while (!opcionGeneral.equals("5"));
        }

    }
