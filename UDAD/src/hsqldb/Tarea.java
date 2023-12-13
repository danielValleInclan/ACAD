package hsqldb;

import java.sql.*;

public class Tarea {
    public static void main(String[] args) {
        String url = ("jdbc:hsqldb:/home/usuario/hsqldb-2.5.1/hsqldb/data");
        String usuario = "ejemplo";
        String passwd = "ejemplo";
        try {
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM DEPARTAMENTOS";
            ResultSet resultSet = statement.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van visualizando
            while (resultSet.next()){
                System.out.printf("%s, %s, %s, %n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            resultSet.close();// Cerrar resultado
            statement.close();// Cerrar statement

            // Cerrar la conexión
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
