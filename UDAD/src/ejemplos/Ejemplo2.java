package ejemplos;

import java.sql.*;

public class Ejemplo2 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            // Preparamos consultas
            Statement statement = connection.createStatement();
            String sql = "UPDATE departamentos SET dnombre=LOWER(dnombre)";
            boolean value = statement.execute(sql);
            if (value){
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    System.out.printf("%d, %s, %s, %n",
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3));
                }
                rs.close();
            } else {
                int f = statement.getUpdateCount();
                System.out.printf("Filas afectadas: %d %n", f);
            }
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
