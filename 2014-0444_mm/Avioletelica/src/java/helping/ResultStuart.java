
package helping;

import java.util.Date;
import java.sql.Time;



public class ResultStuart {
    private int ID;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    private String start_city;
    private String end_city;
    private String end_country;
    private String start_country;
    private String start_air;
    private String end_air;
    private String duration;

    private Date start_date;
    private Time start_time;
  
    
    
    private static int id=10000001;

    public static int getId() {
        
                
        return id;
    }

    public static void setId(int id) {
        ResultStuart.id = id;
    }
    
    
    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }
    
    private int free;

    public ResultStuart(int ID, String start_city, String end_city, String end_country, String start_country, String start_air, String end_air, String duration, Date start_date, int free,Time start_time,int number) {
        this.ID = ID;
        this.start_city = start_city;
        this.end_city = end_city;
        this.end_country = end_country;
        this.start_country = start_country;
        this.start_air = start_air;
        this.end_air = end_air;
        this.duration = duration;
        this.start_date = start_date;
        this.free = free;
        this.start_time=start_time;
        this.number=number;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }
    
}
