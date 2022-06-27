package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYsqlConnector implements DBConnector {
    private final String ADDRESS = "jdbc:mysql://localhost:3306/new_test";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";
    
    @Override           
    public Connection makeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("����̹� �ε� ����");

            Connection conn = DriverManager.getConnection(ADDRESS, USERNAME, PASSWORD);
            System.out.println("mysql ���� ����");

            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("���� �߻�");
            e.printStackTrace();

        }
        return null;

    }
}
