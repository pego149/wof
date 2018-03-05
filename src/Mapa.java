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
        
        // inicializacia miestnosti = nastavenie vychodov
        terasa.pridajVychod(aula);
        terasa.pridajVychod(labak);
        terasa.pridajVychod(bufet);
        aula.pridajVychod(terasa);
        bufet.pridajVychod(terasa);
        labak.pridajVychod(kancelaria);
        labak.pridajVychod(terasa);
        kancelaria.pridajVychod(labak);

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
        Miestnost novaMiestnost = this.aktualnaMiestnost.getPrechod(smer);
        
        if (novaMiestnost == null) {
            System.out.println("Tam nie je vychod!");
        } else {
            this.aktualnaMiestnost = novaMiestnost;
            this.aktualnaMiestnost.vypisVychody();
            System.out.println();
        }
    }
}