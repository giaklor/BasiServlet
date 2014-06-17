package did;

import java.sql.*;
import java.util.*;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DBMS {

	//Dati di identificazione dell'utente (da personalizzare)
	private String user = "postgres";
	private String passwd = "";

	/** 
	 * URL per la connessione alla base di dati e' formato dai seguenti componenti:
	 * <protocollo>://<host del server>/<nome base di dati>.
	 */
	private String url = "jdbc:postgresql://localhost:5432/dblab14";

	/** Driver da utilizzare per la connessione e l'esecuzione delle query. */
	private String driver = "org.postgresql.Driver";

	/** Recupera le denominazioni dei tipi dei corsi offerti */
	private static final String tipiq = "SELECT denominazione FROM Tipo ORDER BY Denominazione";
	
	/** Controlla i dati di login */
	private static final String checkLogin = "SELECT 1 FROM Iscritto WHERE email = ? AND password = ?";
	
	/** Recupera i dati di un iscritto */
	private static final String iscrittoq = "SELECT nome, cognome, to_char(data_nascita, 'DD-MM-YYYY') AS data_nascita, username, password " +
			"FROM Iscritto WHERE email = ?";
	
	/** Recupera i corsi a cui è iscritto uno studente */
	private static final String corsiIscrittoq = "SELECT C.id_corso, C.nome, C.descrizione, to_char(C.data_inizio, 'DD-MM-YYYY') AS data_inizio, " +
			"to_char(C.data_fine, 'DD-MM-YYYY') AS data_fine, C.tipo_corso" +
			"FROM Corso C, Iscritto I, Iscrizione IZ" +
			"WHERE I.email = IZ.iscritto AND C.id_corso = IZ.corso AND I.email = ?";

	/** Recupera i corsi di un certo tipo */
	private static final String corsiTipoq = "SELECT C.id_corso, C.nome, C.descrizione, to_char(C.data_inizio, 'DD-MM-YYYY') AS data_inizio," +
			"to_char(C.data_fine, 'DD-MM-YYYY') AS data_fine, C.tipo_corso, I.id_istruttore, I.nome AS nome_istr, I.cognome AS cognome_istr, " +
			"I.telefono" +
			"FROM Corso C, Istruttore I" +
			"WHERE I.id_istruttore = C.istruttore_resp AND C.tipo_attivita = ?";

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
	private IscrittoBean makeIscrittoBean(ResultSet rs) throws SQLException {
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
	 * Restituisce un <tt>CorsoBean</tt> contenente le informazioni specificate (incluso l'istruttore responsabile).
	 * @param rs il <tt>ResultSet</tt> contenente le informazioni estratte dal DB
	 * @return Il bean creato.
	 * @throws SQLException in caso i campi richiesti non siano presenti nel <tt>ResultSet</tt> passato
	 */
	private CorsoBean makeCorsoIBean(ResultSet rs) throws SQLException {
		CorsoBean bean = makeCorsoBean(rs);
		bean.setIstruttoreResponsabile(makeIstruttoreBean(rs));
		return bean;
	}
	
	/**
	 * Restituisce un <tt>IstruttoreBean</tt> contenente le informazioni specificate.
	 * @param rs il <tt>ResultSet</tt> contenente le informazioni estratte dal DB
	 * @return Il bean creato.
	 * @throws SQLException in caso i campi richiesti non siano presenti nel <tt>ResultSet</tt> passato
	 */
	private IstruttoreBean makeIstruttoreBean(ResultSet rs) throws SQLException {
		IstruttoreBean bean = new IstruttoreBean();
		bean.setIdIstruttore(rs.getInt("id_istruttore"));
		bean.setNome(rs.getString("nome_istr"));
		bean.setCognome(rs.getString("cognome_istr"));
		bean.setTelefono(rs.getString("telefono"));
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
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if(con != null)
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
				if(con != null)
					con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	public IscrittoBean getIscritto(String email) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		IscrittoBean result = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(iscrittoq); 
			pstmt.setString(1, email);
			rs=pstmt.executeQuery(); 
			if(rs.next())
				result = makeIscrittoBean(rs);
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if(con != null)
					con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	public Vector<CorsoBean> getCorsiIscritto(String email) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<CorsoBean> result = new Vector<CorsoBean>();
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(corsiIscrittoq); 
			pstmt.setString(1, email);
			rs=pstmt.executeQuery(); 
			while(rs.next())
				result.add(makeCorsoBean(rs));
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if(con != null)
					con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

	public Vector<CorsoBean> getCorsiTipo(String tipo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<CorsoBean> result = new Vector<CorsoBean>();
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(corsiTipoq); 
			pstmt.setString(1, tipo);
			rs=pstmt.executeQuery(); 
			while(rs.next())
				result.add(makeCorsoIBean(rs));
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if(con != null)
					con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
}
