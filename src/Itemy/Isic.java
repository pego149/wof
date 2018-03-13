package Itemy;


import Itemy.ItemType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cani3
 */
public class Isic implements IItem{
    
    public Isic() {
    }

    @Override
    public String getNazov() {
       return "ISIC";
    }

    @Override
    public String getPopis() {
        return "Umožňuje nákup itemov a vstup do miestnosti";
    }

    @Override
    public int getCena() {
       return 32;
    }

    @Override
    public ItemType getTyp() {
        return ItemType.ITEM_KARTA;
    }
    
}
