package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.*;

import bbdd.ConsultasBBDD;
import dao.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CheckCredentials")
public class CheckCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		String username=req.getParameter("username");
		String password=req.getParameter("password");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf8");

		User user=ConsultasBBDD.checkLogin(new User(username,password));

		JSONObject obj=new JSONObject();
		JSONArray array = new JSONArray();

		array.put(new JSONObject(user));

		if(user!=null) {
			obj.put("code", "ok");
			obj.put("message", "ok");
			obj.put("result",array);
		}else {
			obj.put("code", "ERROR");
			obj.put("message", "User not found");
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
