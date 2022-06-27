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
        String message = "1. �Է� 2. ��� 3.����";
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
            System.out.println("���� �Էµ� �л��� �����ϴ�");
        } else {
            for(StudentDTO s : list) {
                System.out.printf("%d. %s\n", s.getId() ,s.getName());
            }
            String messgae = "�󼼺����� �л��� ��ȣ�� �ڷ� ���Ƿ��� 0�� �Է����ּ���";
            int userChoice = ScannerUtil.nextInt(scanner, messgae);
            
            while(userChoice !=0 && controller.selectOne(userChoice) == null) {
                System.out.println("�߸� �Է��ϼ˽��ϴ�");
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
        
        System.out.printf("��ȣ %d�� �̸� %s\n", s.getId(), s.getName());
        System.out.printf("���� : %03d�� ���� : %03d�� ���� : %03d��\n", s.getKorean(), s.getEnglish() ,s.getMath());
        System.out.printf("���� : %03d�� ��� :%06.2f��\n", sum, average);
        
        String message = " 1. ���� 2 . ���� 3. �ڷΰ���";
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
        String message = "������ ���� �Ͻðڽ��ϱ�";
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
        
        String message = "���ο� ���� ������ �Է����ּ���";
        s.setKorean(ScannerUtil.nextInt(scanner, message));
        
        message = "���ο� ���� ������ �Է����ּ���";
        s.setEnglish(ScannerUtil.nextInt(scanner, message));
        
        message = "���ο� ���� ������ �Է����ּ���";
        s.setMath(ScannerUtil.nextInt(scanner, message));
        
        controller.update(s);
        
    }

    private void insertStudent() {
        StudentDTO s = new StudentDTO();
        String message;
        
        message = "�л��� �̸��� �Է����ּ���";
        s.setName(ScannerUtil.nextLine(scanner, message));
        message = "�л��� ���� ������ �Է����ּ���";
        s.setKorean(ScannerUtil.nextInt(scanner, message, 0 ,100));
        message = "�л��� ���� ������ �Է����ּ���";
        s.setEnglish(ScannerUtil.nextInt(scanner, message, 0, 100));
        message = "�л��� ���� ������ �Է����ּ���";
        s.setMath(ScannerUtil.nextInt(scanner, message, 0, 100));
        
        StudentController controller = new StudentController(connector);
        controller.insert(s);
        
    }
}
