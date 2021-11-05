package it.altran.harraga.model.storia;

public class ValutazioneMultiDisciplinare{

    public String tipologia;
    public String valutazione;

    public ValutazioneMultiDisciplinare(){

    }

    public ValutazioneMultiDisciplinare(String tipologia, String valutazione){
        this.valutazione = valutazione;
        this.tipologia = tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setValutazione(String valutazione) {
        this.valutazione = valutazione;
    }

    public String getValutazione() {
        return valutazione;
    }
}