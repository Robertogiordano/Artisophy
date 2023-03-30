package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.User;
import manager.CommandInvoker;
import manager.CommandsType;

/**
 * Servlet implementation class GetAllMuseumsServlet
 */
@WebServlet("/GetAllMuseumsServlet")
public class GetAllMuseumsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllMuseumsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/json;charset=UTF-8");

	    
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			
		    User user = new User("Laura", null, null, null, "135246");
		    CommandInvoker invoker = new CommandInvoker(user);
		    CommandsType commandType = CommandsType.MUSEUM_GUIDE;
		    List<Object> museums = invoker.executeCommand(commandType);
	
		    JSONArray array = new JSONArray(museums);
		    JSONObject out = new JSONObject();
		    if(museums.isEmpty()) {
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
