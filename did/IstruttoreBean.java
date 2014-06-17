package did;

public class IstruttoreBean {

	private int idIstruttore;
	private String nome;
	private String cognome;
	private String telefono;
	
	public IstruttoreBean() {
		idIstruttore = 0;
		nome = null;
		cognome = null;
		telefono = null;
	}
	
	public int getIdIstruttore() {
		return idIstruttore;
	}
	public void setIdIstruttore(int idIstruttore) {
		this.idIstruttore = idIstruttore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}	
}
