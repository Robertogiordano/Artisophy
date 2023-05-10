/*used to connect artist to artwork*/

package services;

import bbdd.ArtElementType;
import bbdd.ConsultasBBDD;
import dao.ArtElement;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/FiltrarPorArtsName")
public class FiltrarPorArtsName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		String name=req.getParameter("name");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf8");

		List<ArtElement> artElementsList= null;
		JSONObject obj = new JSONObject();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			artElementsList = ConsultasBBDD.filterArtElement(ArtElementType.ARTWORKS,"name LIKE '%"+name+"%'");
			JSONArray array = new JSONArray();


			if (!artElementsList.isEmpty()) {
				Iterator var7 = artElementsList.iterator();

				while(var7.hasNext()) {
					Object a = var7.next();
					JSONObject jsonAl = new JSONObject(a);
					array.put(jsonAl);
				}
			}


			if(array.isEmpty()) {
				obj.put("code", "ERROR");
				obj.put("message", "Listado vacio");
				obj.put("result", array);
			} else {
				obj.put("code", "Ok");
				obj.put("message", "Ok");
				obj.put("result", array);
			}
		} catch (SQLException | ClassNotFoundException e) {
			obj.put("code", "ERROR");
			obj.put("message", e.toString());
		}



		try {
			PrintWriter writer=resp.getWriter();
			writer.write(obj.toString());
			writer.close();
		} catch (IOException e) {
			obj.put("code", "ERROR");
			obj.put("message", e.toString());
		}
		
	}
}
