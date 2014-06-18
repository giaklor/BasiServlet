package did;

public class IscrizioneBean {

	private IscrittoBean iscritto;
	
	private String dataIscrizione;
	
	public IscrizioneBean() {
		iscritto = null;
		dataIscrizione = null;
	}

	public IscrittoBean getIscritto() {
		return iscritto;
	}

	public void setIscritto(IscrittoBean iscritto) {
		this.iscritto = iscritto;
	}

	public String getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(String dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	
	
}
