package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

@WebServlet("/users")
//http://localhost:8080/AIA/users
public class MyServlet extends HttpServlet {
	
	Connection connexion = null;
	String url = "jdbc:mysql://localhost:3306/my_schema";
	String utilisateur = "root";
	String motDePasse = "root";
	

	public void doPost( HttpServletRequest request, HttpServletResponse response) {
		
		int paramID = Integer.parseInt(request.getParameter("id"));
		String paramPseudo = request.getParameter("pseudo");
		String paramFirst = request.getParameter("firstname");
		String paramLast = request.getParameter("lastname");
		
		
		try {
			/*
			response.setContentType("text/html");
			PrintWriter writer=response.getWriter();
			writer.println("<html>");
			writer.println("\t<head><title>DummyPage</title></head>");
			writer.println("\t<body>A great (almost) emptypage written by JB</body>");
			writer.println("</html>");
			writer.close();
			*/
			
			Class.forName( "com.mysql.jdbc.Driver" );		// org.gjt.mm.mysql.Driver  //   com.mysql.jdbc.Driver
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			
			/*
			// Création de l'objet gérant les requêtes 
			Statement statement = connexion.createStatement();
			
			// Exécution d'une requête de lecture 
			ResultSet resultat = statement.executeQuery( "SELECT id, email, mot_de_passe, nom  FROM Utilisateur;" );
			*/
			
			// the mysql insert statement
			String query = " insert into users (id, pseudo, firstname, lastname)" + " values (?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connexion.prepareStatement(query);
			preparedStmt.setInt(1, paramID);
			preparedStmt.setString(2, paramPseudo);
			preparedStmt.setString(3, paramFirst);
			preparedStmt.setString(4, paramLast);

			// execute the preparedstatement
			preparedStmt.execute();
			
			}catch(Exception e){
				e.printStackTrace();
			} finally {
			    if ( connexion != null )
			        try {
			            /* Fermeture de la connexion */
			            connexion.close();
			        } catch ( SQLException ignore ) {
			            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
			        	System.out.println("Error while closing connexion to mysql");
			        }
			}
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse response) {
		try {
			/*
			response.setContentType("text/html");
			PrintWriter writer=response.getWriter();
			writer.println("<html>");
			writer.println("\t<head><title>DummyPage</title></head>");
			writer.println("\t<body>A great (almost) emptypage written by JB</body>");
			writer.println("</html>");
			writer.close();
			*/
			
			Class.forName( "com.mysql.jdbc.Driver" );
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			
			/*
			// Création de l'objet gérant les requêtes 
			Statement statement = connexion.createStatement();
			
			// Exécution d'une requête de lecture 
			ResultSet resultat = statement.executeQuery( "SELECT id, email, mot_de_passe, nom  FROM Utilisateur;" );
			*/
			
			// the mysql insert statement
			String query = " select * from users";
			
			Statement statement = connexion.createStatement();
			
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( query );
			
			response.setContentType("text/html");
			PrintWriter writer=response.getWriter();
			writer.println("<html>");
			writer.println("\t<head><title>DummyPage</title></head>");
			writer.println("\t<body>users:");

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String pseudo = resultat.getString("pseudo");
				String firstname = resultat.getString("firstname");
				String lastname = resultat.getString("lastname");

				/* Traiter ici les valeurs récupérées. */
				writer.println("<br/> id: "+id+"; pseudo: "+pseudo+"; firstname: "+firstname+"; lastname: "+lastname);
			}
			
			writer.println(" </body>");
			writer.println("</html>");
			writer.close();

			}catch(Exception e){
				e.printStackTrace();
			} finally {
			    if ( connexion != null )
			        try {
			            /* Fermeture de la connexion */
			            connexion.close();
			        } catch ( SQLException ignore ) {
			            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
			        	System.out.println("Error while closing connexion to mysql");
			        }
			}
	}

}
