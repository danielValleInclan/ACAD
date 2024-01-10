package com.accesodatos.hibernate.dominio;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reunion")
public class Reunion {

    // las anotaciones Column son obligatorias si las columnas se llaman distinto en
    // bd
    // @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // @Column(name="fecha")
    private LocalDateTime fecha;
    // @Column(name="asunto")
    private String asunto;

    // Relación N:1 entre Reunión y Sala: Una reunión se realiza en una única sala,
    // pero en una sala se realizan muchas reuniones
    // añadimos la columna idSala en la tabla Reunion -> en este caso lo haremos por
    // código, añadiendo el atributo sala a la entidad Reunion, y
    // el atributo con la lista de reuniones a la entidad Sala. Cuando añadamos
    // los dos, comprobamos que se ha creado el atributo sala_id en la tabla
    // reunion, y la FK correspondiente
    // Cuando recupero una reunión, ¿quiero que se traiga todos los datos de la
    // Sala? Si la respuesta es sí-> fetch EAGER (peligroso), si no, LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="FK_SALA"))
    private Sala sala;

    @OneToOne(mappedBy = "reunion")
    private Acta acta;

    // Utilizamos Set en vez de List porque las listas en Java tienen
    // una connotación de orden que ahora mismo no nos interesa

    // CascadeType.ALL=decirle al sistema que cuando cree la reunión
    // también tiene que crear a las personas.
    @ManyToMany(mappedBy = "reuniones", cascade = CascadeType.ALL)
    private Set<Persona> participantes;

    public Reunion() {
        super();
        participantes = new HashSet<Persona>();
    }

    public Reunion(LocalDateTime fecha, String asunto) {
        this();
        this.fecha = fecha;
        this.asunto = asunto;
    }

    public Reunion(LocalDateTime fecha, String asunto, Sala sala) {
        this();
        this.fecha = fecha;
        this.asunto = asunto;
        sala.addReunion(this);
    }

    public int getId() {
        return id;
    }

    //este método no tiene sentido porque se trata de un campo autocalculado
    //recomendable no crearlo
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Sala getSala() {
        return sala;
    }

    //Validamos, en la aplicación, que los datos estén coherentes
    //(aun no está la información en la BBDD).
    //En este caso, revisamos, al asignar el valor al campo sala de reunión
    //que esa sala que estamos añadiendo tiene a esta reunión entre sus reuniones.
    public void setSala(Sala sala) {
        this.sala = sala;
        if(sala!=null && !sala.containsReunion(this)) {
            sala.addReunion(this);
        }
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public Set<Persona> getParticipantes() {
        return participantes;
    }

    /*
     * public void setParticipantes(Set<Persona> participantes) {
     * 	 this.participantes = participantes; }
     */

    public void addParticipante(Persona p) {
        //crea el conjunto: HashSet si no está creado previamente.
        if (participantes == null) {
            participantes = new HashSet<Persona>();
        }
        participantes.add(p);

        //Validamos, en la aplicación, que los datos estén coherentes
        //(aun no está la información en la BBDD).
        //Verificamos que la persona que estamos añadiendo a la reunión,
        //contiene, entre sus reuniones, a "this" reunión
        if (!p.getReuniones().contains(this)) {
            p.addReunion(this);
        }
    }

    @Override
    public String toString() {
        //OJO con las llamadas recursivas a toString. Si mostramos la sala completa, llamará al toString de sala,
        //y éste a su vez, llama al toString de reunión, y el de reunión al de sala, y así sucesivamente, produciendo bucle de llamadas recursivas
        return "Reunion [id=" + id + ", fecha=" + fecha + ", asunto=" + asunto + ", sala=" + sala.getDescripcion() + "]";
    }
    //NOTA: es recomendable, cada vez que tenemos una colección de objetos de tipo reunión
//generar el método hashCode y el equals porque sirven para otros métodos,
//por ejemplo, para el contains y no se comparan por el id porque será 0 hasta que esté en BBDD.
//compararemos por el campo único (que previamente habremos creado) y no por el id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //SOURCE->GENERATE EQUAL HASHCODE
    //marcando el atributo por el que queremos que compare que será el campo único
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reunion other = (Reunion) obj;
        return id == other.id;
    }

}