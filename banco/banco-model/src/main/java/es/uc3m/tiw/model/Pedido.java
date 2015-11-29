package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
 	@GeneratedValue(strategy = AUTO)
	private Integer ID_pedido;
	private Long ImporteCobrar;
	private Long ImporteCobrado;
	private String COD_tarjeta;
	private String COD_operacion;
	private String COD_pago;
	
	public Pedido() {
	}
	
	public Pedido(Long importeCobrar, Long importeCobrado, String cOD_tarjeta,
			String cOD_operacion, String cOD_pago) {
		super();
		this.ImporteCobrar = importeCobrar;
		this.ImporteCobrado = importeCobrado;
		this.COD_tarjeta = cOD_tarjeta;
		this.COD_operacion = cOD_operacion;
		this.COD_pago = cOD_pago;
	}
	
	public Integer getID_pedido() {
		return ID_pedido;
	}
	public void setID_pedido(Integer iD_pedido) {
		this.ID_pedido = iD_pedido;
	}
	public Long getImporteCobrar() {
		return ImporteCobrar;
	}
	public void setImporteCobrar(Long importeCobrar) {
		this.ImporteCobrar = importeCobrar;
	}
	public Long getImporteCobrado() {
		return ImporteCobrado;
	}
	public void setImporteCobrado(Long importeCobrado) {
		this.ImporteCobrado = importeCobrado;
	}
	public String getCOD_tarjeta() {
		return COD_tarjeta;
	}
	public void setCOD_tarjeta(String cOD_tarjeta) {
		this.COD_tarjeta = cOD_tarjeta;
	}
	public String getCOD_operacion() {
		return COD_operacion;
	}
	public void setCOD_operacion(String cOD_operacion) {
		this.COD_operacion = cOD_operacion;
	}
	public String getCOD_pago() {
		return COD_pago;
	}
	public void setCOD_pago(String cOD_pago) {
		this.COD_pago = cOD_pago;
	}
	
	
	
}