<%@page import="java.text.SimpleDateFormat"%>
<%@page import="servlet.ContatoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seja Bem Vindo</title>
</head>
<body>

	<%@page
		import="servlet.Contato,servlet.ContatoDao,java.util.*, java.io.DataOutput"%>
	<table>

		<%
			ContatoDao dao = new ContatoDao();
			List<Contato> contatos = dao.getLista();

			for (Contato contato : contatos) {
		%>
		<tr>
			<td><%=contato.getNome()%></td>
			<td><%=contato.getEmail()%></td>
			<td><%=contato.getEndereco()%></td>

			<%
				SimpleDateFormat dataformatada = new SimpleDateFormat("dd/MM/yyyy");
			%>

			<td><%=dataformatada.format(contato.getDataNascimento().getTime())%></td>



		</tr>
		<%
			}
		%>




	</table>






</body>
</html>