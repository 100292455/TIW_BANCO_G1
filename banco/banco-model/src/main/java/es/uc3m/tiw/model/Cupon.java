package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cupon")
public class Cupon {
	
	@Id
 	@GeneratedValue(strategy = AUTO)
	private int ID_curso;
	private String DES_titulo;
	private String DES_descripcion;
	private int TIPO_dificultad;
	private int horas;
	private int precio_inicial;
	private int precio_final;
	private int TIPO_destacado;
	private String tematica;
	int TIPO_estado;
	String FechaFinDescuento;
	
	public Cupon(String dES_titulo, String dES_descripcion,
			int tIPO_dificultad, int horas, int precio_inicial,
			int precio_final, int tIPO_destacado,
			int tIPO_estado, String fechaFinDescuento, String tematica) {
		super();
		DES_titulo = dES_titulo;
		DES_descripcion = dES_descripcion;
		TIPO_dificultad = tIPO_dificultad;
		this.horas = horas;
		this.precio_inicial = precio_inicial;
		this.precio_final = precio_final;
		TIPO_destacado = tIPO_destacado;
		TIPO_estado = tIPO_estado;
		this.FechaFinDescuento = fechaFinDescuento;
		this.tematica = tematica;
	}
	public Cupon() {
		// TODO Auto-generated constructor stub
	}
	public Cupon(
			String dES_titulo, 
			String dES_descripcion, 
			int tIPO_dificultad, 
			int horas, 
			int precio_inicial, 
			int precio_final,	
			int tIPO_destacado,	
			String tematica,
			int tIPO_estado) {
		super();
		this.DES_titulo = dES_titulo;
		this.DES_descripcion = dES_descripcion;
		this.TIPO_dificultad = tIPO_dificultad;
		this.horas = horas;
		this.precio_inicial = precio_inicial;
		this.precio_final = precio_final;
		this.TIPO_destacado = tIPO_destacado;
		this.TIPO_estado = tIPO_estado;
		this.tematica = tematica;
	}
	public Cupon(String titulo, String descripcion, String tematica, int dificultad, int horas, int precio_inicial) {
		super();
		this.DES_titulo = titulo;
		this.DES_descripcion = descripcion;
		this.TIPO_dificultad = dificultad;
		this.horas = horas;
		this.precio_inicial = precio_inicial;
		this.tematica = tematica;
	}
	public String getFechaFinDescuento() {
		return FechaFinDescuento;
	}
	public void setFechaFinDescuento(String fechaFinDescuento) {
		this.FechaFinDescuento = fechaFinDescuento;
	}
	public int getID_curso() {
		return ID_curso;
	}
	public void setID_curso(int iD_curso) {
		ID_curso = iD_curso;
	}
	public String getDES_titulo() {
		return DES_titulo;
	}
	public void setDES_titulo(String dES_titulo) {
		DES_titulo = dES_titulo;
	}
	public String getDES_descripcion() {
		return DES_descripcion;
	}
	public void setDES_descripcion(String dES_descripcion) {
		DES_descripcion = dES_descripcion;
	}
	public int getTIPO_dificultad() {
		return TIPO_dificultad;
	}
	public void setTIPO_dificultad(int tIPO_dificultad) {
		TIPO_dificultad = tIPO_dificultad;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getPrecio_inicial() {
		return precio_inicial;
	}
	public void setPrecio_inicial(int precio_inicial) {
		this.precio_inicial = precio_inicial;
	}
	public int getPrecio_final() {
		return precio_final;
	}
	public void setPrecio_final(int precio_final) {
		this.precio_final = precio_final;
	}
	public int getTIPO_destacado() {
		return TIPO_destacado;
	}
	public void setTIPO_destacado(int tIPO_destacado) {
		TIPO_destacado = tIPO_destacado;
	}
	public int getTIPO_estado() {
		return TIPO_estado;
	}
	public void setTIPO_estado(int tIPO_estado) {
		TIPO_estado = tIPO_estado;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String Tematica) {
		tematica = Tematica;
	}
	
	
}