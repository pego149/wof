
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
    private ArrayList<IItem> inventar;
    
    public Hrac() {
        this.meno = "Yolo Swaggins";
        this.inventar = new ArrayList<>();
    }
    public boolean zoberItemDoIventara(IItem item) {
        this.inventar.add(item);
        return true;
    }
    
    public void odoberItemZInventara(IItem item) {
        this.inventar.remove(item);
    }
    
    public void oblecItem(IItem item) {
    
    }

    public void vypisInventar() {
        for(IItem item : this.inventar) {
            System.out.print(item.getNazov() + ", ");
        }
        System.out.println("");
    }

    public void vypisPopisItemu(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Coze?");
            return;
        }
        String nazov = prikaz.getParameter();
        for (IItem item : this.inventar) {
            if(item.getNazov().equals(nazov)) {
                System.out.println(item.getNazov() + ": " + item.getPopis());
                break;
            }
        }
        
    }

    boolean maItem(String nazov) {
        for(IItem item : this.inventar){
            if (item.getNazov() == nazov) {
                return true;
            }
        }
        return false;
    }
}
