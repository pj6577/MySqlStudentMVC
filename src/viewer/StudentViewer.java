package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import connector.DBConnector;
import controller.StudentController;
import model.StudentDTO;
import util.ScannerUtil;

public class StudentViewer {
    private Scanner scanner;
    private DBConnector connector;
    
    public StudentViewer(Scanner scanner, DBConnector connector) {
        this.scanner = scanner;
        this.connector = connector;
    }
    
    public void showMenu() {
        String message = "1. 입력 2. 출력 3.종료";
        while(true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if(userChoice == 1 ) {
                insertStudent();
            }else if (userChoice == 2 ) {
                printList();
            }else if (userChoice == 3 ) {
                
            }
        }
    }
    
    private void printList() {
        StudentController controller = new StudentController(connector);
        ArrayList<StudentDTO> list = controller.selectAll();
       
        if(list.isEmpty()) {
            System.out.println("아직 입력된 학생이 없습니다");
        } else {
            for(StudentDTO s : list) {
                System.out.printf("%d. %s\n", s.getId() ,s.getName());
            }
            String messgae = "상세보기할 학생의 번호나 뒤로 가실려면 0을 입력해주세요";
            int userChoice = ScannerUtil.nextInt(scanner, messgae);
            
            while(userChoice !=0 && controller.selectOne(userChoice) == null) {
                System.out.println("잘못 입력하셧습니다");
                userChoice = ScannerUtil.nextInt(scanner, messgae);
            }
            if(userChoice !=0) {
                printOne(userChoice);
            }
        }
    }
    
    private void printOne(int id) {
        StudentController controller = new StudentController(connector);
        StudentDTO s = controller.selectOne(id);
        
        int sum = s.getKorean() + s.getEnglish() + s.getMath();
        double average = (double)sum/3;
        
        System.out.printf("번호 %d번 이름 %s\n", s.getId(), s.getName());
        System.out.printf("국어 : %03d점 영어 : %03d점 수학 : %03d점\n", s.getKorean(), s.getEnglish() ,s.getMath());
        System.out.printf("총점 : %03d점 평균 :%06.2f점\n", sum, average);
        
        String message = " 1. 수정 2 . 삭제 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        
        if(userChoice == 1 ) {
            updateStudent(id);
        }else if (userChoice == 2) {
            deleteStudent(id);
        }else if (userChoice == 3 ) {
            printList();
        }
    }

    private void deleteStudent(int id) {
        String message = "정말로 삭제 하시겠습니까";
        String yesNo = ScannerUtil.nextLine(scanner, message);
        
        if(yesNo.equalsIgnoreCase("Y")) {
            StudentController controller = new StudentController(connector);
            controller.delete(id);
            
            printList();
        }else {
            printOne(id);
        }
    }

    private void updateStudent(int id) {
        StudentController controller = new StudentController(connector);
        StudentDTO s = controller.selectOne(id);
        
        String message = "새로운 국어 점수를 입력해주세요";
        s.setKorean(ScannerUtil.nextInt(scanner, message));
        
        message = "새로운 영어 점수를 입력해주세요";
        s.setEnglish(ScannerUtil.nextInt(scanner, message));
        
        message = "새로운 수학 점수를 입력해주세요";
        s.setMath(ScannerUtil.nextInt(scanner, message));
        
        controller.update(s);
        
    }

    private void insertStudent() {
        StudentDTO s = new StudentDTO();
        String message;
        
        message = "학생의 이름을 입력해주세요";
        s.setName(ScannerUtil.nextLine(scanner, message));
        message = "학생의 국어 점수를 입력해주세요";
        s.setKorean(ScannerUtil.nextInt(scanner, message, 0 ,100));
        message = "학생의 영어 점수를 입력해주세요";
        s.setEnglish(ScannerUtil.nextInt(scanner, message, 0, 100));
        message = "학생의 수학 점수를 입력해주세요";
        s.setMath(ScannerUtil.nextInt(scanner, message, 0, 100));
        
        StudentController controller = new StudentController(connector);
        controller.insert(s);
        
    }
}
