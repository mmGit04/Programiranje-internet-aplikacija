package database;
// Generated Aug 24, 2017 3:10:22 PM by Hibernate Tools 4.3.1



/**
 * PilotLicence generated by hbm2java
 */
public class PilotLicence  implements java.io.Serializable {


     private Integer idpilotLicence;
     private AirplaneLicence airplaneLicence;
     private User user;

    public PilotLicence() {
    }

    public PilotLicence(AirplaneLicence airplaneLicence, User user) {
       this.airplaneLicence = airplaneLicence;
       this.user = user;
    }
   
    public Integer getIdpilotLicence() {
        return this.idpilotLicence;
    }
    
    public void setIdpilotLicence(Integer idpilotLicence) {
        this.idpilotLicence = idpilotLicence;
    }
    public AirplaneLicence getAirplaneLicence() {
        return this.airplaneLicence;
    }
    
    public void setAirplaneLicence(AirplaneLicence airplaneLicence) {
        this.airplaneLicence = airplaneLicence;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}


