package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.TipoSeguroDao;
import dominio.Seguro;
import dominio.SeguroDao;
import dominio.TipoSeguro;

@WebServlet("/servletFormulario")
public class servletFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletFormulario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("tipoSegurosList")!=null)
		{
			TipoSeguroDao tsdao = new TipoSeguroDao();	
            ArrayList<TipoSeguro> lista= tsdao.obtenerTiposSeguro();
			request.setAttribute("listaTs", lista);
			
		}
		if(request.getParameter("proxId")!=null) {
			SeguroDao sdao = new SeguroDao();
			int proxId = sdao.encontrarProximoId();
			request.setAttribute("proxId", proxId);
		}
		if(request.getParameter("seguroAgregado")!=null) {
			request.setAttribute("seguroAgregado", "1");
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");   
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletRedir = "servletFormulario?tipoSegurosList=1&proxId=1"; 
		if(request.getParameter("btnAceptar")!=null)
		{
			
			SeguroDao sDao = new SeguroDao();
			Seguro seguro = new Seguro();
			seguro.setIdSeguro((Integer.parseInt(request.getParameter("txtProxId").toString())));
			seguro.setCostoAsegurado((Float.parseFloat(request.getParameter("txtCostoAsegurado").toString())));
			seguro.setCostoContratacion((Float.parseFloat(request.getParameter("txtCostoContratacion").toString())));
			seguro.setDescripcion(request.getParameter("txtDescripcion").toString());
			seguro.setIdTipo((Integer.parseInt(request.getParameter("tipoSeguro").toString())));			
			boolean pudoAgregarlo = sDao.procedimientoInsertarSeguro(seguro);
			servletRedir += pudoAgregarlo ? "&seguroAgregado=1" : "&seguroAgregado=0";
		}
		response.sendRedirect(servletRedir);
		
	}

}
