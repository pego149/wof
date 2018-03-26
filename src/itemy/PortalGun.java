package itemy;

import dvere.Dvere;
import hra.Hra;
import hra.Miestnost;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pego1
 */
public class PortalGun {
    private Miestnost orange;
    private Miestnost blue;
    private final Hra hra;

    public PortalGun(Hra hra) {
        this.hra = hra;
    }
    
    public void setOrange(Miestnost orange) {
        this.odstranOrange();
        this.orange = orange;
        this.nastav();
    }
    
    public void setBlue(Miestnost blue) {
        this.odstranBlue();
        this.blue = blue;
        this.nastav();
    }
    
    public Miestnost getOrange() {
        return this.orange;
    }
    
    public Miestnost getBlue() {
        return this.blue;
    }
    
    public void nastav() {
        for(Miestnost m : this.hra.getMapa().getMiestnosti()) {
            if (m == this.orange){
                m.setPortalOrange(blue);
            }
            if (m == this.blue){
                m.setPortalBlue(orange);
            }
        }
    }
    
    public void odstranOrange() {
        for(Miestnost m : this.hra.getMapa().getMiestnosti()) {
            m.setPortalOrange(null);
        }
    }
    
    public void odstranBlue() {
        for(Miestnost m : this.hra.getMapa().getMiestnosti()) {
            m.setPortalBlue(null);
        }
    }
}
