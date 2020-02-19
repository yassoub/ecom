package ecomm;

import javax.servlet.http.*;

public class Identification {
	 static String chercheNom (Cookie [] cookies) {
		 // cherche dans les cookies la valeur de celui qui se nomme "nom"
		 // retourne la valeur de ce nom au lieu de inconnu
		 
		 
		 for(int i=0; i<cookies.length;i++) {
    		 if(cookies[i].getName().equals("nomCookie")) {
    			  return cookies[i].getValue();
    		 }
    	 }
		 return "inconnu";
		}
}
