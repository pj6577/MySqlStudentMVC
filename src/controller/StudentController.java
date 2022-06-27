package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connector.DBConnector;
import model.StudentDTO;

public class StudentController {
//   private final String ADDRESS="";
//   private final String USERNAME="";
//   private final String PASSWORD="";
//   ����� 
    private Connection conn;

    public StudentController(DBConnector connector) {
        conn = connector.makeConnection();
    }

    // 1.���� ���̺� ����� ��� �л����� �����ϴ�
    // selectAll();
    public ArrayList<StudentDTO> selectAll() {
        ArrayList<StudentDTO> list = new ArrayList<>();
        String quey = "SELECT *FROM `student`";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(quey);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentDTO s = new StudentDTO();

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setKorean(rs.getInt("korean"));
                s.setEnglish(rs.getInt("english"));
                s.setMath(rs.getInt("math"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. ���� ���̺� Ư�� id ���� ����
    // ��ü�� �����ϴ� selectOnd()
    public StudentDTO selectOne(int id) {
        String query = "SELECT * FROM `student` WHERE `id` = ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
                StudentDTO s = new StudentDTO();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setKorean(rs.getInt("korean"));
                s.setEnglish(rs.getInt("english"));
                s.setMath(rs.getInt("math"));
                
                return s;
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 3. ���ο� ������ �߰��ϴ� 
    // insert()
    public void insert(StudentDTO s) {
        String query = "INSERT INTO `student` (`name`, `korean`, `english`, `math`) values(?, ?, ?, ?)";
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, s.getName());
            pstmt.setInt(2, s.getKorean());
            pstmt.setInt(3, s.getEnglish());
            pstmt.setInt(4, s.getMath());
            
            pstmt.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 4. ���� �л� ������ �����ϴ� 
    //update()
    public void update(StudentDTO s) {
        String query = "UPDATE `student` SET `name` = ?, `korean` = ? , `english` = ?, `math` = ? WHERE `id` = ?";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, s.getName());
            pstmt.setInt(2, s.getKorean());
            pstmt.setInt(3, s.getEnglish());
            pstmt.setInt(4, s.getMath());
            pstmt.setInt(5, s.getId());
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    
    // 5. �л� ������ �����ϴ� 
    // delete()
    public void delete(int id) {
        String query = "DELETE FROM `student` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}