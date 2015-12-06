package es.uc3m.tiw.ejb;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Pedido;
import es.uc3m.tiw.model.dao.PedidoDAO;
import es.uc3m.tiw.model.dao.PedidoDAOImpl;

public class LogicaPedidosImpl implements LogicaPedidos {
	
    @PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private PedidoDAO pedDao = new PedidoDAOImpl(em, ut);
	
	@Override
	public String validarPedido(Pedido pedido) {

    	//String retorno = " "; 
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
			/*	try {
					pedido = pedDao.guardarPedido(pedido);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
				return pedido.getCOD_operacion();
			}
			else if(pedido.getCOD_tarjeta().startsWith("B")){
				pedido.setCOD_operacion(cod_operacionStr);
			/*	try {
					pedido = pedDao.guardarPedido(pedido);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
				return pedido.getCOD_operacion();
			}
			return fail;
		}
		return fail; 
	}

	@Override
	public Double conciliarPedido(String codPedido) {
    	
    	Pedido pedido = pedDao.recuperarPedidoPorCodigoPago(codPedido);
    	Pedido nuevoPedido = new Pedido();
		Double importeConciliado = (Double) (10 * 0.99);
		pedido.setImporteCobrado(importeConciliado);
		try {
			nuevoPedido = pedDao.modificarPedido(pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return nuevoPedido.getImporteCobrado();
	}

}
