package es.uc3m.tiw.web;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import es.uc3m.tiw.model.Pedido;
import es.uc3m.tiw.model.dao.*;
//import es.uc3m.tiw.ejb.LogicaPedidos;
/**
 * La url de acceso sera: 
 * Para getText() -- http://localhost:8080/banco-web/resources/pasarela/prueba 
 * Para getDatos() -- http://localhost:8080/banco-web/resources/pasarela/prueba/23/david 

 * @author Miguel Solera
 *
 */
@Path("pasarela")
public class PasarelaService {
    
    @Context
    private UriInfo context;
    @PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private PedidoDAO pedDao = new PedidoDAOImpl(em, ut);
	
 //   private LogicaPedidos logPedidos;
    /**
     * Default constructor. 
     */
    public PasarelaService(){
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Se accede por GET y se consume texto plano 
     * se devuelve texto plano
     * La URL es: http://localhost:8080/banco-web/resources/pasarela/pedido/10/A1234567890123456789/12345abc/xml
     * La URL es: http://localhost:8080/banco-web/resources/pasarela/pedido/10/K1234567890123456789/12345abc/xml
     * La URL es: http://localhost:8080/banco-web/resources/pasarela/pedido/10/1234567890123456789/12345abc/xml
     * @param importe
     * @param tarjeta
     * @param pedido
     * @return codigo de operacion bancaria o fail
     */
    @GET
    @Path("pedido/{importe}/{tarjeta}/{pedido}/xml")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String devuelveXML(@PathParam("importe")Integer importeCobrar,@PathParam("tarjeta") String tarjeta,@PathParam("pedido") String codPedido){
    	    	
    	Pedido pedido = new Pedido(importeCobrar, tarjeta, codPedido);
    	//String retorno = ""; 
    	//logPedidos.validarPedido(pedido);
    	Calendar c=Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);  
		int day = c.get(Calendar.DATE);
		int hours = c.get(Calendar.HOUR_OF_DAY);
		int seconds = c.get(Calendar.SECOND);
		int miliseconds = c.get(Calendar.MILLISECOND);
		int PM_AM = c.get(Calendar.AM_PM);
		String PM_AMStr = "";
		if (PM_AM == 0){PM_AMStr = "AM";}
		else {PM_AMStr = "PM";}
		
		String fail = "fail"; 
		String cod_operacionStr = "BANCO"+year+month+day+hours+seconds+miliseconds+PM_AMStr;
		if (pedido.getCOD_tarjeta().length() == 20 ){
			if (pedido.getCOD_tarjeta().startsWith("A")) {
				pedido.setCOD_operacion(cod_operacionStr);
				try {
					pedido = pedDao.guardarPedido(pedido);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return cod_operacionStr;
			}
			else if(pedido.getCOD_tarjeta().startsWith("B")){
				pedido.setCOD_operacion(cod_operacionStr);
				try {
					pedido = pedDao.guardarPedido(pedido);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return cod_operacionStr;
			}
			return fail;
		}
		return fail; 
    	//return retorno;
    }
    
    /**
     * Se accede por GET y se consume texto plano
     * se devuelve texto plano
     * La URL es: http://localhost:8080/banco-web/resources/pasarela/pedido/12345abc/xml
     * @param pedido
     * @return importe conciliado
     */
    @GET
    @Path("conciliar/{pedido}/xml")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Integer conciliarPedidos(@PathParam("pedido") String codPedido){
    	    	
    	//String retorno = codPedido; 
    	//logPedidos.conciliarPedido(codPedido);
    	
    	Pedido pedido = pedDao.recuperarPedidoPorCodigoPago(codPedido);
		Integer importeConciliado = (int) (pedido.getImporteCobrar() * 0.99);
		pedido.setImporteCobrado(importeConciliado);
		try {
			pedDao.modificarPedido(pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return importeConciliado;
    	
    	//return retorno;
    }

} 