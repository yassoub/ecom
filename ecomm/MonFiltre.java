package ecomm;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MonFiltre implements Filter {
	private FilterConfig filterConfig = null;
	 
    /**
     * Default constructor. 
     */
    public MonFiltre() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
    public void destroy() {
		this.filterConfig = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	ServletException {
	String nom = null;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
			Cookie[] cookies = hrequest.getCookies();
	 // test s'il existe un cookie dont l'attribut est "nom"
			 for(int i=0; i<cookies.length;i++) {
        		 if(cookies[i].getName().equals("nomCookie")) {
        			 chain.doFilter(request, response);
        		 }       		
        	 }
     		 ((HttpServletResponse) response).sendRedirect("/ECommerce/InscriptionClient");

	}
	
	
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		 this.filterConfig = filterConfig;

	}

}
