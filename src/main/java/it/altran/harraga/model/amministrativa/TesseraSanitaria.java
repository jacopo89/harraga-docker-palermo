package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class TesseraSanitaria {


    private Allegato allegato;

    public TesseraSanitaria() {}
    public TesseraSanitaria(Allegato allegato) {
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
