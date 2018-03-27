package hra;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Trieda Parser cita vstup zadany hracom do terminaloveho okna a pokusi sa
 * interpretovat ho ako prikaz hry. Kazda sprava dajPrikaz sposobi, ze parser
 * precita jeden riadok z terminaloveho okna a vyberie z neho prve dve slova.
 * Tie dve slova pouzije ako parametre v sprave new triede Prikaz.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Parser {  // odkaz na pripustne nazvy prikazov
    private Scanner citac;         // zdroj vstupov od hraca
    private Hra hra;

    /**
     * Vytvori citac na citanie vstupov z terminaloveho okna.
     */
    public Parser(Hra hra) {
        this.citac = new Scanner(System.in);
        this.hra = hra;
    }

    /**
     * @return prikaz zadany hracom
     */
    public Prikaz nacitajPrikaz() {
        System.out.print("> ");     // vyzva pre hraca na zadanie prikazu

        String vstupnyRiadok = this.citac.nextLine();

        String prikaz = null;
        ArrayList<String> parametre = new ArrayList<>();
        // najde prve dve slova v riadku 
        Scanner tokenizer = new Scanner(vstupnyRiadok);
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();      // prve slovo
            while (tokenizer.hasNext()) {
                parametre.add(tokenizer.next());      // druhe slovo
                // vsimnite si, ze zbytok textu sa ignoruje
            }
        }

        // kontrola platnosti prikazu
        if (this.hra.jePrikaz(prikaz)) {
            // vytvori platny prikaz
            return new Prikaz(prikaz, parametre);
        } else {
            // vytvori neplatny - "neznamy" prikaz
            return new Prikaz(null, parametre); 
        }
    }
}
