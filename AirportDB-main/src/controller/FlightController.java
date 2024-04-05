package controller;

import entity.Flight;
import model.FlightModel;

import javax.swing.*;
import java.util.List;

public class FlightController {

    public static FlightModel instanceModel(){
        return new FlightModel();
    }

    public static String listAllFlights() {
        String list = ".........List Flights...........";
        list +="......:::::::   All FLIGHTS   :::::::......\n";
        if (!instanceModel().findAll().isEmpty()) {
            for (Object object : instanceModel().findAll()) {
                Flight flight = (Flight) object;
                list += flight.toString()+"\n";
            }
        }
        return list;
    }

    public static void showAllFlights(){
        JOptionPane.showMessageDialog(null,listAllFlights());
    }

    public static void createFlight() {
        try {
            String destination = JOptionPane.showInputDialog(null, "Enter the flight destination");
            String departureDate = JOptionPane.showInputDialog(null, "Enter the flight departure date");
            String departureTime = JOptionPane.showInputDialog(null, "Enter the flight departure time");
            int airplaneId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the flight airplane id"));

            Flight flight = new Flight();
            flight.setDestination(destination);
            flight.setDepartureDate(departureDate);
            flight.setDepartureTime(departureTime);
            flight.setIdAirplane(airplaneId);

            flight = (Flight) instanceModel().insert(flight);

            if (flight.getId() != 0) {
                JOptionPane.showMessageDialog(null, flight);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }

    }
    public static void updateFlight() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllFlights() + "\nEnter id to update"));
            Flight flight = (Flight) instanceModel().findById(number);
            String destination = JOptionPane.showInputDialog(null, "Enter the flight destination",flight.getDestination());
            String departureDate = JOptionPane.showInputDialog(null, "Enter the flight departure date",flight.getDepartureDate());
            String departureTime = JOptionPane.showInputDialog(null, "Enter the flight departure time",flight.getDepartureTime());
            int airplaneId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the flight airplane id",flight.getIdAirplane()));

            flight.setDestination(destination);
            flight.setDepartureDate(departureDate);
            flight.setDepartureTime(departureTime);
            flight.setIdAirplane(airplaneId);

            if (instanceModel().update(flight)) {
                JOptionPane.showMessageDialog(null, "Update successful");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data");
        }
    }

    public static void deleteFlight() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllFlights() + "\nEnter id to delete"));
            if (instanceModel().delete(number)) {
                JOptionPane.showMessageDialog(null, "Delete successful");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }

    public static void findFlightById() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an flight"));
            Flight flight = (Flight) instanceModel().findById(number);
            if (flight != null) {
                JOptionPane.showMessageDialog(null, flight);
            } else {
                JOptionPane.showMessageDialog(null, "This flight doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number");
        }
    }
    public static String findByDestiny(){
        String list = "List Destiny";
        try {
            String destiny = JOptionPane.showInputDialog(null,"Enter destiny to find");
            List<Object> flights = instanceModel().findByDestiny(destiny.toLowerCase());
            if (!flights.isEmpty()){
                for (Object obj : flights){
                    Flight flight = (Flight) obj;
                    list += "\nID "+ flight.getDestination()+
                            "\nDate "+ flight.getDepartureDate()+
                            "\nHour "+ flight.getDepartureTime()+
                            "\nAirplane ID "+flight.getIdAirplane();
                }
                return list;
            } else {
                JOptionPane.showMessageDialog(null, "This destiny doesn't exist");
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error");
        }
        return list = "There are not airplanes";
    }

    public static void menu(){
        String option;
        String message = """
                            ....::::::   FLIGHTS MENU   ::::::....
                            1. Show Flights.
                            2. Create flight.
                            3. Update flight.
                            4. Delete flight.
                            5. Find flight by id.
                            6. Find flight by destiny.
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
                    showAllFlights();
                    break;
                case "2":
                    createFlight();
                    break;
                case "3":
                    updateFlight();
                    break;
                case "4":
                    deleteFlight();
                    break;
                case "5":
                    findFlightById();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null,findByDestiny());
                    break;
            }
        } while (!option.equals("7"));
    }

}
