/*package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.Entrada;
import manager.EntradasManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/getEntradas")
public class getServiceTemplate extends HttpServlet{
	
	/**
	 *
	 */
	/*private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf8");
		
		List<Entrada> entradas=EntradasManager.getEntradasEventos();
		
		JSONArray array=new JSONArray(entradas);
		JSONObject obj=new JSONObject();
		
		if(!entradas.isEmpty()) {
			obj.put("cod", "ok");
			obj.put("msg", "ok");
			obj.put("res", array);
		}else {
			obj.put("cod", "ERROR");
			obj.put("msg", "Listado Vacio");
			obj.put("res", array);
		}
		
	
		try {
			PrintWriter writer=resp.getWriter();
			writer.write(obj.toString());
			writer.close();	
		} catch (IOException e) {
			obj.put("cod", "ERROR");
			obj.put("msg", "Listado Vacio");
			obj.put("res", array);
		}
		
	}
}
*/