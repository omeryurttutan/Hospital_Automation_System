package hbys.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDBConnection {

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS02:1433;databaseName=Hospital_Document_System1;encrypt=true;trustServerCertificate=true";
        String username = "alparjan";
        String password = "123456";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Veritabanı bağlantısı başarılı!");

            String sql = "SELECT TOP 10 * FROM Patients";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println("PatientID: " + resultSet.getInt("PatientID")
                        + ", FirstName: " + resultSet.getString("FirstName")
                        + ", LastName: " + resultSet.getString("LastName"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Bağlantı başarısız: " + e.getMessage());
        }
    }
}
