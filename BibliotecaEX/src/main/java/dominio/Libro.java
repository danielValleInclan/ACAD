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
import javax.persistence.OneToMany;

@Entity
public class Libro {

	private int id;

	private String titulo;

	private String isbn;

	private String editorial;

	private int paginas;


	public Libro() {
		super();
	}

	public Libro(String titulo, String isbn, String editorial, int paginas) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.editorial = editorial;
		this.paginas = paginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getId() {
		return id;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void addAutor(Autor autor) {
		this.autores.add(autor);
		if (autor != null && !autor.getLibros().contains(this)) {
			autor.addLibro(this);
		}
	}

	public Set<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void addEjemplar(Ejemplar ejemplar) {
		this.ejemplares.add(ejemplar);
		if (ejemplar != null && (ejemplar.getLibro() == null || !ejemplar.getLibro().equals(this))) {
			ejemplar.setLibro(this);
		}
	}
	
	public void removeEjemplar(Ejemplar ejemplar) {
		this.ejemplares.remove(ejemplar);
		if (ejemplar != null && (ejemplar.getLibro() != null && ejemplar.getLibro().equals(this))) {
			ejemplar.setLibro(null);
		}
	}

	public void removeAutor(Autor autor) {
		this.autores.remove(autor);
		if (autor != null && autor.libros.contains(this)) {
			autor.removeLibro(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + ", editorial=" + editorial + ", paginas="
				+ paginas + "]";
	}
}