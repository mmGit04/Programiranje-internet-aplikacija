package database;
// Generated Aug 24, 2017 3:10:22 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Flight generated by hbm2java
 */
public class Flight  implements java.io.Serializable {


     private Integer idflight;
     private Airplane airplane;
     private Airport airportByUnexpAirport;
     private Airport airportByStartAirport;
     private Airport airportByEndAirport;
     private int number;
     private String duration;
     private Date expectTime;
     private Date endTime;
     private int startGate;
     private int endGate;
     private Byte carter;
     private Byte canceled;
     private Integer reservation;
     private Date startDate;
     private Date plannedDate;
     private Date startTime;
     private Date plannedTime;
     private Integer status;
     private Set<Reservation> reservations = new HashSet<Reservation>(0);
     private Set<RadarFlight> radarFlights = new HashSet<RadarFlight>(0);
     private Set<FlightCrew> flightCrews = new HashSet<FlightCrew>(0);

    public Flight() {
    }

	
    public Flight(Airplane airplane, Airport airportByStartAirport, Airport airportByEndAirport, int number, String duration, Date expectTime, Date endTime, int startGate, int endGate) {
        this.airplane = airplane;
        this.airportByStartAirport = airportByStartAirport;
        this.airportByEndAirport = airportByEndAirport;
        this.number = number;
        this.duration = duration;
        this.expectTime = expectTime;
        this.endTime = endTime;
        this.startGate = startGate;
        this.endGate = endGate;
    }
    public Flight(Airplane airplane, Airport airportByUnexpAirport, Airport airportByStartAirport, Airport airportByEndAirport, int number, String duration, Date expectTime, Date endTime, int startGate, int endGate, Byte carter, Byte canceled, Integer reservation, Date startDate, Date plannedDate, Date startTime, Date plannedTime, Integer status, Set<Reservation> reservations, Set<RadarFlight> radarFlights, Set<FlightCrew> flightCrews) {
       this.airplane = airplane;
       this.airportByUnexpAirport = airportByUnexpAirport;
       this.airportByStartAirport = airportByStartAirport;
       this.airportByEndAirport = airportByEndAirport;
       this.number = number;
       this.duration = duration;
       this.expectTime = expectTime;
       this.endTime = endTime;
       this.startGate = startGate;
       this.endGate = endGate;
       this.carter = carter;
       this.canceled = canceled;
       this.reservation = reservation;
       this.startDate = startDate;
       this.plannedDate = plannedDate;
       this.startTime = startTime;
       this.plannedTime = plannedTime;
       this.status = status;
       this.reservations = reservations;
       this.radarFlights = radarFlights;
       this.flightCrews = flightCrews;
    }
   
    public Integer getIdflight() {
        return this.idflight;
    }
    
    public void setIdflight(Integer idflight) {
        this.idflight = idflight;
    }
    public Airplane getAirplane() {
        return this.airplane;
    }
    
    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
    public Airport getAirportByUnexpAirport() {
        return this.airportByUnexpAirport;
    }
    
    public void setAirportByUnexpAirport(Airport airportByUnexpAirport) {
        this.airportByUnexpAirport = airportByUnexpAirport;
    }
    public Airport getAirportByStartAirport() {
        return this.airportByStartAirport;
    }
    
    public void setAirportByStartAirport(Airport airportByStartAirport) {
        this.airportByStartAirport = airportByStartAirport;
    }
    public Airport getAirportByEndAirport() {
        return this.airportByEndAirport;
    }
    
    public void setAirportByEndAirport(Airport airportByEndAirport) {
        this.airportByEndAirport = airportByEndAirport;
    }
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    public String getDuration() {
        return this.duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public Date getExpectTime() {
        return this.expectTime;
    }
    
    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public int getStartGate() {
        return this.startGate;
    }
    
    public void setStartGate(int startGate) {
        this.startGate = startGate;
    }
    public int getEndGate() {
        return this.endGate;
    }
    
    public void setEndGate(int endGate) {
        this.endGate = endGate;
    }
    public Byte getCarter() {
        return this.carter;
    }
    
    public void setCarter(Byte carter) {
        this.carter = carter;
    }
    public Byte getCanceled() {
        return this.canceled;
    }
    
    public void setCanceled(Byte canceled) {
        this.canceled = canceled;
    }
    public Integer getReservation() {
        return this.reservation;
    }
    
    public void setReservation(Integer reservation) {
        this.reservation = reservation;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getPlannedDate() {
        return this.plannedDate;
    }
    
    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getPlannedTime() {
        return this.plannedTime;
    }
    
    public void setPlannedTime(Date plannedTime) {
        this.plannedTime = plannedTime;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Set<Reservation> getReservations() {
        return this.reservations;
    }
    
    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
    public Set<RadarFlight> getRadarFlights() {
        return this.radarFlights;
    }
    
    public void setRadarFlights(Set<RadarFlight> radarFlights) {
        this.radarFlights = radarFlights;
    }
    public Set<FlightCrew> getFlightCrews() {
        return this.flightCrews;
    }
    
    public void setFlightCrews(Set<FlightCrew> flightCrews) {
        this.flightCrews = flightCrews;
    }




}


