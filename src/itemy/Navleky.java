/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemy;

import hrac.SlotyInventara;

/**
 *
 * @author cani3
 */
public class Navleky extends EquippableItem {
    
    public Navleky(String nazov, String popis, int cena) {
        super(nazov, popis, cena, ItemType.ITEM_NAVLEKY, SlotyInventara.NOHY, 100);
    }
    
}
