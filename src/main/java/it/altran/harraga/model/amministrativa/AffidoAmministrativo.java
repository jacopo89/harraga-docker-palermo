package it.altran.harraga.model.amministrativa;
import it.altran.harraga.model.Allegato;


public class AffidoAmministrativo{

    private long dataVerbaleAffidamento;
    private String enteVerbaleAffidamento;
    private Allegato verbaleAffidamento;

    private long dataProvvedimentoAffidamento;
    private String enteProvvedimentoAffidamento;
    private Allegato provvedimentoAffidamento;

    public AffidoAmministrativo() {}
    public AffidoAmministrativo(long dataVerbaleAffidamento, String enteVerbaleAffidamento, Allegato verbaleAffidamento, long dataProvvedimentoAffidamento, String enteProvvedimentoAffidamento, Allegato provvedimentoAffidamento) {
        super();
        this.dataVerbaleAffidamento = dataVerbaleAffidamento;
        this.enteVerbaleAffidamento = enteVerbaleAffidamento;
        this.verbaleAffidamento = verbaleAffidamento;

        this.dataProvvedimentoAffidamento = dataProvvedimentoAffidamento;
        this.enteProvvedimentoAffidamento = enteProvvedimentoAffidamento;
        this.provvedimentoAffidamento = provvedimentoAffidamento;
    }

    public long getDataVerbaleAffidamento() {
        return dataVerbaleAffidamento;
    }

    public void setDataVerbaleAffidamento(long dataVerbaleAffidamento) {
        this.dataVerbaleAffidamento = dataVerbaleAffidamento;
    }

    public String getEnteVerbaleAffidamento() {
        return enteVerbaleAffidamento;
    }

    public void setEnteVerbaleAffidamento(String enteVerbaleAffidamento) {
        this.enteVerbaleAffidamento = enteVerbaleAffidamento;
    }

    public Allegato getVerbaleAffidamento() {
        return verbaleAffidamento;
    }

    public void setVerbaleAffidamento(Allegato verbaleAffidamento) {
        this.verbaleAffidamento = verbaleAffidamento;
    }

    public long getDataProvvedimentoAffidamento() {
        return dataProvvedimentoAffidamento;
    }

    public void setDataProvvedimentoAffidamento(long dataProvvedimentoAffidamento) {
        this.dataProvvedimentoAffidamento = dataProvvedimentoAffidamento;
    }

    public String getEnteProvvedimentoAffidamento() {
        return enteProvvedimentoAffidamento;
    }

    public void setEnteProvvedimentoAffidamento(String enteProvvedimentoAffidamento) {
        this.enteProvvedimentoAffidamento = enteProvvedimentoAffidamento;
    }

    public Allegato getProvvedimentoAffidamento() {
        return provvedimentoAffidamento;
    }

    public void setProvvedimentoAffidamento(Allegato provvedimentoAffidamento) {
        this.provvedimentoAffidamento = provvedimentoAffidamento;
    }
}