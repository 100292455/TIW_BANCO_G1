package es.uc3m.tiw.ejb;

import es.uc3m.tiw.model.Pedido;

public interface LogicaPedidos {
	
	String validarPedido(Pedido pedido);
	
	Integer conciliarPedido(String cod_pedido);

}
