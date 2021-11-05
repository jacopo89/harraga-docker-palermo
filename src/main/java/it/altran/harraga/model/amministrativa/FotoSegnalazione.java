package it.altran.harraga.model.amministrativa;

import it.altran.harraga.model.Allegato;

public class FotoSegnalazione {
    private long data;
    private String ufficioCompetente;
    private Allegato allegato;



    public FotoSegnalazione() {}
    public FotoSegnalazione(long data,  String ufficioCompetente, Allegato allegato) {
        super();
        this.ufficioCompetente = ufficioCompetente;
        this.data = data;
        this.allegato = allegato;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getUfficioCompetente() {
        return ufficioCompetente;
    }

    public void setUfficioCompetente(String ufficioCompetente) {
        this.ufficioCompetente = ufficioCompetente;
    }

    public Allegato getAllegato() {
        return allegato;
    }

    public void setAllegato(Allegato allegato) {
        this.allegato = allegato;
    }
}
