<%@page import="java.util.ArrayList"%>
<%@page import="dominio.TipoSeguro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<% 
	ArrayList<TipoSeguro> listaTiposDeSeguro = null;
	if(request.getAttribute("listaTs")!= null)
	{
		listaTiposDeSeguro = (ArrayList<TipoSeguro>)request.getAttribute("listaTs");
	}

 %>
	<ul style="list-style-type:none; display: flex; padding: 10px;">
		<li style="margin-right:10px;"><a href="Inicio.jsp">Inicio</a></li>
		<li style="margin-right:10px;"><a href="servletTipoSeguro?tipoSegurosList=1">Agregar seguro</a></li>
		<li style="margin-right:10px;"><a href="ListarSeguro.jsp">Listar seguros</a></li>
	</ul>
	
	<h3>Agregar seguro</h3>
	<form method="get">
		<label for="txtNombre" style="margin-right:10px;">ID</label><span>*ID proximo*</span><br>
		<label for="txtDescripcion"style="margin-right:10px;">Descripción</label><input type="text" id="txtDescripcion" name="txtDescripcion"><br>
		<label for="selectTipoSeguro" style="margin-right:10px;">Tipo de seguro</label>
			<select id="selectTipoSeguro" name="tipoSeguro">
       <%
       	if(listaTiposDeSeguro != null)
			for(TipoSeguro tipo : listaTiposDeSeguro) 
			{
		%>
			<option value="<%=tipo.getId()%>"><%= tipo.getDescripcion() %></option>
		<% 
			}
		%>	
			</select><br>
		<label for="txtCostoSeguro" style="margin-right:10px;">Costo de seguro</label><input type="number" id="txtCostoSeguro" name="txtCostoSeguro"><br>
		<label for="txtCostoMaxContratacion" style="margin-right:10px;">Costo máximo de contratación</label><input type="number" id="txtCostoMaxContratacion" name="txtCostoMaxContratacion"><br>	
		<button type="submit" name="btnAceptar">Aceptar</button>
	</form>
</body>
</html>