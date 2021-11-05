package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class CodiceFiscale {


    private Allegato allegato;

    public CodiceFiscale() {}
    public CodiceFiscale(Allegato allegato) {
        super();
        this.allegato = allegato;
    }
    public Allegato getAllegato() {
        return allegato;
    }
    public void setAllegato(Allegato allegato) {
        this.allegato = allegato;
    }

}
