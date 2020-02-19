package ecomm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.Commande;
import bd.DaoI;
import bd.Stock;

/**
 * Servlet implementation class EnregistrerCommande
 */
@WebServlet("/enregistre")
public class EnregistrerCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    		String nom = null;
    		DaoI impl = new DaoI();
		    int nbreProduit = 0;
		    Cookie[] cookies = request.getCookies();
   	    	Stock uneVente = new Stock();

    		List<String> commandes =  new ArrayList<String>();
    		List<Commande> cmd  =null;
		    nom = Identification. chercheNom (cookies);
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println("<html>");
		    out.println("<body>");
		    out.println("<head>");
		    out.println("<title> votre commande </title>");
		    out.println("</head>");
		    out.println("<body bgcolor=\"white\">");
		
		    out.println("<h3>" + "Bonjour " + nom + " voici ta nouvelle commande" + "</h3>");
		    HttpSession panier = request.getSession();
		   
		    	if(panier !=  null) {
    				commandes =  (List<String>) panier.getAttribute("panier");
		    		for (String code : commandes)
    				{
		    			impl.insertCommande(nom, code);
    				}
		    	}
		    out.println("<h3>" + "et voici " + nom + " ta commande complete" + "</h3>");
		    
		    cmd=impl.getAllCommandes(nom);
   		 out.println("<table border=1>");

		    for(Commande cm : cmd) {
		    uneVente.show(cm.getCode(), out);
		    }
   		 out.println("</table><br>");
		    out.println("<a href=\"ViderPanier\"> Vous pouvez commandez un autre disque </a><br> ");
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
