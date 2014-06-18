package did;

public class TipoAttBean {
	
	private String denominazione;
	
	private String descrizione;

	public TipoAttBean() {
		denominazione = null;
	}
	
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String den) {
		denominazione = den;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
