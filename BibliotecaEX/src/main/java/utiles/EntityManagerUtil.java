package utiles;

//del curso de LinkedInLearning
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	public static EntityManager getEntityManager() {
		//en la siguiente línea se pone lo que pusimos en <persistence-unit name="OtraReunionMas" 
		//en el fichero persistence.xml
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("BibliotecaEX");
		EntityManager manager=factory.createEntityManager();

		return manager;
	}
}
