package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/Hellow")
public class MyServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter writer=response.getWriter();
			writer.println("<html>");
			writer.println("\t<head><title>DummyPage</title></head>");
			writer.println("\t<body>Agreat(almost)emptypage</body>");
			writer.println("</html>");
			writer.close();
			
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
