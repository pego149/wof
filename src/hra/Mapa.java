package hra;


import miestnosti.Miestnost;
import itemy.PortalGun;
import hrac.Hrac;
import dvere.PodmienkyVstupu;
import itemy.Isic;
import itemy.Kluc;
import dvere.ZamykatelneDvere;
import dvere.IDvere;
import dvere.Dvere;
import dvere.VytahoveDvere;
import itemy.Item;
import itemy.ItemType;
import itemy.Navleky;
import java.util.ArrayList;
import miestnosti.Vytah;
import npc.Vratnik;

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
    private Hra hra;
    private ArrayList<Miestnost> miestnosti;
    
    public Mapa(Hra hra){
        this.miestnosti = new ArrayList<>();
        this.hra = hra;
        this.vytvorMiestnosti();
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
        Miestnost fra12 = new Miestnost("FRA12", "FRA12 - Najs ucebna", true);
        Miestnost chodba = new Miestnost("chodba", "Chodba - Pozor smyka sa");
        Miestnost infocentrum = new Miestnost("infocentrum", "infocentrum - questovna");
        Miestnost vratnica = new Miestnost("vratnica", "vratnica - straty a nalezy");
        Vytah vytah = new Vytah("vytah", "vytah - funguje", -1, 3);
        Miestnost dolnaChodba = new Miestnost("dolnaChodba", "Dolna chodba - pri Cecku");
        this.miestnosti.add(terasa);
        this.miestnosti.add(aula);
        this.miestnosti.add(bufet);
        this.miestnosti.add(labak);
        this.miestnosti.add(kancelaria);
        this.miestnosti.add(fra12);
        this.miestnosti.add(chodba);
        this.miestnosti.add(vytah);
        this.miestnosti.add(infocentrum);      
        this.miestnosti.add(vratnica);
        
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
        ZamykatelneDvere dLabakKancelaria = new ZamykatelneDvere(labak, kancelaria);
        labak.nastavVychod(kancelaria.getNazov(), dLabakKancelaria);
        kancelaria.nastavVychod(labak.getNazov(), dLabakKancelaria);
        Dvere dChodbaFra12 = new Dvere(chodba, fra12, PodmienkyVstupu.PODMIENKA_ISIC);
        chodba.nastavVychod(fra12.getNazov(), dChodbaFra12);
        fra12.nastavVychod(chodba.getNazov(), dChodbaFra12);
        VytahoveDvere dChodbaVytah = new VytahoveDvere(chodba, vytah, PodmienkyVstupu.PODMIENKA_ZIADNA, vytah, 0);
        chodba.nastavVychod(vytah.getNazov(), dChodbaVytah);
        vytah.nastavVychod(chodba.getNazov(), dChodbaVytah);
        VytahoveDvere dDolnaChodbaVytah = new VytahoveDvere(dolnaChodba, vytah, PodmienkyVstupu.PODMIENKA_ZIADNA, vytah, -1);
        dolnaChodba.nastavVychod(vytah.getNazov(), dDolnaChodbaVytah);
        vytah.nastavVychod(dolnaChodba.getNazov(), dDolnaChodbaVytah);
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
        chodba.nastavVychod(bufet.getNazov(), dChodbaBufet);
        bufet.nastavVychod(chodba.getNazov(), dChodbaBufet);
        
        //pridavanie predmetov
        infocentrum.pridajPredmetDoMiestnosti(new Isic());
        Vratnik vratnik = new Vratnik(this.hra);
        vratnik.getInventar().zoberItemDoIventara(new Kluc("StriebornyKluc", "", -1, dLabakKancelaria));
        vratnica.pridajNPCDoMiestnosti(vratnik);
        bufet.pridajPredmetDoMiestnosti(new Item("PortalGun", "Portal Gun na telepotaciu", 100, ItemType.ITEM_PORTALGUN));
        bufet.pridajPredmetDoMiestnosti(new Navleky("Navleky", "MADE IN CHINA", 20));
        
        this.aktualnaMiestnost = terasa;  // startovacia miestnost hry
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     * @param prikaz
     */
    public void chodDoMiestnosti(Prikaz prikaz) {
        ArrayList<String> list = prikaz.getParametre();
        if (list.size() == 0) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = list.get(0);
        
        if(this.aktualnaMiestnost.getPortalBlue() != null) {
            if (this.aktualnaMiestnost.getPortalBlue().getNazov().equals(smer)) {
                this.aktualnaMiestnost = this.aktualnaMiestnost.getPortalBlue();
                this.aktualnaMiestnost.vypisVychody();
                System.out.println();
                return;
            }
        }
        
        if(this.aktualnaMiestnost.getPortalOrange() != null) {
            if (this.aktualnaMiestnost.getPortalOrange().getNazov().equals(smer)) {
                this.aktualnaMiestnost = this.aktualnaMiestnost.getPortalOrange();
                this.aktualnaMiestnost.vypisVychody();
                System.out.println();
                return;
            }
        }
        
        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        IDvere dvere = this.aktualnaMiestnost.getPrechod(smer);
        if(dvere == null) {
            System.out.println("Tam nie je vychod");
            return;
        }
        
        Miestnost novaMiestnost = dvere.skusPrechod(this.aktualnaMiestnost, this.hra.getHrac());
        
        if (novaMiestnost == null) {
            System.out.println("Nemate opravneneie na vstup!");
        } else {
            if (novaMiestnost.isTrebaNavleky()) {
                if (!this.hra.getHrac().getInventar().maEquipnute(ItemType.ITEM_NAVLEKY)) {
                    System.out.println("Su potrebne navleky.");
                    return;
                }
            }
            this.aktualnaMiestnost = novaMiestnost;
            this.aktualnaMiestnost.vypisVychody();
            System.out.println();
        }
    }
    
    public IDvere getDvere(String nazov) {
        return this.aktualnaMiestnost.getPrechod(nazov);
    }

    public ArrayList<Miestnost> getMiestnosti() {
        return this.miestnosti;
    }
}