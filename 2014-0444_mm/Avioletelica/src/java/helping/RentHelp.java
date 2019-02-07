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
public class RentHelp {
    private String name;
    private int ID;
    private String nameC;
    private int idC;
   private double ponuda;

    public double getPonuda() {
        return ponuda;
    }

    public void setPonuda(double ponuda) {
        this.ponuda = ponuda;
    }
   
   
    public RentHelp(String name, int ID, String nameC, int idC) {
        this.name = name;
        this.ID = ID;
        this.nameC = nameC;
        this.idC = idC;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
}
