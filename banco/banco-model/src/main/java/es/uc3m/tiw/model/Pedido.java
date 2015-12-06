package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
 	@GeneratedValue(strategy = AUTO)
	private Integer ID_pedido;
	@Column(columnDefinition="Decimal(10,2) default '0.0'")
	private Double ImporteCobrar; //lo que viene del portal
	@Column(columnDefinition="Decimal(10,2) default '0.0'")
	private Double ImporteCobrado; //99% de lo que viene del portal -- 1% de comision para el banco
	private String COD_tarjeta; //tarjeta de credito del alumno -- 20 posiciones alfanumericas, la primera A/B
	private String COD_operacion; //codigo de operacion bancaria que generara el banco -- BANCOyyyymmddhhssSSSXX
	private String COD_pago; //codigo de pedido que viene del portal -- ORDERyyyymmddhhssSSSXX
	
	public Pedido() {
	}
	
	public Pedido(Double importeCobrar, String cOD_tarjeta, String cOD_pago) {
		super();
		this.ImporteCobrar = importeCobrar;
		this.COD_tarjeta = cOD_tarjeta;
		this.COD_pago = cOD_pago;
	}
	
	public Pedido(Double importeCobrar, Double importeCobrado, String cOD_tarjeta,
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
	public Double getImporteCobrar() {
		return ImporteCobrar;
	}
	public void setImporteCobrar(Double importeCobrar) {
		this.ImporteCobrar = importeCobrar;
	}
	public Double getImporteCobrado() {
		return ImporteCobrado;
	}
	public void setImporteCobrado(Double importeCobrado) {
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