package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbdd.ArtElementType;
import bbdd.ConsultasBBDD;
import dao.ArtElement;
import org.json.JSONArray;
import org.json.JSONObject;

import dao.User;
import manager.CommandInvoker;
import manager.CommandsType;

/**
 * Servlet implementation class GetAllArtitsServlet
 */
@WebServlet("/GetAllArtistServlet")
public class GetAllArtitsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllArtitsServlet() {
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

			List<ArtElement> artElementsList= ConsultasBBDD.getArtElements(ArtElementType.ARTISTS);

			JSONArray array = new JSONArray();
			JSONObject out = new JSONObject();

			if (!artElementsList.isEmpty()) {
				Iterator var7 = artElementsList.iterator();

				while(var7.hasNext()) {
					Object a = var7.next();
					JSONObject jsonAl = new JSONObject(a);
					array.put(jsonAl);
				}
			}


			if(array.isEmpty()) {
				out.put("code", "ERROR");
				out.put("message", "Listado vacio");
				out.put("result", array);
			} else {
				out.put("code", "Ok");
				out.put("message", "Ok");
				out.put("result", array);
			}
		    
		    PrintWriter writer = response.getWriter();
		    try {
		    	writer.write(out.toString());
		    } finally {
		    	writer.close();
		    }
		}catch(ClassNotFoundException e) {
			JSONObject out = new JSONObject();
			out.put("code", "ERROR");
			out.put("mensaje", "Class not found fired" + e.toString());
			out.put("resultado", new JSONArray());
			PrintWriter writer = response.getWriter();
			try {
				writer.write(out.toString());
			} finally {
				writer.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
