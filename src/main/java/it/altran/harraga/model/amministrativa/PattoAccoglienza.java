package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class PattoAccoglienza {


    private Allegato patto;

    public PattoAccoglienza() {}
    public PattoAccoglienza(Allegato patto) {
        super();
        this.patto = patto;
    }
    public Allegato getPatto() {
        return patto;
    }
    public void setPatto(Allegato patto) {
        this.patto = patto;
    }

}
