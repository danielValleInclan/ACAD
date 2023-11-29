package examenU2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * Daniel Rodríguez
 */
public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Consulta consulta = new Consulta();
        int opcion;
        do {
            System.out.println("Elije una opción: \n" +
                    "1. Crea y muestra una lista de objetos Cliente con los datos de los clientes recuperados de BD.\n" +
                    "2. Mostrar el número de clientes registrados en comercios de la ciudad que indique el\n" +
                    "usuario. Utiliza Statement.\n" +
                    "3. Mostrar los datos de aquellos comercios que hayan distribuído más de un programa.\n" +
                    "Utiliza PreparedStatement.\n" +
                    "4. Actualizar los datos de Registro de los comercios cuyo nombre acabe por la letra que\n" +
                    "indique el usuario. El dato a actualizar es el campo fecha, poniendo la fecha de hoy. El\n" +
                    "programa debe indicar cuántos registros han sido actualizados.\n" +
                    "5. Inserta dos programas nuevos y un fabricante (también nuevo) que desarrolle dichos\n" +
                    "programas, de tal manera que la operación se realice de manera atómica. Si ocurre\n" +
                    "algún fallo, como por ejemplo, que intentemos añadir un programa con un código que\n" +
                    "ya exista, no insertará el otro programa ni tampoco el fabricante.\n" +
                    "6. Salir del programa");
            System.out.println();
            System.out.print("Elije una opción -> ");
            opcion = sc.nextInt();
            switch (opcion){
                case 1 :
                    consulta.opcion1();
                    break;
                case 2 :
                    consulta.opcion2();
                    break;
                case 3 :
                    consulta.opcion3();
                    break;
                case 4 :
                    consulta.opcion4();
                    break;
                case 5 :
                    consulta.opcion5();
                    break;
                case 6 :
                    System.out.println("\tSaliendo del programa ... ");
                    break;
                default :
                    System.out.println("\t Elije una opción correcta!!!");
                    break;
            }
        } while (opcion != 6);
    }
}
