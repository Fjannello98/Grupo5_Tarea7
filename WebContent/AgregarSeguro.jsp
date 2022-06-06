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
    int proxId = 0;
	if(request.getAttribute("listaTs")!= null)
	{
		listaTiposDeSeguro = (ArrayList<TipoSeguro>)request.getAttribute("listaTs");
	}
	if (request.getAttribute("proxId")!=null) {
		proxId = (int)request.getAttribute("proxId");
	}

 %>
	<ul style="list-style-type:none; display: flex; padding: 10px;">
		<li style="margin-right:10px;"><a href="Inicio.jsp">Inicio</a></li>
		<li style="margin-right:10px;"><a href="servletFormulario?tipoSegurosList=1&proxId=1">Agregar seguro</a></li>
		<li style="margin-right:10px;"><a href="ListarSeguro.jsp">Listar seguros</a></li>
	</ul>
	
	<h3>Agregar seguro</h3>
	<form method="post" action="servletFormulario?btnAceptar=1">
		<label for="txtNombre" style="margin-right:10px;">ID</label><span><%=proxId%></span><br>
		<input type="hidden" name="txtProxId" value="<%=proxId %>">
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
		<label for="txtCostoAsegurado" style="margin-right:10px;">Costo de seguro</label><input type="number" id="txtCostoAsegurado" name="txtCostoAsegurado"><br>
		<label for="txtCostoContratacion" style="margin-right:10px;">Costo máximo de contratación</label><input type="number" id="txtCostoContratacion" name="txtCostoContratacion"><br>	
		<button type="submit" name="btnAceptar">Aceptar</button>
	</form>
	<%
	if (request.getAttribute("seguroAgregado")!=null && Integer.parseInt(request.getAttribute("seguroAgregado").toString()) == 1) {
	%>
		<h5 style="color:green">¡Seguro agregado con éxito!</h5>
	<%
	}
	%>
</body>
</html>