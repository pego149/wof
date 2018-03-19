package Hrac;


import Itemy.IItem;
import Itemy.ItemType;
import Itemy.Kluc;
import Dvere.ZamykatelneDvere;
import Hra.Prikaz;

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
    private Inventar inventar;
    
    
    public Hrac() {
        this.meno = "Yolo Swaggins";
        this.inventar = new Inventar();
    }
    
    public boolean maKlucKDveram(ZamykatelneDvere dvere) {
        for(IItem item : this.inventar.getInventar()){
            if (item.getTyp() == ItemType.ITEM_KLUC) {
                Kluc kluc = (Kluc)item;
                if (kluc.getDvere() == dvere) {
                    return true;
                }
            }
        }
        return false;
    }

    public Inventar getInventar() {
        return inventar;
    }
    
    public void nasadItem(String item) {
        this.inventar.nasadItem(item);
    }
}
