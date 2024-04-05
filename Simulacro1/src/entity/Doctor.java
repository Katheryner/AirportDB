package entity;

public class Doctor {
    private int id_doctor;
    private String name_doctor;
    private String last_name_doctor;
    private int idSpecialty;
    private Specialty specialty;

    public Doctor(int idDoctor, String nameDoctor, String lastNameDoctor, int idSpecialty) {
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getName_doctor() {
        return name_doctor;
    }

    public void setName_doctor(String name_doctor) {
        this.name_doctor = name_doctor;
    }

    public String getLast_name_doctor() {
        return last_name_doctor;
    }

    public void setLast_name_doctor(String last_name_doctor) {
        this.last_name_doctor = last_name_doctor;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    public Doctor(){}
    public Doctor(int id_doctor, String name_doctor, String last_name_doctor, int idSpecialty, Specialty specialty) {
        this.id_doctor = id_doctor;
        this.name_doctor = name_doctor;
        this.last_name_doctor = last_name_doctor;
        this.idSpecialty = idSpecialty;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id_doctor=" + id_doctor +
                ", name_doctor='" + name_doctor + '\'' +
                ", last_name_doctor='" + last_name_doctor + '\'' +
                ", idSpecialty=" + idSpecialty +
                ", specialty=" + specialty +
                '}';
    }
}
