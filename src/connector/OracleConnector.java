package connector;

import java.sql.Connection;

public class OracleConnector implements DBConnector {

    @Override
    public Connection makeConnection() {
        System.out.println("����Ŭ DB ���� �õ�");
        System.out.println("-- �ڵ� --");
        System.out.println("����Ŭ DB ���� ����");
        return null;
    }
    
}
