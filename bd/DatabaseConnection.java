package bd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Servlet implementation class DatabaseConnection
 */
@WebServlet("/DatabaseConnection")
public class DatabaseConnection extends HttpServlet {
	   private static DatabaseConnection instance;
	   private Connection connection;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseConnection() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException {
    	String pilote =  getServletContext().getInitParameter("jdbc.Driver");
    	String localisation = getServletContext().getInitParameter("localisation");
        try {
            Class.forName(pilote);
            this.connection = DriverManager.getConnection(localisation, "root", "");
        } catch (ClassNotFoundException e) {
        	log("Driver BD non trouvé"); throw  new ServletException();
        } catch(SQLException e) {
        	log("Base de données non trouvé"); throw  new ServletException();

        }
    }
    public void destroy() {
    	if(connection !=null) {
    		try {
    			connection.close();
    		}catch(SQLException e) {
    			log("Erreur fermeture BD");
    		}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}




    

    



