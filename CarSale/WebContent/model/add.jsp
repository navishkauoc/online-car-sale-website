<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../WEB-INF/jspf/head.jsp" %>
</head>
<body>

	<div class="container">
		<%@include file="../WEB-INF/jspf/header.jsp" %>
		
		<div class="row">
			<div class="col-md-3">
				<%@include file="../WEB-INF/jspf/sidebar.jsp" %>
			</div>
			<div class="col-md-9">
				<%
				if(request.getParameter("msg") != null){
					out.println("<div class='alert alert-danger'>");
					out.print(request.getParameter("msg"));
					out.println("</div>");
				}
				%>
				<div class="row well well-lg">
					<h3> Model Add </h3>
					<hr/>
					<form action="../model_add" method="post">
						<label> Brand </label>
						<select name="brand_id" class="form-control">
							<option value="1">Toyota</option>
							<option value="2">Honda</option>
							<option value="3">BMW</option>
						</select>
						<label> Name </label>
						<input type="text" class="form-control" name="name">
						<hr/>
						<input type="submit" value="SAVE" class="btn btn-success">
						<input type="reset" value="RESET" class="btn btn-danger">
					</form>
				</div>
			</div>
		</div>
		
		<%@include file="../WEB-INF/jspf/footer.jsp" %>
	</div>

</body>
</html>