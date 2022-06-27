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
//   상수를 
    private Connection conn;

    public StudentController(DBConnector connector) {
        conn = connector.makeConnection();
    }

    // 1.현재 테이블에 저장된 모든 학생들을 리턴하는
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

    // 2. 현재 테이블에 특정 id 값을 가진
    // 객체를 리턴하는 selectOnd()
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
    // 3. 새로운 정보를 추가하는 
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
    // 4. 기존 학생 정보를 수정하는 
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
    
    // 5. 학생 정보를 삭제하는 
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