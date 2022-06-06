<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<ul style="list-style-type:none; display: flex; padding: 10px;">
	<li style="margin-right:10px;"><a href="Inicio.jsp">Inicio</a></li>
	<li style="margin-right:10px;"><a href="servletFormulario?tipoSegurosList=1&proxId=1">Agregar seguro</a></li>
	<li style="margin-right:10px;"><a href="ListarSeguro.jsp">Listar seguros</a></li>
</ul>

<h1>Soy la p�gina listar seguro</h1>

<% 
	ArrayList<Seguro> listaSeguros = null;
	if(request.getAttribute("listaS")!=null)
	{
		listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaS");
	}

 %>

<table border="1">
	<tr> <th>ID</th>  <th>Descripcion</th>  <th>Tipo de Seguro</th>  <th>Costo Contratacion</th> <th>Costo Asegurado</th> </tr>
	
	<%  if(listaSeguros!=null)
		for(Seguro seguro : listaSeguros) 
		{
	%>
	<tr>  
	     <form action="servletUsuario" method="post">
	     <td><%=seguro.getIdSeguro() %> <input type="hidden" name="idUsuario" value="<%=user.getId() %>"> </td>    
	     <td><%=seguro.getIdTipo() %></td>   
	     <td><%=seguro.getDescripcion() %></td>
	     <td><%=seguro.getCostoContratacion() %></td>   
	     <td><%=seguro.getCostoAsegurado() %></td>    
	     <td> <input type="submit" name="btnEliminar" value="Eliminar"> </td>  
	     </form>  
	</tr>
	<%  } %>

</table>
</body>
</html>