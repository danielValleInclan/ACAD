package tare1;

import java.sql.*;

public class Tarea4 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        String dept_no = args[0];

        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            String sql = "SELECT e.apellido, e.salario, e.oficio, d.dnombre \n" +
                    "FROM empleados e\n" +
                    "JOIN departamentos d ON e.dept_no = d.dept_no\n" +
                    "WHERE d.dept_no = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(dept_no));

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                System.out.printf("%s => salario: %.2f, oficio: %s, departamento: %s %n",
                        rs.getString("apellido"),
                        rs.getFloat("salario"),
                        rs.getString("oficio"),
                        rs.getString("dnombre"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
