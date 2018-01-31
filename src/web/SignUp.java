package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
// http://localhost:8080/AIA/signup
public class SignUp extends HttpServlet {

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

			String query = " insert into logins (login, mdp)" + " values (?, ?)";

			PreparedStatement preparedStmt = connexion.prepareStatement(query);
			preparedStmt.setString(1, paramlogin);
			preparedStmt.setString(2, parammdp);

			preparedStmt.execute();

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
