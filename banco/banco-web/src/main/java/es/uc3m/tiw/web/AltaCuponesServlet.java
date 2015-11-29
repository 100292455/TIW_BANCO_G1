package es.uc3m.tiw.web;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.*;
import es.uc3m.tiw.model.dao.*;

@WebServlet("/AltaPedidoes")
public class AltaCuponesServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/GestionPedidoes.jsp";
	private static final String GESTION_PedidoES_JSP = "/GestionPedidoes.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private PedidoDAO cupDao;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		cupDao = new PedidoDAOImpl(em, ut);

	}
	
	public void destroy() {
		cupDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(GESTION_PedidoES_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Coger los datos del Pedido que el profesor quiere crear
		String nombreCurso = request.getParameter("nombreCurso");
		String precio1 = request.getParameter("precio");
		String tipo_Pedido1 = request.getParameter("tipo_Pedido");
		String fecha_fin = request.getParameter("fecha_fin");
		HttpSession sesion = request.getSession();
		ServletContext context = sesion.getServletContext();
		/*****/
		
		String mensaje ="";
		String pagina = "";
		pagina = GESTION_PedidoES_JSP;
			
		
		
		
		//Comprobar que los datos almacenados en la peticion no estan vacios
		String m = comprobarPedido(nombreCurso, precio1, tipo_Pedido1, fecha_fin);
		//Si el Pedido a crear presenta todos los datos necesarios para crearlo, entonces:
		if (m.equals(null) || m.equals("")){
			int precio2 = Integer.parseInt(precio1);
			int tipo_Pedido2 = Integer.parseInt(tipo_Pedido1);
			//Creamos el Pedido
			Pedido PedidoNuevo = crearPedido(fecha_fin,  tipo_Pedido2, precio2);
			try {
				PedidoNuevo = cupDao.guardarPedido(PedidoNuevo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Guardamos curso modificado
			pagina = ENTRADA_JSP;
			
			//metemos el curso a matricular en el cont
			//metemos la tabla de Pedidoes en el contexto para poder utilizarla desde otras paginas
			Collection<Pedido> listadoPedidoes = cupDao.buscarTodosLosPedidos();
			context.setAttribute("Pedidoes", listadoPedidoes);
		
		//Si falta algun dato de los introducidos por el formulario para crear el Pedido, enviamos un mensaje de error a dicha pagina	
		}else{
			
			mensaje = m;
			request.setAttribute("mensaje", mensaje);
			//context.setAttribute("Pedido", Pedido);
		}
			
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		}

	//Creamos el Pedido
	private Pedido crearPedido(String fecha_fin, int tipo_Pedido,  int descuento) {
		Pedido c = new Pedido();
		return c;
	}


	//Comprobar que los datos almacenados en la peticion no estan vacios
	private String comprobarPedido(String nombrePromo, String precio, String tipo_promocion, String fecha_fin) {
		String m = "";
		
		if (nombrePromo.equals("") || precio.equals("") || tipo_promocion.equals("") || fecha_fin.equals("")) {
			m ="Fallo al crear nuevo Pedido. ";
		}
		
		return m;
	}

}