package ejemplos;

import java.sql.*;

public class Ejemplo1 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            // Preparamos consultas
            Statement statement = connection.createStatement();
            String sql = "SELECT  * FROM empleados";
            ResultSet resultSet = statement.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van visualizando
            while (resultSet.next()){
                System.out.printf("%d, %s, %s, %n",
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            resultSet.close();// Cerrar resultado
            statement.close();// Cerrar statement
            connection.close();// Cerrar conexion
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
