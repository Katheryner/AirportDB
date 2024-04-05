package entity;

import java.sql.Date;
import java.sql.Time;



public class Appointment {
    private int id_appointment;
    private String date_appointment;
    private String hour_appointment;
    private String reason;
    private int idPatient;
    private int idDoctor;

    public Appointment() {

    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public String getDate_appointment() {
        return date_appointment;
    }

    public void setDate_appointment(String date_appointment) {
        this.date_appointment = date_appointment;
    }

    public String getHour_appointment() {
        return hour_appointment;
    }

    public void setHour_appointment(String hour_appointment) {
        this.hour_appointment = hour_appointment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Appointment(int id_appointment, String date_appointment, String hour_appointment, String reason, int idPatient, int idDoctor) {
        this.id_appointment = id_appointment;
        this.date_appointment = date_appointment;
        this.hour_appointment = hour_appointment;
        this.reason = reason;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
    }

    @Override
    public String toString() {
        return "Appointments: " +
                id_appointment +
                ", date: " + date_appointment + '\'' +
                ", hour: '" + hour_appointment + '\'' +
                ", reason: '" + reason + '\'' +
                 + idPatient +
                 + idDoctor +
                '}'+"\n";
    }
}
