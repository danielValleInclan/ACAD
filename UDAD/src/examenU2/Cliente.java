package examenU2;

public class Cliente {

    /**
     * Daniel Rodríguez
     */

    private String dni, nombre, sexo;
    private int edad;

    public Cliente(String dni, String nombre, int edad, String sexo) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
