package dominio;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String nombre;
	@ManyToMany
	@JoinTable(
			name = "autor_libro",
			joinColumns = @JoinColumn(name = "autor_id"),
			inverseJoinColumns = @JoinColumn(name = "libro_id")
	)
	Set<Libro> libros = new HashSet<>();
	
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