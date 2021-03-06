package database;
// Generated Aug 24, 2017 3:10:22 PM by Hibernate Tools 4.3.1



/**
 * RadarCord generated by hbm2java
 */
public class RadarCord  implements java.io.Serializable {


     private Integer idradarCord;
     private RadarCenter radarCenter;
     private double lat;
     private double lng;

    public RadarCord() {
    }

    public RadarCord(RadarCenter radarCenter, double lat, double lng) {
       this.radarCenter = radarCenter;
       this.lat = lat;
       this.lng = lng;
    }
   
    public Integer getIdradarCord() {
        return this.idradarCord;
    }
    
    public void setIdradarCord(Integer idradarCord) {
        this.idradarCord = idradarCord;
    }
    public RadarCenter getRadarCenter() {
        return this.radarCenter;
    }
    
    public void setRadarCenter(RadarCenter radarCenter) {
        this.radarCenter = radarCenter;
    }
    public double getLat() {
        return this.lat;
    }
    
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return this.lng;
    }
    
    public void setLng(double lng) {
        this.lng = lng;
    }




}


