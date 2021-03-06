package did;

public class CorsoBean {
	
	private int idCorso;
	
	private String nome;
	
	private String descrizione;
	
	private String dataInizio;
	
	private String dataFine;
	
	private String tipoCorso;
	
	private IstruttoreBean istruttoreResponsabile;
	
	public CorsoBean() {
		this.idCorso = 0;
		this.nome = null;
		this.descrizione = null;
		this.dataInizio = null;
		this.dataFine = null;
		this.tipoCorso = null;
		this.istruttoreResponsabile = null;
	}


	public int getIdCorso() {
		return idCorso;
	}

	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public String getTipoCorso() {
		return tipoCorso;
	}

	public void setTipoCorso(String tipoCorso) {
		this.tipoCorso = tipoCorso;
	}

	public IstruttoreBean getIstruttoreResponsabile() {
		return istruttoreResponsabile;
	}

	public void setIstruttoreResponsabile(IstruttoreBean istruttoreResponsabile) {
		this.istruttoreResponsabile = istruttoreResponsabile;
	}
}
