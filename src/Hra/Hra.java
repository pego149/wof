package Hra;


import Itemy.PortalGun;
import Hrac.Hrac;
import Itemy.IItem;
import Dvere.ZamykatelneDvere;
import Dvere.IDvere;

/**
 * Trieda Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty. 
 * To je v tejto verzii vsetko. Hru treba skutocne zancne rozsirit,
 * aby bola zaujimava.
 * 
 * Ak chcete hrat "World of FRI", vytvorte instanciu triedy Hra (hra) 
 * a poslite jej spravu hraj.
 * 
 * Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private Parser parser;
    private Mapa mapa;
    private Hrac hrac;
    private PortalGun portalGun;
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.parser = new Parser();
        this.hrac = new Hrac();
        this.mapa = new Mapa(this.hrac);
        this.portalGun = new PortalGun();
    }

    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        do {
            Prikaz prikaz = this.parser.nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(prikaz);
        } while (!jeKoniec);
        
        System.out.println("Maj sa fajn!");
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        this.mapa.getAktualnaMiestnost().vypisVychody();
    }

    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Prikaz prikaz) {
        boolean jeKoniec = false;

        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();
        
        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.mapa.chodDoMiestnosti(prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            case "zobrazInventar":
                this.hrac.getInventar().vypisInventar();
                return false;
            case "popisItemu":
                this.hrac.getInventar().vypisPopisItemu(prikaz);
                return false;
            case "kuk":
                //porozhliadni sa
                this.mapa.getAktualnaMiestnost().vypisPredmety();
                return false;
            case "zober":
                IItem item = this.mapa.getAktualnaMiestnost().zoberPredmet(prikaz);
                if (item != null) {
                    this.hrac.getInventar().zoberItemDoIventara(item);
                }
                return false;
            case "otocKlucom":
                if (!prikaz.maParameter()) {
                    System.out.println("Ake dvere?");
                    return false;
                }
                String nazovDveri = prikaz.getParameter();
                IDvere dvere = this.mapa.getDvere(nazovDveri);
                if ( dvere == null) {
                    System.out.println("Dvere neexistuju.");
                    return false;
                }
                if ( dvere instanceof ZamykatelneDvere ) {
                    ZamykatelneDvere zamDvere = (ZamykatelneDvere)dvere;
                    if (this.hrac.maKlucKDveram(zamDvere)) {
                        zamDvere.prepniStavZamknutia();
                    } else {
                        System.out.println("Nemas kluc.");
                    }
                } else {
                    System.out.println("Dvere nevyzaduju kluc.");
                }
                return false;
            case "portalGunOrange":
                if (this.hrac.getInventar().maItem("PortalGun")) {
                    this.portalGun.setOrange(this.mapa.getAktualnaMiestnost().getNazov());
                    System.out.println("Oranzovy portal je v miestnosti: " + this.mapa.getAktualnaMiestnost().getNazov());
                } else {
                    System.out.println("Nemas portalgun.");
                }                
                return false;
            case "portalGunBlue":
                if (this.hrac.getInventar().maItem("PortalGun")) {
                    this.portalGun.setBlue(this.mapa.getAktualnaMiestnost().getNazov());
                    System.out.println("Modry portal je v miestnosti: " + this.mapa.getAktualnaMiestnost().getNazov());
                } else {
                    System.out.println("Nemas portalgun.");
                } 
                return false;
            case "portal":
                this.mapa.portal(this.portalGun);
                return false;
            case "nasad":
                if (!prikaz.maParameter()) {
                    System.out.println("Aky item?");
                    return false;
                }
                String nazovItemu = prikaz.getParameter();
                hrac.nasadItem(nazovItemu);
                return false;
            default:
                return false;
        }
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.print("   chod ukonci pomoc zobrazInventar popisItemu kuk zober otocKlucom nasad portal");
        if (this.hrac.getInventar().maItem("PortalGun")) {
           System.out.print(" portalGunOrange portalGunBlue");
        }
        System.out.println();
        this.mapa.getAktualnaMiestnost().vypisVychody();
    }

    /** 
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * Prikaz ukoncenia nema parameter.
     * 
     * @return true, if this command quits the game, false otherwise.
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }
}
