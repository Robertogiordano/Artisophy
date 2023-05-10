package services;

import bbdd.ConsultasBBDD;
import dao.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterUser")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		String name=req.getParameter("name");
		String surname=req.getParameter("surname");
		String username=req.getParameter("username");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf8");

		JSONObject obj=new JSONObject();


		if(ConsultasBBDD.registerUserBBDD(new User(name,surname,username,email,password))) {
			obj.put("code", "ok");
			obj.put("message", "ok");
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
