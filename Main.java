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
     * Questo metodo risponde alle richieste HTTP di tipo GET. Elabora le richieste, impostando
     * gli eventuali attributi necessari, e ridirige la visualizzazione alle pagine jsp relative.
     *
     * @param request Oggetto HttpServletRequest dal quale ottenere informazioni circa la
     *                richiesta effettuata.
     * @param response Oggetto HttpServletResponse per l'invio delle risposte.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		//Definizione e recupero dell'eventuale parametro della servlet
		String ps = "";
		//Dichiaro l'oggetto Dispatcher necessario per passare il controllo ad una JSP o una pagina HTML
		RequestDispatcher rd = null;
	
		if (request.getParameter("ps") != null) {// Ottengo se presente il parametro 'ps'
			ps = request.getParameter("ps");
		}
	
		try {
			// Oggetto per l'interazione con il Database
			DBMS dbms = new DBMS();
			if (ps.equals("")) {
				// Parametro ps assente o vuoto, visualizzo la home page del sito.
				Vector<TipoAttBean> tipi = dbms.getTipiAttivita();
				request.setAttribute("tipi", tipi);
				//Preparo il Dispatcher
				rd = request.getRequestDispatcher("index.jsp");
			}			
			
			if (ps.equals("tipo")) { //visualizzo i corsi e le info del tipo specificato

			}
			
         //Passo il controllo alla JSP
         rd.forward(request,response);

		} catch(Exception e) {  //Gestisco eventuali eccezioni visualizzando lo stack delle chiamate
			e.printStackTrace();
		}
    }
}
