package services;

import bbdd.ConsultasBBDD;
import dao.User;
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

@WebServlet("/ModifyUser")
public class ModifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		String nameOld=req.getParameter("nameOld");
		String surnamesOld=req.getParameter("surnamesOld");
		String usernameOld=req.getParameter("usernameOld");
		String emailOld=req.getParameter("emailOld");
		String passwordOld=req.getParameter("passwordOld");

//		String nameOld="Robertoo";
//		String surnamesOld="Giordanoo";
//		String usernameOld="rgiordano";
//		String emailOld="rgiordano@al.uloyola.es";
//		String passwordOld="qwertyuiop";

		User oldUser=new User(nameOld,surnamesOld,usernameOld,emailOld,passwordOld);

		String nameNew=req.getParameter("nameNew");
		String surnamesNew=req.getParameter("surnamesNew");
		String usernameNew=req.getParameter("usernameNew");
		String emailNew=req.getParameter("emailNew");
		String passwordNew=req.getParameter("passwordNew");

//		String nameNew="Roberto";
//		String surnamesNew="Giordanoo234";
//		String usernameNew="rgiordano";
//		String emailNew="rgiordano@al.uloyola.es";
//		String passwordNew="qwertyuiop";

		User newUser=new User(nameNew,surnamesNew,usernameNew,emailNew,passwordNew);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf8");

		JSONObject obj=new JSONObject();

		try{
		User user=ConsultasBBDD.modifyUserBBDD(oldUser,newUser);


		JSONArray array = new JSONArray();

		array.put(new JSONObject(user));

		obj.put("code", "ok");
		obj.put("message", "ok");
		obj.put("result",array);
		}catch (SQLException e){
		obj.put("code", "ERROR");
		obj.put("message", "User not found. Error: "+e.toString());
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
