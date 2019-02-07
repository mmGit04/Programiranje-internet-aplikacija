package database;
// Generated Aug 24, 2017 3:10:22 PM by Hibernate Tools 4.3.1



/**
 * Rezervacije generated by hbm2java
 */
public class Rezervacije  implements java.io.Serializable {


     private Integer idrezervacije;
     private String ime;
     private String prezime;
     private int passport;
     private int people;
     private int number;
     private int flight;

    public Rezervacije() {
    }

    public Rezervacije(String ime, String prezime, int passport, int people, int number, int flight) {
       this.ime = ime;
       this.prezime = prezime;
       this.passport = passport;
       this.people = people;
       this.number = number;
       this.flight = flight;
    }
   
    public Integer getIdrezervacije() {
        return this.idrezervacije;
    }
    
    public void setIdrezervacije(Integer idrezervacije) {
        this.idrezervacije = idrezervacije;
    }
    public String getIme() {
        return this.ime;
    }
    
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getPrezime() {
        return this.prezime;
    }
    
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public int getPassport() {
        return this.passport;
    }
    
    public void setPassport(int passport) {
        this.passport = passport;
    }
    public int getPeople() {
        return this.people;
    }
    
    public void setPeople(int people) {
        this.people = people;
    }
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    public int getFlight() {
        return this.flight;
    }
    
    public void setFlight(int flight) {
        this.flight = flight;
    }




}


