package ecomm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    		Cookie[] cookies = request.getCookies();
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
    		// si parametre ordre == ajouter affichage du disque à ajouter au panier
    		// ajout du nouveau disque dans le panier
    		out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
    		 out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
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
