/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

import hra.Prikaz;
import itemy.EquippableItem;
import itemy.IItem;
import itemy.ItemType;
import java.util.ArrayList;

/**
 *
 * @author cani3
 */
public class Inventar {
    private ArrayList<IItem> inventar;
    
    public Inventar() {
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
            System.out.print(item.getNazov() + " ");
        }
        System.out.println();
    }
    
    public void vypisInventar(ItemType typ) {
        for(IItem item : this.inventar) {
            if(item.getTyp() == typ) {
                System.out.print(item.getNazov() + " ");
            }
        }
        System.out.println();
    }

    public void vypisPopisItemu(Prikaz prikaz) {
        ArrayList<String> list = prikaz.getParametre();
        if (list.size() < 0) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Coze?");
            return;
        }
        String nazov = list.get(0);
        for (IItem item : this.inventar) {
            if(item.getNazov().equals(nazov)) {
                System.out.println(item.getNazov() + ": " + item.getPopis());
                break;
            }
        }
        
    }

    public boolean maItem(String nazov) {
        for(IItem item : this.inventar){
            if (item.getNazov() == nazov) {
                return true;
            }
        }
        return false;
    }
    
    public IItem dajItem(String nazov) {
        for(IItem item : this.inventar){
            if (item.getNazov().equals(nazov)) {
                return item;
            }
        }
        return null;
    } 

    public ArrayList<IItem> getInventar() {
        return inventar;
    }
}
