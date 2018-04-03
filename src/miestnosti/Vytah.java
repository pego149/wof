/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miestnosti;

import hra.IPrikazy;
import hra.Prikaz;

/**
 *
 * @author cani3
 */
public class Vytah extends Miestnost implements IPrikazy{
    private int poschodie;
    private static final String[] PLATNE_PRIKAZY = {
        "chodNaPoschodie", "vypisPoschodie"
    };
    
    public Vytah(String nazov, String popis) {
        super(nazov, popis);
        this.poschodie = 0;
    }

    @Override
    public void getPrikazy() {
        for (String string : this.PLATNE_PRIKAZY) {
            System.out.print(string + ", ");
        }
    }

    @Override
    public boolean spracujPrikaz(Prikaz prikaz) {
        String nazovPrikazu = prikaz.getNazov();
        switch(nazovPrikazu) {
            case "chodNaPoschodie":
                
                break;
            case "vypisPoschodie":
                break;
        }
        return false;
    }
    
    public String[] getPLATNE_PRIKAZY() {
        return PLATNE_PRIKAZY;
    }
    
}
