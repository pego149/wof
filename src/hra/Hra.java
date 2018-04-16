package hra;


import itemy.PortalGun;
import hrac.Hrac;
import itemy.IItem;
import dvere.ZamykatelneDvere;
import dvere.IDvere;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import miestnosti.Miestnost;
import npc.IPokecatelny;

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
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "zobrazInventar", "popisItemu", 
        "kuk", "zober", "portalGunOrange", "portalGunBlue", 
        "otocKlucom", "nasad", "pokecaj"
    };
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() throws IOException {
        this.parser = new Parser(this);
        this.hrac = new Hrac();
        this.mapa = new Mapa(this);
        this.portalGun = new PortalGun(this);
        this.nacitaj();
    }

    public Mapa getMapa() {
        return mapa;
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
        
        IPokecatelny aktualny = this.hrac.getAktualnyPokecatelny();
        if (aktualny != null) {
            if (aktualny.spracujPrikaz(prikaz)) {
                System.out.println("Odisiel si od " + aktualny.getNazov() + ".");
                this.hrac.setAktualnyPokecatelny(null);
            }
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
                this.mapa.getAktualnaMiestnost().vypisNpc();
                return false;
            case "zober":
                IItem item = this.mapa.getAktualnaMiestnost().zoberPredmet(prikaz);
                if (item != null) {
                    this.hrac.getInventar().zoberItemDoIventara(item);
                }
                return false;
            case "otocKlucom":
            {
                ArrayList<String> list = prikaz.getParametre();
                if (list.size() < 0) {
                    System.out.println("Ake dvere?");
                    return false;
                }
                String nazovDveri = list.get(0);
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
            }
            case "portalGunOrange":
                if (this.hrac.getInventar().maItem("PortalGun")) {
                    this.portalGun.setOrange(this.mapa.getAktualnaMiestnost());
                    System.out.println("Oranzovy portal je v miestnosti: " + this.mapa.getAktualnaMiestnost().getNazov());
                } else {
                    System.out.println("Nemas portalgun.");
                }                
                return false;
            case "portalGunBlue":
                if (this.hrac.getInventar().maItem("PortalGun")) {
                    this.portalGun.setBlue(this.mapa.getAktualnaMiestnost());
                    System.out.println("Modry portal je v miestnosti: " + this.mapa.getAktualnaMiestnost().getNazov());
                } else {
                    System.out.println("Nemas portalgun.");
                } 
                return false;
            case "nasad":
            {
                ArrayList<String> list = prikaz.getParametre();
                if (list.size() < 0) {
                    System.out.println("Aky item?");
                    return false;
                }
                String nazovItemu = list.get(0);
                hrac.nasadItem(nazovItemu);
                return false;
            }
            case "pokecaj":
            {
                ArrayList<String> list = prikaz.getParametre();
                if (list.size() < 0) {
                    System.out.println("Ake NPC?");
                    return false;
                }
                String nazovNpc = list.get(0);
                IPokecatelny npc = this.mapa.getAktualnaMiestnost().dajNpc(nazovNpc);
                if (npc == null) {
                    System.out.println("Npc sa nenaslo.");
                    return false;
                }
                this.hrac.setAktualnyPokecatelny(npc);
                npc.getPrikazy();
                return false;
            }
            default:
                break;
        }
        Miestnost aktualnaMiestnost = this.mapa.getAktualnaMiestnost();
        if (aktualnaMiestnost instanceof IPrikazy) {
            IPrikazy prikazovaMiestnost = (IPrikazy)aktualnaMiestnost;
            prikazovaMiestnost.spracujPrikaz(prikaz);
        }
        for (IDvere dvere : aktualnaMiestnost.getVsetkyDvere()) {
            if (dvere instanceof IPrikazy) {
                IPrikazy prikazoveDvere = (IPrikazy)dvere;
                prikazoveDvere.spracujPrikaz(prikaz);
            }
        }
        return false;
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
        for (String string : this.PLATNE_PRIKAZY) {
            System.out.print(string + ", ");
        }
        if (this.hrac.getInventar().maItem("PortalGun")) {
           System.out.print(" portalGunOrange portalGunBlue");
        }
        
        
        Miestnost aktualnaMiestnost = this.mapa.getAktualnaMiestnost();
        if (aktualnaMiestnost instanceof IPrikazy) {
            IPrikazy prikazovaMiestnost = (IPrikazy)aktualnaMiestnost;
            prikazovaMiestnost.getPrikazy();
        }
        for (IDvere dvere : aktualnaMiestnost.getVsetkyDvere()) {
            if (dvere instanceof IPrikazy) {
                IPrikazy prikazoveDvere = (IPrikazy)dvere;
                prikazoveDvere.getPrikazy();
            }
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
        ArrayList<String> list = prikaz.getParametre();
        if (list.size() == 0) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }

    public Hrac getHrac() {
        return hrac;
    }
    
    /**
     * Kontroluje, ci nazov v parametri je platny prikaz.
     *  
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        String[] prikazy = this.PLATNE_PRIKAZY;
        IPokecatelny pokec = this.hrac.getAktualnyPokecatelny();
        if (pokec != null) {
            prikazy = pokec.getPLATNE_PRIKAZY();
            for (int i = 0; i < prikazy.length; i++) {
                if (prikazy[i].equals(nazov)) {
                    return true;
                }
            }
        } else {    
            ArrayList<String[]> poliaPrikazov = new ArrayList<>();
            poliaPrikazov.add(prikazy);
            Miestnost aktualnaMiestnost = this.mapa.getAktualnaMiestnost();
            if (aktualnaMiestnost instanceof IPrikazy) {
                IPrikazy prikazovaMiestnost = (IPrikazy)aktualnaMiestnost;
                poliaPrikazov.add(prikazovaMiestnost.getPLATNE_PRIKAZY());
            }
            for (IDvere dvere : aktualnaMiestnost.getVsetkyDvere()) {
                if (dvere instanceof IPrikazy) {
                    IPrikazy prikazoveDvere = (IPrikazy)dvere;
                    poliaPrikazov.add(prikazoveDvere.getPLATNE_PRIKAZY());
                }
            }
            for (String[] polePrikazov : poliaPrikazov) {
                for (int i = 0; i < polePrikazov.length; i++) {
                    if(polePrikazov[i].equals(nazov)) {
                        return true;
                    }
                }
            }
        }
        // ak algoritmus dosiahne tento bod, parameter nie je platny prikaz
        return false;
    }
    
    public void nacitaj() throws IOException{
        File subor = new File("C:\\Users\\cani3\\Desktop\\01wof\\src\\subor.txt");
        Scanner citac = new Scanner(subor);
        while (citac.hasNextLine()) {
            String riadok = citac.nextLine();
            String typ = "";
            for (int i = 0; i < riadok.length(); i++) {
                if (riadok.charAt(i) == ':' || riadok.charAt(i) == '\t') {
                    break;
                }
                typ += riadok.charAt(i);
            }
            String nazov = "";
            String trieda = "";
            String nazovVTriede = "";
            String popisVTriede = "";
            int vytahOd = 0;
            int vytahDo = 0;
            int vytahDefault = 0;
            switch(typ) {
                case "miestnost":
                    int x = 10;
                    for (int i = x; i < riadok.length(); i++) {
                        if(riadok.charAt(i) == '[') {
                            x++;
                            break;
                        }
                        nazov += riadok.charAt(i);
                        x++;
                    }
                    for (int i = x; i < riadok.length(); i++) {
                        if(riadok.charAt(i) == ']') {
                            x++;
                            break;
                        }
                        trieda += riadok.charAt(i);
                        x++;
                    }
                    for (int i = x + 1; i < riadok.length(); i++) {
                        if(riadok.charAt(i) == ',') {
                            x++;
                            break;
                        }
                        nazovVTriede += riadok.charAt(i);
                        x++;
                    }
                    for (int i = x + 1; i < riadok.length(); i++) {
                        if(riadok.charAt(i) == ')' || riadok.charAt(i) == ',') {
                            x++;
                            break;
                        }
                        popisVTriede += riadok.charAt(i);
                        x++;
                    }
                    if(trieda.equals("vytah")) {
                        String s = "";
                        for (int i = x + 1; i < riadok.length(); i++) {
                            if(riadok.charAt(i) == ')' || riadok.charAt(i) == ',') {
                                x++;
                                break;
                            }
                            s += riadok.charAt(i);
                            x++;
                        }
                        vytahOd = Integer.parseInt(s);
                        s = "";
                        for (int i = x + 1; i < riadok.length(); i++) {
                            if(riadok.charAt(i) == ')' || riadok.charAt(i) == ',') {
                                x++;
                                break;
                            }
                            s += riadok.charAt(i);
                            x++;
                        }
                        vytahDo = Integer.parseInt(s);
                        s = "";
                        for (int i = x + 1; i < riadok.length(); i++) {
                            if(riadok.charAt(i) == ')' || riadok.charAt(i) == ',') {
                                x++;
                                break;
                            }
                            s += riadok.charAt(i);
                            x++;
                        }
                        vytahDefault = Integer.parseInt(s);
                    }
                    String param = "";
                    riadok = citac.nextLine();
                    for (int i = 1; i < riadok.length(); i++) {
                        if (riadok.charAt(i) == ':') {
                            break;
                        }
                        param += riadok.charAt(i);
                    }
                    System.out.println(param);
                    String nazovVchodu = "";
                    while (!nazovVchodu.equals("estnost")) {
                        riadok = citac.nextLine();
                        for (int i = 2; i < riadok.length(); i++) {
                            if (riadok.charAt(i) == ':') {
                                break;
                            }
                            nazovVchodu += riadok.charAt(i);
                        }
                        System.out.println(nazovVchodu);
                    }
                    riadok = citac.nextLine();
                    typ = "";
                    
            }
            
            System.out.println(typ);
            System.out.println(nazov);
            System.out.println(trieda);
            System.out.println(nazovVTriede);
            System.out.println(popisVTriede);
            System.out.println(vytahOd);
            System.out.println(vytahDo);
            System.out.println(vytahDefault);
        }
        
        /**
        this.pocetVrcholov = citac.nextInt();
        for(int i = 1; i <= this.pocetVrcholov; i++){
            this.vrcholy.add(new Vrchol(i));
        }
        citac.hasNextLine();
        while (citac.hasNextLine()) {
            int vrcholA = citac.nextInt();
            int vrcholB = citac.nextInt();
            if (prvy == 0) {
                vrcholA++;
                vrcholB++;
            }
            int ohodnotenieHrany = 1;
            if (ajOhodnotenie) {
                ohodnotenieHrany = citac.nextInt();
            }
            for(Vrchol v1 : this.vrcholy){
                if (vrcholA == v1.getZnak()) {
                    for(Vrchol v2 : this.vrcholy){
                        if (vrcholB == v2.getZnak()) {
                            Hrana h1 = new Hrana(v1,v2,ohodnotenieHrany);
                            this.hrany.add(h1);
                            v1.pridajHranu(h1);
                            if (!digraf) {
                                Hrana h2 = new Hrana(v2,v1,ohodnotenieHrany);
                                this.hrany.add(h2);
                                v2.pridajHranu(h2);
                            }
                        }
                    }
                }
            }
        }**/
        citac.close();
    }
}