package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bbdd.ArtElementType;
import bbdd.ConsultasBBDD;
import dao.ArtElement;
import dao.Museum;
import dao.User;
import manager.CommandInvoker;

/**
 * Servlet implementation class GetSeeArtist
 */
@WebServlet("/FiltrarPorMuseosPrice")
public class FiltrarPorMuseosPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrarPorMuseosPrice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/json;charset=UTF-8");

	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
	    	User user = new User("Laura", "", "", "", "135246");
	    	String precio = request.getParameter("price");
		    CommandInvoker invoker = new CommandInvoker(user);
		    
		    List<ArtElement> museums = ConsultasBBDD.filterArtElement(ArtElementType.MUSEUMS, "price= "+precio);
		    
		    List<Museum> museumsList = new LinkedList<Museum>();
			for(Object museum : museums) {
				museumsList.add((Museum) museum);
		    }
		    
		    JSONArray array = new JSONArray(museumsList);
		    JSONObject out = new JSONObject();
		    if(museumsList.isEmpty()) {
		    	out.put("code", "ERROR");
		    	out.put("mesaje", "Listado vacio");
		    	out.put("resultado", array);
		    } else {
		    	out.put("code", "Ok");
		    	out.put("mesaje", "Ok");
		    	out.put("resultado", array);
		    }
		    
		    PrintWriter writer = response.getWriter();
		    try {
		    	writer.write(out.toString());
		    } finally {
		    	writer.close();
		    }
	    
	    }catch(ClassNotFoundException e) {
			PrintWriter out2 = response.getWriter();
		    out2.println("SQL exception fired");
		    out2.println(e.toString());
		} catch(SQLException e) {
			PrintWriter out2 = response.getWriter();
		    out2.println("SQL exception fired");
		    out2.println(e.toString());
		}
	}
}
