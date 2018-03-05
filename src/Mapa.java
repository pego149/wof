
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cani3
 */
public class Mapa {
    private Miestnost aktualnaMiestnost;
    private Hrac hrac;
    
    public Mapa(Hrac hrac){
        this.vytvorMiestnosti();
        this.hrac = hrac;
    }
    
    public Miestnost getAktualnaMiestnost() {
        return aktualnaMiestnost;
    }
    
    /**
     * Vytvori mapu hry - miestnosti.
     */
    public void vytvorMiestnosti() {
        // vytvorenie miestnosti
        Miestnost terasa = new Miestnost("terasa", "terasa - hlavny vstup na fakultu");
        Miestnost aula = new Miestnost("aula", "aula");
        Miestnost bufet = new Miestnost("bufet", "bufet");
        Miestnost labak = new Miestnost("labak", "pocitacove laboratorium");
        Miestnost kancelaria = new Miestnost("kancelaria", "kancelaria spravcu pocitacoveho laboratoria");
        Miestnost fra12 = new Miestnost("FRA12", "FRA12 - Najs ucebna");
        Miestnost chodba = new Miestnost("chodba", "Chodba - Pozor smyka sa");
        Miestnost vytah = new Miestnost("vytah", "vytah - nefugnuje");
        ArrayList<IItem> predmetyInfocentra = new ArrayList<>();
        predmetyInfocentra.add(new Isic());
        Miestnost infocentrum = new Miestnost("infocentrum", "infocentrum - questovna", predmetyInfocentra);
        Miestnost vratnica = new Miestnost("vratnica", "vratnica - straty a nalezy");

         // inicializacia miestnosti = nastavenie vychodov
        Dvere dTerasaAula = new Dvere(terasa, aula, PodmienkyVstupu.PODMIENKA_ZIADNA);
        terasa.nastavVychod(aula.getNazov(), dTerasaAula);
        aula.nastavVychod(terasa.getNazov(), dTerasaAula);
        Dvere dTerasaLabak = new Dvere(terasa, labak, PodmienkyVstupu.PODMIENKA_ZIADNA);
        terasa.nastavVychod(labak.getNazov(), dTerasaLabak);
        labak.nastavVychod(terasa.getNazov(), dTerasaLabak);
        Dvere dTerasaBufet = new Dvere(terasa, bufet, PodmienkyVstupu.PODMIENKA_ZIADNA);
        terasa.nastavVychod(bufet.getNazov(), dTerasaBufet);
        bufet.nastavVychod(terasa.getNazov(), dTerasaBufet);
        Dvere dLabakKancelaria = new Dvere(labak, kancelaria, PodmienkyVstupu.PODMIENKA_KLUC);
        labak.nastavVychod(kancelaria.getNazov(), dLabakKancelaria);
        kancelaria.nastavVychod(labak.getNazov(), dLabakKancelaria);
        Dvere dChodbaFra12 = new Dvere(chodba, fra12, PodmienkyVstupu.PODMIENKA_ISIC);
        chodba.nastavVychod(fra12.getNazov(), dChodbaFra12);
        fra12.nastavVychod(chodba.getNazov(), dChodbaFra12);
        Dvere dChodbaVytah = new Dvere(chodba, vytah, PodmienkyVstupu.PODMIENKA_ZIADNA);
        chodba.nastavVychod(vytah.getNazov(), dChodbaVytah);
        vytah.nastavVychod(chodba.getNazov(), dChodbaVytah);    
        Dvere dChodbaTerasa = new Dvere(chodba, terasa, PodmienkyVstupu.PODMIENKA_ZIADNA);
        chodba.nastavVychod(terasa.getNazov(), dChodbaTerasa);
        terasa.nastavVychod(chodba.getNazov(), dChodbaTerasa);
        Dvere dChodbaLabak = new Dvere(chodba, labak, PodmienkyVstupu.PODMIENKA_ZIADNA);
        chodba.nastavVychod(labak.getNazov(), dChodbaLabak);
        labak.nastavVychod(chodba.getNazov(), dChodbaLabak);
        Dvere dChodbaVratnica = new Dvere(chodba, vratnica, PodmienkyVstupu.PODMIENKA_ZIADNA);
        chodba.nastavVychod(vratnica.getNazov(), dChodbaVratnica);
        vratnica.nastavVychod(chodba.getNazov(), dChodbaVratnica);
        Dvere dInfocentrumVratnica = new Dvere(infocentrum, vratnica, PodmienkyVstupu.PODMIENKA_ZIADNA);
        infocentrum.nastavVychod(vratnica.getNazov(), dInfocentrumVratnica);
        vratnica.nastavVychod(infocentrum.getNazov(), dInfocentrumVratnica);
        Dvere dChodbaBufet = new Dvere(bufet, chodba, PodmienkyVstupu.PODMIENKA_ISIC);
        chodba.nastavVychod(bufet.getNazov(), dChodbaVratnica);
        bufet.nastavVychod(chodba.getNazov(), dChodbaVratnica);
        
        this.aktualnaMiestnost = terasa;  // startovacia miestnost hry
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     * @param prikaz
     */
    public void chodDoMiestnosti(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();

        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        Dvere dvere = this.aktualnaMiestnost.getPrechod(smer);
        if(dvere == null) {
            System.out.println("Tam nie je vychod");
            return;
        }
        
        Miestnost novaMiestnost = dvere.skusPrechod(this.aktualnaMiestnost, this.hrac);
        
        if (novaMiestnost == null) {
            System.out.println("Nemate opravneneie na vstup!");
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            this.aktualnaMiestnost.vypisVychody();
            System.out.println();
        }
    }
}