/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventar;

/**
 *
 * @author cani3
 */
public enum SlotyInventara {
    HLAVA(0, "Hlava: "),
    TELO(1, "Telo: "),
    NOHY(2, "Nohy: "),
    MAX_SLOTOV(3, "");
    
    private final int id;
    private final String popis;
    
    SlotyInventara(int id, String popis) {
        this.id = id;
        this.popis = popis;
    }

    public int getValue() {
        return this.id;
    }
    
    public String getPopis() {
        return this.popis;
    }
}
