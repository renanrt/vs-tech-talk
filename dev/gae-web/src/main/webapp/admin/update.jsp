<%@ page import="br.com.videosoft.pinpad.domain.entities.Customer" %>
<html>
<body>
	<h1>Update Customer</h1>
	
	<%
			Customer customer = (Customer)request.getAttribute("customer");
		%>
	
	<form method="post" action="../update" >
		<input type="hidden" name="originalName" id="originalName" 
			value="<%=customer.getName() %>" /> 
		
		<table>
			<tr>
				<td>
					UserName :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="name" id="name" 
						value="<%=customer.getName() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Email :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="email" id="email" 
						value="<%=customer.getEmail()%>" />
				</td>
			</tr>
		</table>
		<input type="submit" class="update" title="Update" value="Update" />
	</form>
	
</body>
</html>