package es.uc3m.tiw.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Pedido;

public class PedidoDAOImpl implements PedidoDAO{
	
	private final EntityManager em;
	private final UserTransaction ut;
	
	
	public PedidoDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}
	
	@Override
	public Pedido guardarPedido(Pedido nuevoPedido) throws Exception {
		ut.begin();
		em.persist(nuevoPedido);
		ut.commit();
		return nuevoPedido;
	}

	@Override
	public Pedido modificarPedido(Pedido Pedido) throws Exception {
		ut.begin();
		em.merge(Pedido);
		ut.commit();
		return Pedido;
	}

	@Override
	public void borrarPedido(Pedido Pedido) throws Exception {
		ut.begin();
		em.remove(em.merge(Pedido));
		ut.commit();		
	}

	@Override
	public Pedido recuperarPedidoPorPK(Integer pk) {
		return em.find(Pedido.class, pk);
	}

	@Override
	public Collection<Pedido> buscarTodosLosPedidos() {
		return em.createQuery("select p from pedido p",Pedido.class).getResultList();
	}

	@Override
	public Pedido recuperarPedidoPorCodigoPago(String COD_pago) {
		return em.createQuery("select p from pedido p where p.COD_pago='"+COD_pago+"'", Pedido.class).getSingleResult();
		
	}

}
