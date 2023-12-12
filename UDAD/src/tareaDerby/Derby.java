package tareaDerby;

import java.sql.*;

public class Derby {
    public static void main(String[] args) {
        String url = "jdbc:derby:/home/usuario/db-derby-10.17.1.0-bin/ejemploDB;";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection connection = DriverManager.getConnection(url);
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

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
