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
		
		String den = "";
		
		String idCorso = "";

		// Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
		RequestDispatcher rd = null;

		if (request.getParameter("ps") != null) {
			ps = request.getParameter("ps");
		}
		if (request.getParameter("den") != null) {
			den = request.getParameter("den");
		}
		if (request.getParameter("idCorso") != null) {
			idCorso = request.getParameter("idCorso");
		}

		try {
			DBMS dbms = new DBMS();

			if (ps.equals("")) { 
				// Visualizzo la home page
				Vector<TipoAttBean> tipi = dbms.getTipiAttivita();
				request.setAttribute("tipi", tipi);
				rd = request.getRequestDispatcher("index.jsp");
			}
			else if (ps.equals("login")) { 
				// Visualizzo la pagina di login
				rd = request.getRequestDispatcher("login.jsp");
			}
			else if (ps.equals("tipo") && !den.equals("")) { 
				// Visualizzo i corsi e le info del tipo specificato
				TipoAttBean tipo = dbms.getTipoAttivita(den);
				Vector<CorsoBean> corsiTipo = dbms.getCorsiTipo(den);
				request.setAttribute("tipo", tipo);
				request.setAttribute("corsiTipo", corsiTipo);
				rd = request.getRequestDispatcher("tipoAtt.jsp");
			}
			else if (ps.equals("corso") && !idCorso.equals("")) { 
				// Visualizzo la pagina del corso
				int id = Integer.parseInt(idCorso);
				CorsoBean corso = dbms.getCorso(id);
				Vector<IscrizioneBean> iscritti = dbms.getIscrittiCorso(id);
				Vector<MaterialeBean> materiale = dbms.getMaterialiCorso(id);
				Vector<IstruttoreBean> istruttoriAux = dbms.getIstruttoriAuxCorso(id);
				request.setAttribute("corso", corso);
				request.setAttribute("iscritti", iscritti);
				request.setAttribute("materiale", materiale);
				request.setAttribute("istruttoriAux", istruttoriAux);
				rd = request.getRequestDispatcher("corso.jsp");
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
					IscrittoBean stud = dbms.getIscritto(email);
					Vector<CorsoBean> corsiIscritto = dbms.getCorsiIscritto(email);
					Map<Integer, Vector<MaterialeBean>> materiali = new HashMap<Integer, Vector<MaterialeBean>>();
					for (CorsoBean c : corsiIscritto) {
						Vector<MaterialeBean> m = dbms.getMaterialiCorso(c.getIdCorso());
						materiali.put(c.getIdCorso(), m);
					}
					request.setAttribute("stud", stud);
					request.setAttribute("corsiIscritto", corsiIscritto);
					request.setAttribute("materiali", materiali);
					rd = request.getRequestDispatcher("iscritto.jsp");
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
