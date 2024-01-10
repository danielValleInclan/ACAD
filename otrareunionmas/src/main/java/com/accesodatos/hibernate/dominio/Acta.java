package com.accesodatos.hibernate.dominio;

import javax.persistence.*;

//Toda reunión tiene su acta (relación 1:1). Añadimos el atributo en las dos entidades

@Entity
@Table(name="acta")
public class Acta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@Column(unique=true, nullable=false)
	private String contenido;
	
	@OneToOne
	@JoinColumn
	private Reunion reunion;

	public Acta() {
		super();
	}

	public Acta(String contenido, Reunion reunion) {
		super();
		this.contenido = contenido;
		this.reunion = reunion;
		reunion.setActa(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	@Override
	public String toString() {
		return "Acta [id=" + id + ", contenido=" + contenido + "]";
	}	
}
