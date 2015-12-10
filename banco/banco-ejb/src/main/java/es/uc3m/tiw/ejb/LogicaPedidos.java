package es.uc3m.tiw.ejb;

import javax.ejb.Local;

import es.uc3m.tiw.model.Pedido;

@Local
public interface LogicaPedidos {
	
	String validarPedido(Pedido pedido);
	 
	Double conciliarPedido(String cod_pedido);

}
   

