package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="prestamo")
public class Prestamo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Usuario usuario;
	

	private Ejemplar ejemplar;
	
	@Column(name="fecha_prestamo")
	private LocalDateTime fechaPrestamo=LocalDateTime.now();
	
	@Column(name="fecha_devolucion")
	private LocalDateTime fechaDevolucion;

	public Prestamo() {
		super();
	}

	public Prestamo(Usuario usuario, Ejemplar ejemplar) {
		super();
		this.usuario = usuario;
		this.ejemplar = ejemplar;
	}
	
	public Prestamo(Usuario usuario, Ejemplar ejemplar, LocalDateTime fechaPrestamo) {
		super();
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.fechaPrestamo = fechaPrestamo;
	}

	public Prestamo(Usuario usuario, Ejemplar ejemplar, LocalDateTime fechaPrestamo, LocalDateTime fechaDevolucion) {
		super();
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public LocalDateTime getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public String toString() {
		return "Prestamo [usuario=" + usuario + ", ejemplar=" + ejemplar + ", fechaPrestamo=" + fechaPrestamo
				+ ", fechaDevolucion=" + fechaDevolucion + "]";
	}
}