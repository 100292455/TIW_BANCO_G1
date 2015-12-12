package es.uc3m.tiw.ejb;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uc3m.tiw.model.Pedido;
import es.uc3m.tiw.model.dao.PedidoDAOBanco;
import es.uc3m.tiw.model.dao.PedidoDAOImplBanco;

/**
 * Session Bean implementation class PruebaBean
 */
@Stateless(name="pedidos")
@LocalBean
public class LogicaPedidosImpl implements LogicaPedidos {

	@PersistenceContext(unitName = "demoTIWBanco")
	private EntityManager em;
	private PedidoDAOBanco pedDao = new PedidoDAOImplBanco(em);

	@PostConstruct
	public void inicio(){
		pedDao = new PedidoDAOImplBanco(em);
	}

	/* (non-Javadoc)
	 * @see es.uc3m.tiw.ejb.LogicaPedidosRemote#validarPedido(es.uc3m.tiw.model.Pedido)
	 */
	@Override
	public String validarPedido(Pedido pedido) {
		System.out.println("validar 1");
		Calendar c=Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;  
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
				return pedido.getCOD_operacion();
			}
			else if(pedido.getCOD_tarjeta().startsWith("B")){
				pedido.setCOD_operacion(cod_operacionStr);
				try { 
					pedido = pedDao.guardarPedido(pedido);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				return pedido.getCOD_operacion();
			}
			return fail;
		}
		return fail; 
	}
     
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.ejb.LogicaPedidosRemote#conciliarPedido(java.lang.String)
	 */
	
	@Override
	public Double conciliarPedido(String codPedido) {
		System.out.println("conciliar 1");
		Pedido pedido = pedDao.recuperarPedidoPorCodigoPago(codPedido);
		Double importeConciliado = (Double) (pedido.getImporteCobrar() * 0.99);
		pedido.setImporteCobrado(importeConciliado);
		try {
			pedDao.modificarPedido(pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return pedido.getImporteCobrado();
    	
	}

}
