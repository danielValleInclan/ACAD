package dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Autor {

	private int id;

	private String nombre;
	
	public Autor() {
		super();
	}

	public Autor(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	
	public Set<Libro> getLibros() {
		return libros;
	}
	
	public void addLibro(Libro libro) {
		this.libros.add(libro);
		if(libro!=null && !libro.autores.contains(this)) {
			libro.addAutor(this);
		}
	}
	
	public void removeLibro(Libro libro) {
		this.libros.remove(libro);
		if(libro!=null && libro.autores.contains(this)) {
			libro.removeAutor(this);
		}
	}	

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + "]";
	}
}