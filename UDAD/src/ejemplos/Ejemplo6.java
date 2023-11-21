package ejemplos;

import java.sql.*;

public class Ejemplo6 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        String dep = args[0];
        String oficio = args[1];

        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            String sql = "SELECT apellido, salario FROM empleados WHERE dept_no = ? AND oficio = ? ORDER BY 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(dep));
            statement.setString(2, oficio);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                System.out.printf("%s => %.2f %n", rs.getString("apellido"), rs.getFloat("salario"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
