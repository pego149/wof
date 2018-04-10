/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miestnosti;

import dvere.IDvere;
import dvere.VytahoveDvere;
import hra.IPrikazy;
import hra.Prikaz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author cani3
 */
public class Vytah extends Miestnost implements IPrikazy{
    private int poschodie;
    private static final String[] PLATNE_PRIKAZY = {
        "chodNaPoschodie", "vypisPoschodia"
    };
    private int najnizsiePoschodie;
    private int najvyssiePoschodie;
    private ArrayList<IDvere> aktivovaneDvere;
    
    public Vytah(String nazov, String popis, int min, int max) {
        super(nazov, popis);
        this.poschodie = 0;
        this.najnizsiePoschodie = min;
        this.najvyssiePoschodie = max;
        this.aktivovaneDvere = new ArrayList<>();
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
                ArrayList<String> list = prikaz.getParametre();
                if(list.size() != 1) {
                    System.out.println("Ake poschodie?");
                    break;
                }
                int vyslednePoschodie = Integer.parseInt(list.get(0));
                if (vyslednePoschodie < this.najnizsiePoschodie || vyslednePoschodie > this.najvyssiePoschodie) {
                    System.out.println("Take poschodie neexistuje");
                    break;
                }
                this.zavolajVytah(vyslednePoschodie);
                break;
            case "vypisPoschodia":
                for (int i = this.najnizsiePoschodie; i <= this.najvyssiePoschodie; i++) {
                    System.out.print(i + " ");
                }
                break;
        }
        return false;
    }
    
    public String[] getPLATNE_PRIKAZY() {
        return PLATNE_PRIKAZY;
    }

    public int getPoschodie() {
        return poschodie;
    }

    public void zavolajVytah(int poschodie) {
        this.poschodie = poschodie;
        this.aktivovaneDvere.clear();
        for (Map.Entry<String, IDvere> data : this.getDvere().entrySet()) {
            if(data.getValue() instanceof VytahoveDvere) {
                VytahoveDvere vytahDvere = (VytahoveDvere) data.getValue();
                if (vytahDvere.getPoschodie() == this.poschodie) {
                    this.aktivovaneDvere.add(data.getValue());
                    return;
                }
            }
        }
    }
    
    public void vypisVychody() {
        System.out.println("Teraz si v miestnosti " + this.getPopis());
        System.out.print("Vychody: ");
        for (Map.Entry<String, IDvere> data : this.getDvere().entrySet()) {
            if(data.getValue() instanceof VytahoveDvere) {
                VytahoveDvere vytahDvere = (VytahoveDvere) data.getValue();
                if (vytahDvere.getPoschodie() == this.poschodie) {
                    System.out.println(data.getKey() + " ");
                }
            } else {
                System.out.print(data.getKey() + " ");
            }
        }
    }
    
    @Override
    public IDvere getPrechod(String ciel) {
        IDvere prechod = this.getDvere().get(ciel);
        if (prechod instanceof VytahoveDvere) {
            VytahoveDvere vytahDvere = (VytahoveDvere) prechod;
            if (vytahDvere.getPoschodie() == this.poschodie) {
                return prechod;
            } else {
                System.out.println("Nie si na spravnom poschodi");
                return null;
            }
        }
        System.out.println("Toto nema nikdy nastat. - VytahException");
        return null;
    }
    
    public Collection<IDvere> getVsetkyDvere() {
        return this.aktivovaneDvere;
    }
    
    public void nastavDvere(String nazovDveri, IDvere dvere) {
        if (dvere instanceof VytahoveDvere) {
            VytahoveDvere vytahDvere = (VytahoveDvere) dvere;
            if (vytahDvere.getPoschodie() == this.poschodie) {
                this.aktivovaneDvere.add(dvere);
            }
        }
        super.nastavVychod(nazovDveri, dvere);
    }
}
