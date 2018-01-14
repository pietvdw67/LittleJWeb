<%@ attribute name="description" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="value" required="false" %>

<p><% out.print(description); %>:<br>
	<input type="<% out.print(type); %>" name="<% out.print(name); %>"
	<%
		if (value != null){
			out.print("value=\"" + value + "\"");
		}
	%>
	/>
</p>