/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvere;

import hra.IPrikazy;
import hra.Prikaz;
import hrac.Hrac;
import miestnosti.Miestnost;
import miestnosti.Vytah;

/**
 *
 * @author cani3
 */
public class VytahoveDvere extends Dvere implements IPrikazy{
    private Vytah vytah;
    private int poschodie;
    private static final String[] PLATNE_PRIKAZY = {
        "zavolajVytah"
    };
    
    public VytahoveDvere(Miestnost vstup, Miestnost vystup, PodmienkyVstupu podmienka, Vytah vytah, int poschodie) {
        super(vstup, vystup, podmienka);
        this.vytah = vytah;
        this.poschodie = poschodie;
    }

    @Override
    public void getPrikazy() {
        for (String string : this.PLATNE_PRIKAZY) {
            System.out.print(string + ", ");
        }
    }

    @Override
    public boolean spracujPrikaz(Prikaz prikaz) {
        String nazov = prikaz.getNazov();
        if (nazov.equals("zavolajVytah")) {
            this.vytah.zavolajVytah(poschodie);
            System.out.println("Vytah bol zavolany.");
        }
        return false;
    }

    @Override
    public String[] getPLATNE_PRIKAZY() {
        return this.PLATNE_PRIKAZY;
    }

    @Override
    public Miestnost skusPrechod(Miestnost odkial, Hrac hrac) {
        if (this.poschodie != this.vytah.getPoschodie()) {
            System.out.println("Musis zavolat vytah.");
        }
        return super.skusPrechod(odkial, hrac);
    }

    public int getPoschodie() {
        return this.poschodie;
    }
    
}
