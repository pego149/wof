/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import hra.Prikaz;

/**
 *
 * @author cani3
 */
public interface IPokecatelny {
    void getPrikazy();
    String getNazov();
    boolean spracujPrikaz(Prikaz prikaz);
    String[] getPLATNE_PRIKAZY();
}
