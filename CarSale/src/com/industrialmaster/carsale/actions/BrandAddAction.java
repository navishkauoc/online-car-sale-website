package com.industrialmaster.carsale.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.industrialmaster.carsale.db.DB;

@WebServlet("/brand_add")
public class BrandAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//01. Collect input field values
		String name = request.getParameter("name");
		
		//02. Generate non-fillables
		
		String error = "";
		//03. Process data(Validations)
		if(name == null || name.length() < 5) {
			error += "Name must have 5 characters minimum.";
		}
		
		if(error.contentEquals("")) {
			//Valid form
			try{
				Connection connection = DB.getCon();
				//05.SQL & Execution
				String sql = "INSERT INTO brand (name) VALUES (?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, name);
				ps.executeUpdate();
				
				response.sendRedirect("msg.jsp?msg=Brand Saved Successfully!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			//Invalid form
			response.sendRedirect("register.jsp?msg="+error);
		}
	}

}
