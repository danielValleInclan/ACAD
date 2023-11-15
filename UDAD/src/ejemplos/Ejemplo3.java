package ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo3 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            // Recuperar argumentos de main
            String dep = args[0]; // num departamentos
            String dnombre = args[1]; // nombre
            String loc = args[2]; // localidad

            // construir orden INSERT
            String sql = String.format("INSERT INTO departamentos VALUES ('%s', '%s', '%s')",
                    dep, dnombre, loc);

            System.out.println(sql);
            Statement statement = connection.createStatement();
            int filas;
            try {
                filas = statement.executeUpdate(sql);
                System.out.printf("Filas afectadas: " + filas);
            } catch (SQLException e){
                System.out.printf("HA OCURRIDO UNA EXCEPCION:%n");
                System.out.printf("Mensaje      : %s %n", e.getMessage());
                System.out.printf("SQL estado   : %s %n", e.getSQLState());
                System.out.printf("Cód error    : %s %n", e.getErrorCode());
            }
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
