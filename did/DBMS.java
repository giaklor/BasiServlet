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

	/** Driver da utilizzare per la connessione e l'esecuzione delle query */
	private String driver = "org.postgresql.Driver";

	/** Recupera le informazioni dei tipi dei corsi offerti */
	private static final String tipiq = "SELECT denominazione, descrizione FROM Tipo ORDER BY Denominazione";
	
	/** Recupera le informazioni di un tipo di attivit&grave */
	private static final String tipoq = "SELECT denominazione, descrizione FROM Tipo WHERE denominazione = ?";
	
	/** Controlla i dati di login */
	private static final String checkLogin = "SELECT 1 FROM Iscritto WHERE username = ? AND password = ?";
	
	/** Recupera i dati di un iscritto */
	private static final String iscrittoq = "SELECT nome, cognome, to_char(data_nascita, 'DD-MM-YYYY') AS data_nascita, username, password " +
			"FROM Iscritto WHERE email = ?";
	
	/** Recupera i corsi a cui &egrave iscritto uno studente */
	private static final String corsiIscrittoq = "SELECT C.id_corso, C.nome, C.descrizione, to_char(C.data_inizio, 'DD/MM/YYYY') AS data_inizio, " +
			"to_char(C.data_fine, 'DD/MM/YYYY') AS data_fine, C.tipo_corso " +
			"FROM Corso C, Iscritto I, Iscrizione IZ " +
			"WHERE I.email = IZ.iscritto AND C.id_corso = IZ.corso AND I.email = ?";

	/** Recupera i corsi di un certo tipo */
	private static final String corsiTipoq = "SELECT C.id_corso, C.nome, C.descrizione, to_char(C.data_inizio, 'DD/MM/YYYY') AS data_inizio," +
			"to_char(C.data_fine, 'DD/MM/YYYY') AS data_fine, C.tipo_corso, I.id_istruttore, I.nome AS nome_istr, I.cognome AS cognome_istr, " +
			"I.telefono " +
			"FROM Corso C, Istruttore I " +
			"WHERE I.id_istruttore = C.istruttore_resp AND C.tipo_attivita = ?";
	
	/** Recupera i dati di un corso */
	private static final String corsoq = "SELECT C.id_corso, C.nome, C.descrizione, to_char(C.data_inizio, 'DD/MM/YYYY') AS data_inizio," +
			"to_char(C.data_fine, 'DD/MM/YYYY') AS data_fine, C.tipo_corso, I.id_istruttore, I.nome AS nome_istr, I.cognome AS cognome_istr, " +
			"I.telefono " +
			"FROM Corso C, Istruttore I " +
			"WHERE I.id_istruttore = C.istruttore_resp AND C.id_corso = ?";
	
	/** Recupera gli istruttori assegnati a un certo corso, ma non responsabili */
	private static final String istruttoriAuxCorsoq = "SELECT I.id_istruttore, I.nome AS nome_istr, I.cognome AS cognome_istr, I.telefono "
			+ "FROM Istruttore I, Istruttori_Corsi IC WHERE IC.istruttore = I.id_istruttore AND IC.corso = ?";
	
	/** Recupera gli iscritti a un dato corso */
	private static final String iscrittiCorsoq = "SELECT I.nome, I.cognome, to_char(I.data_nascita, 'DD/MM/YYYY') AS data_nascita, I.email, "
			+ "I.password, to_char(IZ.data_iscrizione, 'DD/MM/YYYY') AS data_iscr FROM Iscritto I, Iscrizione IZ WHERE IZ.iscritto = I.email AND IZ.corso = ?";

	/** Recupera il materiale di un corso */
	private static final String materialeCorsoq = "SELECT M.percorso, M.nome, M.tipo, M.formato "
			+ "FROM Materiale_Didattico M, Materiali_Corso MC WHERE M.percorso = MC.materiale AND MC.corso = ?";
	
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
		bean.setDescrizione(rs.getString("descrizione"));
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
	 * Restituisce un <tt>IscrizioneBean</tt> contenente le informazioni specificate.
	 * @param rs il <tt>ResultSet</tt> contenente le informazioni estratte dal DB
	 * @return Il bean creato.
	 * @throws SQLException in caso i campi richiesti non siano presenti nel <tt>ResultSet</tt> passato
	 */
	private IscrizioneBean makeIscrizioneBean(ResultSet rs) throws SQLException {
		IscrizioneBean bean = new IscrizioneBean();
		bean.setIscritto(makeIscrittoBean(rs));
		bean.setDataIscrizione(rs.getString("data_iscr"));
		return bean;
	}
	
	/**
	 * Restituisce un <tt>MaterialeBean</tt> contenente le informazioni specificate.
	 * @param rs il <tt>ResultSet</tt> contenente le informazioni estratte dal DB
	 * @return Il bean creato.
	 * @throws SQLException in caso i campi richiesti non siano presenti nel <tt>ResultSet</tt> passato
	 */
	private MaterialeBean makeMaterialeBean(ResultSet rs) throws SQLException {
		MaterialeBean bean = new MaterialeBean();
		bean.setPercorso(rs.getString("percorso"));
		bean.setNome(rs.getString("nome"));
		bean.setTipo(rs.getString("tipo"));
		bean.setFormato(rs.getString("formato"));
		return bean;
	}

	/**
	 * Verifica i dati dell'account specificati.
	 * @param username il nome utente
	 * @param password la password
	 * @return <tt>true</tt> se i dati sono corretti, <tt>false</tt> altrimenti.
	 */
	public boolean checkLoginData(String username, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(checkLogin); 
			pstmt.setString(1, username);
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
	
	/**
	 * Restituisce le informazioni del tipo di attivit&agrave specificato.
	 * @param denominazione l'identificatore del tipo
	 * @return Il bean con le informazioni del tipo.
	 * @see TipoAttBean
	 */
	public TipoAttBean getTipoAttivita(String denominazione) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TipoAttBean result = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(tipoq); 
			pstmt.setString(1, denominazione);
			rs=pstmt.executeQuery(); 
			if(rs.next())
				result = makeTipoAttBean(rs);
			
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
	
	public Vector<IstruttoreBean> getIstruttoriAuxCorso(int idCorso) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<IstruttoreBean> result = new Vector<IstruttoreBean>();
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(istruttoriAuxCorsoq); 
			pstmt.setInt(1, idCorso);
			rs=pstmt.executeQuery(); 
			while(rs.next())
				result.add(makeIstruttoreBean(rs));
			
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

	public Vector<IscrizioneBean> getIscrittiCorso(int idCorso) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<IscrizioneBean> result = new Vector<IscrizioneBean>();
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(iscrittiCorsoq); 
			pstmt.setInt(1, idCorso);
			rs=pstmt.executeQuery(); 
			while(rs.next())
				result.add(makeIscrizioneBean(rs));
			
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
	
	public Vector<MaterialeBean> getMaterialiCorso(int idCorso) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<MaterialeBean> result = new Vector<MaterialeBean>();
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(materialeCorsoq); 
			pstmt.setInt(1, idCorso);
			rs=pstmt.executeQuery(); 
			while(rs.next())
				result.add(makeMaterialeBean(rs));
			
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
	
	public CorsoBean getCorso(int idCorso) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CorsoBean result = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(corsoq); 
			pstmt.setInt(1, idCorso);
			rs=pstmt.executeQuery(); 
			if(rs.next())
				result = makeCorsoIBean(rs);
			
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
