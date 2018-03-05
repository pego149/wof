
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
public class Hrac {
    private String meno;
    private ArrayList<Item> inventar;
    
    public Hrac() {
        this.meno = "Yolo Swaggins";
        this.inventar = new ArrayList<>();
    }
    public boolean zoberItemDoIventara(Item item) {
        this.inventar.add(item);
        return true;
    }
    
    public void odoberItemZInventara(Item item) {
        this.inventar.remove(item);
    }
    
    public void oblecItem(Item item) {
    
    }

    public void vypisInventar() {
        for(Item item : this.inventar) {
            System.out.println(item.getNazov() + ", ");
        }  
    }

    public void vypisPopisItemu(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Coze?");
            return;
        }
        String nazov = prikaz.getParameter();
        for (Item item : this.inventar) {
            if(item.getNazov().equals(nazov)) {
                System.out.println(item.getNazov() + ": " + item.getPopis());
                break;
            }
        }
        
    }
}
