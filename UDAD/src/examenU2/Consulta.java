package examenU2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Daniel Rodríguez
 */
public class Consulta {
    private String url = ("jdbc:mysql://localhost:3306/examenu2");
    private String usuario = "examenu2";
    private String passwd = "examenu2@";
    private ArrayList<Cliente> clientes = new ArrayList<>();
    public void opcion1(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            // Preparamos consultas
            Statement statement = connection.createStatement();
            String sql = "select * from CLIENTE";
            ResultSet resultSet = statement.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van visualizando
            while (resultSet.next()){
                clientes.add(new Cliente(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)));
            }
            resultSet.close();// Cerrar resultado
            statement.close();// Cerrar statement
            connection.close();// Cerrar conexion

            System.out.println("Mostrar la lista de clientes");

            for (Cliente cliente: clientes){
                System.out.printf("Clientes:\n" +
                        "\t %s, %s, %d, %s %n",
                        cliente.getDni(),
                        cliente.getNombre(),
                        cliente.getEdad(),
                        cliente.getSexo());
            }
        } catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("Cód error: " + e.getErrorCode());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void opcion2(){
        String ciudad = elejirComercio();
        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            // Preparamos consultas
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT COUNT(c.dni) AS NumClientesRegistrados" +
                    " FROM REGISTRA r" +
                    " INNER JOIN CLIENTE c ON r.dni = c.dni" +
                    " INNER JOIN COMERCIO co ON r.cif = co.cif" +
                    " WHERE co.ciudad = '%s'", ciudad);
            ResultSet resultSet = statement.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van visualizando
            System.out.print("\n \t Número de clientes : ");
            while (resultSet.next()){
                System.out.printf("%d %n",
                        resultSet.getInt(1));
            }
            resultSet.close();// Cerrar resultado
            statement.close();// Cerrar statement
            connection.close();// Cerrar conexion
        } catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("Cód error: " + e.getErrorCode());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void opcion3(){
        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            String sql = "SELECT co.*\n" +
                    "FROM COMERCIO co\n" +
                    "JOIN DISTRIBUYE d ON co.cif = d.cif\n" +
                    "GROUP BY co.cif, co.nombre, co.ciudad, co.trabajadores\n" +
                    "HAVING COUNT(d.codigo) > 1;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            System.out.println("Los comercios son los siguientes:");
            while (rs.next()){
                System.out.printf("%d, %s, %s, %d %n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
            }
            System.out.println();
        }  catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("Cód error: " + e.getErrorCode());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void opcion4(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe una letra");
        String letra = "%";
        letra = letra.concat(sc.next());
        String fecha = String.format(LocalDate.now().toString());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            // Preparamos consultas
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE REGISTRA SET fecha = '%s' " +
                            "WHERE cif IN (SELECT cif FROM COMERCIO WHERE nombre LIKE '%s')", fecha, letra);
            boolean value = statement.execute(sql);

            if (value){
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    System.out.printf("%d, %s, %s, %d %n",
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4));
                }
                rs.close();
            } else {
                int f = statement.getUpdateCount();
                System.out.printf("Filas afectadas: %d %n", f);

            }
            statement.close();
            connection.close();
        }  catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("Cód error: " + e.getErrorCode());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void opcion5(){

        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            connection.setAutoCommit(false);
            insertarProg(24, "PRUEBA_PROG", "VER", 2.00, connection);
            insertarProg(10, "PRUEBA_PROG_2", "VER", 2.00, connection);
            insertarFabr(10, "Fab_prueba", "ESPANA", connection);
            insertarDes(21, 10, connection);
            insertarDes(22, 10, connection);
            connection.commit();
        }  catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("Cód error: " + e.getErrorCode());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void insertarProg(int codigo, String nombre, String version, Double precio, Connection connection) throws SQLException {
        // construir inserts
        String sql = "INSERT INTO PROGRAMA VALUES " + "(?, ?, ?, ?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, codigo);
        statement.setString(2, nombre);
        statement.setString(3, version);
        statement.setDouble(4, precio);
        int filas;
        filas = statement.executeUpdate();
        System.out.println("Filas afectadas: " + filas);
    }

    private void insertarFabr(int id_fab, String nombre, String pais, Connection connection) throws SQLException {
        // construir inserts
        String sql = "INSERT INTO FABRICANTE VALUES " + "(?, ?, ?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_fab);
        statement.setString(2, nombre);
        statement.setString(3, pais);
        int filas;
        filas = statement.executeUpdate();
        System.out.println("Filas afectadas: " + filas);
    }

    private void insertarDes(int codigo, int id_fab, Connection connection) throws SQLException {
        // construir inserts
        String sql = "INSERT INTO DESARROLLA VALUES " + "(?, ?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_fab);
        statement.setInt(2, codigo);
        int filas;
        filas = statement.executeUpdate();
        System.out.println("Filas afectadas: " + filas);
    }

    private String elejirComercio(){
        String opcion;

        Boolean comprobar = false;
        ArrayList<String> ciudades = new ArrayList<>(); // Para comprobar que la ciudad introducida es correcta

        Scanner sc = new Scanner(System.in);
        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            // Preparamos consultas
            Statement statement = connection.createStatement();
            String sql = "SELECT  * FROM COMERCIO";
            ResultSet resultSet = statement.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van visualizando
            while (resultSet.next()){
                String ciudad = resultSet.getString(3);
                System.out.printf("%s %n",
                        ciudad);
                ciudades.add(ciudad);
            }
            resultSet.close();// Cerrar resultado
            statement.close();// Cerrar statement
            connection.close();// Cerrar conexion
        }  catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("Cód error: " + e.getErrorCode());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        do { // comprueba que la ciudad es correcta
            System.out.print("Elije una ciudad  -> ");
            opcion = sc.next();
            for (String s: ciudades){
                if (s.equals(opcion)) {
                    comprobar = true;
                    break;
                } else System.out.println("Inserta una ciudad correcta");
            }
        } while (!comprobar);

        return opcion;
    }
}
