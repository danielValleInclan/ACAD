package tare1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tarea5 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            connection.setAutoCommit(false);
            try {
                insertarDep(Integer.parseInt(args[0]), "Tarea5", "SEVILLA", connection);
                insertarDep(Integer.parseInt(args[1]), "Tarea5_2", "SEVIILA", connection);
                insertarDep(Integer.parseInt(args[2]), "Tarea5_3", "SEVIILA", connection);
                connection.commit();
            } catch (SQLException e){
                connection.rollback();
                throw new SQLException();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static void insertarDep(int dept_no, String nombre, String loc, Connection connection) throws SQLException {
        // construir inserts
        String sql = "INSERT INTO departamentos VALUES " + "(?, ?, ?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, dept_no);
        statement.setString(2, nombre);
        statement.setString(3, loc);
        int filas;
        filas = statement.executeUpdate();
        System.out.println("Filas afectadas: " + filas);
    }
}