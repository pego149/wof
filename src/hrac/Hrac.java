package hrac;


import itemy.IItem;
import itemy.ItemType;
import itemy.Kluc;
import dvere.ZamykatelneDvere;
import hra.Prikaz;
import npc.IPokecatelny;

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
    private IPokecatelny aktualnyPokecatelny;
    
    
    public Hrac() {
        this.meno = "Yolo Swaggins";
        this.inventar = new Inventar();
        this.aktualnyPokecatelny = null;
    }

    public IPokecatelny getAktualnyPokecatelny() {
        return aktualnyPokecatelny;
    }

    public void setAktualnyPokecatelny(IPokecatelny aktualnyPokecatelny) {
        this.aktualnyPokecatelny = aktualnyPokecatelny;
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
