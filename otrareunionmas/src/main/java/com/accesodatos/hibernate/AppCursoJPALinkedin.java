package com.accesodatos.hibernate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.accesodatos.hibernate.dao.ActaDao;
import com.accesodatos.hibernate.dao.PersonaDao;
import com.accesodatos.hibernate.dao.ReunionDao;
import com.accesodatos.hibernate.dominio.Acta;
import com.accesodatos.hibernate.dominio.Persona;
import com.accesodatos.hibernate.dominio.Reunion;

public class AppCursoJPALinkedin {

	public static void main(String[] args) {

		// 1-trabajamos sobre reuniones
		ReunionDao reunionDao = new ReunionDao();

		// muestro todas las reuniones
		List<Reunion> reuniones = reunionDao.getAll();
		System.out.println("*** " + reuniones);

		// añado una reunión - como hemos marcado la pk de reunión como
		// autoincremental (IDENTITY),
		// al guardar y luego mostrar, se ve que te ha generado el id
		Reunion rNueva = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunión Nueva");
		System.out.println(rNueva);
		reunionDao.save(rNueva);
		System.out.println(rNueva);

		// obtenemos la reunión de mañana
		System.out.println("Mañana tenemos esta reunión: " + reunionDao.getProximaReunion());

		System.out.println("Las reuniones de mañana son: " + reunionDao.getReunionesManyana());

		// 2-Trabajamos ahora con Salas
		/*
		 * SalaDao salaDao=new SalaDao(); Sala sala=new Sala("S201", "Sala grande", 25);
		 * salaDao.save(sala); System.out.println(salaDao.getAll());
		 * 
		 * sala.setDescripcion("Sala grande reformada"); salaDao.update(sala);
		 * 
		 * Sala sala2=new Sala("S202", "Salón de actos", 25); salaDao.save(sala2);
		 * 
		 * System.out.println(salaDao.getAll());
		 * 
		 * salaDao.delete(sala); System.out.println("Borrada: "+salaDao.getAll());
		 */

		// 3-trabajamos con actas

		ActaDao actaDao = new ActaDao();
		Acta acta1 = new Acta("Reunión anulada", rNueva);
		actaDao.save(acta1);
		// rNueva.setActa(acta1);
		// reunionDao.update(rNueva);

		// esto que sigue queda engorroso, porque cada vez que creemos un acta asociada
		// a una reunión vamos a querer que esté vinculada
		// de los dos lados, por lo que mejor añadir en el constructor de Acta la
		// vinculación con la reunión, y comento las dos instrucciones siguientes

		// 4-participantes en reuniones. Si en el atributo participantes de la clase
		// Reunion no tenemos puesto cascade, cuando añado los participantes a
		// la reunión, incluso actualizando la reunión con save, no se añadirán las
		// filas en la tabla persona_reunion, ni en la tabla persona. Si
		// añadimos cascade ALL, se añadirán en la tabla persona, pero no en
		// persona_reunion. Para que esto suceda, hay que hacer **
		Persona p1 = new Persona(1154, "Raul", "Sanchez Díaz");
		Persona p2 = new Persona(2154, "Laura", "Lopez Gil");
		rNueva.addParticipante(p1);
		rNueva.addParticipante(p2);
		reunionDao.update(rNueva);

		// ** Creamos la lista de reuniones a la que asiste p1 y la lista a la que
		// asiste p2 y se las asignamos a las personas

		PersonaDao personaDao = new PersonaDao();

		Reunion r2 = new Reunion(LocalDateTime.now(), "Reunión 2");
		reunionDao.save(r2);
		Persona p3 = new Persona(9922, "Marta", "Sanchez Sanchez");
		p3.addReunion(r2);
		personaDao.save(p3);

		Set<Reunion> reunionesP1 = new HashSet<Reunion>();
		reunionesP1.add(rNueva);
		// p1.setReuniones(reunionesP1);

		Set<Reunion> reunionesP2 = new HashSet<Reunion>();
		reunionesP2.add(rNueva);
		reunionesP2.add(r2);
		// p2.setReuniones(reunionesP2);
		reunionDao.update(r2);
	}
}
