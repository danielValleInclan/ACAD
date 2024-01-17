package com.accesoadatos.andaluciaskills.dao;

import com.accesoadatos.andaluciaskills.dominio.Usuario;

public class UsuarioDao extends AbstractDao<Usuario> {
    public UsuarioDao() {
        setClazz(Usuario.class);
    }
}
