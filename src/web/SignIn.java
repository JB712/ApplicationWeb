package web;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signin")
// http://localhost:8080/AIA/signin
public class SignIn extends HttpServlet {
	
	Connection connexion = null;
	String url = "jdbc:mysql://localhost:3306/my_schema";
	String utilisateur = "root";
	String motDePasse = "root";

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String paramlogin = request.getParameter("login");
		String parammdp = request.getParameter("mdp");
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

			String query = "SELECT id FROM users WHERE pseudo =" + paramlogin
					+ " and exist ( select * from logins "
					+ "where login =" + paramlogin
					+ " and mdp =" + parammdp + ")";

			Statement statement = connexion.createStatement();
			
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( query );
			
			response.setContentType("text/html");
			PrintWriter writer=response.getWriter();
			writer.println("<html>");
			writer.println("\t<head><title>DummyPage</title></head>");
			writer.println("\t<body>you are : " + paramlogin);

			/* Récupération des données du résultat de la requête de lecture */
			if (resultat.next()) {
				int id = resultat.getInt("id");
				/* Traiter ici les valeurs récupérées. */
				writer.println("<br/> id: "+id+ " qu'on va s'occuper de crypter aujourd'hui pour en faire un token bien joli");
			}
			
			writer.println(" </body>");
			writer.println("</html>");
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
					System.out.println("Error while closing connexion to mysql");
				}
		}

	}

}
