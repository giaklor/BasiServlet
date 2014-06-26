package did;

public class LezioneBean {

	private int giorno;
	
	private String oraInizio;
	
	private String oraFine;
	
	public LezioneBean() {
		giorno = 0;
		oraInizio = null;
		oraFine = null;
	}
	
	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public String getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}

	public String getOraFine() {
		return oraFine;
	}

	public void setOraFine(String oraFine) {
		this.oraFine = oraFine;
	}
}
