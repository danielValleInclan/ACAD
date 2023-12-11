package ejemplos.db4o;

public class Persona {
    private String nombre, ciudad;

    public Persona(String nombre, String ciudad) {
        super();
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public Persona() {
        super();
        this.nombre = null;
        this.ciudad = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
