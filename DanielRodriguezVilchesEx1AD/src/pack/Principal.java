package pack;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

		AgendaContactos agendaContactos = new AgendaContactos();

    	try (Scanner scanner = new Scanner(System.in)) {
			int opcion;

			do {
			    System.out.println("Menú de Agenda de Contactos");
			    System.out.println("1. Agregar contacto");
			    System.out.println("2. Listar contactos");
			    System.out.println("3. Guardar en archivo binario");
			    System.out.println("4. Guardar en archivo y Salir");
			    System.out.print("Seleccione una opción: ");
				opcion = scanner.nextInt();
			    switch (opcion) {
			        case 1:
			            System.out.println("Introduzca los datos del nuevo contacto:");
			            System.out.print("Nombre: ");
			            String nombre = scanner.next();
			            System.out.print("Teléfono: ");
			            String telefono = scanner.next();
			            System.out.print("Correo Electrónico: ");
			            String correo = scanner.next();

						agendaContactos.agregarContacto(nombre, telefono, correo);

			            break;

			        case 2:
			            System.out.println("Listado de contactos:");
						agendaContactos.listarContactos();
			            break;

			        case 3:
						agendaContactos.guardarContactosEnArchivoBin();
			            System.out.println("Contactos guardados en archivo binario.");
			            break;

			        case 4:
						agendaContactos.guardarContactosEnArchivo();
			            System.out.println("Contactos guardados en archivo de texto.");
			            System.out.println("Saliendo del programa.");
			            break;

			        default:
			            System.out.println("Opción no válida. Inténtelo de nuevo.");
			    }
			} while (opcion != 4);
		} catch (NumberFormatException e){
			System.out.println("***Debes escribir un número***");
		}
    }
}