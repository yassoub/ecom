package ecomm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class InscriptionClient
 */
@WebServlet("/InscriptionClient")

public class InscriptionClient extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String nomRecu=null, motPasseRecu=null;
    	 String nomCookie=null, motPasseCookie=null;
    	 // initialisation cookies et paramètres
    	 Cookie[] allCookies = request.getCookies();
    	 if(request.getMethod().equals("GET")){
        	 nomRecu=request.getParameter("nom");
        	 motPasseRecu=request.getParameter("pass");

    	 }
    	 if(request.getCookies() != null) {
    		 for(int i=0; i<allCookies.length;i++) {
        		 if(allCookies[i].getName().equals("nomCookie")) {
        			 nomCookie = allCookies[i].getValue();
        		 }
        		 if(allCookies[i].getName().equals("motPasseCookie")) {
        			 motPasseCookie = allCookies[i].getValue();
        		 }
        	 }
    	 }
    	 
    	 response.setContentType("text/html");
    	 PrintWriter out = response.getWriter();

    	 if (nomCookie==null && nomRecu==null){
    	 // Cas 1 : cas où il n'y a ni de cookies ni de parametres
    	 out.println("<html>");
    	 out.println("<body>");
    	 out.println("<head>");
    	 out.println("<title> inscription d'un client </title>");
    	 out.println("</head>");
    	 out.println("<body bgcolor='white' >");
    	 out.println("NR:"+ nomRecu +" | MPR: "+ motPasseRecu +" | NC: "+ nomCookie +" | MPC: "+ motPasseCookie );
    	 out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
    	 out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
    	 out.print(" <form action='InscriptionClient' method='GET' > ");
    	 out.println("nom");
    	 out.println("<input type='text' size='20' name='nom' required>");
    	 out.println("<br>");
    	 out.println("mot de passe");
    	 out.println("<input type='password' size='20' name='pass' required> <br>");
    	 out.println("<input type='submit' value='Inscription'>");
    	 out.println("</form>");
    	 out.println("</body>");
    	 out.println("</html>");
    	 } else if (nomCookie==null && nomRecu!=null){
    		  //cas 2 il n'y a pas de cookies mais les paramètres nom et mot de passes sont présents :
    		 
    		 Cookie c1 = new Cookie("nomCookie",nomRecu);
    		 Cookie c2 = new Cookie("motPasseCookie",motPasseRecu);
    		 response.addCookie(c1);
    		 response.addCookie(c2);
        	  nomRecu=null; motPasseRecu=null;
        	  

     		 response.sendRedirect("/ECommerce/InscriptionClient");
    		 
    	 }else if (identique(nomRecu,nomCookie) && identique(motPasseRecu,motPasseCookie)){
    		 
    		 // cas 4 le nom et le mot passe sont correctes, appel à la servlet achat
    		 response.sendRedirect("/ECommerce/AfficherLesDisques");
    	 }else if(identiques(nomRecu,nomCookie,motPasseRecu,motPasseCookie)){
    		 nomRecu=null; motPasseRecu=null;
    		 nomCookie=null; motPasseCookie=null;
    		 Cookie c1 = new Cookie("nomCookie","");
    		 Cookie c2 = new Cookie("motPasseCookie","");
    		 c1.setMaxAge(0);
    		 c2.setMaxAge(0);
    		 response.addCookie(c1);
    		 response.addCookie(c2);
     		 response.sendRedirect("/ECommerce/InscriptionClient");
    	 }else {
    	 
    		 // cas 3 les cookies sont présents demande de s'identifier
    		 
    		 out.println("<html>");
        	 out.println("<body>");
        	 out.println("<head>");
        	 out.println("<title> Authentification d'un client </title>");
        	 out.println("</head>");
        	 out.println("<body bgcolor='white' >");
        	 out.println("NR:"+ nomRecu +" | MPR: "+ motPasseRecu +" | NC: "+ nomCookie +" | MPC: "+ motPasseCookie );
        	 out.println("<h2>" + "Authentification" + "</h2>");
        	 out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
        	 out.print(" <form action='InscriptionClient' method='GET' > ");
        	 out.println("nom");
        	 out.println("<input type='text' size='20' name='nom' required>");
        	 out.println("<br>");
        	 out.println("mot de passe");
        	 out.println("<input type='password' size='20' name='pass' required> <br>");
        	 out.println("<input type='submit' value='Sidentifier'>");
        	 out.println("</form>");
        	 out.println("</body>");
        	 out.println("</html>");
    	 }
	}

    boolean identique (String recu, String cookie) {
    	 return ((recu != null) && (recu.length() >3) && (cookie != null) && (recu.equals(cookie) ));
    	 }

    boolean identiques (String recu, String cookie,String recus,String cookies) {
    	if(recu != null && cookie!=null && recu!=null && cookies!=null) {
        	if(recu.equals(cookie) && recus.equals(cookies)) {
        		return false;
        	}else {
        		return true;
        	}
    	}else {
    		return false;
    	}
   	 }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
