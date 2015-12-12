package es.uc3m.tiw.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;

import es.uc3m.tiw.model.Pedido;

public class PedidoDAOImplBanco implements PedidoDAOBanco{
	
	private final EntityManager em;
	
	
	public PedidoDAOImplBanco(EntityManager em) {
		super();
		this.em = em;
	}
	
	@Override
	public Pedido guardarPedido(Pedido nuevoPedido) throws Exception {
		em.persist(nuevoPedido);
		return nuevoPedido;
	}

	@Override
	public Pedido modificarPedido(Pedido Pedido) throws Exception {
		em.merge(Pedido);
		return Pedido;
	}

	@Override
	public void borrarPedido(Pedido Pedido) throws Exception {
		em.remove(em.merge(Pedido));
	}

	@Override
	public Pedido recuperarPedidoPorPK(Integer pk) {
		return em.find(Pedido.class, pk);
	}

	@Override
	public Collection<Pedido> buscarTodosLosPedidos() {
		return em.createQuery("select p from Pedido p",Pedido.class).getResultList();
	}

	@Override
	public Pedido recuperarPedidoPorCodigoPago(String COD_pago) {
		return em.createQuery("select p from Pedido p where p.COD_pago='"+COD_pago+"'", Pedido.class).getSingleResult();
		  
	} 

}
