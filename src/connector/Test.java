package connector;

import java.sql.Connection;

public class Test {
    
    private Connection conn;
    public Test(DBConnector connector) {
        this.conn = connector.makeConnection();
    }

    public static void main(String[] args) {
        DBConnector connector = new MYsqlConnector();
        
        Test t = new Test(connector);
    }
}
