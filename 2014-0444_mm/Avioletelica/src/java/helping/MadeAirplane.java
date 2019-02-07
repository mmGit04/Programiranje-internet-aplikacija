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
public class MadeAirplane {
    private int id;
    private String name;
    private String tip;

    public MadeAirplane(int id, String name, String tip) {
        this.id = id;
        this.name = name;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    
}
