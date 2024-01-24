package dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ejemplar {

	private int id;

	private String codigo;
	
	public String localizacion;
	
	private Libro libro;
	
	private Set<Usuario> usuarios=new HashSet<>();

	public Ejemplar() {
		super();
	}

	public Ejemplar(String codigo, String localizacion) {
		super();
		this.codigo = codigo;
		this.localizacion = localizacion;
	}

	public Ejemplar(String codigo, String localizacion, Libro libro) {
		super();
		this.codigo = codigo;
		this.localizacion = localizacion;
		this.libro = libro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public int getId() {
		return id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		if(usuario!=null && !usuario.getEjemplaresPrestados().contains(this)) {
			usuario.addEjemplarPrestado(this);
		}
	}
	
	public void removeUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
		if(usuario!=null && usuario.getEjemplaresPrestados().contains(this)) {
			usuario.removeEjemplarPrestado(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejemplar other = (Ejemplar) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", codigo=" + codigo + ", localizacion=" + localizacion + "]";
	}
}
