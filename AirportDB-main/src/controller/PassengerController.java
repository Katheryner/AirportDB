package controller;

import entity.Passenger;
import model.PassengerModel;

import javax.swing.*;
import java.util.List;

public class PassengerController {
    public static PassengerModel instanceModel() {
        return new PassengerModel();
    }

    public static String listAllPassengers() {
        String list= "List all passengers \n";
        list +="......:::::::   PASSENGERS   :::::::......\n";
        if (!instanceModel().findAll().isEmpty()) {
            for (Object object : instanceModel().findAll()) {
                Passenger passenger = (Passenger) object;
                list += passenger.toString()+"\n";
            }
        }
        return list;
    }

    public static void showAllPassengers(){
        JOptionPane.showMessageDialog(null,listAllPassengers());
    }
    public static void createPassenger() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter the name");
            String last = JOptionPane.showInputDialog(null, "Enter the last name ");
            String identify = JOptionPane.showInputDialog(null,"Enter de document");

            Passenger passenger = new Passenger();
            passenger.setName(name);
            passenger.setLast_name(last);
            passenger.setIdentity(identify);

            passenger = (Passenger) instanceModel().insert(passenger);

            if (passenger.getId() != 0) {
                JOptionPane.showMessageDialog(null, passenger);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }

    public static void updatePassenger() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllPassengers() + "\nEnter id to update"));
            Passenger passenger = (Passenger) instanceModel().findById(number);
            String name = JOptionPane.showInputDialog(null, "Enter the passenger name", passenger.getName());
            String last = JOptionPane.showInputDialog(null, "Enter the passenger last name", passenger.getLast_name());
            String identity = JOptionPane.showInputDialog(null, "Enter the passenger document", passenger.getLast_name());
            passenger.setName(name);
            passenger.setLast_name(last);
            passenger.setIdentity(identity);
            if (instanceModel().update(passenger)) {
                JOptionPane.showMessageDialog(null, "Update successful");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }

    public static void deletePassenger() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllPassengers() + "\nEnter id to delete"));
            if (instanceModel().delete(number)) {
                JOptionPane.showMessageDialog(null, "Delete successful");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findPassengerById() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an passenger"));
            Passenger passenger = (Passenger) instanceModel().findById(number);
            if (passenger != null) {
                JOptionPane.showMessageDialog(null, passenger);
            } else {
                JOptionPane.showMessageDialog(null, "This passenger doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }
    public static String findByName(){
        String list = "List Passengers";
        try {
            String name = JOptionPane.showInputDialog(null,"Enter passenger's name  to find");
            List<Object> listAllPassengers = instanceModel().findByName(name.toLowerCase());
            if (!listAllPassengers.isEmpty()){
                for (Object obj : listAllPassengers){
                    Passenger passenger = (Passenger) obj;
                    list += "\nID " + passenger.getId() +
                            "\nName "+passenger.getName()+
                            "\nLast Name "+ passenger.getLast_name() +
                            "\nIdentify "+passenger.getIdentity();
                }
                return list;
            } else {
                JOptionPane.showMessageDialog(null, "This passenger doesn't exist");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error");
        }
        return list = "Not found name";
    }

    public static void menu(){
        String option;
        String message = """
                            ....:::::: PASSENGER MENU   ::::::....
                            1. Show passengers.
                            2. Create passenger.
                            3. Update passenger.
                            4. Delete passenger.
                            5. Find passenger by id
                            6. Find passenger by name
                            7. Exit.
                                            
                            ENTER THE OPTION TO CONTINUE...
                            """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }
            switch (option){
                case "1":
                    showAllPassengers();
                    break;
                case "2":
                    createPassenger();
                    break;
                case "3":
                    updatePassenger();
                    break;
                case "4":
                    deletePassenger();
                    break;
                case "5":
                    findPassengerById();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null,findByName());;
                    break;
            }
        } while (!option.equals("7"));
    }
}
