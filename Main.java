import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import did.*;

/**
 * Questa classe gestisce le richieste HTTP
 * 
 */
public class Main extends HttpServlet {

	/**
	 * Risponde alle richieste HTTP di tipo GET. Elabora le richieste, impostando
	 * gli eventuali attributi necessari, e ridirige la visualizzazione alle pagine jsp relative.
	 *
	 * @param request Oggetto HttpServletRequest dal quale ottenere informazioni circa la
	 *                richiesta effettuata.
	 * @param response Oggetto HttpServletResponse per l'invio delle risposte.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// Definizione e recupero dell'eventuale parametro della servlet
		String ps = "";

		// Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
		RequestDispatcher rd = null;

		// Ottengo se presente il parametro 'ps'
		if (request.getParameter("ps") != null) {
			ps = request.getParameter("ps");
		}

		try {
			// Oggetto per l'interazione con il Database
			DBMS dbms = new DBMS();

			if (ps.equals("")) {
				// Parametro ps assente o vuoto, visualizzo la home page del sito.
				//Vector<TipoAttBean> tipi = dbms.getTipiAttivita();
				Vector<TipoAttBean> tipi = new Vector<TipoAttBean>();
				request.setAttribute("tipi", tipi);
				//Preparo il Dispatcher
				rd = request.getRequestDispatcher("index.jsp");
			}
			else if (ps.equals("login")) { // visualizzo la pagina di login
				rd = request.getRequestDispatcher("login.jsp");
			}
			else if (ps.equals("tipo")) { //visualizzo i corsi e le info del tipo specificato
				//TODO
			}

			//Passo il controllo alla JSP
			rd.forward(request,response);

		} catch(Exception e) {  //Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher rd = null;
		
		String email = "";
		String password = "";
		
		try {
			DBMS dbms = new DBMS();
		
			if (request.getParameter("email") != null) {
				email = request.getParameter("email");
			}
			if (request.getParameter("password") != null) {
				password = request.getParameter("pwd");
			}
			
			if (!email.equals("")) { // effettuo il login
				if (dbms.checkLoginData(email, password)) {
					StudenteBean stud = dbms.getStudente(email);
					Vector<CorsoBean> corsiStud = dbms.getCorsiStudente(email);
					request.setAttribute("stud", stud);
					request.setAttribute("corsiStud", corsiStud);
					rd = request.getRequestDispatcher("studente.jsp");
				}
				else {
					request.setAttribute("failed", "");
					rd = request.getRequestDispatcher("login.jsp");
				}
			}
			
			rd.forward(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
