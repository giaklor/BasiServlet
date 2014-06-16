import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import did.*;

/**
 * Questa classe gestisce le richieste HTTP
 * 
 */
@SuppressWarnings("serial")
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

		if (request.getParameter("ps") != null) {
			ps = request.getParameter("ps");
		}

		try {
			DBMS dbms = new DBMS();

			if (ps.equals("")) { 
				// Visualizo la home page
				Vector<TipoAttBean> tipi = dbms.getTipiAttivita();
				request.setAttribute("tipi", tipi);
				rd = request.getRequestDispatcher("login.jsp");
			}
			else if (ps.equals("login")) { 
				// Visualizzo la pagina di login
				rd = request.getRequestDispatcher("login.jsp");
			}
			else if (ps.equals("tipo")) { 
				// Visualizzo i corsi e le info del tipo specificato
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
					IscrittoBean stud = dbms.getStudente(email);
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
