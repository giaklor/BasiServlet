/**        DBMS.java        */
package did;

import java.sql.*;
import java.util.*;
/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DBMS {

	//Dati di identificazione dell'utente (da personalizzare)
    private String user = "userlab14";
    private String passwd = "quattordiciX8";
	
    /** 
     * URL per la connessione alla base di dati e' formato dai seguenti componenti:
     * <protocollo>://<host del server>/<nome base di dati>.
     */
    private String url = "jdbc:postgresql://dbserver.sci.univr.it/did2014";
  
	/** Driver da utilizzare per la connessione e l'esecuzione delle query. */
    private String driver = "org.postgresql.Driver";

	/** Recupera le denominazioni dei tipi dei corsi offerti */
	private static final String tipiq = "SELECT Denominazione FROM Tipo ORDER BY Denominazione";

	
    /**
     * Costruttore della classe. Carica i driver da utilizzare per la
     * connessione alla base di dati.
     *
     * @throws ClassNotFoundException Eccezione generata nel caso in cui
     *         i driver per la connessione non siano trovati nel CLASSPATH.
     */
    public DBMS() throws ClassNotFoundException {
		Class.forName(driver);
    }

    /**
     * Restituisce un <tt>TipoAttBean</tt> contenente le informazioni specificate.
     * @param rs il <tt>ResultSet</tt> contenente le informazioni estratte dal DB
     * @return Il bean creato.
     * @throws SQLException in caso i campi richiesti non siano presenti nel <tt>ResultSet</tt> passato
     */
    private TipoAttBean makeTipoAttBean(ResultSet rs) throws SQLException {
    	TipoAttBean bean = new TipoAttBean();
    	bean.setDenominazione(rs.getString("denominazione"));
    	return bean;
    }	

	/**
	 * Restituisce i tipi di attivita' offerti.
	 * @return Il vettore dei tipi
	 * @see TipoAttBean
	 */
	public Vector<TipoAttBean> getTipiAttivita() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<TipoAttBean> result = new Vector<TipoAttBean>();
		
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(tipiq); 
			// Eseguo la query
			rs=pstmt.executeQuery(); 
			// Memorizzo il risultato dell'interrogazione in Vector di Bean
			while(rs.next())
				result.add(makeTipoAttBean(rs));
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
    }

}
