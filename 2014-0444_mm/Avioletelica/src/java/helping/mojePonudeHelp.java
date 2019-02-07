/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helping;

/**
 *
 * @author MINKA
 */
public class mojePonudeHelp {

    private int kupac;
    private String nameK;
    private double ponuda;
    private String nameA;
    private int idA;
    private int idP;

    public mojePonudeHelp(int kupac, String nameK, double ponuda, String nameA, int idA, int idP) {
        this.kupac = kupac;
        this.nameK = nameK;
        this.ponuda = ponuda;
        this.nameA = nameA;
        this.idA = idA;
        this.idP = idP;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }
    

    
    
    public int getKupac() {
        return kupac;
    }

    public void setKupac(int kupac) {
        this.kupac = kupac;
    }

    public String getNameK() {
        return nameK;
    }

    public void setNameK(String nameK) {
        this.nameK = nameK;
    }

    public double getPonuda() {
        return ponuda;
    }

    public void setPonuda(double ponuda) {
        this.ponuda = ponuda;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }
    
    
}
