/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cani3
 */
public class Dvere {
    private Miestnost vstup;
    private Miestnost vystup;
    private PodmienkyVstupu podmienka;
    
    public Dvere(Miestnost vstup, Miestnost vystup, PodmienkyVstupu podmienka) {
        this.vstup = vstup;
        this.vystup = vystup;
        this.podmienka = podmienka;
    }
    
    public Miestnost skusPrechod(Miestnost odkial, Hrac hrac) {
        boolean jeVstup = false;
        Miestnost ciel;
        if (odkial == this.vstup) {
            jeVstup = true;
            ciel = this.vystup;
        } else {
            ciel = this.vstup;
        }
        
        switch(podmienka) {
            case PODMIENKA_ZIADNA:
                break;
            case PODMIENKA_ISIC:
                if (jeVstup && hrac.maItem("ISIC")){
                
                }
                break;
            case PODMIENKA_KLUC:
                //future
                break;
        }
        return ciel;
    }
    
}
