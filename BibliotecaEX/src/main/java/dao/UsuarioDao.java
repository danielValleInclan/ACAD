package dao;

import dominio.Usuario;

public class UsuarioDao extends AbstractDao<Usuario> {

	public UsuarioDao() {
		setClazz(Usuario.class);
	}
}
