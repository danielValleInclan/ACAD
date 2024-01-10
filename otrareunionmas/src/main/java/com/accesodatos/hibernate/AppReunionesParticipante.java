package com.accesodatos.hibernate;

import com.accesodatos.hibernate.dao.PersonaDao;
import com.accesodatos.hibernate.dao.ReunionDao;
import com.accesodatos.hibernate.dominio.Persona;

public class AppReunionesParticipante {

	public static void main(String[] args) {
		ReunionDao reunionDao=new ReunionDao();
		
		System.out.println("Reuniones del participante: "+reunionDao.reunionesParticipante("152"));
		
		PersonaDao personaDao=new PersonaDao();
		Persona p=personaDao.get("152");
		System.out.println(p.getReuniones());
	}
}
