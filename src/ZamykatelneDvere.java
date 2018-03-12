/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cani3
 */
public class ZamykatelneDvere implements IDvere {
    private Miestnost vstup;
    private Miestnost vystup;
    private boolean jeZamknute;

    public ZamykatelneDvere(Miestnost vstup, Miestnost vystup) {
        this.vstup = vstup;
        this.vystup = vystup;
        this.jeZamknute = true;
    }

    @Override
    public Miestnost skusPrechod(Miestnost odkial, Hrac hrac) {
        if (this.jeZamknute) {
            System.out.println("Dvere sa zamkunuťie!!");
            return null;
        }
        Miestnost ciel;
        if (odkial == this.vstup) {
            ciel = this.vystup;
        } else {
            ciel = this.vstup;
        }
        return ciel;
    }
    
    public void prepniStavZamknutia() {
        if (this.jeZamknute == true) {
            this.jeZamknute = false;            
        } else {
            this.jeZamknute = true;
        }
    }

    @Override
    public String toString() {
        return this.vstup.getNazov() + "->" + this.vystup.getNazov();
    }
         
}
