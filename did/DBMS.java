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
	
	/** Controlla i dati di login */
	private static final String checkLogin = "SELECT 1 FROM Iscritto WHERE email = ? AND password = ?";


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
	 * Restituisce uno <tt>StudenteBean</tt> contenente le informazioni specificate.
	 * @param rs il <tt>ResultSet</tt> contenente le informazioni estratte dal DB
	 * @return Il bean creato.
	 * @throws SQLException in caso i campi richiesti non siano presenti nel <tt>ResultSet</tt> passato
	 */
	private IscrittoBean makeStudenteBean(ResultSet rs) throws SQLException {
		IscrittoBean bean = new IscrittoBean();
		bean.setNome(rs.getString("nome"));
		bean.setCognome(rs.getString("cognome"));
		bean.setDataNascita(rs.getString("data_nascita"));
		bean.setEmail(rs.getString("email"));
		bean.setPassword(rs.getString("password"));
		return bean;
	}
	
	/**
	 * Restituisce un <tt>CorsoBean</tt> contenente le informazioni specificate.
	 * @param rs il <tt>ResultSet</tt> contenente le informazioni estratte dal DB
	 * @return Il bean creato.
	 * @throws SQLException in caso i campi richiesti non siano presenti nel <tt>ResultSet</tt> passato
	 */
	private CorsoBean makeCorsoBean(ResultSet rs) throws SQLException {
		CorsoBean bean = new CorsoBean();
		bean.setIdCorso(rs.getInt("id_corso"));
		bean.setNome(rs.getString("nome"));
		bean.setDescrizione(rs.getString("descrizione"));
		bean.setDataInizio(rs.getString("data_inizio"));
		bean.setDataFine(rs.getString("data_fine"));
		bean.setTipoCorso(rs.getString("tipo_corso"));
		return bean;
	}
	
	/**
	 * Verifica i dati dell'account specificati.
	 * @param email l'indirizzo email
	 * @param password la password
	 * @return <tt>true</tt> se i dati sono corretti, <tt>false</tt> altrimenti.
	 */
	public boolean checkLoginData(String email, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(checkLogin); 
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery(); 
			return rs.next();
			
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return false;
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
	
	public IscrittoBean getStudente(String email) {
		//TODO
		return new IscrittoBean();
	}
	
	public Vector<CorsoBean> getCorsiStudente(String email) {
		//TODO
		return new Vector<CorsoBean>();
	}

}
