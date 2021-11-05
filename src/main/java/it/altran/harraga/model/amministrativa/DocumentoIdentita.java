package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class DocumentoIdentita {
    private Allegato allegato;

    public DocumentoIdentita() {}

    public DocumentoIdentita(Allegato allegato) {
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
