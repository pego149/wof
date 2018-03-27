/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import hra.Hra;
import hra.Prikaz;
import inventar.Inventar;
import itemy.IItem;
import itemy.ItemType;
import java.util.ArrayList;

/**
 *
 * @author cani3
 */
public class Vratnik implements IPokecatelny {
    private Inventar inventar;
    private Hra hra;
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "vypisKluce", "dajKluc", "odid"
    };

    public Vratnik(Hra hra) {
        this.inventar = new Inventar();
        this.hra = hra;
    }

    @Override
    public void getPrikazy() {
        System.out.println("Mozes pouzit tieto prikazy");
        for (String string : this.PLATNE_PRIKAZY) {
            System.out.print(string + ", ");
        }
    }

    @Override
    public String getNazov() {
        return "Vratnik";
    }

    @Override
    public boolean spracujPrikaz(Prikaz prikaz) {
        String nazovPrikazu = prikaz.getNazov();
        switch(nazovPrikazu) {
            case "vypisKluce":
                this.inventar.vypisInventar(ItemType.ITEM_KLUC);
                break;
            case "dajKluc":
                ArrayList<String> list = prikaz.getParametre();
                if (list.size() < 0) {
                    System.out.println("Aky kluc?");
                    break;
                }
                String nazovKluca = list.get(0);
                IItem kluc = inventar.dajItem(nazovKluca);
                if (kluc == null) {
                    System.out.println("Kluc sa nenasiel.");
                    break;
                }
                if (kluc.getTyp() != ItemType.ITEM_KLUC) {
                    System.out.println("Kluc sa nenasiel.");
                    break;
                }
                this.hra.getHrac().getInventar().zoberItemDoIventara(kluc);
                this.inventar.odoberItemZInventara(kluc);
                break;
            case "pomoc":
                this.getPrikazy();
                break;
            case "odid":
                return true;
                
        }
        return false;
    }

    public Inventar getInventar() {
        return inventar;
    }

    public String[] getPLATNE_PRIKAZY() {
        return PLATNE_PRIKAZY;
    }
}
