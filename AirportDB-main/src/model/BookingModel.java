package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;
import entity.Booking;
import entity.Flight;
import entity.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Booking booking = (Booking) object;
        Connection connection = ConfigDB.openConnection();
        String sql = "INSERT INTO bookings (booking_date,seat,id_passenger,id_flight) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,booking.getBookingDate());
            ps.setString(2,booking.getSeat());
            ps.setInt(3,booking.getIdPassenger());
            ps.setInt(4,booking.getIdFlight());
            int rows = ps.executeUpdate();
            if(rows>0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    booking.setId(rs.getInt(1));
                    System.out.println("Insert: booking inserted successfully");
                }
            }
        }catch (SQLException e){
            System.out.println("Insert: error in database"+e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return booking;
    }

    @Override
    public boolean update(Object object) {
        Booking booking = (Booking) object;
        boolean isUpdated = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "UPDATE passengers SET booking_date = ?,seat = ?, id_passenger= ? id_flight=? WHERE id = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,booking.getBookingDate());
            ps.setString(2,booking.getSeat());
            ps.setInt(3,booking.getIdPassenger());
            ps.setInt(4,booking.getIdFlight());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Update: booking update successfully");
                isUpdated = true;
            }

        } catch (SQLException e) {
            System.out.println("Update: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;    }

    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;
        Connection connection = ConfigDB.openConnection();
        String sql = "DELETE FROM bookings WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Delete: booking deleted successfully");
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Delete: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listBooking = new ArrayList<>();
        Connection connection = ConfigDB.openConnection();
        String sql = "SELECT * FROM bookings";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listBooking.add(new Booking(rs.getInt("id"),rs.getString("booking_date"), rs.getString("seat"),rs.getInt("id_passenger"),rs.getInt("id_flight")));
            }

        } catch (SQLException e) {
            System.out.println("FindAll: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listBooking;
    }

    @Override
    public Object findById(int id) {
        Connection connection = ConfigDB.openConnection();
        Booking booking = null;
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                booking = new Booking(rs.getInt("id"),rs.getString("booking_date"), rs.getString("seat"),rs.getInt("id_passenger"),rs.getInt("id_flight"));
            }

        } catch (SQLException e) {
            System.out.println("FindById: error in database\n" + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return booking;
    }

    public boolean isSeatAvailable(String idSeat, int id_flight){
        Connection connection = ConfigDB.openConnection();
        boolean result = true;
        String sql = "SELECT b.id, b.seat FROM bookings b WHERE seat = ? AND id_flight = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, idSeat);
            ps.setInt(2, id_flight);
            ps.executeQuery();

            ResultSet set = ps.getResultSet();

            while(set.next()){
                String seat = set.getString("seat");
                if(seat.equals(idSeat)){
                    result = false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
