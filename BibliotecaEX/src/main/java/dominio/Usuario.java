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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

	public Usuario() {
		super();
	}

	public Usuario(String nombre) {
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

	public Set<Ejemplar> getEjemplaresPrestados() {
		return ejemplaresPrestados;
	}

	public void addEjemplarPrestado(Ejemplar ejemplarPrestado) {
		this.ejemplaresPrestados.add(ejemplarPrestado);
		if(ejemplarPrestado!=null && !ejemplarPrestado.getUsuarios().contains(this)) {
			ejemplarPrestado.addUsuario(this);
		}
	}
	
	public void removeEjemplarPrestado(Ejemplar ejemplarPrestado) {
		this.ejemplaresPrestados.remove(ejemplarPrestado);
		if(ejemplarPrestado!=null && ejemplarPrestado.getUsuarios().contains(this)) {
			ejemplarPrestado.removeUsuario(this);
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
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + "]";
	}
}