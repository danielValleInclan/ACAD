import java.sql.*;

public class Ejemplo1 {
    public static void main(String[] args) {
        String url = ("jdbc:mysql://localhost:3306/empresa");
        String usuario = "userJDBC";
        String passwd = "usuario";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, usuario, passwd);
            Statement statement = connection.createStatement();
            String sql = "SELECT  * FROM departamentos";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                System.out.printf("%d, %s, %s, %n",
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
