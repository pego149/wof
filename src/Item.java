/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cani3
 */
public class Item implements IItem {
    private String nazov;
    private String popis;
    private int cena;
    private ItemType typ;

    public Item(String nazov, String popis, int cena, ItemType typ) {
        this.nazov = nazov;
        this.popis = popis;
        this.cena = cena;
        this.typ = typ;
    }

    public String getNazov() {
        return nazov;
    }

    public String getPopis() {
        return popis;
    }

    public int getCena() {
        return cena;
    }

    public ItemType getTyp() {
        return typ;
    }
    
    
}
