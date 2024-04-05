package controller;

import entity.Airplane;
import entity.Booking;
import entity.Flight;
import model.BookingModel;
import model.FlightModel;

import javax.swing.*;
import java.awt.print.Book;

public class BookingController {

    private static final FlightModel flightModel = new FlightModel();

    public static BookingModel instanceModel() {
        return new BookingModel();
    }

    public static String listAllBooking() {
        String list = "List all booking \n";
        list += "...All BOOKING...\n";
        if (!instanceModel().findAll().isEmpty()) {
            for (Object object : instanceModel().findAll()) {
                Booking booking = (Booking) object;
                list += booking.toString() + "\n";
            }
        }
        return list;
    }

    public static void showAllBooking() {
        JOptionPane.showMessageDialog(null, listAllBooking());
    }

    public static void createBooking() {
        try {

            String seat = JOptionPane.showInputDialog(null, "Enter the seat");
            int idPassenger = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the id passenger"));
            int idFlight = Integer.parseInt(JOptionPane.showInputDialog(null, FlightController.listAllFlights()+"Enter the id flight"));

            boolean isSeatAvailable = instanceModel().isSeatAvailable(seat, idFlight);

            if(!isSeatAvailable){
                JOptionPane.showMessageDialog(null, "The seat " + seat + " is not available.");
                return;
            }

            Flight flight = (Flight) BookingController.flightModel.findById(idFlight);
            String bookingDate = flight.getDepartureDate();

            Booking booking = new Booking();
            booking.setBookingDate(bookingDate);
            booking.setSeat(seat);
            booking.setIdPassenger(idPassenger);
            booking.setIdFlight(idFlight);

            booking = (Booking) instanceModel().insert(booking);

            if (booking.getId() != 0) {

                JOptionPane.showMessageDialog(null, booking);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data " + e.getMessage());
        }

    }

    public static void updateBooking() {
        String listBooking = listAllBooking();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooking + "\nEnter the id to update"));
        Booking booking = (Booking) instanceModel().findById(idUpdate);
        try {
            String bookingDate = JOptionPane.showInputDialog(null, "Enter the booking date");
            String seat = JOptionPane.showInputDialog(null, "Enter the seat");
            int idPassenger = JOptionPane.showConfirmDialog(null, "Enter the id passenger");
            int idFlight = JOptionPane.showConfirmDialog(null, "Enter the id flight");

            booking.setBookingDate(bookingDate);
            booking.setSeat(seat);
            booking.setIdPassenger(idPassenger);
            booking.setIdFlight(idFlight);
            if (instanceModel().update(booking)) {
                JOptionPane.showMessageDialog(null, "Update successful");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter valid data " + e.getMessage());
        }

    }

    public static void deleteBooking() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, listAllBooking() + "\nEnter id to delete"));
            if (instanceModel().delete(number)) {
                JOptionPane.showMessageDialog(null, "Delete successful");
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number " + e.getMessage());
        }
    }

    public static void findBookingById() {
        try {
            int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter id to find an booking"));
            Booking booking = (Booking) instanceModel().findById(number);
            if (booking != null) {
                JOptionPane.showMessageDialog(null, booking);
            } else {
                JOptionPane.showMessageDialog(null, "This booking doesn't exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a number " + e.getMessage());
        }
    }
    public static void menu(){
        String option;
        String message = """
                            ....::::::   BOOKING MENU   ::::::....
                            1. Show bookings.
                            2. Create booking.
                            3. Update booking.
                            4. Delete booking.
                            5. Find booking.
                            6. Exit.
                                            
                            ENTER THE OPTION TO CONTINUE...
                            """;
        do {
            option = JOptionPane.showInputDialog(null, message);
            if (option == null) {
                break;
            }
            switch (option){
                case "1":
                    showAllBooking();
                    break;
                case "2":
                    createBooking();
                    break;
                case "3":
                    updateBooking();
                    break;
                case "4":
                    deleteBooking();
                    break;
                case "5":
                    findBookingById();
                    break;
            }
        } while (!option.equals("6"));
    }
}