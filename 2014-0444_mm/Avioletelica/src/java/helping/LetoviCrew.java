package helping;

import java.sql.Time;
import java.util.Date;

public class LetoviCrew {

    private int ID;
    private int number;
    private String start_city;
    private String start_air;
    private String start_country;
    private String end_city;
    private String end_country;
    private String end_air;
    private String unexp_airport;
    private String unexp_city;
    private String unexp_country;
    private String start_gate;
    private String end_gate;
    private String duration;

    private Date expect_time;
    private Date end_time;
    
    private Date start_date;
    private Time start_time;
    private Date planned_date;
    private Time planned_time;
     private int reservation;
    private int status;
    private byte carter;
    private byte canceled;
    private String airplane;

    public LetoviCrew(int ID, int number, String start_city, String start_air, String start_country, String end_city, String end_country, String end_air, String unexp_airport, String unexp_city, String unexp_country, String start_gate, String end_gate, String duration, Date expect_time, Date end_time, int reservation, Date start_date, Time start_time, Date planned_date, Time planned_time, int status, byte carter, byte canceled, String airplane) {
        this.ID = ID;
        this.number = number;
        this.start_city = start_city;
        this.start_air = start_air;
        this.start_country = start_country;
        this.end_city = end_city;
        this.end_country = end_country;
        this.end_air = end_air;
        this.unexp_airport = unexp_airport;
        this.unexp_city = unexp_city;
        this.unexp_country = unexp_country;
        this.start_gate = start_gate;
        this.end_gate = end_gate;
        this.duration = duration;
        this.expect_time = expect_time;
        this.end_time = end_time;
        this.reservation = reservation;
        this.start_date = start_date;
        this.start_time = start_time;
        this.planned_date = planned_date;
        this.planned_time = planned_time;
        this.status = status;
        this.carter = carter;
        this.canceled = canceled;
        this.airplane = airplane;
    }
  
    
    public String getUnexp_city() {
        return unexp_city;
    }

    public void setUnexp_city(String unexp_city) {
        this.unexp_city = unexp_city;
    }

    public String getUnexp_country() {
        return unexp_country;
    }

    public void setUnexp_country(String unexp_country) {
        this.unexp_country = unexp_country;
    }

   

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStart_city() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city = start_city;
    }

    public String getEnd_city() {
        return end_city;
    }

    public void setEnd_city(String end_city) {
        this.end_city = end_city;
    }

    public String getEnd_country() {
        return end_country;
    }

    public void setEnd_country(String end_country) {
        this.end_country = end_country;
    }

    public String getStart_country() {
        return start_country;
    }

    public void setStart_country(String start_country) {
        this.start_country = start_country;
    }

    public String getStart_air() {
        return start_air;
    }

    public void setStart_air(String start_air) {
        this.start_air = start_air;
    }

    public String getEnd_air() {
        return end_air;
    }

    public void setEnd_air(String end_air) {
        this.end_air = end_air;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getExpect_time() {
        return expect_time;
    }

    public void setExpect_time(Date expect_time) {
        this.expect_time = expect_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getStart_gate() {
        return start_gate;
    }

    public void setStart_gate(String start_gate) {
        this.start_gate = start_gate;
    }

    public String getEnd_gate() {
        return end_gate;
    }

    public void setEnd_gate(String end_gate) {
        this.end_gate = end_gate;
    }

    public String getUnexp_airport() {
        return unexp_airport;
    }

    public void setUnexp_airport(String unexp_airport) {
        this.unexp_airport = unexp_airport;
    }

    public byte getCarter() {
        return carter;
    }

    public void setCarter(byte carter) {
        this.carter = carter;
    }

    public byte getCanceled() {
        return canceled;
    }

    public void setCanceled(byte canceled) {
        this.canceled = canceled;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Date getPlanned_date() {
        return planned_date;
    }

    public void setPlanned_date(Date planned_date) {
        this.planned_date = planned_date;
    }

    public Time getPlanned_time() {
        return planned_time;
    }

    public void setPlanned_time(Time planned_time) {
        this.planned_time = planned_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
