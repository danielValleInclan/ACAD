package com.accesoadatos.andaluciaskills.dominio;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String passwd;

    @OneToOne
    @JoinColumn
    private Experto experto;

    public Usuario() {
    }

    public Usuario(String username, String passwd, Experto experto) {
        this.username = username;
        this.passwd = passwd;
        this.experto = experto;
    }

    public Experto getExperto() {
        return experto;
    }

    public void setExperto(Experto experto) {
        this.experto = experto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
