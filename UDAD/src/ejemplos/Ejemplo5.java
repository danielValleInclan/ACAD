package ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejemplo5 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        String dep = args[0], dnombre = args[1], loc = args[2];

        // Crear driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establecer conexion
        Connection connection = DriverManager.getConnection(url, usuario, passwd);

        // construir inserts
        String sql = "INSERT INTO departamentos VALUES " + "(?, ?, ?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(dep));
        statement.setString(2, dnombre);
        statement.setString(3, loc);

        int filas;
        filas = statement.executeUpdate();
        System.out.println("Filas afectadas: " + filas);
    }
}
