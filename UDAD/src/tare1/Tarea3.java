package tare1;

import java.sql.*;
import java.time.LocalDate;

public class Tarea3 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        String sql;
        String eror = "";
        boolean value = true;
        try {
            // Crear driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexion
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            Statement statement = connection.createStatement();
            // Recuperar argumentos de main
            String numEmple = args[0]; // num empleado
            String apellido = args[1]; // apellido
            String oficio = args[2]; // nombre
            String dir = args[3]; // localidad
            String salario = args[4];
            String comision = args[5];
            String dept_no = args[6];

            // Comprobar si existe el número del departamento
            sql = String.format("SELECT dept_no FROM departamentos WHERE dept_no = %s %n", dept_no);
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()){
                eror = eror.concat(" Departamento no existe | ");
                value = false;
            }

            // Comprobar que no exista un empleado con el mismo número

            sql = String.format("SELECT emp_no FROM empleados WHERE emp_no = %s;", numEmple);
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                eror = eror.concat( "Empleado existe | ");
                value = false;
            }

            // Comprobar que exista un director con el mismo número
            sql = String.format("SELECT dir FROM empleados WHERE dir = %s;", dir);
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                eror = eror.concat( "Director no existe | ");
                value = true;
            }
            resultSet.close();

            // Comprobar salario
            double salarioDouble = Double.parseDouble(salario);
            if (salarioDouble <= 0){
                eror = eror.concat("El salario de ser mas de 0 | ");
                value = false;
            }

            // Ejecutar el INSERT
            if (value){
                // Fecha actual
                String fecha = String.valueOf(LocalDate.now());
                // construir orden INSERT
                sql = String.format("INSERT INTO empleados(emp_no, apellido, oficio, dir, salario, comision, dept_no, fecha_alt)" +
                                " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                        numEmple, apellido, oficio, dir, salario, comision, dept_no, fecha);
                System.out.println(sql);
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
            } else {
                System.out.printf("Error: " + eror);
            }
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
