package es.uc3m.tiw.web;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import es.uc3m.tiw.ejb.LogicaPedidos;
import es.uc3m.tiw.model.Pedido;
/**
 * @author Miguel Solera
 *
 */ 
@Stateless
@Path("pasarela")
public class PasarelaService {
    
	@Context
    private UriInfo context; 
	
	@EJB(name="pedidos")
	private LogicaPedidos logPedidos;
    /**
     * Default constructor. 
     */
	
    public PasarelaService(){
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
    public String devuelveXML(@PathParam("importe")Double importeCobrar,@PathParam("tarjeta") String tarjeta,@PathParam("pedido") String codPedido){
    	System.out.println("Entramos en generar orden");
    	Pedido pedido = new Pedido(importeCobrar, tarjeta, codPedido);
    	String retorno = " "; 
    	retorno = logPedidos.validarPedido(pedido);
    	
    	return retorno;
    }
    
    /**
     * Se accede por GET y se consume texto plano
     * se devuelve texto plano
     * La URL es: http://localhost:8080/banco-web/resources/pasarela/conciliar/12345abc/xml
     * @param pedido
     * @return importe conciliado
     */ 
    @GET
    @Path("conciliar/{pedido}/xml")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Double conciliarPedidos(@PathParam("pedido") String codPedido){
    	System.out.println("Entramos en conciliar");
    	Double retorno ; 
    	retorno = logPedidos.conciliarPedido(codPedido);
    	
    	
    	return retorno;
    }

} 