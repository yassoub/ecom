package ecomm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.Stock;

/**
 * Servlet implementation class CommanderUnDisque
 */
@WebServlet("/commande")
public class CommanderUnDisque extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommanderUnDisque() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request,
    		 HttpServletResponse response)
    		throws IOException, ServletException
    		{ String nom = null;
    		int nbreProduit = 0;	
    		List<String> commandes =  new ArrayList<String>();
    		Cookie[] cookies = request.getCookies();
   		    HttpSession panier = request.getSession();
   		    String id = request.getParameter("code");
   	    	Stock uneVente = new Stock();

    		nom = Identification.chercheNom(cookies);
    		 response.setContentType("text/html");
    		 PrintWriter out = response.getWriter();
    		 out.println("<html>");
    		 out.println("<body>");
    		 out.println("<head>");
    		 out.println("<title> votre commande </title>");
    		 out.println("</head>");
    		 out.println("<body bgcolor=\"white\">");
    		 out.println("<h3>" + "Bonjour "+ nom + " voici votre commande" + "</h3>");
    		// affichage de tous les disques présents dans le panier (éléments de la session)
    		 out.println("<table border=1>");

    		 if (panier.isNew()) {
    				if(request.getParameter("ordre").equals("ajouter")) {
    					commandes.add(id);
        	    		
        	    		//fetch commandes

        				for (String code : commandes)
        				{
        					uneVente.show(code, out);
        				}
    	    		}else {
    	       		 out.println("<tr><td>Empty Panier</td></tr>");
    	    			
    	    		}
    			} else {
    				if(panier != null) {
        				commandes =  (List<String>) panier.getAttribute("panier");

    				}
    				
    				if(request.getParameter("ordre").equals("ajouter")) {
    					commandes.add(id);
    				}

    				for (String code : commandes)
    				{
    					uneVente.show(code, out);
    				}
    			}
    		 out.println("</table>");

    		 nbreProduit = commandes.size();
    		// ajout du nouveau disque dans le panier
	    		panier.setAttribute("panier", commandes);
	    		out.println("<h4>Votre Panier Contient:<b> "+nbreProduit+" </b>disque(s)</h4>");
    		out.println("<a href=\"AfficherLesDisques\"> Vous pouvez commandez un autre disque </a><br> ");
    		 out.println("<a href=\"enregistre\"> Vous pouvez enregistrer votre commande </a><br> ");
    		 out.println("</body>");
    		 out.println("</html>");
    		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
