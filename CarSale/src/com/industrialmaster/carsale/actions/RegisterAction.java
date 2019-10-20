package com.industrialmaster.carsale.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.industrialmaster.carsale.db.DB;

@WebServlet("/reg")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//01. Collect input field values (Collecting Fillable)
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String photo = request.getParameter("photo");
		
		//02. Generate non-fillables
		Date date = new Date();
		int status = 1;
		String role = "u";
		
		String errors = "";
		//03. Process data (Validations)
		if(name == null || name.length() < 5) {
			errors += "Name must have 5 characters minimum.";
		}
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(!email.matches(regex)){
			errors += "Enter a valid email.";
		}
		if(password == null || name.length() < 5) {
			errors += "Enter a valid password.";
		}
		if(errors.contentEquals("")) {
			//Valid form
			try{
				Connection connection = DB.getCon();
				//05.SQL & Execution
				String sql = "INSERT INTO `member` (name, email, password, photo, mobile, date, status, role)  VALUES (?,?,?,?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, password);
				ps.setString(4, photo);
				ps.setString(5, mobile);
				ps.setDate(6, new java.sql.Date(date.getTime()));
				ps.setInt(7, status);
				ps.setString(8, role);
				ps.executeUpdate();
				
				response.sendRedirect("msg.jsp?msg=Thank you for registering with us!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			//Invalid form
			response.sendRedirect("register.jsp?msg="+errors);
		}
	}

}
