package did;

public class MaterialeBean {

	private String percorso;
	
	private String nome;
	
	private String tipo;
	
	private String formato;
	
	public MaterialeBean() {
		percorso = null;
		nome = null;
		tipo = null;
		formato = null;
	}
	
	public String getPercorso() {
		return percorso;
	}

	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
}
