package database;
// Generated Aug 24, 2017 3:10:22 PM by Hibernate Tools 4.3.1



/**
 * Ponude generated by hbm2java
 */
public class Ponude  implements java.io.Serializable {


     private Integer idponude;
     private int kupac;
     private int prodavac;
     private int avion;
     private double ponuda;
     private int odobreno;

    public Ponude() {
    }

    public Ponude(int kupac, int prodavac, int avion, double ponuda, int odobreno) {
       this.kupac = kupac;
       this.prodavac = prodavac;
       this.avion = avion;
       this.ponuda = ponuda;
       this.odobreno = odobreno;
    }
   
    public Integer getIdponude() {
        return this.idponude;
    }
    
    public void setIdponude(Integer idponude) {
        this.idponude = idponude;
    }
    public int getKupac() {
        return this.kupac;
    }
    
    public void setKupac(int kupac) {
        this.kupac = kupac;
    }
    public int getProdavac() {
        return this.prodavac;
    }
    
    public void setProdavac(int prodavac) {
        this.prodavac = prodavac;
    }
    public int getAvion() {
        return this.avion;
    }
    
    public void setAvion(int avion) {
        this.avion = avion;
    }
    public double getPonuda() {
        return this.ponuda;
    }
    
    public void setPonuda(double ponuda) {
        this.ponuda = ponuda;
    }
    public int getOdobreno() {
        return this.odobreno;
    }
    
    public void setOdobreno(int odobreno) {
        this.odobreno = odobreno;
    }




}


