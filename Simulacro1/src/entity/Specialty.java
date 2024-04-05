package entity;

public class Specialty {

    private int id_specialty;

    private String name_specialty;
    private String description;

    public int getId_specialty() {
        return id_specialty;
    }

    public void setId_specialty(int id_specialty) {
        this.id_specialty = id_specialty;
    }

    public String getName_specialty() {
        return name_specialty;
    }

    public void setName_specialty(String name_specialty) {
        this.name_specialty = name_specialty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Specialty(){}
    public Specialty(int id_specialty, String name_specialty, String description) {
        this.id_specialty = id_specialty;
        this.name_specialty = name_specialty;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Specialty: " +
                "id: " + id_specialty +
                ", name: '" + name_specialty + '\'' +
                ", description: '" + description + '\'' +
                '}';
    }
}
