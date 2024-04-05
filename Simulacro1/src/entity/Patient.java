package entity;


import java.sql.Date;

public class Patient {
    private int id_patient;
    private String name_patient;
    private String last_name_patient;
    private String birthday;
    private String document;

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getName_patient() {
        return name_patient;
    }

    public void setName_patient(String name_patient) {
        this.name_patient = name_patient;
    }

    public String getLast_name_patient() {
        return last_name_patient;
    }

    public void setLast_name_patient(String last_name_patient) {
        this.last_name_patient = last_name_patient;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
    public Patient(){
    }
    public Patient(int id_patient, String name_patient, String last_name_patient, String birthday, String document) {
        this.id_patient = id_patient;
        this.name_patient = name_patient;
        this.last_name_patient = last_name_patient;
        this.birthday = birthday;
        this.document = document;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id_patient=" + id_patient +
                ", name_patient='" + name_patient + '\'' +
                ", last_name_patient='" + last_name_patient + '\'' +
                ", birthday=" + birthday +
                ", document='" + document + '\'' +
                '}';
    }
}
