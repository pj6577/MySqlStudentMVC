package day0627;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import day0616.Student;

//MySQL 과 연결하는 코드
public class Ex01Mysql {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_test", "root", "1234");
            System.out.println("mysql 연결 성공");

            // 1.insert
            // String query = "INSERT INTO student(`name`, `korean`, `english`, `math`)" +
            // "values(\"박재현\", 80 ,80, 80)";
            String query = "INSERT INTO student(`name`, `korean`, `english`, `math`)" + "values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "김씨");
            ps.setInt(2, 90);
            ps.setInt(3, 91);
            ps.setInt(4, 92);
            ps.executeUpdate();
            System.out.println("insert 성공");

            // 2.update
            query = "UPDATE `student` SET `name` = ? WHERE id = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, "김네모");
            ps.setInt(2, 1);
            ps.executeUpdate();
            System.out.println("업데이트 성공");
            
//            // 3.delete
            query = "DELETE FROM `student` WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, 4);
            ps.executeUpdate();
            System.out.println("delete 성공");
            
            // 4. selectOne()
            Student s = new Student();
            
            query = "SELECT *FROM `student` WHERE id = ?";
            ps =conn.prepareStatement(query);
            ps.setInt(1, 3);
            
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            s.id = rs.getInt("id");
            s.name=rs.getString("name");
            s.korean = rs.getInt("korean");
            s.english = rs.getInt("english");
            s.math = rs.getInt("math");
            
            s.printInfo();
            
            //selectAll()
            ArrayList<Student> list = new ArrayList<>();
            query = "SELECT *FROM `student`";
            ps = conn.prepareStatement(query);
            
            rs = ps.executeQuery();
            while(rs.next()) {
                Student temp = new Student();
                temp.id = rs.getInt("id");
                temp.name = rs.getString("name");
                temp.korean = rs.getInt("korean");
                temp.english = rs.getInt("english");
                temp.math = rs.getInt("math");
                
                list.add(temp);
            }
            System.out.println("list.size() : " + list.size());
            for(Student st : list) {
                st.printInfo();
            }
            
            
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("문제 발생");
            e.printStackTrace();
        } finally {
            System.out.println("finally 코드 블록 실행");
        }
    }
}
