package entity;

public class Flight {
    private int id;
    private String destination;
    private String departureDate;
    private String departureTime;
    private int idAirplane;
    private Airplane airplane;

    public Flight(){}

    public Flight(int id, String destination, String departureDate, String departureTime, int idAirplane) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.idAirplane = idAirplane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(int idAirplane) {
        this.idAirplane = idAirplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "Flight: " +
                "id=" + id +
                ", destination ='" + destination + '\'' +
                ", Date='" + departureDate + '\'' +
                ", Time='" + departureTime + '\'' +
                ", id Airplane=" + idAirplane +
                ", airplane=" + airplane +
                '}'+"\n";
    }
}
