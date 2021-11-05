package it.altran.harraga.model.storia;

public class StoriaPersonale {

    private String vitaMinore;
    private String scuola;
    private String hobbies;
    private String ragioniEspatrio;
    private String eventualiTimori;
    private String informazioniViaggio;

    public StoriaPersonale(){
    }

    public StoriaPersonale(String vitaMinore, String scuola, String hobbies, String ragioniEspatrio, String eventualiTimori, String informazioniViaggio)
    {
        this.vitaMinore = vitaMinore;
        this.scuola = scuola;
        this.hobbies = hobbies;
        this.ragioniEspatrio = ragioniEspatrio;
        this.eventualiTimori = eventualiTimori;
        this.informazioniViaggio = informazioniViaggio;
    }

    public String getEventualiTimori() {
        return eventualiTimori;
    }

    public void setEventualiTimori(String eventualiTimori) {
        this.eventualiTimori = eventualiTimori;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getRagioniEspatrio() {
        return ragioniEspatrio;
    }

    public void setRagioniEspatrio(String ragioniEspatrio) {
        this.ragioniEspatrio = ragioniEspatrio;
    }

    public String getScuola() {
        return scuola;
    }

    public void setScuola(String scuola) {
        this.scuola = scuola;
    }

    public String getVitaMinore() {
        return vitaMinore;
    }

    public void setVitaMinore(String vitaMinore) {
        this.vitaMinore = vitaMinore;
    }

    public String getInformazioniViaggio() {
        return informazioniViaggio;
    }

    public void setInformazioniViaggio(String informazioniViaggio) {
        this.informazioniViaggio = informazioniViaggio;
    }
}

