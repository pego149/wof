package itemy;


import dvere.ZamykatelneDvere;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cani3
 */
public class Kluc implements IItem {
    private String nazov;
    private String popis;
    private int cena;
    private ItemType typ;
    private ZamykatelneDvere dvere; 

    public Kluc(String nazov, String popis, int cena, ZamykatelneDvere dvere) {
        this.nazov = nazov;
        this.cena = cena;
        this.typ = ItemType.ITEM_KLUC;
        this.dvere = dvere;
        
        if(popis.isEmpty()) {
            this.popis = "Kluc od:" + this.dvere;
        } else {
            this.popis = popis;
        }
    }

    public ZamykatelneDvere getDvere() {
        return dvere;
    }

    @Override
    public String getNazov() {
        return nazov;
    }

    @Override
    public String getPopis() {
        return popis;
    }

    @Override
    public int getCena() {
        return cena;
    }

    @Override
    public ItemType getTyp() {
        return typ;
    }
    
}
