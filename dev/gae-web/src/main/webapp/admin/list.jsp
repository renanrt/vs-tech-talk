<%@ page import="java.util.List" %>
<%@ page import="br.com.videosoft.pinpad.domain.entities.Customer" %>
<html>
<body>
	<h1>GAE + Spring 3 MVC REST + CRUD Example</h1>

	Function : <a href="addCustomerPage">Add Customer</a>
	<hr />

	<h2>All Customers</h2>
	<table border="1">
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		<%
			List<Customer> customers = (List<Customer>)request.getAttribute("customerList");
				    for(Customer e : customers){
		%>
			<tr>
				<td><%=e.getName() %></td>
				<td><%=e.getEmail() %></td>
				<td><%=e.getDate() %></td>
				<td><a href="update/<%=e.getName()%>">Update</a> | <a href="delete/<%=e.getName()%>">Delete</a></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>