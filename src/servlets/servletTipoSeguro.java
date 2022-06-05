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
import dominio.TipoSeguro;

/**
 * Servlet implementation class servletTipoSeguro
 */
@WebServlet("/servletTipoSeguro")
public class servletTipoSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTipoSeguro() {
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");   
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
